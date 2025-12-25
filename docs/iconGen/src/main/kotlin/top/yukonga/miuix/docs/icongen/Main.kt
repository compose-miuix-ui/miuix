// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.docs.icongen

import java.io.File

data class SvgPath(
    val d: String,
    val fill: String? = null,
    val fillAlpha: Float = 1.0f,
    val fillRule: String = "nonzero",
    val stroke: String? = null,
    val strokeAlpha: Float = 1.0f,
    val strokeWidth: Float = 0.0f,
    val transform: String = ""
)

/** Simple CLI entry that converts Compose vector icon definitions to SVG files. */
fun main(args: Array<String>) {
    val map = mutableMapOf<String, String>()
    val srcDirs = mutableListOf<String>()
    var i = 0
    while (i < args.size) {
        val a = args[i]
        if (a == "--src") {
            val next = args.getOrNull(i + 1)
            if (next != null && !next.startsWith("--")) {
                srcDirs.add(next)
                i += 2
            } else {
                error("--src <dir> required")
            }
        } else if (a.startsWith("--")) {
            val next = args.getOrNull(i + 1)
            if (next != null && !next.startsWith("--")) {
                map[a] = next; i += 2
            } else {
                map[a] = "true"; i += 1
            }
        } else i++
    }
    if (srcDirs.isEmpty()) error("--src <dir> required")
    val outDir = map["--out"] ?: error("--out <dir> required")
    val light = map["--light"] ?: "#000000"
    val dark = map["--dark"] ?: "#FFFFFF"
    val preserveColors = map["--preserve-colors"]?.equals("true", true) == true
    val genDoc = map["--gen-doc"]?.equals("true", true) == true
    val docFilePath = map["--doc-file"]
    val docFilePathZh = map["--doc-file-zh"]

    val dest = File(outDir)
    if (!dest.exists()) dest.mkdirs()

    var count = 0
    // Map of BaseName -> Map<Style, RelativePath>
    val basicIconsMap = mutableMapOf<String, MutableMap<String, String>>()
    val extendedIconsMap = mutableMapOf<String, MutableMap<String, String>>()

    srcDirs.forEach { srcDir ->
        val src = File(srcDir)
        if (!src.isDirectory) error("Source directory not found: $src")

        val ktFiles =
            src.walkTopDown().filter { it.isFile && it.extension == "kt" && it.name != "MiuixIcon.kt" && it.name != "AllIcons.kt" }

        ktFiles.forEach { file ->
            val content = file.readText()

            // Regex to find ImageVector.Builder blocks and extract name, viewport, and pathData
            val builderRegex = Regex("ImageVector\\.Builder\\(([\\s\\S]*?)\\)\\.apply\\s*\\{([\\s\\S]*?)\\}\\.build\\(\\)")

            builderRegex.findAll(content).forEach { match ->
                val builderParams = match.groupValues[1]
                val applyBlock = match.groupValues[2]

                var iconName = file.nameWithoutExtension
                var viewportWidth = 24f
                var viewportHeight = 24f

                // Parse Builder Params
                if (builderParams.contains("name =")) {
                    val nameRegex = Regex("name\\s*=\\s*\"([^\"]+)\"")
                    iconName = nameRegex.find(builderParams)?.groupValues?.getOrNull(1) ?: iconName

                    val viewportWRegex = Regex("viewportWidth\\s*=\\s*([0-9.]+)f")
                    val viewportHRegex = Regex("viewportHeight\\s*=\\s*([0-9.]+)f")
                    viewportWidth = viewportWRegex.find(builderParams)?.groupValues?.getOrNull(1)?.toFloatOrNull() ?: 24f
                    viewportHeight = viewportHRegex.find(builderParams)?.groupValues?.getOrNull(1)?.toFloatOrNull() ?: 24f
                } else {
                    // Positional args: "Name", w.dp, h.dp, vw, vh
                    val posRegex =
                        Regex("\"([^\"]+)\"\\s*,\\s*([0-9.]+)f?\\.dp\\s*,\\s*([0-9.]+)f?\\.dp\\s*,\\s*([0-9.]+)f\\s*,\\s*([0-9.]+)f")
                    val posMatch = posRegex.find(builderParams)
                    if (posMatch != null) {
                        iconName = posMatch.groupValues[1]
                        viewportWidth = posMatch.groupValues[4].toFloatOrNull() ?: 24f
                        viewportHeight = posMatch.groupValues[5].toFloatOrNull() ?: 24f
                    }
                }

                var scaleX: Float
                var scaleY: Float
                var translationX: Float
                var translationY: Float
                var groupTransform = ""

                val groupRegex = Regex("group\\(([^)]+)\\)")
                val groupMatch = groupRegex.find(applyBlock)
                if (groupMatch != null) {
                    val params = groupMatch.groupValues[1]
                    scaleX = Regex("scaleX\\s*=\\s*([0-9.-]+)f").find(params)?.groupValues?.get(1)?.toFloatOrNull() ?: 1.0f
                    scaleY = Regex("scaleY\\s*=\\s*([0-9.-]+)f").find(params)?.groupValues?.get(1)?.toFloatOrNull() ?: 1.0f
                    translationX = Regex("translationX\\s*=\\s*([0-9.-]+)f").find(params)?.groupValues?.get(1)?.toFloatOrNull() ?: 0.0f
                    translationY = Regex("translationY\\s*=\\s*([0-9.-]+)f").find(params)?.groupValues?.get(1)?.toFloatOrNull() ?: 0.0f

                    if (scaleX != 1.0f || scaleY != 1.0f || translationX != 0.0f || translationY != 0.0f) {
                        groupTransform = " transform=\"matrix($scaleX 0 0 $scaleY $translationX $translationY)\""
                    }
                }

                val paths = mutableListOf<SvgPath>()

                // Helper functions for attribute parsing
                fun parseFill(text: String): String? {
                    val fillRegex = Regex("fill\\s*=\\s*SolidColor\\(Color\\.([a-zA-Z]+)\\)")
                    val match = fillRegex.find(text)
                    return when (match?.groupValues?.getOrNull(1)) {
                        "Black" -> "#000000"
                        "White" -> "#FFFFFF"
                        "Transparent" -> "none"
                        else -> null
                    }
                }

                fun parseStroke(text: String): String? {
                    val regex = Regex("stroke\\s*=\\s*SolidColor\\(Color\\.([a-zA-Z]+)\\)")
                    val match = regex.find(text)
                    return when (match?.groupValues?.getOrNull(1)) {
                        "Black" -> "#000000"
                        "White" -> "#FFFFFF"
                        "Transparent" -> "none"
                        else -> null
                    }
                }

                fun parseAlpha(text: String, key: String): Float {
                    val regex = Regex("$key\\s*=\\s*([0-9.]+)f")
                    return regex.find(text)?.groupValues?.get(1)?.toFloatOrNull() ?: 1.0f
                }

                fun parseFloat(text: String, key: String): Float {
                    val regex = Regex("$key\\s*=\\s*([0-9.]+)f")
                    return regex.find(text)?.groupValues?.get(1)?.toFloatOrNull() ?: 0.0f
                }

                fun parseFillRule(text: String): String {
                    val regex = Regex("pathFillType\\s*=\\s*PathFillType\\.([a-zA-Z]+)")
                    return if (regex.find(text)?.groupValues?.getOrNull(1) == "EvenOdd") "evenodd" else "nonzero"
                }

                // 1. Find DSL paths: path(params) { body }
                val pathDslRegex = Regex("path\\(([\\s\\S]*?)\\)\\s*\\{")
                pathDslRegex.findAll(applyBlock).forEach { match ->
                    val params = match.groupValues[1]
                    val blockStart = match.range.last + 1

                    // Extract block body
                    var count = 1
                    var currentIndex = blockStart
                    while (currentIndex < applyBlock.length && count > 0) {
                        val char = applyBlock[currentIndex]
                        if (char == '{') count++
                        else if (char == '}') count--
                        currentIndex++
                    }

                    if (count == 0) {
                        val body = applyBlock.substring(blockStart, currentIndex - 1)
                        val d = parseDslPath(body)

                        paths.add(
                            SvgPath(
                                d = d,
                                fill = parseFill(params),
                                fillAlpha = parseAlpha(params, "fillAlpha"),
                                fillRule = parseFillRule(params),
                                stroke = parseStroke(params),
                                strokeAlpha = parseAlpha(params, "strokeAlpha"),
                                strokeWidth = parseFloat(params, "strokeLineWidth"),
                                transform = groupTransform
                            )
                        )
                    }
                }

                // 2. Find Vector paths: addPath(params containing pathData)
                val addPathRegex = Regex("addPath\\s*\\(")
                addPathRegex.findAll(applyBlock).forEach { match ->
                    val start = match.range.last
                    var count = 1
                    var currentIndex = start + 1
                    while (currentIndex < applyBlock.length && count > 0) {
                        val char = applyBlock[currentIndex]
                        if (char == '(') count++
                        else if (char == ')') count--
                        currentIndex++
                    }

                    if (count == 0) {
                        val argsContent = applyBlock.substring(start + 1, currentIndex - 1)
                        // Extract pathData list
                        val listStartIdx = argsContent.indexOf("listOf(")
                        if (listStartIdx != -1) {
                            // extract list content
                            var lCount = 1
                            var lIndex = listStartIdx + 7 // after "listOf("
                            while (lIndex < argsContent.length && lCount > 0) {
                                val char = argsContent[lIndex]
                                if (char == '(') lCount++
                                else if (char == ')') lCount--
                                lIndex++
                            }
                            if (lCount == 0) {
                                val pathDataContent = argsContent.substring(listStartIdx + 7, lIndex - 1)
                                val d = parsePathNodes(pathDataContent)
                                paths.add(
                                    SvgPath(
                                        d = d,
                                        fill = parseFill(argsContent),
                                        fillAlpha = parseAlpha(argsContent, "fillAlpha"),
                                        fillRule = parseFillRule(argsContent),
                                        stroke = parseStroke(argsContent),
                                        strokeAlpha = parseAlpha(argsContent, "strokeAlpha"),
                                        strokeWidth = parseFloat(argsContent, "strokeLineWidth"),
                                        transform = groupTransform
                                    )
                                )
                            }
                        }
                    }
                }

                if (paths.isNotEmpty()) {
                    val svg = buildSvg(paths, viewportWidth, viewportHeight, light, dark, preserveColors)

                    val relativeDir = file.relativeTo(src).parentFile
                    val outSubDir = File(dest, relativeDir.path)
                    outSubDir.mkdirs()

                    File(outSubDir, "$iconName.svg").writeText(svg)
                    count++

                    val relativePath = File(relativeDir, "$iconName.svg").path.replace("\\", "/")

                    val isBasic =
                        file.path.contains("basic${File.separator}") || file.path.contains("basic/") || file.path.contains("basic\\")
                    val targetMap = if (isBasic) basicIconsMap else extendedIconsMap

                    if (iconName.endsWith(".Regular")) {
                        val simpleName = iconName.removeSuffix(".Regular")
                        targetMap.getOrPut(simpleName) { mutableMapOf() }["Regular"] = relativePath
                    } else if (iconName.endsWith(".Light")) {
                        val simpleName = iconName.removeSuffix(".Light")
                        targetMap.getOrPut(simpleName) { mutableMapOf() }["Light"] = relativePath
                    } else if (iconName.endsWith(".Heavy")) {
                        val simpleName = iconName.removeSuffix(".Heavy")
                        targetMap.getOrPut(simpleName) { mutableMapOf() }["Heavy"] = relativePath
                    } else if (!iconName.contains(".")) {
                        targetMap.getOrPut(iconName) { mutableMapOf() }["Regular"] = relativePath
                    }
                }
            }
        }
    }

    println("[iconGen] Generated $count SVG(s) into $dest")
    if (genDoc && (basicIconsMap.isNotEmpty() || extendedIconsMap.isNotEmpty())) {

        fun generateTable(map: Map<String, Map<String, String>>, header: String): String {
            val sb = StringBuilder()
            sb.append(header).append("\n")
            sb.append("|---|---|---|---|\n")
            map.keys.sorted().forEach { name ->
                val variants = map[name]!!
                val light = variants["Light"]?.let { "<img src=\"/icons/$it\" width=\"24\" height=\"24\" />" } ?: "-"
                val regular = variants["Regular"]?.let { "<img src=\"/icons/$it\" width=\"24\" height=\"24\" />" } ?: "-"
                val heavy = variants["Heavy"]?.let { "<img src=\"/icons/$it\" width=\"24\" height=\"24\" />" } ?: "-"
                sb.append("| `$name`` | $light | $regular | $heavy |\n")
            }
            return sb.toString()
        }

        // Generate English Doc
        if (docFilePath != null || docFilePathZh == null) {
            val basicTable = generateTable(basicIconsMap, "| Icon Name | Light | Regular | Heavy |")
            val extendedTable = generateTable(extendedIconsMap, "| Icon Name | Light | Regular | Heavy |")

            val content = StringBuilder()
            content.append("### Basic Icons\n\n")
            content.append("Basic icons include commonly used basic UI elements such as arrows and checkmarks. These icons are also used in Miuix's own components. Below is the complete list:\n\n")
            content.append(basicTable).append("\n")
            content.append("### Extended Icons\n\n")
            content.append("Extended icons include a wide variety of icons for different use cases. Below is the complete list:\n\n")
            content.append(extendedTable).append("\n")

            if (docFilePath != null) {
                updateDocFile(File(docFilePath), content.toString(), "### Basic Icons")
            } else {
                val docFile = File(dest, "icons-list.md")
                docFile.writeText(content.toString())
                println("[iconGen] Generated documentation list into $docFile")
            }
        }

        // Generate Chinese Doc
        if (docFilePathZh != null) {
            val basicTable = generateTable(basicIconsMap, "| 图标名称 | Light | Regular | Heavy |")
            val extendedTable = generateTable(extendedIconsMap, "| 图标名称 | Light | Regular | Heavy |")

            val content = StringBuilder()
            content.append("### Basic（基础图标）\n\n")
            content.append("基础图标包含一些常用的基本界面元素，如箭头、勾选等，这些图标在 Miuix 本身的组件中也会使用到。以下是完整的列表：\n\n")
            content.append(basicTable).append("\n")
            content.append("### Extended (扩展图标)\n\n")
            content.append("扩展图标包含更多场景下的图标。以下是完整的列表：\n\n")
            content.append(extendedTable).append("\n")

            updateDocFile(File(docFilePathZh), content.toString(), "### Basic（基础图标）")
        }
    }
}

