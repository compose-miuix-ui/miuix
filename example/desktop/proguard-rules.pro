# Workaround for ProGuard VerifyError on SkiaParagraph:
# ProGuard's method-specialization optimization pass rewrites Paragraph-czeN-Hc
# with concrete return type SkiaParagraph while the method body returns Paragraph interface,
# causing JVM java.lang.VerifyError: Bad return type.
-optimizations !method/specialization/*

# JNA (Java Native Access)
# JNA uses JNI callbacks and reflection that ProGuard cannot detect.
-keep class com.sun.jna.** { *; }
-keepclassmembers class * extends com.sun.jna.** { *; }
-keep interface * extends com.sun.jna.** { *; }
-keep class * implements com.sun.jna.Callback { *; }
-keep class * implements com.sun.jna.Library { *; }
-keep class * implements com.sun.jna.Structure { *; }
-keep class * extends com.sun.jna.Structure { *; }
-keepclassmembers class * extends com.sun.jna.Structure {
    <fields>;
}
-keep class * extends com.sun.jna.NativeLong { *; }
-dontwarn com.sun.jna.**
-dontnote com.sun.jna.**

# Nucleus (darkmode-detector & core-runtime)
# Native entry points and JNI callback methods (e.g. onThemeChanged) must be preserved.
-keepclasseswithmembernames,includedescriptorclasses class dev.nucleusframework.** {
    native <methods>;
}
-keep class dev.nucleusframework.**Bridge { *; }
-keep class dev.nucleusframework.**Jni { *; }
-keep class dev.nucleusframework.darkmodedetector.** { *; }
-keep class dev.nucleusframework.core.runtime.** { *; }
-dontwarn dev.nucleusframework.**
-dontnote dev.nucleusframework.**

# miuix-window
-keep class top.yukonga.miuix.kmp.window.** { *; }
-dontnote top.yukonga.miuix.**
