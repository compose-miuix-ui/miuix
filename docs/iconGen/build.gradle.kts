// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(projects.miuix)
}

val iconsSourceDir = rootProject.layout.projectDirectory.dir("miuix/src/commonMain/kotlin/top/yukonga/miuix/kmp/icon").asFile
val extendedIconsSourceDir = rootProject.layout.projectDirectory.dir("miuix-icons/src/commonMain/kotlin/top/yukonga/miuix/kmp/icon").asFile
val outputDir = project.file("../public/icons")
val docFile = project.file("../guide/icons.md")
val docFileZh = project.file("../zh_CN/guide/icons.md")

tasks.register<JavaExec>("generateIcons") {
    group = "iconGen"
    description = "Generate SVGs from Compose ImageVector definitions"
    dependsOn(tasks.named("classes"))
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("top.yukonga.miuix.docs.icongen.MainKt")
    val lightColor = project.findProperty("iconLightColor")?.toString() ?: "#000000"
    val darkColor = project.findProperty("iconDarkColor")?.toString() ?: "#FFFFFF"
    val preserve = project.findProperty("iconPreserveColors")?.toString()?.equals("true", true) == true
    outputs.dir(outputDir)
    doFirst {
        if (!outputDir.exists()) {
            outputDir.mkdirs()
        }
    }
    args = listOf(
        "--src", iconsSourceDir.absolutePath,
        "--src", extendedIconsSourceDir.absolutePath,
        "--out", outputDir.absolutePath,
        "--light", lightColor,
        "--dark", darkColor,
        "--preserve-colors", preserve.toString(),
        "--gen-doc", "true",
        "--doc-file", docFile.absolutePath,
        "--doc-file-zh", docFileZh.absolutePath
    )
}
