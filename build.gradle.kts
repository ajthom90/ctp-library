import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform") version "1.3.72"
    kotlin("plugin.serialization") version "1.3.72"
}

group = "dev.ajthom"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven {
        url = uri("https://dl.bintray.com/korlibs/korlibs")
    }
}

val klockVersion = "1.11.14"
val serializationVersion = "0.20.0"

kotlin {
    /* Targets configuration omitted. 
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */

    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    jvm()
    js().browser {
        webpackTask {

        }
    }

    mingwX64 {
//        binaries {
//            framework {
//                baseName = "CovidTrackingWin"
//            }
//        }
    }

    iOSTarget("ios") {
        binaries {
            framework {
                baseName = "CovidTrackingShared"
            }
        }
    }

    macosX64 {
        binaries {
            executable {
                entryPoint = "dev.ajthom.covid.main"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("com.soywiz.korlibs.klock:klock:$klockVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$serializationVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        jvm().compilations["main"].defaultSourceSet {
            dependsOn(commonMain)
            dependencies {
                implementation("com.soywiz.korlibs.klock:klock-jvm:$klockVersion")
                implementation("com.github.kittinunf.fuel:fuel-kotlinx-serialization:2.2.3")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serializationVersion")
            }
        }

        macosX64().compilations["main"].defaultSourceSet {
            dependsOn(commonMain)
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$serializationVersion")
            }
        }

        iOSTarget("ios") {
            compilations["main"].defaultSourceSet {
                dependsOn(commonMain)
                dependencies {
                    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$serializationVersion")
                }
            }
        }

        js().compilations["main"].defaultSourceSet {
            dependsOn(commonMain)
            dependencies {
//                implementation(kotlin("js"))
                implementation("com.soywiz.korlibs.klock:klock-js:$klockVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:$serializationVersion")
            }
        }

        mingwX64().compilations["main"].defaultSourceSet {
            dependsOn(commonMain)
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$serializationVersion")
            }
        }
    }
}

val packForXcode by tasks.creating(Sync::class) {
    val targetDir = File(buildDir, "xcode-frameworks")

    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    println(mode)
    val framework = kotlin.targets.getByName<KotlinNativeTarget>("ios").binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)

    from({ framework.outputDirectory })
    into(targetDir)

    doLast {
        val gradlew = File(targetDir, "gradlew")
        gradlew.writeText("#!/bin/bash\n"
                + "export 'JAVA_HOME=${System.getProperty("java.home")}'\n"
                + "cd '${rootProject.rootDir}'\n"
                + "./gradlew \$@\n")
        gradlew.setExecutable(true)
    }
}

tasks.getByName("build").dependsOn(packForXcode)