fun updateDocFile(docFile: File, newContent: String, startHeader: String) {
    if (docFile.exists()) {
        val content = docFile.readText()
        val startIndex = content.indexOf(startHeader)
        val finalContent = if (startIndex != -1) {
            content.take(startIndex) + newContent
        } else {
            content + "\n\n" + newContent
        }
        docFile.writeText(finalContent)
        println("[iconGen] Updated documentation at $docFile")
    } else {
        println("[iconGen] Documentation file not found at $docFile, creating new one.")
        docFile.writeText(newContent)
    }
}

fun parsePathNodes(content: String): String {
    val sb = StringBuilder()
    val nodeRegex = Regex("PathNode\\.([a-zA-Z]+)(?:\\(([^)]*)\\))?")
    nodeRegex.findAll(content).forEach { match ->
        val type = match.groupValues[1]
        val argsStr = match.groupValues[2]
        val args = argsStr.split(",").mapNotNull { it.trim().removeSuffix("f").toFloatOrNull() }
        when (type) {
            "MoveTo" -> if (args.size >= 2) sb.append("M${args[0]} ${args[1]} ")
            "RelativeMoveTo" -> if (args.size >= 2) sb.append("m${args[0]} ${args[1]} ")
            "LineTo" -> if (args.size >= 2) sb.append("L${args[0]} ${args[1]} ")
            "RelativeLineTo" -> if (args.size >= 2) sb.append("l${args[0]} ${args[1]} ")
            "HorizontalTo" -> if (args.isNotEmpty()) sb.append("H${args[0]} ")
            "RelativeHorizontalTo" -> if (args.isNotEmpty()) sb.append("h${args[0]} ")
            "VerticalTo" -> if (args.isNotEmpty()) sb.append("V${args[0]} ")
            "RelativeVerticalTo" -> if (args.isNotEmpty()) sb.append("v${args[0]} ")
            "QuadTo" -> if (args.size >= 4) sb.append("Q${args[0]} ${args[1]} ${args[2]} ${args[3]} ")
            "RelativeQuadTo" -> if (args.size >= 4) sb.append("q${args[0]} ${args[1]} ${args[2]} ${args[3]} ")
            "CurveTo" -> if (args.size >= 6) sb.append("C${args[0]} ${args[1]} ${args[2]} ${args[3]} ${args[4]} ${args[5]} ")
            "RelativeCurveTo" -> if (args.size >= 6) sb.append("c${args[0]} ${args[1]} ${args[2]} ${args[3]} ${args[4]} ${args[5]} ")
            "ReflectiveQuadTo" -> if (args.size >= 2) sb.append("T${args[0]} ${args[1]} ")
            "RelativeReflectiveQuadTo" -> if (args.size >= 2) sb.append("t${args[0]} ${args[1]} ")
            "ReflectiveCurveTo" -> if (args.size >= 4) sb.append("S${args[0]} ${args[1]} ${args[2]} ${args[3]} ")
            "RelativeReflectiveCurveTo" -> if (args.size >= 4) sb.append("s${args[0]} ${args[1]} ${args[2]} ${args[3]} ")
            "Close" -> sb.append("Z ")
        }
    }
    return sb.toString().trim()
}

