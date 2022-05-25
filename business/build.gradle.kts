plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(AppDependencies.coroutinesLibrary)
    implementation(AppDependencies.appLibraries)

    //region Dagger Hilt
    implementation(AppDependencies.hiltLibrary)
    kapt(AppDependencies.hilAndroidCompiler)
    kapt(AppDependencies.hilCompiler)
    //endregion

    implementation(project(":mainLibrary"))
    implementation(project(":marvelRepository"))
    implementation(project(":dtos"))

}