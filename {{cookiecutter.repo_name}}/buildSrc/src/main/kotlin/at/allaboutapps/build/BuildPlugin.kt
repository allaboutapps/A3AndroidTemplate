package at.allaboutapps.build

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.TestedExtension
import org.gradle.api.JavaVersion
import org.gradle.api.JavaVersion.VERSION_11
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.Copy
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePluginWrapper
import java.io.File

class BuildPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.plugins.all { setupPlugin(this, project) }
    }

    private fun setupPlugin(plugin: Plugin<*>, project: Project) {
        when (plugin) {
            is JavaPlugin,
            is JavaLibraryPlugin,
            -> {
                val javaPluginExtension = project.extensions.getByType<JavaPluginExtension>()
                javaPluginExtension.apply {
                    sourceCompatibility = VERSION_11
                    targetCompatibility = VERSION_11
                }
                project.afterEvaluate {
                    verifyJava11Targeting(javaPluginExtension.sourceCompatibility)
                }
            }
            is LibraryPlugin -> {
                project.extensions.getByType<LibraryExtension>().apply {
                    configureAndroidCommonOptions(project)
                }
            }
            is AppPlugin -> {
                registerKtLintCheck(project)
                project.extensions.getByType<AppExtension>().apply {
                    configureAndroidCommonOptions(project)
                    configureAndroidApplicationOptions(project)
                }
            }
            is KotlinBasePluginWrapper -> {
                project.tasks.withType<KotlinJvmCompile> {
                    kotlinOptions {
                        jvmTarget = VERSION_11.toString()
                    }
                }
            }
        }
    }

    /**
     * Register the pre-commit hook for ktlint to enforce code style on commit.
     */
    private fun registerKtLintCheck(project: Project) {
        project.tasks.register("installKtlintGitHook", Copy::class.java) {
            val gitDirName = System.getenv("GIT_DIR")
                ?.takeIf(String::isNotEmpty)
                ?: ".git"

            var gitDir = File(project.rootProject.rootDir, gitDirName)

            if (gitDir.isFile) {
                // is a worktree
                gitDir = File(gitDir.readText().removePrefix("gitdir: ").trim(), "../..").normalize()
            }

            from(File(project.rootProject.rootDir, "hooks/ktlint-git-pre-commit-hook-android.sh"))
            into(File(gitDir, "hooks"))
            rename { "pre-commit" }
            fileMode = 493 // 493 == 0755 (octal)
        }

        project.tasks.findByName("preBuild")?.dependsOn("installKtlintGitHook")
    }

    private fun TestedExtension.configureAndroidCommonOptions(project: Project) {
        compileOptions.apply {
            sourceCompatibility = VERSION_11
            targetCompatibility = VERSION_11
        }

        compileSdkVersion(COMPILE_SDK_VERSION)
        defaultConfig.targetSdk = TARGET_SDK_VERSION
        defaultConfig.minSdk = DEFAULT_MIN_SDK_VERSION

        testOptions.unitTests.isReturnDefaultValues = true

        project.afterEvaluate {
            val minSdkVersion = defaultConfig.minSdkVersion!!.apiLevel
            check(minSdkVersion >= DEFAULT_MIN_SDK_VERSION) {
                "minSdkVersion $minSdkVersion bigger than the default of $DEFAULT_MIN_SDK_VERSION"
            }
            verifyJava11Targeting(compileOptions.sourceCompatibility)
        }
    }

    private fun verifyJava11Targeting(javaVersion: JavaVersion) {
        check(javaVersion == VERSION_11) {
            "You're targeting Java $javaVersion. Please remove `sourceCompatibility = $javaVersion` from build.gradle"
        }
    }

    private fun AppExtension.configureAndroidApplicationOptions(project: Project) {
        defaultConfig.apply {
            versionCode = 1
            versionName = "1.0"
        }
    }

    companion object {
        const val COMPILE_SDK_VERSION = 34
        const val TARGET_SDK_VERSION = 34
        const val DEFAULT_MIN_SDK_VERSION = 26
    }
}
