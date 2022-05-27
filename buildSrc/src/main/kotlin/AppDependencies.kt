import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * This class have all the dependencies of the project
 * along with the Extension functions on the Dependency handler
 * creating the list of specific group of dependencies
 */
object AppDependencies {


    //region All Dependencies

    //region Kotlin std
    private const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    //endregion

    //region Android UI
    private const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private const val material =
        "com.google.android.material:material:${Versions.materialComponents}"
    //endregion

    //region Navigation
    private const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationKotlin}"
    private const val navigationUi =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigationKotlin}"

    //endregion

    //region LifeCycle
    private const val lifeCycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycle}"
    private const val lifeCycleSavedState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifeCycle}"
    private const val lifeCycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}"
    private const val lifeCycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"
    private const val lifeCycleCommonJava =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.lifeCycle}"
    //endregion

    //region Coroutines
    private const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    private const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    //endregion

    //region Retrofit
    private const val retrofitAdapter =
        "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofitAdapter}"
    private const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
    private const val retrofitConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit2}"
    private const val retrofitLogging =
        "com.squareup.okhttp3:logging-interceptor:${Versions.retrofitLogging}"
    //endregion

    //region Androidx
//    private const val scopeAndroidx = "org.koin:koin-androidx-scope:${Versions.koin_version}"
//    private const val fragmentAndroidx = "org.koin:koin-androidx-fragment:${Versions.koin_version}"
//    private const val viewModelAndroidx =
//        "org.koin:koin-androidx-viewmodel:${Versions.koin_version}"
//    private const val koinAndroid = "org.koin:koin-androidx-ext:${Versions.koin_version}"
    private const val swiperefreshlayoutX = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
    //endregion

    //region  Paging
    private const val pagingRuntime = "androidx.paging:paging-runtime:${Versions.paging}"
    private const val pagingRuntimeKtx = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
    private const val pagingCommon = "androidx.paging:paging-common:${Versions.paging}"
    private const val pagingCommonKtx = "androidx.paging:paging-common-ktx:${Versions.paging}"
    private const val pagingRxjava3 = "androidx.paging:paging-rxjava3:${Versions.paging}"
    private const val pagingGuava = "androidx.paging:paging-guava:${Versions.paging}"
    //endregion

    //region Dagger Hilt
    private const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.daggerHiltAndroid}"
//    private const val hiltLifecycleViewModel =
//        "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.daggerHilt}"
    private const val hiltWork = "androidx.hilt:hilt-work:${Versions.daggerHilt}"
    private const val hiltNavigationFragment =
        "androidx.hilt:hilt-navigation-fragment:${Versions.daggerHilt}"
    private const val hiltCommon = "androidx.hilt:hilt-common:${Versions.daggerHilt}"

    const val hilAndroidCompiler =
        "com.google.dagger:hilt-android-compiler:${Versions.daggerHiltAndroid}"
    const val hilCompiler = "androidx.hilt:hilt-compiler:${Versions.daggerHilt}"
    //endregion

    //region MultiScreen Support
    private const val multiScreenSupport = "com.intuit.sdp:sdp-android:${Versions.multiScreenSizes}"
    //endregion

    //region Testing
    private const val mutliDex = "androidx.multidex:multidex:${Versions.dex_version}"
    private const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    private const val junit = "junit:junit:${Versions.junit}"
    private const val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    private const val fragmentTesting =
        "androidx.fragment:fragment-testing:${Versions.fragmentTesting}"
    //endregion

    //region Image Libs
    private const val glide = "com.github.bumptech.glide:glide:4.11.0"
    private const val circleImageView = "de.hdodenhof:circleimageview:3.1.0"
    //endregion

    // Room
    private const val roomRuntime = "androidx.room:room-runtime:${Versions.room_version}"


//    annotationProcessor "androidx.room:room-compiler:$room_version"
//    kapt "android.arch.persistence.room:compiler:1.0.0"

    //region Serialization

    private const val kotlinSerialization =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerialization}"
    //endregion
    // endregion

    val appLibraries = arrayListOf<String>().apply {
        add(mutliDex)
        add(kotlinStdLib)
        add(coreKtx)
        add(appcompat)
        add(constraintLayout)
        add(material)
        add(navigationFragment)
        add(navigationUi)
        add(lifeCycle)
        add(lifeCycleSavedState)
        add(lifeCycleRuntime)
        add(lifeCycleViewModel)
        add(lifeCycleCommonJava)
//        add(viewModelAndroidx)
//        add(fragmentAndroidx)
//        add(scopeAndroidx)
//        add(koinAndroid)
        add(kotlinSerialization)
        add(retrofitLogging)
        add(swiperefreshlayoutX)
    }

    val roomLibraries = arrayListOf<String>().apply {
        add(roomRuntime)
    }

    val retrofitLibraries = arrayListOf<String>().apply {
        add(retrofitAdapter)
        add(retrofit2)
        add(retrofitConverter)
        add(retrofitLogging)
    }

    val coroutinesLibrary = arrayListOf<String>().apply {
        add(coroutinesCore)
        add(coroutinesAndroid)
    }

    val pagingLibrary = arrayListOf<String>().apply {
        add(pagingRuntime)
        add(pagingRuntimeKtx)
        add(pagingCommon)
        add(pagingCommonKtx)
        add(pagingRxjava3)
        add(pagingGuava)
    }


    val hiltLibrary = arrayListOf<String>().apply {
        add(hiltAndroid)
//        add(hiltLifecycle ViewModel)
        add(hiltWork)
        add(hiltNavigationFragment)
        add(hiltCommon)
    }


    val imageLibs = arrayListOf<String>().apply {
        add(glide)
        add(circleImageView)
    }

    val customLibs = arrayListOf<String>().apply {
        add(multiScreenSupport)
//        add(expandableRecyclerview)

    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
        add(fragmentTesting)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
        add(mockito)
    }
}

////util functions for adding the different type dependencies from build.gradle.kts.kts file
//fun DependencyHandler.kapt(list: List<String>) {
//    list.forEach { dependency ->
//        add("kapt", dependency)
//    }
//}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun implementation(singleDependency: String): String {
    return "implementation$singleDependency"

}

fun DependencyHandler.implementationProject(moduleDependency: String): String {
    return "implementation project($moduleDependency)"

}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}