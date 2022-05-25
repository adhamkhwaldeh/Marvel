// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
//        jcenter()
        mavenCentral()
//        maven(url = "https://maven.fabric.io/public")
//        maven(url = "http://dl.bintray.com/hedzr/maven")
//        maven(url = "https://maven.google.com")
//        maven(url = "https://dl.bintray.com/kotlin/kotlinx/")
//        mavenLocal()
//        maven(url = uri("https://plugins.gradle.org/m2/"))

//        maven("https://maven.fabric.io/public")
//        maven("http://dl.bintray.com/hedzr/maven")
//        maven("https://maven.google.com")
//        maven("https://dl.bintray.com/kotlin/kotlinx/")
//        mavenLocal()
//        maven(url = uri("https://plugins.gradle.org/m2/"))
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-beta05")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHiltAndroid}")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
//        jcenter()
        mavenCentral()
//        maven(url = "https://maven.fabric.io/public")
//        maven(url = "http://dl.bintray.com/hedzr/maven")
//        maven(url = "https://maven.google.com")
//        maven(url = "https://dl.bintray.com/kotlin/kotlinx/")
//        mavenLocal()
//        maven(url = uri("https://plugins.gradle.org/m2/"))
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

apply("config.gradle")