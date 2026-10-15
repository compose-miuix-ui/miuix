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
            val scrollDownStart = (height * 0.80f).toInt()
            val scrollDownEnd = (height * 0.20f).toInt()

            repeat(16) {
                device.swipe(swipeX, scrollDownStart, swipeX, scrollDownEnd, 12)
                Thread.sleep(800)
            }

            val tabTitles = listOf("Icon", "Color", "TextStyle", "Settings")
            for (title in tabTitles) {
                val tab = device.findObjects(By.pkg(packageName).text(title)).lastOrNull()
                if (tab != null) {
                    tab.click()
                    device.waitForIdle()
                    Thread.sleep(400)
                }
            }
        }
    }
}
