package at.allaboutapps.build

import com.android.build.api.dsl.ApplicationBaseFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.CompileOptions
import com.android.build.api.dsl.DefaultConfig
import com.android.build.api.dsl.LibraryBaseFlavor
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.TestedExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.plugins.PluginContainer
import org.gradle.api.tasks.Copy
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.hasPlugin
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsSubpluginIndicator
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import java.io.File

class BuildPlugin : Plugin<Project> {

    private companion object {
        const val GIT_WORKTREE_PREFIX = "gitdir: "
        const val INSTALL_KTLINT_GIT_PRE_COMMIT_HOOK_TASK_NAME = "installKtlintGitHook"
    }

    override fun apply(project: Project) {
        project.plugins.all {
            setupPlugin(project, plugin = this@all)
        }
    }

    private fun setupPlugin(project: Project, plugin: Plugin<*>) {
        when (plugin) {
            is JavaPlugin,
            is JavaLibraryPlugin,
            -> {
                setupJavaPlugin(project)
            }

            is KotlinBasePluginWrapper -> {
                setupKotlinPlugin(project)
            }

            is AppPlugin,
            is LibraryPlugin,
            -> {
                setupAndroidPlugin(project, plugin)
            }

            is AndroidExtensionsSubpluginIndicator -> {
                error("Disallowing the 'kotlin-android-extension' plugin. Use ViewBinding instead")
            }
        }
    }

