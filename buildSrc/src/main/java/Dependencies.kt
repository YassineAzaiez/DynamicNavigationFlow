object ProjectDependencies {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Libs {
    //kotlin
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    //UI
    const val appCompact = "androidx.appcompat:appcompat:${Versions.appCompact}"
    const val fragment = "androidx.fragment:fragment:${Versions.fragment}"
    const val ktx = "androidx.core:core-ktx:${Versions.KtxCore}"
    const val cardview = "androidx.cardview:cardview:${Versions.cardView}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val materialDesign = "com.google.android.material:material:${Versions.googleMaterial}"

    //dagger
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    //lifecycle
    const val lifecycleExtension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

    //Coroutines
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val coil = "io.coil-kt:coil:${Versions.coil}"

    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    const val securityEncryption = "androidx.security:security-crypto:${Versions.securityCrypto}"

    //Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    //OkHttp
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okHttpInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    //Stetho interceptor
    const val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
    const val stethoOkHttp = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"

    //Navigation
    const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
    const val navigation_ui =  "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"




}

object TestLibs {
    //Unit tests
    const val junit = "junit:junit:${Versions.junit}"
    const val androidxJunit = "androidx.test.ext:junit:${Versions.androidxJunit}"
    const val androidxJunitExtension = "androidx.test.ext:junit-ktx:${Versions.androidxJunit}"
    const val googleTruth = "com.google.truth:truth:${Versions.googleTruth}"
    const val coreTesting = "android.arch.core:core-testing:${Versions.coreTesting}"
    const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutine}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"

    //UI Android Test
    const val testRunner = "androidx.test:runner:${Versions.testCoreRunner}"
    const val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
    const val barista = "com.schibsted.spain:barista:${Versions.barista}"
    const val excludeKotlin = "org.jetbrains.kotlin"
    const val fragmentTests = "androidx.fragment:fragment-testing:${Versions.fragmentTesting}"
}