fun parseDslPath(content: String): String {
    val sb = StringBuilder()
    val moveRegex = Regex("moveTo\\((.*?)f, (.*?)f\\)")
    val lineToRegex = Regex("lineTo\\((.*?)f, (.*?)f\\)")
    val curveToRegex = Regex("curveTo\\((.*?)f, (.*?)f, (.*?)f, (.*?)f, (.*?)f, (.*?)f\\)")
    val moveRelRegex = Regex("moveToRelative\\((.*?)f, (.*?)f\\)")
    val lineRelRegex = Regex("lineToRelative\\((.*?)f, (.*?)f\\)")
    val curveRelRegex = Regex("curveToRelative\\((.*?)f, (.*?)f, (.*?)f, (.*?)f, (.*?)f, (.*?)f\\)")
    val hLineRegex = Regex("horizontalLineTo\\((.*?)f\\)")
    val vLineRegex = Regex("verticalLineTo\\((.*?)f\\)")
    val hLineRelRegex = Regex("horizontalLineToRelative\\((.*?)f\\)")
    val vLineRelRegex = Regex("verticalLineToRelative\\((.*?)f\\)")
    val quadRegex = Regex("quadTo\\((.*?)f, (.*?)f, (.*?)f, (.*?)f\\)")
    val quadRelRegex = Regex("quadToRelative\\((.*?)f, (.*?)f, (.*?)f, (.*?)f\\)")
    val rCurveRegex = Regex("reflectiveCurveTo\\((.*?)f, (.*?)f, (.*?)f, (.*?)f\\)")
    val rCurveRelRegex = Regex("reflectiveCurveToRelative\\((.*?)f, (.*?)f, (.*?)f, (.*?)f\\)")
    val rQuadRegex = Regex("reflectiveQuadTo\\((.*?)f, (.*?)f\\)")
    val rQuadRelRegex = Regex("reflectiveQuadToRelative\\((.*?)f, (.*?)f\\)")
    val closeRegex = Regex("close\\(\\)")

    content.lineSequence().forEach { line ->
        moveRegex.findAll(line).forEach { r -> sb.append("M${r.groupValues[1]} ${r.groupValues[2]} ") }
        lineToRegex.findAll(line).forEach { r -> sb.append("L${r.groupValues[1]} ${r.groupValues[2]} ") }
        curveToRegex.findAll(line)
            .forEach { r -> sb.append("C${r.groupValues[1]} ${r.groupValues[2]} ${r.groupValues[3]} ${r.groupValues[4]} ${r.groupValues[5]} ${r.groupValues[6]} ") }
        moveRelRegex.findAll(line).forEach { r -> sb.append("m${r.groupValues[1]} ${r.groupValues[2]} ") }
        lineRelRegex.findAll(line).forEach { r -> sb.append("l${r.groupValues[1]} ${r.groupValues[2]} ") }
        curveRelRegex.findAll(line)
            .forEach { r -> sb.append("c${r.groupValues[1]} ${r.groupValues[2]} ${r.groupValues[3]} ${r.groupValues[4]} ${r.groupValues[5]} ${r.groupValues[6]} ") }
        hLineRegex.findAll(line).forEach { r -> sb.append("H${r.groupValues[1]} ") }
        vLineRegex.findAll(line).forEach { r -> sb.append("V${r.groupValues[1]} ") }
        hLineRelRegex.findAll(line).forEach { r -> sb.append("h${r.groupValues[1]} ") }
        vLineRelRegex.findAll(line).forEach { r -> sb.append("v${r.groupValues[1]} ") }
        quadRegex.findAll(line)
            .forEach { r -> sb.append("Q${r.groupValues[1]} ${r.groupValues[2]} ${r.groupValues[3]} ${r.groupValues[4]} ") }
        quadRelRegex.findAll(line)
            .forEach { r -> sb.append("q${r.groupValues[1]} ${r.groupValues[2]} ${r.groupValues[3]} ${r.groupValues[4]} ") }
        rCurveRegex.findAll(line)
            .forEach { r -> sb.append("S${r.groupValues[1]} ${r.groupValues[2]} ${r.groupValues[3]} ${r.groupValues[4]} ") }
        rCurveRelRegex.findAll(line)
            .forEach { r -> sb.append("s${r.groupValues[1]} ${r.groupValues[2]} ${r.groupValues[3]} ${r.groupValues[4]} ") }
        rQuadRegex.findAll(line).forEach { r -> sb.append("T${r.groupValues[1]} ${r.groupValues[2]} ") }
        rQuadRelRegex.findAll(line).forEach { r -> sb.append("t${r.groupValues[1]} ${r.groupValues[2]} ") }
        if (closeRegex.containsMatchIn(line)) sb.append("Z ")
    }
    return sb.toString().trim()
}

