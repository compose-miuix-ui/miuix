// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.macos

import androidx.compose.ui.awt.ComposeDialog
import androidx.compose.ui.awt.ComposeWindow
import com.sun.jna.Callback
import com.sun.jna.Native
import com.sun.jna.NativeLibrary
import com.sun.jna.Pointer
import com.sun.jna.Structure
import java.awt.EventQueue
import java.awt.Window
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent

/**
 * Installs SaltUI's custom macOS title bar without relying on private AppKit subview indexes.
 * The actual title bar is resolved from a standard window button, while optional background and
 * decoration views are discovered by their runtime roles. Only constraints owned by this class
 * are removed during full-screen transitions and disposal.
 */
internal class MacOSWindowTitleBar internal constructor(
    private val windowHandle: () -> Long,
    private val backend: MacOSWindowTitleBarBackend?,
    private val window: Window? = null,
) {
    constructor(window: Window) : this(
        windowHandle = {
            when (window) {
                is ComposeWindow -> window.windowHandle
                is ComposeDialog -> window.windowHandle
                else -> 0L
            }
        },
        backend = MacOSNativeWindowTitleBar.instance,
        window = window,
    )

    private var installation: MacOSWindowTitleBarInstallation? = null
    private var customHeaderHeight: Float? = null
    private var updatePending = false
    private var disposed = false
    private val componentListener = object : ComponentAdapter() {
        override fun componentShown(event: ComponentEvent) {
            scheduleUpdate()
        }
    }

    init {
        window?.addComponentListener(componentListener)
    }

    fun update(customHeaderHeight: Float) {
        if (!customHeaderHeight.isFinite() || customHeaderHeight <= 0f) return
        if (disposed) return

        this.customHeaderHeight = customHeaderHeight
        updateNow(customHeaderHeight)
        if (window != null) {
            scheduleUpdate()
        }
    }

    private fun updateNow(customHeaderHeight: Float) {
        if (disposed) return

        val native = backend ?: return
        val current = installation
        if (current != null) {
            runCatching {
                native.update(current, customHeaderHeight)
            }
            return
        }

        val handle = windowHandle()
        if (handle == 0L) return
        installation = runCatching {
            native.install(Pointer(handle), customHeaderHeight)
        }.getOrNull()
    }

    private fun scheduleUpdate() {
        if (disposed || updatePending) return
        updatePending = true
        EventQueue.invokeLater {
            updatePending = false
            customHeaderHeight?.let(::updateNow)
        }
    }

    fun performWindowDrag(): Boolean {
        if (disposed) return false

        val native = backend ?: return false
        val handle = windowHandle()
        if (handle == 0L) return false
        return runCatching {
            native.performWindowDrag(Pointer(handle))
        }.getOrDefault(false)
    }

    fun dispose() {
        if (!disposed) {
            disposed = true
            window?.removeComponentListener(componentListener)
        }
        val native = backend ?: return
        val current = installation ?: return
        val removed = runCatching {
            native.remove(current)
        }.isSuccess
        if (removed) installation = null
    }
}

internal interface MacOSWindowTitleBarInstallation

internal interface MacOSWindowTitleBarBackend {
    fun install(
        nativeWindow: Pointer,
        customHeaderHeight: Float,
    ): MacOSWindowTitleBarInstallation?

    fun update(
        installation: MacOSWindowTitleBarInstallation,
        customHeaderHeight: Float,
    )

    fun performWindowDrag(nativeWindow: Pointer): Boolean

    fun remove(installation: MacOSWindowTitleBarInstallation)
}

