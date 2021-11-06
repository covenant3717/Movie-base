import org.gradle.api.Action
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.plugins.ExtensionAware

/*
fun DependencyHandler.implementation(dependency: String) =
    add("implementation", dependency)

fun DependencyHandler.implementation(projectDependency: ProjectDependency) =
    add("implementation", projectDependency)

fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

fun DependencyHandler.releaseImplementation(dependencyNotation: Any): Dependency? =
    add("releaseImplementation", dependencyNotation)
*/

fun BaseAppModuleExtension.kotlinOptions(configure: Action<KotlinJvmOptions>) =
    (this as ExtensionAware).extensions.configure("kotlinOptions", configure)