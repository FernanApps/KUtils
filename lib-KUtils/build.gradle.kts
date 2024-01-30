import de.undercouch.gradle.tasks.download.Download
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("de.undercouch.download").version("3.4.3")
}

kotlin {
    applyDefaultHierarchyTemplate()

    jvm {
        compilations.all {
            compilerOptions.configure {
                jvmTarget.set(JvmTarget.JVM_1_8)
            }
        }
    }

    androidTarget {
        publishLibraryVariants("release")

        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "lib"
            isStatic = true
        }
    }

    js(IR) {
        browser()
    }

    configure(targets) {
        if (this is KotlinNativeTarget && konanTarget.family.isAppleFamily) {
            compilations.getByName("main") {
                val objc by cinterops.creating {
                    defFile(project.file("src/iosMain/def/objc.def"))
                }
            }
        }
    }


    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.ui)
            }
        }
        val androidMain by getting {
            dependencies {

            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("com.willowtreeapps.assertk:assertk:0.28.0")
            }
        }
    }
}

android {
    namespace = "androidx.compose.material3.windowsizeclass"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
}

task<DefaultTask>("my-download-task") {
    val url = "https://www.africau.edu/images/default/sample.pdf"
    val dest = File("...")
    task<Download>("download-task") {
        src(url)
        dest(dest)
    }
    dependsOn("download-task")
}

val kase64Name = "Kase64"
val kase64Zip = "${kase64Name}.zip"


val pathModule = project.projectDir
val pathKase64Zip: String = File(pathModule, kase64Zip).absolutePath
val pathKase64LibExternal =  File(pathModule,"$kase64Name/Kase64-main/kase64/src/commonMain/kotlin/saschpe").absolutePath

// \Kase64\Kase64-main\kase64\src\commonMain\kotlin\saschpe
tasks.register("downloadKase64"){
    val sourceUrl = "https://codeload.github.com/saschpe/Kase64/zip/refs/heads/main"
    download(sourceUrl, pathKase64Zip)
}


tasks.register<Copy>("downloadAndUnZipKase64") {
    dependsOn("downloadKase64")
    from(zipTree(pathKase64Zip))
    into("$pathModule/$kase64Name")
}

tasks.register<Copy>("aaaaaaaaaaaaaaaaaaaaaaaa") {
    dependsOn("downloadAndUnZipKase64")
    from(pathKase64LibExternal)
    into("$pathModule/other")
}


fun download(url : String, path : String){
    val destFile = File(path)
    ant.invokeMethod("get", mapOf("src" to url, "dest" to destFile))
}