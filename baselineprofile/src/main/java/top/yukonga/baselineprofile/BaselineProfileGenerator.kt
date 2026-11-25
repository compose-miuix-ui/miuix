// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This test class generates a baseline profile for the target package.
 *
 * It covers app startup and critical user journeys including tab navigation
 * and scrolling through component demos to ensure all miuix library code paths
 * are profiled.
 *
 * Run the generator with:
 * ```
 * ./gradlew :miuix-ui:generateBaselineProfile
 * ```
 * After you run the generator, you can verify the improvements running the
 * [StartupBenchmarks] benchmark.
 *
 * When using this class to generate a baseline profile, only API 33+ or rooted
 * API 28+ are supported.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class BaselineProfileGenerator {

    @get:Rule
    val rule = BaselineProfileRule()

    private val packageName: String
        get() = InstrumentationRegistry.getArguments().getString("targetAppId")
            ?: throw Exception("targetAppId not passed as instrumentation runner arg")

    @Test
    fun startup() {
        rule.collect(
            packageName = packageName,
            maxIterations = 2,
            includeInStartupProfile = true,
        ) {
            pressHome()
            startActivityAndWait()
            device.wait(Until.hasObject(By.pkg(packageName).depth(0)), 10_000)
            device.waitForIdle()
        }
    }

    @Test
    fun interactions() {
        rule.collect(
            packageName = packageName,
            maxIterations = 2,
            includeInStartupProfile = false,
        ) {
            pressHome()
            startActivityAndWait()
            device.wait(Until.hasObject(By.text("SearchBar")), 5_000)
            device.waitForIdle()

            val width = device.displayWidth
            val height = device.displayHeight
            val swipeX = (width * 0.96f).toInt()
            val scrollDownStart = (height * 0.65f).toInt()
            val scrollDownEnd = (height * 0.35f).toInt()

            val componentPages = listOf(
                "PullToRefresh",
                "Dialog",
                "BottomSheet",
                "Snackbar",
                "Slider",
                "ColorPicker",
                "Preferences",
                "Glass Component",
            )
            for (title in componentPages) {
                var item = device.findObject(By.text(title))
                var attempts = 0
                while (item == null && attempts < 10) {
                    device.swipe(swipeX, scrollDownStart, swipeX, scrollDownEnd, 40)
                    Thread.sleep(500)
                    item = device.findObject(By.text(title))
                    attempts++
                }
                if (item != null) {
                    item.click()
                    device.waitForIdle()
                    Thread.sleep(500)
                    when (title) {
                        "PullToRefresh" -> {
                            device.swipe(swipeX, scrollDownEnd, swipeX, scrollDownStart, 30)
                            Thread.sleep(600)
                        }

                        "Dialog" -> {
                            device.findObject(By.text("Dialog (O)"))?.let {
                                it.click()
                                device.waitForIdle()
                                Thread.sleep(400)
                                device.pressBack()
                                device.waitForIdle()
                                Thread.sleep(300)
                            }
                        }

                        "BottomSheet" -> {
                            device.findObject(By.text("BottomSheet (O)"))?.let {
                                it.click()
                                device.waitForIdle()
                                Thread.sleep(400)
                                device.pressBack()
                                device.waitForIdle()
                                Thread.sleep(300)
                            }
                        }

                        "Snackbar" -> {
                            device.findObject(By.text("Short (4s)"))?.let {
                                it.click()
                                device.waitForIdle()
                                Thread.sleep(400)
                            }
                        }

                        "Glass Component" -> {
                            device.findObject(By.desc("Sort"))?.let {
                                it.click()
                                device.waitForIdle()
                                Thread.sleep(400)
                                device.pressBack()
                                device.waitForIdle()
                                Thread.sleep(300)
                            }
                            device.swipe(swipeX, scrollDownStart, swipeX, scrollDownEnd, 40)
                            Thread.sleep(400)
                        }

                        "Preferences" -> {
                            repeat(8) {
                                device.swipe(swipeX, scrollDownStart, swipeX, scrollDownEnd, 40)
                                Thread.sleep(200)
                            }
                        }

                        else -> {
                            device.swipe(swipeX, scrollDownStart, swipeX, scrollDownEnd, 40)
                            Thread.sleep(400)
                        }
                    }
                    val backButton = device.findObject(By.desc("Back"))
                    if (backButton != null) {
                        backButton.click()
                    } else {
                        device.pressBack()
                    }
                    device.waitForIdle()
                    Thread.sleep(700)
                    if (device.currentPackageName != packageName) break
                }
            }

            val scrollX = width / 2
            val pagerTabs = listOf("Icon", "Color", "TextStyle")
            for (tabTitle in pagerTabs) {
                val tab = device.findObjects(By.pkg(packageName).text(tabTitle)).lastOrNull() ?: continue
                tab.click()
                device.waitForIdle()
                Thread.sleep(500)
                repeat(2) {
                    device.swipe(scrollX, scrollDownStart, scrollX, scrollDownEnd, 30)
                    Thread.sleep(250)
                }
                device.swipe(scrollX, scrollDownStart, scrollX, scrollDownEnd, 8)
                Thread.sleep(400)
                device.swipe(scrollX, scrollDownEnd, scrollX, scrollDownStart, 8)
                Thread.sleep(400)
            }

            val tab = device.findObjects(By.pkg(packageName).text("Settings")).lastOrNull()
            if (tab != null) {
                tab.click()
                device.waitForIdle()
                Thread.sleep(400)
                device.findObject(By.text("Color Mode"))?.let {
                    it.click()
                    device.waitForIdle()
                    Thread.sleep(400)
                    device.findObject(By.text("MonetDark"))?.let { option ->
                        option.click()
                        device.waitForIdle()
                        Thread.sleep(500)
                    }
                }
                repeat(2) {
                    device.swipe(scrollX, scrollDownStart, scrollX, scrollDownEnd, 30)
                    Thread.sleep(250)
                }
                device.swipe(scrollX, scrollDownEnd, scrollX, scrollDownStart, 8)
                Thread.sleep(300)
            }
        }
    }
}
