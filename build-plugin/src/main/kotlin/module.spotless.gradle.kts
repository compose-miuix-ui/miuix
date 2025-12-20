plugins {
    id("com.diffplug.spotless")
}

spotless {
    kotlin {
        target("src/**/*.kt")
        licenseHeaderFile(rootProject.file("spotless/copyright.txt"), "^(?![ \\t]*(?:\\/\\/|\\/\\*)).*[\\w].*$")
    }

    kotlinGradle {
        target("*.kts")
        licenseHeaderFile(rootProject.file("spotless/copyright.txt"), "^(?![ \\t]*(?:\\/\\/|\\/\\*)).*[\\w].*$")
    }
}