fun buildSvg(paths: List<SvgPath>, w: Float, h: Float, light: String, dark: String, preserve: Boolean): String {
    fun num(v: Float): String = if (v % 1f == 0f) v.toInt().toString() else v.toString().trimEnd('0').trimEnd('.')

    return buildString {
        appendLine("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
        appendLine("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 ${num(w)} ${num(h)}\" fill=\"none\" stroke=\"none\">")
        appendLine("  <style>\n    :root{color-scheme:light dark;}\n    svg{color:$light;}\n    @media (prefers-color-scheme: dark){svg{color:$dark;}}\n  </style>")

        paths.forEach { p ->
            val fillValue = if (preserve) {
                p.fill ?: "currentColor"
            } else {
                if (p.fill == "#000000" || p.fill == "#FFFFFF") "currentColor" else (p.fill ?: "currentColor")
            }

            val opacityAttr = if (p.fillAlpha < 1.0f) " fill-opacity=\"${p.fillAlpha}\"" else ""

            val strokeAttr = if (p.stroke != null) {
                val sVal = if (p.stroke == "#000000" || p.stroke == "#FFFFFF") "currentColor" else p.stroke
                " stroke=\"$sVal\""
            } else ""

            val strokeAlphaAttr = if (p.strokeAlpha < 1.0f) " stroke-opacity=\"${p.strokeAlpha}\"" else ""
            val strokeWidthAttr = if (p.strokeWidth > 0f) " stroke-width=\"${p.strokeWidth}\"" else ""

            appendLine("  <path d=\"${p.d}\" fill=\"$fillValue\"$opacityAttr$strokeAttr$strokeAlphaAttr$strokeWidthAttr${p.transform} fill-rule=\"${p.fillRule}\" clip-rule=\"${p.fillRule}\" />")
        }
        appendLine("</svg>")
    }
}
