// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

/**
 * Converts auto-generated precise baseline profiles into wildcard patterns
 * suitable for library distribution, similar to AndroidX's ProfileParser.
 *
 * Precise method signatures are fragile (break on compiler/R8 changes),
 * while wildcard patterns like `HSPLClassName;->**(**)**` are stable.
 */
abstract class ConvertBaselineProfileTask : DefaultTask() {
    @get:InputFile
    abstract val inputFile: RegularFileProperty

    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    @get:Input
    abstract val targetPackage: Property<String>

    @get:Input
    abstract val excludePackages: ListProperty<String>

    @TaskAction
    fun convert() {
        val input = inputFile.get().asFile
        val output = outputFile.get().asFile
        val prefix = targetPackage.get()
        val excludes = excludePackages.getOrElse(emptyList())

        val classFlags = mutableMapOf<String, MutableSet<Char>>()

        input.readLines().forEach { line ->
            if (line.isBlank()) return@forEach

            val lIndex = line.indexOf('L')
            if (lIndex < 0) return@forEach

            val flags = line.substring(0, lIndex)
            val rest = line.substring(lIndex + 1)

            // Extract base class name: truncate at ';' or '$'
            val semicolonIdx = rest.indexOf(';')
            val dollarIdx = rest.indexOf('$')
            val endIdx = when {
                semicolonIdx >= 0 && dollarIdx >= 0 -> minOf(semicolonIdx, dollarIdx)
                semicolonIdx >= 0 -> semicolonIdx
                dollarIdx >= 0 -> dollarIdx
                else -> rest.length
            }
            val className = rest.substring(0, endIdx)

            if (!className.startsWith(prefix)) return@forEach
            if (excludes.any { className.startsWith(it) }) return@forEach

            val flagSet = classFlags.getOrPut(className) { mutableSetOf() }
            flags.forEach { ch -> flagSet.add(ch) }
        }

        val outputLines = classFlags.map { (className, flags) ->
            val hasMethodFlags = 'H' in flags || 'S' in flags || 'P' in flags

            if (!hasMethodFlags) {
                // Bare class reference: only pre-load class, don't pre-compile methods
                "L${className};"
            } else {
                // Build aggregated flag string in HSPL order
                val flagStr = buildString {
                    if ('H' in flags) append('H')
                    if ('S' in flags) append('S')
                    if ('P' in flags) append('P')
                    append('L')
                }

                if (className.endsWith("Kt")) {
                    // Kotlin file class: ** covers inner/synthetic classes
                    "${flagStr}${className}**->**(**)**"
                } else {
                    "${flagStr}${className};->**(**)**"
                }
            }
        }.sorted()

        output.parentFile.mkdirs()
        output.writeText(outputLines.joinToString("\n") + "\n")
    }
}
