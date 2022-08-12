@file:Suppress("PackageDirectoryMismatch")

// no package so that we don't need an import

object Versions {
    const val Dagger = "2.43.2"
    const val Glide = "4.12.0"
    const val Kotlin = "1.7.10"
    const val Moshi = "1.13.0"
    const val OkHttp = "4.10.0"
    const val Retrofit = "2.9.0"
    const val Room = "2.5.0-alpha02"
    const val RxJava3 = "3.1.5"
    const val LifecycleVersion = "2.6.0-alpha01"
    const val NavVersion = "2.5.1"
}

object Dependencies {
    const val AndroidXAnnotations = "androidx.annotation:annotation:1.5.0-alpha02"
    const val AndroidXAppCompat = "androidx.appcompat:appcompat:1.6.0-beta01"
    const val AndroidXBiometric = "androidx.biometric:biometric:1.2.0-alpha04"
    const val AndroidXCoreKtx = "androidx.core:core-ktx:1.9.0-alpha05"
    const val AndroidXMultiDex = "androidx.multidex:multidex:2.0.1"
    const val AndroidXRecyclerView = "androidx.recyclerview:recyclerview:1.3.0-beta01"
    const val AndroidXCardView = "androidx.cardview:cardview:1.0.0"
    const val AndroidXConstraintLayout = "androidx.constraintlayout:constraintlayout:2.2.0-alpha01"
    const val AndroidXPreferenceManager = "androidx.preference:preference-ktx:1.2.0"

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
    const val FirebaseMessaging = "com.google.firebase:firebase-messaging:20.2.4"
    const val FirebaseAnalytics = "com.google.firebase:firebase-analytics-ktx:17.5.0"
    const val FirebaseCrashlytics = "com.google.firebase:firebase-crashlytics:17.2.1"

    // Play Services
    const val PlayServicesMaps = "com.google.android.gms:play-services-location:20.0.0"

    // A3
    const val A3Utilities = "com.github.allaboutapps:A3Utilities:V1.0.4"
    const val A3WebView = "com.github.allaboutapps:A3WebView:v0.1.1"
    const val A3RecyclerViewDecorations = "com.github.allaboutapps:A3RecyclerViewDecorations:V1.0.2"

    // Dagger
    const val Dagger = "com.google.dagger:dagger:${Versions.Dagger}"
    const val DaggerAndroid = "com.google.dagger:dagger-android:${Versions.Dagger}"
    const val DaggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.Dagger}"
    const val DaggerCompiler = "com.google.dagger:dagger-compiler:${Versions.Dagger}"
    const val DaggerCompilerAndroid = "com.google.dagger:dagger-android-processor:${Versions.Dagger}"

    const val LeakCanary = "com.squareup.leakcanary:leakcanary-android:2.9.1"

    const val Epoxy = "com.airbnb.android:epoxy:4.6.3"

    const val KotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.Kotlin}"

    const val MaterialComponents = "com.google.android.material:material:1.7.0-alpha03"

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
    const val RetrofitAdapterRxJava3 = "com.squareup.retrofit2:adapter-rxjava3:${Versions.Retrofit}"
    const val RetrofitMock = "com.squareup.retrofit2:retrofit-mock:${Versions.Retrofit}"

    // Room
    const val RoomRuntime = "androidx.room:room-runtime:${Versions.Room}"
    const val RoomRxJava3 = "androidx.room:room-rxjava3:${Versions.Room}"
    const val RoomCompiler = "androidx.room:room-compiler:${Versions.Room}"

    // Reactive
    const val RxJava3 = "io.reactivex.rxjava3:rxjava:${Versions.RxJava3}"
    const val RxJavaAndroid3 = "io.reactivex.rxjava3:rxandroid:3.0.0"
    const val RxKotlin3 = "io.reactivex.rxjava3:rxkotlin:3.0.1"
    const val RxBinding = "com.jakewharton.rxbinding4:rxbinding:4.0.0"
    const val RxRelay = "com.jakewharton.rxrelay3:rxrelay:3.0.1"

    // Misc
    const val Timber = "com.jakewharton.timber:timber:5.0.1"
    const val ViewBindingDelegate = "com.github.Zhuinden:fragmentviewbindingdelegate-kt:1.0.0"
}
