// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.docs.icongen

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.graphics.vector.VectorPath
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.SymbolWeights
import top.yukonga.miuix.kmp.symbol.VariableSymbol
import top.yukonga.miuix.kmp.symbol.toImageVector
import java.io.File

private val SYMBOL_WEIGHT_PRESETS = listOf(
    "Light" to SymbolWeights.Light,
    "Normal" to SymbolWeights.Normal,
    "Regular" to SymbolWeights.Regular,
    "Medium" to SymbolWeights.Medium,
    "Demibold" to SymbolWeights.Demibold,
)

private data class SvgPath(
    val d: String,
    val fill: String,
    val fillRule: String,
)

fun main(args: Array<String>) {
    val srcDirs = mutableListOf<String>()
    val options = mutableMapOf<String, String>()
    var i = 0
    while (i < args.size) {
        val arg = args[i]
        if (arg == "--src" && i + 1 < args.size) {
            srcDirs.add(args[i + 1])
            i += 2
        } else if (arg.startsWith("--") && i + 1 < args.size) {
            options[arg] = args[i + 1]
            i += 2
        } else {
            i++
        }
    }

    val outDir = options["--out"] ?: error("--out is required")
    val light = options["--light"] ?: "#000000"
    val dark = options["--dark"] ?: "#FFFFFF"
    val preserveColors = options["--preserve-colors"]?.equals("true", ignoreCase = true) ?: false
    val genDoc = options["--gen-doc"]?.equals("true", ignoreCase = true) ?: false
    val docFilePath = options["--doc-file"]
    val docFilePathZh = options["--doc-file-zh"]

    val dest = File(outDir)
    if (!dest.exists()) dest.mkdirs()

    var count = 0
    val symbolIconsMap = mutableMapOf<String, MutableMap<String, String>>()

    srcDirs.forEach { srcDir ->
        val src = File(srcDir)
        if (!src.isDirectory) error("Source directory not found: $src")

        src.walkTopDown()
            .filter { it.isFile && it.extension == "kt" }
            .forEach { file ->
                val content = file.readText()
                if (content.contains("VariableSymbol(")) {
                    count += generateSymbolSvgs(
                        content = content,
                        dest = dest,
                        light = light,
                        dark = dark,
                        preserveColors = preserveColors,
                        symbolIconsMap = symbolIconsMap,
                    )
                }
            }
    }

    println("[iconGen] Generated $count SVG(s) into ${dest.absolutePath}")

    if (genDoc && symbolIconsMap.isNotEmpty()) {
        val weightNames = SYMBOL_WEIGHT_PRESETS.map { it.first }
        val separator = "|" + "---|".repeat(weightNames.size + 1)

        fun generateTable(headerName: String): String {
            val sb = StringBuilder()
            sb.append("| $headerName | ${weightNames.joinToString(" | ")} |\n")
            sb.append("$separator\n")
            symbolIconsMap.keys.sorted().forEach { name ->
                val styles = symbolIconsMap[name]!!
                val row = weightNames.joinToString(" | ") { weight ->
                    val path = styles[weight]
                    if (path != null) """<img src="/icons/$path" width="24" height="24" />""" else ""
                }
                sb.append("| `$name` | $row |\n")
            }
            return sb.toString()
        }

        if (docFilePath != null || docFilePathZh == null) {
            val content = buildString {
                append("### Symbol Icons\n\n")
                append(
                    "Variable symbol icons ship in `miuix-icons-symbol` and are exposed through `MiuixIcons.Symbol`. " +
                        "They support any continuous `wght` in `150f..700f`. " +
                        "Below is the complete list across the five named weight presets:\n\n",
                )
                append(generateTable("Icon Name")).append("\n")
            }
            if (docFilePath != null) {
                updateDocFile(File(docFilePath), content, "### Symbol Icons")
            } else {
                val docFile = File(dest, "icons-list.md")
                docFile.writeText(content)
                println("[iconGen] Generated documentation list into $docFile")
            }
        }

        if (docFilePathZh != null) {
            val content = buildString {
                append("### Symbol（可变符号图标）\n\n")
                append(
                    "可变符号图标位于 `miuix-icons-symbol` 构件中，通过 `MiuixIcons.Symbol` 访问，" +
                        "支持 `150f..700f` 任意连续字重。以下展示五个命名预设字重下的完整列表：\n\n",
                )
                append(generateTable("图标名称")).append("\n")
            }
            updateDocFile(File(docFilePathZh), content, "### Symbol（可变符号图标）")
        }
    }
}

