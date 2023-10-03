package at.allaboutapps.build

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

class ScreenComposePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        require(project.path.startsWith(":screens")) {
            "Plugin only applicable to (compose) :screens"
        }

        project.plugins.all { setupPlugin(this, project) }
    }

    private fun setupPlugin(plugin: Plugin<*>, project: Project) {
        when (plugin) {
            is LibraryPlugin -> {
                project.extensions.getByType<LibraryExtension>().apply {
                    configureAndroidCommonOptions(project)
                }
            }

            is AppPlugin -> {
                project.extensions.getByType<AppExtension>().apply {
                    configureAndroidCommonOptions(project)
                }
            }
        }
    }

    private fun BaseExtension.configureAndroidCommonOptions(project: Project) {
        // val publicSiblingPath = project.parent!!.path + ":public"
        // project.dependencies.project(publicSiblingPath, configuration = "implementation")

        buildFeatures.compose = true

        val catalog = project.rootProject
            .extensions
            .getByType(VersionCatalogsExtension::class)
            .named("libs")
        val composeCompilerVersion = catalog
            .findVersion("compose.compiler")
            .get()
        composeOptions.kotlinCompilerExtensionVersion = composeCompilerVersion.toString()

        project.afterEvaluate {
            val lint = catalog.findLibrary("lint.slack.compose").get()
            project.dependencies.addProvider("lintChecks", lint)

            val composePlatform = project.dependencies.platform(
                catalog.findLibrary("androidx.compose.platform").get(),
            )
            project.dependencies.add("implementation", composePlatform)
            project.dependencies.add(
                "debugImplementation",
                catalog.findLibrary("androidx.compose.ui.tooling").get(),
            )
            project.dependencies.add(
                "implementation",
                catalog.findBundle("screen.compose.impl").get(),
            )

            try {
                project.dependencies.add(
                    "kapt",
                    catalog.findBundle("screen.compose.impl.kapt").get(),
                )
            } catch (ex: Exception) {
                throw IllegalStateException("kapt not found on ${project.path}")
            }
        }
    }
}
