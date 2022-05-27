plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}


android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = "com.aljawad.sons.marvel"
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.compileSdk)
        versionCode = AppConfig.versionCode
//        versionCode(AppConfig.versionCode)
        versionName = AppConfig.versionName
//        versionName(AppConfig.versionName)
        multiDexEnabled = true

        //TODO: remove this after migration to Hilt
        javaCompileOptions.annotationProcessorOptions.arguments["dagger.hilt.disableModulesHaveInstallInCheck"] =
            "true"


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

//        getByName("debug") {
//            applicationIdSuffix(".debug")
//        }
//
//        create("production") {
//            applicationIdSuffix(".prod")
//        }
//
//        create("development") {
//            applicationIdSuffix(".dev")
//        }

    }

    aaptOptions {
//        noCompress = ["tflite"].toMutableList()
//        noCompress = ["tflite"].toMutableList()
        noCompress.apply {
            add("tflite")
        }  //= mutableListOf("tflite")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }


    kapt {
        correctErrorTypes = true
    }
//    hilt {
//        enableAggregatingTask = true
//    }
    hilt.enableExperimentalClasspathAggregation = true

    //TODO: remove this after migration to Hilt
//    javaCompileOptions {
//        annotationProcessorOptions.arguments["dagger.hilt.disableModulesHaveInstallInCheck"] =
//            "true"
//    }

//    hilt {
//        enableAggregatingTask = true
//    }


//    hilt { enableAggregatingTask  = true }
//    packagingOptions {
//        exclude("META-INF/notice.txt")
//    }

//    packagingOptions {
//        exclude("META-INF/DEPENDENCIES")
//        exclude("META-INF/LICENSE")
//        exclude("META-INF/LICENSE.txt")
//        exclude("META-INF/license.txt")
//        exclude("META-INF/NOTICE")
//        exclude("META-INF/NOTICE.txt")
//        exclude("META-INF/notice.txt")
//        exclude("META-INF/ASL2.0")
//        exclude("META-INF/*.kotlin_module")
//        exclude("project.properties")
//        exclude("META-INF/INDEX.LIST")
//        exclude("META-INF/kotlin-stdlib-common.kotlin_module")
//        exclude("META-INF/gradle/incremental.annotation.processors")
////        resources.excludes.add("META-INF/*")
//    }

}

dependencies {
    implementation(AppDependencies.coroutinesLibrary)
    implementation(AppDependencies.appLibraries)
    implementation(AppDependencies.retrofitLibraries)
    implementation(AppDependencies.pagingLibrary)

    //region Dagger Hilt
    implementation(AppDependencies.hiltLibrary)
    kapt(AppDependencies.hilAndroidCompiler)
    kapt(AppDependencies.hilCompiler)
    //endregion

    //region ML
    // MLKit Face Detection
    implementation("com.google.mlkit:face-detection:16.1.2")

    // CameraX dependencies
    implementation("androidx.camera:camera-camera2:1.0.1")
    implementation("androidx.camera:camera-lifecycle:1.0.1")
    implementation("androidx.camera:camera-view:1.0.0-alpha28")
    implementation("androidx.camera:camera-extensions:1.0.0-alpha28")

    // TensorFlow Lite dependencies
    implementation("org.tensorflow:tensorflow-lite:2.4.0")
    implementation("org.tensorflow:tensorflow-lite-gpu:2.4.0")
    implementation("org.tensorflow:tensorflow-lite-support:0.1.0")

    // DocumentFile and ExitInterface
    implementation("androidx.documentfile:documentfile:1.0.1")
    implementation("androidx.exifinterface:exifinterface:1.3.3")
    //endregion

    implementation(project(":marvelCore"))
    implementation(project(":marvelRepository"))
    implementation(project(":mainLibrary"))
}
