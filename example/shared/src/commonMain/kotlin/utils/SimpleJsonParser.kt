// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package utils

data class Libs(
    val libraries: List<Library>,
)

data class Library(
    val uniqueId: String,
    val artifactVersion: String? = null,
    val name: String,
    val description: String? = null,
    val website: String? = null,
    val scm: Scm? = null,
    val licenses: List<String> = emptyList(),
)

data class Scm(
    val url: String? = null,
)

class SimpleJsonParser(private val json: String) {
    private var index = 0

    fun parseLibs(): Libs {
        skipWhitespace()
        if (peek() != '{') throw IllegalArgumentException("Expected '{' at start")
        consume() // '{'

        var libraries: List<Library> = emptyList()

        while (peek() != '}') {
            skipWhitespace()
            val key = parseString()
            skipWhitespace()
            if (consume() != ':') throw IllegalArgumentException("Expected ':'")
            skipWhitespace()

            if (key == "libraries") {
                libraries = parseLibraries()
            } else {
                skipValue()
            }

            skipWhitespace()
            if (peek() == ',') consume()
        }
        consume() // '}'
        return Libs(libraries)
    }

    private fun parseLibraries(): List<Library> {
        if (consume() != '[') throw IllegalArgumentException("Expected '['")
        val list = mutableListOf<Library>()
        while (peek() != ']') {
            skipWhitespace()
            list.add(parseLibrary())
            skipWhitespace()
            if (peek() == ',') consume()
        }
        consume() // ']'
        return list
    }

    private fun parseLibrary(): Library {
        if (consume() != '{') throw IllegalArgumentException("Expected '{'")

        var uniqueId = ""
        var artifactVersion: String? = null
        var name = ""
        var description: String? = null
        var website: String? = null
        var scm: Scm? = null
        var licenses: List<String> = emptyList()

        while (peek() != '}') {
            skipWhitespace()
            val key = parseString()
            skipWhitespace()
            if (consume() != ':') throw IllegalArgumentException("Expected ':'")
            skipWhitespace()

            when (key) {
                "uniqueId" -> uniqueId = parseString()
                "artifactVersion" -> artifactVersion = parseString()
                "name" -> name = parseString()
                "description" -> description = parseString()
                "website" -> website = parseString()
                "scm" -> scm = parseScm()
                "licenses" -> licenses = parseStringList()
                else -> skipValue()
            }

            skipWhitespace()
            if (peek() == ',') consume()
        }
        consume()
        return Library(uniqueId, artifactVersion, name, description, website, scm, licenses)
    }

    private fun parseScm(): Scm? {
        if (peek() == 'n') {
            consume("null")
            return null
        }
        if (consume() != '{') throw IllegalArgumentException("Expected '{' or 'null'")
        var url: String? = null
        while (peek() != '}') {
            skipWhitespace()
            val key = parseString()
            skipWhitespace()
            consume(':')
            skipWhitespace()
            if (key == "url") {
                url = parseString()
            } else {
                skipValue()
            }
            skipWhitespace()
            if (peek() == ',') consume()
        }
        consume()
        return Scm(url)
    }

    private fun parseStringList(): List<String> {
        if (consume() != '[') throw IllegalArgumentException("Expected '['")
        val list = mutableListOf<String>()
        while (peek() != ']') {
            skipWhitespace()
            list.add(parseString())
            skipWhitespace()
            if (peek() == ',') consume()
        }
        consume()
        return list
    }

    private fun parseString(): String {
        if (consume() != '"') throw IllegalArgumentException("Expected '\"'")
        val sb = StringBuilder()
        while (peek() != '"') {
            val c = consume()
            if (c == '\\') {
                when (val escaped = consume()) {
                    '"' -> sb.append('"')

                    '\\' -> sb.append('\\')

                    '/' -> sb.append('/')

                    'b' -> sb.append('\b')

                    'f' -> sb.append('\u000C')

                    'n' -> sb.append('\n')

                    'r' -> sb.append('\r')

                    't' -> sb.append('\t')

                    'u' -> {
                        val hex = StringBuilder()
                        repeat(4) { hex.append(consume()) }
                        sb.append(hex.toString().toInt(16).toChar())
                    }

                    else -> sb.append(escaped)
                }
            } else {
                sb.append(c)
            }
        }
        consume()
        return sb.toString()
    }

    private fun skipValue() {
        val c = peek()
        when {
            c == '"' -> parseString()

            c == '[' -> {
                consume()
                while (peek() != ']') {
                    skipWhitespace()
                    skipValue()
                    skipWhitespace()
                    if (peek() == ',') consume()
                }
                consume()
            }

            c == '{' -> {
                consume()
                while (peek() != '}') {
                    skipWhitespace()
                    parseString()
                    skipWhitespace()
                    consume(':')
                    skipWhitespace()
                    skipValue()
                    skipWhitespace()
                    if (peek() == ',') consume()
                }
                consume()
            }

            c.isDigit() || c == '-' -> {
                while (index < json.length && (json[index].isDigit() || json[index] == '.' || json[index] == 'e' || json[index] == 'E' || json[index] == '-' || json[index] == '+')) {
                    index++
                }
            }

            c == 't' -> consume("true")

            c == 'f' -> consume("false")

            c == 'n' -> consume("null")
        }
    }

    private fun skipWhitespace() {
        while (index < json.length && json[index].isWhitespace()) {
            index++
        }
    }

    private fun peek(): Char = if (index < json.length) json[index] else 0.toChar()

    private fun consume(): Char = if (index < json.length) json[index++] else 0.toChar()

    private fun consume(expected: Char): Char {
        val c = consume()
        if (c != expected) throw IllegalArgumentException("Expected '$expected' but got '$c'")
        return c
    }

    private fun consume(expected: String) {
        for (c in expected) consume(c)
    }
}
