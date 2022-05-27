plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(AppDependencies.appLibraries)
    implementation(AppDependencies.retrofitLibraries)
    implementation(AppDependencies.pagingLibrary)

    //region Dagger Hilt
    implementation(AppDependencies.hiltLibrary)
    kapt(AppDependencies.hilAndroidCompiler)
    kapt(AppDependencies.hilCompiler)
    //endregion

    implementation(AppDependencies.imageLibs)


    implementation(project(":dtos"))
    implementation(project(":marvelRepository"))
    implementation(project(":mainLibrary"))
    implementation(project(":business"))

}