private class MacOSNativeWindowTitleBar private constructor(
    private val objectiveC: NativeLibrary,
    private val dispatch: TitleBarDispatch,
    private val mainQueue: Pointer,
) : MacOSWindowTitleBarBackend {
    private val getClass = objectiveC.getFunction("objc_getClass")
    private val registerSelector = objectiveC.getFunction("sel_registerName")
    private val sendMessage = objectiveC.getFunction("objc_msgSend")

    // Intel returns a 32-byte NSRect through objc_msgSend_stret's hidden result pointer. arm64
    // returns it through objc_msgSend and does not expose objc_msgSend_stret.
    private val sendStructMessage = if (
        requiresObjectiveCStructReturn(System.getProperty("os.arch").orEmpty())
    ) {
        objectiveC.getFunction("objc_msgSend_stret")
    } else {
        null
    }
    private val allocateClassPair = objectiveC.getFunction("objc_allocateClassPair")
    private val registerClassPair = objectiveC.getFunction("objc_registerClassPair")
    private val addMethod = objectiveC.getFunction("class_addMethod")
    private val getObjectClassName = objectiveC.getFunction("object_getClassName")
    private val selectors = mutableMapOf<String, Pointer>()
    private val observerHandlers = mutableMapOf<Long, FullScreenHandler>()
    private val pendingMainThreadCallbacks = mutableSetOf<TitleBarDispatchCallback>()

    private val willEnterFullScreenCallback = notificationCallback { handler ->
        handler.willEnterFullScreen()
    }
    private val didEnterFullScreenCallback = notificationCallback { handler ->
        handler.didEnterFullScreen()
    }
    private val willExitFullScreenCallback = notificationCallback { handler ->
        handler.willExitFullScreen()
    }
    private val didExitFullScreenCallback = notificationCallback { handler ->
        handler.didExitFullScreen()
    }

    private val observerClass: Pointer? by lazy {
        createClass(
            name = OBSERVER_CLASS_NAME,
            superclassName = "NSObject",
            methods = listOf(
                ObjectiveCMethod(
                    selector = WILL_ENTER_FULL_SCREEN_SELECTOR,
                    callback = willEnterFullScreenCallback,
                ),
                ObjectiveCMethod(
                    selector = DID_ENTER_FULL_SCREEN_SELECTOR,
                    callback = didEnterFullScreenCallback,
                ),
                ObjectiveCMethod(
                    selector = WILL_EXIT_FULL_SCREEN_SELECTOR,
                    callback = willExitFullScreenCallback,
                ),
                ObjectiveCMethod(
                    selector = DID_EXIT_FULL_SCREEN_SELECTOR,
                    callback = didExitFullScreenCallback,
                ),
            ),
        )
    }

    override fun install(
        nativeWindow: Pointer,
        customHeaderHeight: Float,
    ): MacOSWindowTitleBarInstallation? = runOnMainThread {
        installOnMainThread(nativeWindow, customHeaderHeight.toDouble())
    }

    private fun installOnMainThread(
        nativeWindow: Pointer,
        customHeaderHeight: Double,
    ): NativeInstallation? {
        val originalTitlebarAppearsTransparent =
            messageBoolean(nativeWindow, "titlebarAppearsTransparent")
        val originalTitleVisibility = messageLong(nativeWindow, "titleVisibility")
        val originalStyleMask = messageLong(nativeWindow, "styleMask")

        messageVoid(nativeWindow, "retain")
        val installation = NativeInstallation(
            nativeWindow = nativeWindow,
            customHeaderHeight = customHeaderHeight,
            originalTitlebarAppearsTransparent = originalTitlebarAppearsTransparent,
            originalTitleVisibility = originalTitleVisibility,
            originalStyleMask = originalStyleMask,
        )

        try {
            messageVoid(nativeWindow, "setTitlebarAppearsTransparent:", 1.toByte())
            messageVoid(nativeWindow, "setTitleVisibility:", NS_WINDOW_TITLE_HIDDEN)
            messageVoid(
                nativeWindow,
                "setStyleMask:",
                originalStyleMask or NS_WINDOW_STYLE_MASK_FULL_SIZE_CONTENT_VIEW,
            )

            if (!isFullScreen(nativeWindow)) {
                installation.layout = applyLayout(installation) ?: error(
                    "Unable to resolve the native macOS title bar",
                )
            }
            installation.observer = createFullScreenObserver(installation) ?: error(
                "Unable to observe macOS full-screen transitions",
            )
            return installation
        } catch (_: Throwable) {
            removeObserver(installation.observer)
            installation.layout?.let(::removeLayout)
            restoreWindowState(installation)
            messageVoid(nativeWindow, "release")
            return null
        }
    }

    override fun update(
        installation: MacOSWindowTitleBarInstallation,
        customHeaderHeight: Float,
    ) {
        val nativeInstallation = installation as NativeInstallation
        runOnMainThreadAsync {
            nativeInstallation.customHeaderHeight = customHeaderHeight.toDouble()
            ensureLayout(nativeInstallation)?.let { layout ->
                messageVoid(
                    layout.heightConstraint,
                    "setConstant:",
                    nativeInstallation.customHeaderHeight,
                )
                layout.buttonHorizontalConstraints.forEachIndexed { index, constraint ->
                    messageVoid(
                        constraint,
                        "setConstant:",
                        nativeInstallation.customHeaderHeight / 2.0 +
                            index * LEGACY_HORIZONTAL_BUTTON_OFFSET,
                    )
                }
                refreshLayout(layout)
            }
        }
    }

    override fun performWindowDrag(nativeWindow: Pointer): Boolean {
        if (isMainThread()) return performWindowDragOnMainThread(nativeWindow)

        // AppKit can synchronously wait for the AWT event handler while it delivers mouse and
        // resize events. Waiting synchronously for AppKit here would deadlock both event loops.
        return dispatchOnMainThread {
            performWindowDragOnMainThread(nativeWindow)
        }
    }

    private fun performWindowDragOnMainThread(nativeWindow: Pointer): Boolean {
        val applicationClass = classPointer("NSApplication")
            ?: return false
        val application = messagePointer(applicationClass, "sharedApplication")
            ?: return false
        val event = messagePointer(application, "currentEvent")
            ?: return false
        messageVoid(nativeWindow, "performWindowDragWithEvent:", event)
        return true
    }

    override fun remove(installation: MacOSWindowTitleBarInstallation) {
        val nativeInstallation = installation as NativeInstallation
        runOnMainThreadAsync {
            if (nativeInstallation.removed) return@runOnMainThreadAsync
            removeObserver(nativeInstallation.observer)
            nativeInstallation.observer = null
            nativeInstallation.layout?.let(::removeLayout)
            nativeInstallation.layout = null
            setWindowControlsHidden(nativeInstallation.nativeWindow, false)
            restoreWindowState(nativeInstallation)
            messageVoid(nativeInstallation.nativeWindow, "release")
            nativeInstallation.removed = true
        }
    }

    private fun ensureLayout(installation: NativeInstallation): NativeLayout? {
        // AppKit can clear the full-screen style before the exit notification. Queued Compose
        // updates must not reinstall constraints while AppKit is still moving the title bar.
        if (
            installation.removed ||
            installation.fullScreenTransitionInProgress ||
            isFullScreen(installation.nativeWindow)
        ) {
            return null
        }
        installation.layout?.let { return it }
        return applyLayout(installation).also { layout ->
            installation.layout = layout
        }
    }

    private fun applyLayout(installation: NativeInstallation): NativeLayout? {
        val nativeWindow = installation.nativeWindow
        val contentView = messagePointer(nativeWindow, "contentView") ?: return null
        val themeFrame = messagePointer(contentView, "superview") ?: return null
        val buttons = WINDOW_BUTTON_TYPES.mapNotNull { buttonType ->
            messagePointer(nativeWindow, "standardWindowButton:", buttonType)
        }
        val titlebar = buttons.firstOrNull()
            ?.let { messagePointer(it, "superview") }
            ?: return null
        val titlebarContainer = messagePointer(titlebar, "superview") ?: return null
        val titlebarSubviews = subviews(titlebar)
        val titlebarContainerSubviews = subviews(titlebarContainer)
        val textFieldClass = classPointer("NSTextField")
        val titleBarHierarchy = viewHierarchy(titlebarContainer)
        val titleViews = textFieldClass?.let { klass ->
            titleBarHierarchy.filter { view ->
                messageBoolean(view, "isKindOfClass:", klass)
            }
        }.orEmpty()
        val dragViews = titleBarHierarchy.filter(::isWindowDragView)
        val hiddenViewStates = uniqueViews(titleViews + dragViews).map { view ->
            HiddenViewState(
                view = view,
                hidden = messageBoolean(view, "isHidden"),
            )
        }
        val backgroundViews = titlebarSubviews.filter { view ->
            view !in buttons && view !in titleViews && isTitlebarBackground(view)
        }
        val decorationViews = titlebarContainerSubviews.filter { view ->
            view != titlebar && className(view).contains("TitlebarDecoration")
        }
        // AppKit can use either artwork on macOS 26+, depending on the launcher's linked SDK
        // Read the cell's natural size before changing its frame; Liquid Glass buttons already
        // have 14pt artwork and must not receive the legacy artwork enlargement
        val nativeButtonSizes = if (usesModernWindowButtonLayout) {
            buttons.associateWith { button ->
                messagePointer(button, "cell")?.let { cell ->
                    messageSize(cell, "cellSize")
                } ?: messageRect(button, "frame").size
            }
        } else {
            emptyMap()
        }
        val scaledButtons = nativeButtonSizes
            .filterValues { size ->
                size.width == WINDOW_BUTTON_FRAME_WIDTH &&
                    size.height == WINDOW_BUTTON_FRAME_HEIGHT
            }
            .keys
            .toList()
        val retainedConstraints = mutableListOf<Pointer>()
        var changedViews = emptyList<ViewState>()

        try {
            val heightConstraint = rememberConstraint(retainedConstraints) {
                constantConstraint(titlebar, "heightAnchor", installation.customHeaderHeight)
            }
            rememberConstraint(retainedConstraints) {
                constraint(titlebar, "leftAnchor", themeFrame, "leftAnchor")
            }
            rememberConstraint(retainedConstraints) {
                constraint(titlebar, "widthAnchor", themeFrame, "widthAnchor")
            }
            rememberConstraint(retainedConstraints) {
                constraint(titlebar, "topAnchor", themeFrame, "topAnchor")
            }

            val fillViews = listOf(titlebarContainer) +
                decorationViews +
                backgroundViews
            fillViews.forEach { view ->
                rememberConstraint(retainedConstraints) {
                    constraint(view, "leftAnchor", titlebar, "leftAnchor")
                }
                rememberConstraint(retainedConstraints) {
                    constraint(view, "rightAnchor", titlebar, "rightAnchor")
                }
                rememberConstraint(retainedConstraints) {
                    constraint(view, "topAnchor", titlebar, "topAnchor")
                }
                rememberConstraint(retainedConstraints) {
                    constraint(view, "bottomAnchor", titlebar, "bottomAnchor")
                }
            }

            val buttonHorizontalConstraints = mutableListOf<Pointer>()
            buttons.forEachIndexed { index, button ->
                rememberConstraint(retainedConstraints) {
                    constraint(button, "centerYAnchor", titlebar, "centerYAnchor")
                }
                if (usesModernWindowButtonLayout) {
                    val nativeSize = nativeButtonSizes.getValue(button)
                    rememberConstraint(retainedConstraints) {
                        constantConstraint(button, "widthAnchor", nativeSize.width)
                    }
                    rememberConstraint(retainedConstraints) {
                        constantConstraint(button, "heightAnchor", nativeSize.height)
                    }
                    rememberConstraint(retainedConstraints) {
                        constraint(
                            firstView = button,
                            firstAnchorName = "leftAnchor",
                            secondView = titlebar,
                            secondAnchorName = "leftAnchor",
                            constant = WINDOW_BUTTON_LEADING +
                                index * (WINDOW_BUTTON_FRAME_WIDTH + WINDOW_BUTTON_SPACING),
                        )
                    }
                } else {
                    buttonHorizontalConstraints += rememberConstraint(retainedConstraints) {
                        constraint(
                            firstView = button,
                            firstAnchorName = "centerXAnchor",
                            secondView = titlebar,
                            secondAnchorName = "leftAnchor",
                            constant = installation.customHeaderHeight / 2.0 +
                                index * LEGACY_HORIZONTAL_BUTTON_OFFSET,
                        )
                    }
                }
            }

            changedViews = uniqueViews(
                listOf(titlebar) + fillViews + buttons,
            ).map { view ->
                ViewState(
                    view = view,
                    translatesAutoresizingMaskIntoConstraints = messageBoolean(
                        view,
                        "translatesAutoresizingMaskIntoConstraints",
                    ),
                )
            }
            changedViews.forEach { state ->
                messageVoid(
                    state.view,
                    "setTranslatesAutoresizingMaskIntoConstraints:",
                    0.toByte(),
                )
            }
            retainedConstraints.forEach { constraint ->
                messageVoid(constraint, "setActive:", 1.toByte())
            }
            val layout = NativeLayout(
                constraints = retainedConstraints.toList(),
                heightConstraint = heightConstraint,
                buttonHorizontalConstraints = buttonHorizontalConstraints,
                scaledButtons = scaledButtons,
                changedViews = changedViews,
                hiddenViewStates = hiddenViewStates,
                layoutRoot = themeFrame,
            )
            refreshLayout(layout)
            return layout
        } catch (_: Throwable) {
            retainedConstraints.forEach { constraint ->
                runCatching { messageVoid(constraint, "setActive:", 0.toByte()) }
                runCatching { messageVoid(constraint, "release") }
            }
            changedViews.forEach { state ->
                runCatching {
                    messageVoid(
                        state.view,
                        "setTranslatesAutoresizingMaskIntoConstraints:",
                        if (state.translatesAutoresizingMaskIntoConstraints) {
                            1.toByte()
                        } else {
                            0.toByte()
                        },
                    )
                }
            }
            hiddenViewStates.forEach { state ->
                runCatching {
                    messageVoid(
                        state.view,
                        "setHidden:",
                        if (state.hidden) 1.toByte() else 0.toByte(),
                    )
                }
            }
            runCatching { messageVoid(themeFrame, "layoutSubtreeIfNeeded") }
            scaledButtons.forEach { button ->
                runCatching {
                    restoreWindowButtonArtwork(button)
                }
            }
            return null
        }
    }

    private fun removeLayout(layout: NativeLayout) {
        layout.constraints.forEach { constraint ->
            runCatching { messageVoid(constraint, "setActive:", 0.toByte()) }
        }
        layout.changedViews.forEach { state ->
            runCatching {
                messageVoid(
                    state.view,
                    "setTranslatesAutoresizingMaskIntoConstraints:",
                    if (state.translatesAutoresizingMaskIntoConstraints) 1.toByte() else 0.toByte(),
                )
            }
        }
        layout.hiddenViewStates.forEach { state ->
            runCatching {
                messageVoid(
                    state.view,
                    "setHidden:",
                    if (state.hidden) 1.toByte() else 0.toByte(),
                )
            }
        }
        runCatching { messageVoid(layout.layoutRoot, "layoutSubtreeIfNeeded") }
        layout.scaledButtons.forEach { button ->
            runCatching {
                restoreWindowButtonArtwork(button)
            }
        }
        layout.constraints.forEach { constraint ->
            runCatching { messageVoid(constraint, "release") }
        }
    }

    private fun refreshLayout(layout: NativeLayout) {
        layout.hiddenViewStates.forEach { state ->
            messageVoid(state.view, "setHidden:", 1.toByte())
        }
        messageVoid(layout.layoutRoot, "layoutSubtreeIfNeeded")
        layout.scaledButtons.forEach(::scaleWindowButtonArtwork)
    }

    private fun createFullScreenObserver(installation: NativeInstallation): Pointer? {
        val klass = observerClass ?: return null
        val allocatedObserver = messagePointer(klass, "alloc") ?: return null
        val observer = messagePointer(allocatedObserver, "init") ?: return null
        val key = Pointer.nativeValue(observer)
        val handler = FullScreenHandler(
            willEnterFullScreen = {
                installation.fullScreenTransitionInProgress = true
                installation.layout?.let(::removeLayout)
                installation.layout = null
            },
            didEnterFullScreen = {
                installation.fullScreenTransitionInProgress = false
            },
            willExitFullScreen = {
                installation.fullScreenTransitionInProgress = true
                setWindowControlsHidden(installation.nativeWindow, true)
            },
            didExitFullScreen = {
                installation.fullScreenTransitionInProgress = false
                try {
                    ensureLayout(installation)
                } finally {
                    setWindowControlsHidden(installation.nativeWindow, false)
                }
                if (installation.layout == null) {
                    scheduleLayoutRetry(installation)
                }
            },
        )
        synchronized(observerHandlers) {
            observerHandlers[key] = handler
        }

        return try {
            val center = defaultNotificationCenter() ?: error(
                "Unable to access NSNotificationCenter",
            )
            addObserver(
                center = center,
                observer = observer,
                selectorName = WILL_ENTER_FULL_SCREEN_SELECTOR,
                notificationName = NS_WINDOW_WILL_ENTER_FULL_SCREEN_NOTIFICATION,
                nativeWindow = installation.nativeWindow,
            )
            addObserver(
                center = center,
                observer = observer,
                selectorName = DID_ENTER_FULL_SCREEN_SELECTOR,
                notificationName = NS_WINDOW_DID_ENTER_FULL_SCREEN_NOTIFICATION,
                nativeWindow = installation.nativeWindow,
            )
            addObserver(
                center = center,
                observer = observer,
                selectorName = WILL_EXIT_FULL_SCREEN_SELECTOR,
                notificationName = NS_WINDOW_WILL_EXIT_FULL_SCREEN_NOTIFICATION,
                nativeWindow = installation.nativeWindow,
            )
            addObserver(
                center = center,
                observer = observer,
                selectorName = DID_EXIT_FULL_SCREEN_SELECTOR,
                notificationName = NS_WINDOW_DID_EXIT_FULL_SCREEN_NOTIFICATION,
                nativeWindow = installation.nativeWindow,
            )
            observer
        } catch (_: Throwable) {
            synchronized(observerHandlers) {
                observerHandlers.remove(key)
            }
            defaultNotificationCenter()?.let { center ->
                runCatching { messageVoid(center, "removeObserver:", observer) }
            }
            runCatching { messageVoid(observer, "release") }
            null
        }
    }

    private fun scheduleLayoutRetry(installation: NativeInstallation) {
        // NSWindowDidExitFullScreenNotification can arrive before AppKit has rebuilt the complete
        // title-bar hierarchy. Queue the retry for the next AppKit turn so the notification can
        // unwind without involving the AWT event thread.
        dispatchOnMainThread {
            if (installation.removed || installation.layout != null) return@dispatchOnMainThread
            runCatching { ensureLayout(installation) }
        }
    }

    private fun addObserver(
        center: Pointer,
        observer: Pointer,
        selectorName: String,
        notificationName: String,
        nativeWindow: Pointer,
    ) {
        val name = nsString(notificationName) ?: error(
            "Unable to create notification name $notificationName",
        )
        messageVoid(
            center,
            "addObserver:selector:name:object:",
            observer,
            selector(selectorName),
            name,
            nativeWindow,
        )
    }

    private fun removeObserver(observer: Pointer?) {
        observer ?: return
        synchronized(observerHandlers) {
            observerHandlers.remove(Pointer.nativeValue(observer))
        }
        defaultNotificationCenter()?.let { center ->
            runCatching { messageVoid(center, "removeObserver:", observer) }
        }
        runCatching { messageVoid(observer, "release") }
    }

    private fun notificationCallback(
        action: (FullScreenHandler) -> Unit,
    ) = object : ObjectiveCNotificationCallback {
        override fun invoke(receiver: Pointer?, command: Pointer?, notification: Pointer?) {
            val observer = receiver ?: return
            val handler = synchronized(observerHandlers) {
                observerHandlers[Pointer.nativeValue(observer)]
            } ?: return
            runCatching { action(handler) }
        }
    }

    private fun createClass(
        name: String,
        superclassName: String,
        methods: List<ObjectiveCMethod>,
    ): Pointer? {
        classPointer(name)?.let { return it }
        val superclass = classPointer(superclassName) ?: return null
        val klass = allocateClassPair.invokePointer(arrayOf(superclass, name, 0L))
            ?.takeUnless(::isNull)
            ?: return classPointer(name)
        val allMethodsAdded = methods.all { method ->
            addMethod.invokeInt(
                arrayOf(
                    klass,
                    selector(method.selector),
                    method.callback,
                    OBJECTIVE_C_VOID_OBJECT_METHOD_ENCODING,
                ),
            ) != 0
        }
        if (!allMethodsAdded) return null
        registerClassPair.invokeVoid(arrayOf(klass))
        return klass
    }

    private fun restoreWindowState(installation: NativeInstallation) {
        runCatching {
            messageVoid(
                installation.nativeWindow,
                "setTitlebarAppearsTransparent:",
                if (installation.originalTitlebarAppearsTransparent) 1.toByte() else 0.toByte(),
            )
        }
        runCatching {
            messageVoid(
                installation.nativeWindow,
                "setTitleVisibility:",
                installation.originalTitleVisibility,
            )
        }
        runCatching {
            messageVoid(
                installation.nativeWindow,
                "setStyleMask:",
                installation.originalStyleMask,
            )
        }
    }

    private fun setWindowControlsHidden(nativeWindow: Pointer, hidden: Boolean) {
        WINDOW_BUTTON_TYPES.forEach { buttonType ->
            messagePointer(nativeWindow, "standardWindowButton:", buttonType)?.let { button ->
                runCatching {
                    messageVoid(button, "setHidden:", if (hidden) 1.toByte() else 0.toByte())
                }
            }
        }
    }

    private fun setViewBoundsSize(view: Pointer, width: Double, height: Double) {
        messageVoid(view, "setBoundsSize:", NSSize.ByValue(width, height))
    }

    private fun setViewBoundsOrigin(view: Pointer, x: Double, y: Double) {
        messageVoid(view, "setBoundsOrigin:", NSPoint.ByValue(x, y))
    }

    private fun scaleWindowButtonArtwork(button: Pointer) {
        val frame = messageRect(button, "frame")
        val boundsWidth = frame.size.width * WINDOW_BUTTON_BOUNDS_WIDTH_RATIO
        val boundsHeight = frame.size.height * WINDOW_BUTTON_BOUNDS_HEIGHT_RATIO
        setViewBoundsSize(button, boundsWidth, boundsHeight)
        setViewBoundsOrigin(
            view = button,
            x = windowButtonBoundsOrigin(
                frameSize = frame.size.width,
                normalFrameSize = WINDOW_BUTTON_FRAME_WIDTH,
                artworkAdjustment = WINDOW_BUTTON_RESTORED_X_ALIGNMENT,
                boundsSize = boundsWidth,
            ),
            y = windowButtonBoundsOrigin(
                frameSize = frame.size.height,
                normalFrameSize = WINDOW_BUTTON_FRAME_HEIGHT,
                artworkAdjustment = WINDOW_BUTTON_RESTORED_Y_ALIGNMENT,
                boundsSize = boundsHeight,
            ),
        )
    }

    private fun windowButtonBoundsOrigin(
        frameSize: Double,
        normalFrameSize: Double,
        artworkAdjustment: Double,
        boundsSize: Double,
    ): Double {
        if (frameSize <= normalFrameSize) return 0.0
        val visualOffset = (frameSize - normalFrameSize) / 2.0 + artworkAdjustment
        return visualOffset * boundsSize / frameSize
    }

    private fun restoreWindowButtonArtwork(button: Pointer) {
        val frame = messageRect(button, "frame")
        setViewBoundsOrigin(button, 0.0, 0.0)
        setViewBoundsSize(button, frame.size.width, frame.size.height)
    }

    private fun isFullScreen(nativeWindow: Pointer): Boolean = messageLong(nativeWindow, "styleMask") and NS_WINDOW_STYLE_MASK_FULL_SCREEN != 0L

    private fun isTitlebarBackground(view: Pointer): Boolean {
        val name = className(view)
        return name == "NSView" ||
            name.contains("TitlebarBackground") ||
            name.contains("VisualEffect")
    }

    private fun isWindowDragView(view: Pointer): Boolean = className(view).contains("WindowDragView")

    private fun viewHierarchy(root: Pointer): List<Pointer> {
        val views = mutableListOf<Pointer>()
        val pending = ArrayDeque<Pointer>()
        val visited = mutableSetOf<Long>()
        pending.add(root)
        while (
            pending.isNotEmpty() &&
            views.size.toLong() < MAX_REASONABLE_SUBVIEW_COUNT
        ) {
            val view = pending.removeFirst()
            if (!visited.add(Pointer.nativeValue(view))) continue
            views += view
            subviews(view).forEach(pending::addLast)
        }
        return views
    }

    private fun subviews(view: Pointer): List<Pointer> {
        val array = messagePointer(view, "subviews") ?: return emptyList()
        val count = messageLong(array, "count")
        if (count <= 0L || count > MAX_REASONABLE_SUBVIEW_COUNT) return emptyList()
        return (0L until count).mapNotNull { index ->
            messagePointer(array, "objectAtIndex:", index)
        }
    }

    private fun uniqueViews(views: List<Pointer>): List<Pointer> = views.distinctBy(Pointer::nativeValue)

    private fun className(value: Pointer): String = getObjectClassName.invokePointer(arrayOf(value))
        ?.takeUnless(::isNull)
        ?.getString(0)
        .orEmpty()

    private fun defaultNotificationCenter(): Pointer? = classPointer("NSNotificationCenter")?.let { centerClass ->
        messagePointer(centerClass, "defaultCenter")
    }

    private fun nsString(value: String): Pointer? {
        val stringClass = classPointer("NSString") ?: return null
        return messagePointer(stringClass, "stringWithUTF8String:", value)
    }

    private fun rememberConstraint(
        retainedConstraints: MutableList<Pointer>,
        create: () -> Pointer,
    ): Pointer {
        val constraint = create()
        messageVoid(constraint, "retain")
        retainedConstraints += constraint
        return constraint
    }

    private fun constraint(
        firstView: Pointer,
        firstAnchorName: String,
        secondView: Pointer,
        secondAnchorName: String,
        constant: Double? = null,
    ): Pointer {
        val firstAnchor = messagePointer(firstView, firstAnchorName)
            ?: error("Unable to read $firstAnchorName")
        val secondAnchor = messagePointer(secondView, secondAnchorName)
            ?: error("Unable to read $secondAnchorName")
        return if (constant == null) {
            messagePointer(firstAnchor, "constraintEqualToAnchor:", secondAnchor)
        } else {
            messagePointer(
                firstAnchor,
                "constraintEqualToAnchor:constant:",
                secondAnchor,
                constant,
            )
        } ?: error("Unable to create a title bar layout constraint")
    }

    private fun constantConstraint(
        view: Pointer,
        anchorName: String,
        constant: Double,
    ): Pointer {
        val anchor = messagePointer(view, anchorName)
            ?: error("Unable to read $anchorName")
        return messagePointer(anchor, "constraintEqualToConstant:", constant)
            ?: error("Unable to create a title bar constant constraint")
    }

    private fun classPointer(name: String): Pointer? = getClass.invokePointer(arrayOf(name))?.takeUnless(::isNull)

    private fun selector(name: String): Pointer = synchronized(selectors) {
        selectors.getOrPut(name) {
            registerSelector.invokePointer(arrayOf(name))
                ?: error("Unable to register Objective-C selector $name")
        }
    }

    private fun messagePointer(
        receiver: Pointer,
        name: String,
        vararg arguments: Any?,
    ): Pointer? = sendMessage.invokePointer(arrayOf(receiver, selector(name), *arguments))
        ?.takeUnless(::isNull)

    private fun messageBoolean(
        receiver: Pointer,
        name: String,
        vararg arguments: Any?,
    ): Boolean {
        val value = sendMessage.invoke(
            Byte::class.javaPrimitiveType,
            arrayOf(receiver, selector(name), *arguments),
        ) as Byte
        return value.toInt() != 0
    }

    private fun messageLong(
        receiver: Pointer,
        name: String,
        vararg arguments: Any?,
    ): Long = sendMessage.invoke(
        Long::class.javaPrimitiveType,
        arrayOf(receiver, selector(name), *arguments),
    ) as Long

    private fun messageRect(receiver: Pointer, name: String): NSRect {
        val structMessage = sendStructMessage
        if (structMessage != null) {
            val result = NSRect()
            result.write()
            structMessage.invokeVoid(
                arrayOf(result.pointer, receiver, selector(name)),
            )
            result.read()
            return result
        }
        return sendMessage.invoke(
            NSRect.ByValue::class.java,
            arrayOf(receiver, selector(name)),
        ) as NSRect
    }

    // NSSize fits in registers on both arm64 and x86_64, unlike the larger NSRect
    private fun messageSize(receiver: Pointer, name: String): NSSize = sendMessage.invoke(
        NSSize.ByValue::class.java,
        arrayOf(receiver, selector(name)),
    ) as NSSize

    private fun messageVoid(receiver: Pointer, name: String, vararg arguments: Any?) {
        sendMessage.invokeVoid(arrayOf(receiver, selector(name), *arguments))
    }

    private fun <T> runOnMainThread(block: () -> T): T {
        if (isMainThread()) return block()

        var value: Any? = null
        var failure: Throwable? = null
        val callback = object : TitleBarDispatchCallback {
            override fun invoke(context: Pointer?) {
                try {
                    value = block()
                } catch (throwable: Throwable) {
                    failure = throwable
                }
            }
        }
        dispatch.dispatch_sync_f(mainQueue, null, callback)
        failure?.let { throw it }

        @Suppress("UNCHECKED_CAST")
        return value as T
    }

    private fun runOnMainThreadAsync(block: () -> Unit): Boolean {
        if (isMainThread()) {
            block()
            return true
        }
        return dispatchOnMainThread(block)
    }

    private fun dispatchOnMainThread(block: () -> Unit): Boolean {
        lateinit var callback: TitleBarDispatchCallback
        callback = object : TitleBarDispatchCallback {
            override fun invoke(context: Pointer?) {
                try {
                    runCatching(block)
                } finally {
                    synchronized(pendingMainThreadCallbacks) {
                        pendingMainThreadCallbacks.remove(callback)
                    }
                }
            }
        }
        synchronized(pendingMainThreadCallbacks) {
            pendingMainThreadCallbacks.add(callback)
        }
        return runCatching {
            dispatch.dispatch_async_f(mainQueue, null, callback)
        }.onFailure {
            synchronized(pendingMainThreadCallbacks) {
                pendingMainThreadCallbacks.remove(callback)
            }
        }.isSuccess
    }

    private fun isMainThread(): Boolean {
        val threadClass = classPointer("NSThread") ?: return false
        return messageBoolean(threadClass, "isMainThread")
    }

    private fun isNull(pointer: Pointer): Boolean = Pointer.nativeValue(pointer) == 0L

    private data class NativeInstallation(
        val nativeWindow: Pointer,
        var customHeaderHeight: Double,
        val originalTitlebarAppearsTransparent: Boolean,
        val originalTitleVisibility: Long,
        val originalStyleMask: Long,
        var layout: NativeLayout? = null,
        var observer: Pointer? = null,
        var removed: Boolean = false,
        var fullScreenTransitionInProgress: Boolean = false,
    ) : MacOSWindowTitleBarInstallation

    private data class NativeLayout(
        val constraints: List<Pointer>,
        val heightConstraint: Pointer,
        val buttonHorizontalConstraints: List<Pointer>,
        val scaledButtons: List<Pointer>,
        val changedViews: List<ViewState>,
        val hiddenViewStates: List<HiddenViewState>,
        val layoutRoot: Pointer,
    )

    private data class ViewState(
        val view: Pointer,
        val translatesAutoresizingMaskIntoConstraints: Boolean,
    )

    private data class HiddenViewState(
        val view: Pointer,
        val hidden: Boolean,
    )

    private data class FullScreenHandler(
        val willEnterFullScreen: () -> Unit,
        val didEnterFullScreen: () -> Unit,
        val willExitFullScreen: () -> Unit,
        val didExitFullScreen: () -> Unit,
    )

    private data class ObjectiveCMethod(
        val selector: String,
        val callback: Callback,
    )

    companion object {
        private val usesModernWindowButtonLayout =
            System.getProperty("os.version")
                .orEmpty()
                .substringBefore('.')
                .toIntOrNull()
                ?.let { it >= 26 }
                ?: false

        val instance: MacOSNativeWindowTitleBar? by lazy {
            runCatching {
                val objectiveC = NativeLibrary.getInstance("objc")
                val system = NativeLibrary.getInstance("System")
                val mainQueue = runCatching {
                    system.getFunction("dispatch_get_main_queue").invokePointer(emptyArray())
                }.getOrNull() ?: system.getGlobalVariableAddress("_dispatch_main_q")
                require(Pointer.nativeValue(mainQueue) != 0L)

                MacOSNativeWindowTitleBar(
                    objectiveC = objectiveC,
                    dispatch = Native.load("System", TitleBarDispatch::class.java),
                    mainQueue = mainQueue,
                )
            }.getOrNull()
        }

        private val WINDOW_BUTTON_TYPES = listOf(0L, 1L, 2L)
        private const val WINDOW_BUTTON_BOUNDS_WIDTH_RATIO =
            MacOSWindowMetrics.WINDOW_BUTTON_BOUNDS_WIDTH_RATIO
        private const val WINDOW_BUTTON_BOUNDS_HEIGHT_RATIO =
            MacOSWindowMetrics.WINDOW_BUTTON_BOUNDS_HEIGHT_RATIO
        private const val WINDOW_BUTTON_FRAME_WIDTH =
            MacOSWindowMetrics.WINDOW_BUTTON_FRAME_WIDTH
        private const val WINDOW_BUTTON_FRAME_HEIGHT =
            MacOSWindowMetrics.WINDOW_BUTTON_FRAME_HEIGHT
        private const val WINDOW_BUTTON_RESTORED_X_ALIGNMENT = -0.5
        private const val WINDOW_BUTTON_RESTORED_Y_ALIGNMENT = 0.25
        private const val WINDOW_BUTTON_LEADING = MacOSWindowMetrics.WINDOW_BUTTON_LEADING
        private const val WINDOW_BUTTON_SPACING = MacOSWindowMetrics.WINDOW_BUTTON_SPACING
        private const val LEGACY_HORIZONTAL_BUTTON_OFFSET =
            MacOSWindowMetrics.LEGACY_HORIZONTAL_BUTTON_OFFSET
        private const val NS_WINDOW_TITLE_HIDDEN = 1L
        private const val NS_WINDOW_STYLE_MASK_FULL_SCREEN = 1L shl 14
        private const val NS_WINDOW_STYLE_MASK_FULL_SIZE_CONTENT_VIEW = 1L shl 15
        private const val MAX_REASONABLE_SUBVIEW_COUNT = 1024L
        private const val OBSERVER_CLASS_NAME = "SaltUIWindowTitleBarObserver"
        private const val WILL_ENTER_FULL_SCREEN_SELECTOR = "saltWindowWillEnterFullScreen:"
        private const val DID_ENTER_FULL_SCREEN_SELECTOR = "saltWindowDidEnterFullScreen:"
        private const val WILL_EXIT_FULL_SCREEN_SELECTOR = "saltWindowWillExitFullScreen:"
        private const val DID_EXIT_FULL_SCREEN_SELECTOR = "saltWindowDidExitFullScreen:"
        private const val NS_WINDOW_WILL_ENTER_FULL_SCREEN_NOTIFICATION =
            "NSWindowWillEnterFullScreenNotification"
        private const val NS_WINDOW_DID_ENTER_FULL_SCREEN_NOTIFICATION =
            "NSWindowDidEnterFullScreenNotification"
        private const val NS_WINDOW_WILL_EXIT_FULL_SCREEN_NOTIFICATION =
            "NSWindowWillExitFullScreenNotification"
        private const val NS_WINDOW_DID_EXIT_FULL_SCREEN_NOTIFICATION =
            "NSWindowDidExitFullScreenNotification"
        private const val OBJECTIVE_C_VOID_OBJECT_METHOD_ENCODING = "v@:@"
    }
}

