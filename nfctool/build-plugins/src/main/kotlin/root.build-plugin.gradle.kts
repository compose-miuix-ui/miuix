import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class RootBuildPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply("module.spotless")
    }
}