plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

dependencies {
    implementation(AppDependencies.coroutinesLibrary)
    implementation(AppDependencies.appLibraries)

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

    implementation(project(":mainLibrary"))

}