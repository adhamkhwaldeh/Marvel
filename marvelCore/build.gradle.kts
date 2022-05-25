plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(AppDependencies.appLibraries)

    //region Dagger Hilt
    implementation(AppDependencies.hiltLibrary)
    kapt(AppDependencies.hilAndroidCompiler)
    kapt(AppDependencies.hilCompiler)
    //endregion

    // CameraX dependencies
    implementation("androidx.camera:camera-camera2:1.0.1")
    implementation("androidx.camera:camera-lifecycle:1.0.1")
    implementation("androidx.camera:camera-view:1.0.0-alpha28")
    implementation("androidx.camera:camera-extensions:1.0.0-alpha28")

    implementation(project(":marvelRepository"))
    implementation(project(":mainLibrary"))
    implementation(project(":dtos"))
    implementation(project(":business"))

}