@Suppress("FunctionName")
private interface TitleBarDispatch : com.sun.jna.Library {
    fun dispatch_async_f(
        queue: Pointer,
        context: Pointer?,
        callback: TitleBarDispatchCallback,
    )

    fun dispatch_sync_f(
        queue: Pointer,
        context: Pointer?,
        callback: TitleBarDispatchCallback,
    )
}

private interface TitleBarDispatchCallback : Callback {
    fun invoke(context: Pointer?)
}

private interface ObjectiveCNotificationCallback : Callback {
    fun invoke(receiver: Pointer?, command: Pointer?, notification: Pointer?)
}

internal fun requiresObjectiveCStructReturn(architecture: String): Boolean = architecture.equals("x86_64", ignoreCase = true) ||
    architecture.equals("amd64", ignoreCase = true)

@Structure.FieldOrder("width", "height")
internal open class NSSize(
    @JvmField var width: Double = 0.0,
    @JvmField var height: Double = 0.0,
) : Structure() {
    class ByValue(
        width: Double = 0.0,
        height: Double = 0.0,
    ) : NSSize(width, height),
        Structure.ByValue
}

@Structure.FieldOrder("x", "y")
internal open class NSPoint(
    @JvmField var x: Double = 0.0,
    @JvmField var y: Double = 0.0,
) : Structure() {
    class ByValue(
        x: Double = 0.0,
        y: Double = 0.0,
    ) : NSPoint(x, y),
        Structure.ByValue
}

@Structure.FieldOrder("origin", "size")
internal open class NSRect(
    @JvmField var origin: NSPoint = NSPoint(),
    @JvmField var size: NSSize = NSSize(),
) : Structure() {
    class ByValue :
        NSRect(),
        Structure.ByValue
}
