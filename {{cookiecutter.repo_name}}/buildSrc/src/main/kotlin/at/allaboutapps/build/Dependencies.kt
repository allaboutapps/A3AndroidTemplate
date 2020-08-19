@file:Suppress("PackageDirectoryMismatch")

// no package so that we don't need an import

object Versions {
    const val Dagger = "2.27"
    const val Glide = "4.11.0"
    const val Kotlin = "1.3.72"
    const val Moshi = "1.9.2"
    const val OkHttp = "3.12.6"
    const val Retrofit = "2.8.1"
    const val Room = "2.2.5"
    const val RxJava = "2.2.19"
    const val LifecycleVersion = "2.2.0"
    const val NavVersion = "2.3.0-alpha06"
}

object Dependencies {
    const val AndroidXAnnotations = "androidx.annotation:annotation:1.1.0"
    const val AndroidXAppCompat = "androidx.appcompat:appcompat:1.1.0"
    const val AndroidXBiometric = "androidx.biometric:biometric:1.0.1"
    const val AndroidXCoreKtx = "androidx.core:core-ktx:1.2.0"
    const val AndroidXMultiDex = "androidx.multidex:multidex:2.0.1"
    const val AndroidXRecyclerView = "androidx.recyclerview:recyclerview:1.1.0"
    const val AndroidXCardView = "androidx.cardview:cardview:1.0.0"
    const val AndroidXConstraintLayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta5"
    const val AndroidXPreferenceManager = "androidx.preference:preference-ktx:1.1.1"

    // Lifecycle
    const val AndroidXLifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LifecycleVersion}"
    const val AndroidXLifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LifecycleVersion}"
    const val AndroidXLifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.LifecycleVersion}"
    const val AndroidXLifecycleReactiveStreams = "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.LifecycleVersion}"

    // Navigation
    const val AndroidXNavigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.NavVersion}"
    const val AndroidXNavigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.NavVersion}"
    const val AndroidXNavigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NavVersion}"

    // Firebase
    const val FirebaseMessaging = "com.google.firebase:firebase-messaging:20.1.7"
    const val FirebaseAnalytics = "com.google.firebase:firebase-analytics-ktx:17.4.1"
    const val FirebaseCrashlytics = "com.google.firebase:firebase-crashlytics:17.0.0"

    // Play Services
    const val PlayServicesMaps = "com.google.android.gms:play-services-location:17.0.0"

    // A3
    const val A3Utilities = "at.allaboutapps.a3utilities:a3utilities:1.0.3"
    const val A3WebView = "at.allaboutapps.web:a3webview:0.1.0"
    const val A3RecyclerViewDecorations =
        "at.allaboutapps.recyclerview:a3recyclerview-decorations:1.0.1"

    // Dagger
    const val Dagger = "com.google.dagger:dagger:${Versions.Dagger}"
    const val DaggerAndroid = "com.google.dagger:dagger-android:${Versions.Dagger}"
    const val DaggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.Dagger}"
    const val DaggerCompiler = "com.google.dagger:dagger-compiler:${Versions.Dagger}"
    const val DaggerCompilerAndroid = "com.google.dagger:dagger-android-processor:${Versions.Dagger}"

    const val LeakCanary = "com.squareup.leakcanary:leakcanary-android:2.1"

    const val Epoxy = "com.airbnb.android:epoxy:3.9.0"

    const val KotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.Kotlin}"

    const val MaterialComponents = "com.google.android.material:material:1.2.0-alpha06"
    const val Moshi = "com.squareup.moshi:moshi-kotlin:${Versions.Moshi}"
    const val MoshiAdapters = "com.squareup.moshi:moshi-adapters:${Versions.Moshi}"
    const val MoshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.Moshi}"

    // OkHttp
    const val OkHttp = "com.squareup.okhttp3:okhttp:${Versions.OkHttp}"
    const val OkHttpUrlConnection = "com.squareup.okhttp3:okhttp-urlconnection:${Versions.OkHttp}"
    const val OkHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.OkHttp}"

    // Retrofit
    const val Retrofit = "com.squareup.retrofit2:retrofit:${Versions.Retrofit}"
    const val RetrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.Retrofit}"
    const val RetrofitAdapterRxJava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.Retrofit}"
    const val RetrofitMock = "com.squareup.retrofit2:retrofit-mock:${Versions.Retrofit}"

    // Room
    const val RoomRuntime = "androidx.room:room-runtime:${Versions.Room}"
    const val RoomRxJava = "androidx.room:room-rxjava2:${Versions.Room}"
    const val RoomCompiler = "androidx.room:room-compiler:${Versions.Room}"

    // Reactive
    const val RxJava = "io.reactivex.rxjava2:rxjava:${Versions.RxJava}"
    const val RxJavaAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
    const val RxKotlin = "io.reactivex.rxjava2:rxkotlin:2.4.0"
    const val RxBinding = "com.jakewharton.rxbinding3:rxbinding:3.1.0"
    const val RxRelay = "com.jakewharton.rxrelay2:rxrelay:2.1.1"

    // Misc
    const val Timber = "com.jakewharton.timber:timber:4.7.1"
}
