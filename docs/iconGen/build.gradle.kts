// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(projects.miuixUi)
}

val iconsSourceDir =
    rootProject.layout.projectDirectory
        .dir("miuix-ui/src/commonMain/kotlin/top/yukonga/miuix/kmp/icon")
        .asFile
val extendedIconsSourceDir =
    rootProject.layout.projectDirectory
        .dir("miuix-icons/src/commonMain/kotlin/top/yukonga/miuix/kmp/icon")
        .asFile
val outputDir = project.file("../public/icons")
val docFile = project.file("../guide/icons.md")
val docFileZh = project.file("../zh_CN/guide/icons.md")
val mainClasspath = objects.fileCollection().from(sourceSets.main.map { it.runtimeClasspath })
val lightColor = providers.gradleProperty("iconLightColor").getOrElse("#000000")
val darkColor = providers.gradleProperty("iconDarkColor").getOrElse("#FFFFFF")
val preserve = providers.gradleProperty("iconPreserveColors").map { it.equals("true", true) }.getOrElse(false)

tasks.register<JavaExec>("generateIcons") {
    group = "iconGen"
    description = "Generate SVGs from Compose ImageVector definitions"
    dependsOn(tasks.named("classes"))
    classpath = mainClasspath
    mainClass.set("top.yukonga.miuix.docs.icongen.MainKt")
    outputs.dir(outputDir)
    args =
        listOf(
            "--src",
            iconsSourceDir.absolutePath,
            "--src",
            extendedIconsSourceDir.absolutePath,
            "--out",
            outputDir.absolutePath,
            "--light",
            lightColor,
            "--dark",
            darkColor,
            "--preserve-colors",
            preserve.toString(),
            "--gen-doc",
            "true",
            "--doc-file",
            docFile.absolutePath,
            "--doc-file-zh",
            docFileZh.absolutePath,
        )
}