private fun generateSymbolSvgs(
    content: String,
    dest: File,
    light: String,
    dark: String,
    preserveColors: Boolean,
    symbolIconsMap: MutableMap<String, MutableMap<String, String>>,
): Int {
    val name = Regex("""name\s*=\s*"([^"]+)"""").find(content)?.groupValues?.get(1) ?: return 0
    val categoryName = Regex("""category\s*=\s*SymbolCategory\.(\w+)""").find(content)?.groupValues?.get(1) ?: "Core"
    val category = runCatching { SymbolCategory.valueOf(categoryName) }.getOrDefault(SymbolCategory.Core)
    val advanceWidth = Regex("""advanceWidth\s*=\s*(\d+)""").find(content)?.groupValues?.get(1)?.toInt() ?: 1330
    val defaultColorHex = Regex("""defaultColor\s*=\s*Color\(0x([0-9A-Fa-f]{8})\)""").find(content)?.groupValues?.get(1)
    val encodedData = Regex("""encodedData\s*=\s*"([^"]+)"""").find(content)?.groupValues?.get(1) ?: return 0

    val defaultColor = if (defaultColorHex != null) {
        Color(defaultColorHex.toLong(16))
    } else {
        Color.Unspecified
    }
    val symbol = VariableSymbol(
        name = name,
        category = category,
        advanceWidth = advanceWidth,
        defaultColor = defaultColor,
        encodedData = encodedData,
    )

    var generated = 0
    val fillHex = defaultColorHex?.let { "#${it.substring(2)}" }
    val preserveThis = preserveColors || fillHex != null

    for ((weightLabel, weightValue) in SYMBOL_WEIGHT_PRESETS) {
        val iv = symbol.toImageVector(weight = weightValue)
        val paths = iv.root.filterIsInstance<VectorPath>().map { vp ->
            SvgPath(
                d = pathNodesToSvgD(vp.pathData),
                fill = fillHex ?: "#000000",
                fillRule = "nonzero",
            )
        }
        val subDir = File(dest, "symbol/${weightLabel.lowercase()}")
        if (!subDir.exists()) subDir.mkdirs()
        File(subDir, "$name.svg").writeText(
            buildSvg(paths, iv.viewportWidth, iv.viewportHeight, light, dark, preserveThis),
        )
        symbolIconsMap.getOrPut(name) { mutableMapOf() }[weightLabel] = "symbol/${weightLabel.lowercase()}/$name.svg"
        generated++
    }
    return generated
}

private fun pathNodesToSvgD(nodes: List<PathNode>): String {
    val sb = StringBuilder()
    for (node in nodes) {
        when (node) {
            is PathNode.MoveTo -> sb.append("M").append(node.x).append(" ").append(node.y).append(" ")

            is PathNode.LineTo -> sb.append("L").append(node.x).append(" ").append(node.y).append(" ")

            is PathNode.QuadTo -> sb.append("Q").append(node.x1).append(" ").append(node.y1)
                .append(" ").append(node.x2).append(" ").append(node.y2).append(" ")

            PathNode.Close -> sb.append("Z ")

            else -> Unit
        }
    }
    return sb.toString().trim()
}

private fun updateDocFile(docFile: File, newContent: String, startHeader: String) {
    if (!docFile.exists()) {
        println("[iconGen] Warning: Doc file not found at $docFile")
        return
    }
    val content = docFile.readText()
    val startIndex = content.indexOf(startHeader)
    if (startIndex != -1) {
        docFile.writeText(content.substring(0, startIndex) + newContent)
        println("[iconGen] Updated documentation at $docFile")
    } else {
        println("[iconGen] Warning: Could not find header '$startHeader' in $docFile")
    }
}

private fun buildSvg(
    paths: List<SvgPath>,
    vw: Float,
    vh: Float,
    light: String,
    dark: String,
    preserveColors: Boolean,
): String {
    val style = if (!preserveColors) {
        """<style>:root{--icon-color:$light;}@media (prefers-color-scheme: dark){:root{--icon-color:$dark;}}</style>"""
    } else {
        ""
    }
    val pathEls = paths.joinToString("\n") { p ->
        val fill = if (preserveColors) p.fill else "var(--icon-color)"
        """<path d="${p.d}" fill="$fill" fill-rule="${p.fillRule}"/>"""
    }
    return """<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 ${vw.toCleanString()} ${vh.toCleanString()}">$style$pathEls</svg>"""
}

private fun Float.toCleanString(): String {
    val i = toInt()
    return if (this == i.toFloat()) i.toString() else toString()
}
