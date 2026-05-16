import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class ModuleSpotlessPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply("com.diffplug.spotless")
        project.extensions.configure<SpotlessExtension> {
            kotlin {
                target("**/*.kt", "**/*.kts")
                ktlint("1.8.0")
                licenseHeaderFile(project.rootProject.file("spotless.license.txt"))
            }
            kotlinGradle {
                target("**/*.gradle.kts")
                ktlint("1.8.0")
            }
        }
    }
}