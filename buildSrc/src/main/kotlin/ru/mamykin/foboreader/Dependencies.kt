package ru.mamykin.foboreader

object Versions {
    const val viewModelVersion = "1.1.0"
    const val koinVersion = "2.0.1"
    const val roomVersion = "2.2.1"
    const val coroutinesVersion = "1.3.2"
    const val retrofitVersion = "2.3.0"
    const val okHttpVersion = "4.9.0"
    const val picassoVersion = "2.5.2"
    const val mockitoVersion = "2.13.0"
    const val mockitoKotlinVersion = "1.5.0"
    const val junitVersion = "4.12"
    const val kluentVersion = "1.35"
    const val flowbindingVersion = "0.11.1"
    const val multidexVersion = "2.0.1"
    const val coreKtxVersion = "1.0.2"
    const val recyclerViewVersion = "1.2.0-alpha03"
    const val materialLibVersion = "1.0.0"
    const val constraintLayoutVersion = "1.1.3"
    const val retrofitCoroutinesAdapterVersion = "0.9.2"
    const val retrofitLoggingInterceptorVersion = "4.3.0"
    const val espressoCoreVersion = "3.1.1"
    const val recyclicalVersion = "1.1.1"
    const val leakcanaryVersion = "2.5"
    const val kotlin_version = "1.4.10"
    const val nav_version = "2.2.2"
}

object Dependencies {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
    const val koinScope = "org.koin:koin-androidx-scope:${Versions.koinVersion}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koinVersion}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val cardView = "androidx.cardview:cardview:${Versions.materialLibVersion}"
    const val materialLib = "com.google.android.material:material:${Versions.materialLibVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val lifecycleExtensions = "android.arch.lifecycle:extensions:${Versions.viewModelVersion}"
    const val lifecycleViewModel = "android.arch.lifecycle:viewmodel:${Versions.viewModelVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val okio = "com.squareup.okio:okio:${Versions.retrofitVersion}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val retrofitCoroutinesAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutinesAdapterVersion}"
    const val retrofitLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.retrofitLoggingInterceptorVersion}"
    const val picasso = "com.squareup.picasso:picasso:${Versions.picassoVersion}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
    const val flowbinding = "io.github.reactivecircus.flowbinding:flowbinding-android:${Versions.flowbindingVersion}"
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockitoVersion}"
    const val mockitoKotlin = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlinVersion}"
    const val kluent = "org.amshove.kluent:kluent:${Versions.kluentVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
    const val multidex = "androidx.multidex:multidex:${Versions.multidexVersion}"
    const val recyclical = "com.afollestad:recyclical:${Versions.recyclicalVersion}"
    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanaryVersion}"
}