    private fun setupJavaPlugin(project: Project) {
        with(project.extensions.getByType<JavaPluginExtension>()) {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        project.afterEvaluate {
            val javaPluginExtension: JavaPluginExtension = this@afterEvaluate.extensions.getByType()

            val sourceCompatibility: JavaVersion = javaPluginExtension.sourceCompatibility
            check(sourceCompatibility.isJava8) {
                "The Java source compatibility of ${this@afterEvaluate} is set to $sourceCompatibility, " +
                    "but it must be set to ${JavaVersion.VERSION_1_8}. " +
                    "Remove `sourceCompatibility = JavaVersion.${sourceCompatibility.name}` from your Gradle build file"
            }

            val targetCompatibility: JavaVersion = javaPluginExtension.targetCompatibility
            check(targetCompatibility.isJava8) {
                "The Java target compatibility of ${this@afterEvaluate} is set to $targetCompatibility, " +
                    "but it must be set to ${JavaVersion.VERSION_1_8}. " +
                    "Remove `targetCompatibility = JavaVersion.${targetCompatibility.name}` from your Gradle build file"
            }
        }
    }

    private fun setupKotlinPlugin(project: Project) {
        project.tasks.withType<KotlinJvmCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }

        if (project.plugins.hasPlugin<AppPlugin>() || project.plugins.hasPlugin<LibraryPlugin>()) {
            val kotlinOptions: KotlinJvmOptions = (project.extensions.getByType<TestedExtension>() as ExtensionAware)
                .extensions.getByType()

            // the `KotlinJvmOptions`/"kotlinOptions" extension is only available after the Kotlin plugin is applied,
            // which is why we're setting it in this function rather than in `setupAndroidPlugin`
            with(kotlinOptions) {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }

        project.afterEvaluate {
            this@afterEvaluate.tasks.withType<KotlinJvmCompile> {
                val jvmTarget: String? = kotlinOptions.jvmTarget

                checkNotNull(jvmTarget) {
                    "The Kotlin JVM bytecode target of ${this@withType} in ${this@afterEvaluate} is unset, " +
                        "but it must be set to ${JavaVersion.VERSION_1_8}. " +
                        "Remove `jvmTarget = null` from your Gradle build file"
                }

                val jvmTargetVersion: JavaVersion = JavaVersion.toVersion(jvmTarget)
                check(jvmTargetVersion.isJava8) {
                    "The Kotlin JVM bytecode target of ${this@withType} in ${this@afterEvaluate} is set to $jvmTargetVersion, " +
                        "but it must be set to ${JavaVersion.VERSION_1_8}. " +
                        "Remove `jvmTarget = ${jvmTarget.quoted()}` from your Gradle build file"
                }
            }
        }
    }

    private fun setupAndroidPlugin(project: Project, plugin: Plugin<*>) {
        if (plugin is AppPlugin) {
            registerKtlintGitPreCommitHookInstallTask(project)
        }

        with(project.extensions.getByType<TestedExtension>() as CommonExtension<*, *, *, *>) {
            compileSdk = AndroidConfig.COMPILE_SDK

            defaultConfig {
                minSdk = AndroidConfig.DEFAULT_MIN_SDK

                when (this@defaultConfig) {
                    is ApplicationBaseFlavor -> {
                        targetSdk = AndroidConfig.TARGET_SDK

                        versionCode = 1
                        versionName = "1.0"
                    }

                    is LibraryBaseFlavor -> {
                        targetSdk = AndroidConfig.TARGET_SDK
                    }

                    else -> error("Unknown default config type (${this@defaultConfig})")
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            // note that jvmTarget is not set in this function, but in `setupKotlinPlugin` because
            // we need to wait until the Kotlin plugin has been applied

            testOptions {
                unitTests {
                    isReturnDefaultValues = true
                }
            }
        }

        project.afterEvaluate {
            val androidExtension: CommonExtension<*, *, *, *> =
                (this@afterEvaluate.extensions.getByType<TestedExtension>() as CommonExtension<*, *, *, *>)

            // no need to check for null here, for some reason putting `compileSdk = null` in
            // the Gradle build files has no effect
            val compileSdk: Int = androidExtension.compileSdk!!
            check(compileSdk == AndroidConfig.COMPILE_SDK) {
                "The Android compile SDK of ${this@afterEvaluate} is set to API level $compileSdk, " +
                    "but it must be set to API level ${AndroidConfig.COMPILE_SDK}. " +
                    "Remove `compileSdk = $compileSdk` from your Gradle build file"
            }

            val defaultConfig: DefaultConfig = androidExtension.defaultConfig

            val minSdk: Int? = defaultConfig.minSdk
            checkNotNull(minSdk) {
                "The Android minimum SDK of ${this@afterEvaluate} is unset, " +
                    "but it must be set to an API level equals or less than ${AndroidConfig.DEFAULT_MIN_SDK}. " +
                    "Either remove `minSdk = null` from your Gradle build files or change it"
            }
            check(minSdk <= AndroidConfig.DEFAULT_MIN_SDK) {
                "The Android minimum SDK of ${this@afterEvaluate} is set to API level $minSdk, " +
                    "but it must be set to an API level equals or less than ${AndroidConfig.DEFAULT_MIN_SDK}. " +
                    "Either remove `minSdk = $minSdk` from your Gradle build files or change it"
            }

            val targetSdk: Int? =
                when (defaultConfig) {
                    is ApplicationBaseFlavor -> defaultConfig.targetSdk
                    // apparently, setting the targetSdk to a non-null integer in a library has no effect, we'll still
                    // keep this here, firstly because setting null has still an effect and secondly just in case
                    is LibraryBaseFlavor -> defaultConfig.targetSdk
                    else -> error("Could not determine target SDK")
                }
            checkNotNull(targetSdk) {
                "The Android target SDK of ${this@afterEvaluate} is unset, " +
                    "but it must be set to API level ${AndroidConfig.TARGET_SDK}. " +
                    "Remove `targetSdk = null` from your Gradle build file"
            }
            check(targetSdk == AndroidConfig.TARGET_SDK) {
                "The Android target SDK of ${this@afterEvaluate} is set to API level $targetSdk, " +
                    "but it must be set to API level ${AndroidConfig.TARGET_SDK}. " +
                    "Remove `targetSdk = $targetSdk` from your Gradle build file"
            }

            val compileOptions: CompileOptions = androidExtension.compileOptions

            val sourceCompatibility: JavaVersion = compileOptions.sourceCompatibility
            check(sourceCompatibility.isJava8) {
                "The Java source compatibility of ${this@afterEvaluate} is set to $sourceCompatibility, " +
                    "but it must be set to ${JavaVersion.VERSION_1_8}. " +
                    "Remove `sourceCompatibility = JavaVersion.${sourceCompatibility.name}` from your Gradle build file"
            }

            val targetCompatibility: JavaVersion = compileOptions.targetCompatibility
            check(targetCompatibility.isJava8) {
                "The Java target compatibility of ${this@afterEvaluate} is set to $targetCompatibility, " +
                    "but it must be set to ${JavaVersion.VERSION_1_8}. " +
                    "Remove `targetCompatibility = JavaVersion.${targetCompatibility.name}` from your Gradle build file"
            }

            val jvmTarget: String? = (androidExtension as ExtensionAware).extensions
                .getByType<KotlinJvmOptions>()
                .jvmTarget

            checkNotNull(jvmTarget) {
                "The Kotlin JVM bytecode target of ${this@afterEvaluate} is unset, " +
                    "but it must be set to ${JavaVersion.VERSION_1_8}. " +
                    "Remove `jvmTarget = null` from your Gradle build file"
            }

            val jvmTargetVersion: JavaVersion = JavaVersion.toVersion(jvmTarget)
            check(jvmTargetVersion.isJava8) {
                "The Kotlin JVM bytecode target of ${this@afterEvaluate} is set to $jvmTargetVersion, " +
                    "but it must be set to ${JavaVersion.VERSION_1_8}. " +
                    "Remove `jvmTarget = ${jvmTarget.quoted()}` from your Gradle build file"
            }
        }
    }

    /**
     * Registers a task that runs on app pre-build that installs the ktlint Git pre-commit hook to enforce code style on
     * commit.
     */
    private fun registerKtlintGitPreCommitHookInstallTask(project: Project) {
        project.tasks.register(INSTALL_KTLINT_GIT_PRE_COMMIT_HOOK_TASK_NAME, Copy::class.java) {
            val rootProjectDir: File = this@register.project.rootProject.rootDir

            val gitDir: File = getGitDir(rootProjectDir)

            from(rootProjectDir.resolve("hooks/ktlint-git-pre-commit-hook-android.sh"))
            into(gitDir.resolve("hooks"))
            rename { "pre-commit" }
            fileMode = 493 // 493 == 0755 (octal)
        }

        project.tasks.getByName("preBuild")
            .dependsOn(INSTALL_KTLINT_GIT_PRE_COMMIT_HOOK_TASK_NAME)
    }

    /**
     * Returns the proper Git directory from [dir], respecting the `GIT_DIR` environment variable and checking for
     * worktrees.
     */
    private fun getGitDir(dir: File): File {
        val gitDirName: String = System.getenv("GIT_DIR")
            ?.takeIf(String::isNotEmpty)
            ?: ".git"

        val gitDir = File(dir, gitDirName)

        if (gitDir.isDirectory) {
            return gitDir
        }

        check(gitDir.isFile) {
            "Git directory is invalid file type (neither directory nor regular file)"
        }

        // if the git "directory" is actually a regular file, then it is a worktree (see man page git-worktree(1))

        val worktreeGitFileContents: String = gitDir.readText()

        check(worktreeGitFileContents.startsWith(GIT_WORKTREE_PREFIX)) {
            "When the Git \"directory\" is a regular file, the contents of it must start with \"$GIT_WORKTREE_PREFIX\""
        }

        val worktreePrivateSubdirectory: File = worktreeGitFileContents
            .removePrefix(GIT_WORKTREE_PREFIX)
            .removeTrailingNewline()
            .toFile()

        val commonDir: File = worktreePrivateSubdirectory
            .resolve("commondir")
            .readText()
            .removeTrailingNewline()
            .toFile()

        return worktreePrivateSubdirectory
            .resolve(commonDir)
            .normalize()
    }
}

private inline fun <reified T : Plugin<*>> PluginContainer.hasPlugin(): Boolean {
    return this.hasPlugin(T::class)
}

@Suppress("NOTHING_TO_INLINE")
private inline fun String.removeTrailingNewline(): String {
    return this.removeSuffix("\n")
}

@Suppress("NOTHING_TO_INLINE")
private inline fun String.toFile(): File {
    return File(this)
}

private fun String.quoted(): String {
    return "\"" +
        this
            .replace(oldValue = "\\", newValue = "\\\\")
            .replace(oldValue = "\"", newValue = "\\\"") +
        "\""
}
