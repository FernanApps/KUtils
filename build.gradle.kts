
plugins {
    id("com.android.library") version "8.2.0" apply false
    id("com.android.application") version "8.2.0" apply false
    kotlin("multiplatform") version "1.9.21" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
    id("org.jetbrains.compose") version "1.5.11" apply false
    id("com.vanniktech.maven.publish") version "0.27.0"
    id("org.jetbrains.kotlin.jvm") version "1.9.21" apply false
}

allprojects {

}

fun isCorrectLib(projectName: String) = projectName.startsWith("lib")


subprojects {
    if (isCorrectLib(project.name)) {
        apply(plugin = "maven-publish")
        apply(plugin = "com.vanniktech.maven.publish")

    }
    afterEvaluate {
        if (isCorrectLib(project.name)) {

            println("Project Name ::: ${project.name}")
            publishing {
                repositories {
                    maven("https://maven.pkg.github.com/FernanApps/KUtils") {
                        name = "GitHubPackages"
                        credentials(PasswordCredentials::class) {
                            username = System.getenv("GITHUB_ACTOR")
                            password = System.getenv("GITHUB_TOKEN")
                        }
                        //credentials(PasswordCredentials::class)
                    }
                }
            }

            //  .\gradlew generateGradleProperties --no-configuration-cache
            tasks.register("generateGradleProperties") {
                doLast {
                    val fileName = "gradle.properties"
                    val nameLib = project.name
                    // Fix
                    val content = "POM_ARTIFACT_ID=${nameLib.lowercase()}"
                    val projectDir = project.projectDir
                    val filePath = projectDir.resolve(fileName)
                    if (filePath.exists()) {
                        filePath.delete()
                    }
                    filePath.createNewFile()
                    filePath.writeText(content)
                    println("File :  ${filePath.absolutePath} in ${project.name}")
                }

            }

        }

    }
}


