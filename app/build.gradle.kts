plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.google.firebase.crashlytics)

}

android {
    namespace = "com.downloader.hmvideodownloader"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.myowndo.onlineappvp"
        minSdk = 24
        targetSdk = 34
        versionCode = 3
        versionName = "3.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_HOST", "\"https://awebhtpo3u8g5t.ecoweb-network.com\"")
        buildConfigField ("String", "BASE_OAUTH_METHOD", "\"anonymous\"")
        buildConfigField ("String", "SHARED_PREFS", "\"NORTHGHOST_SHAREDPREFS\"")
        buildConfigField ("String", "STORED_HOST_URL_KEY", "\"com.northghost.afvclient.STORED_HOST_KEY\"")
        buildConfigField ("String", "STORED_CARRIER_ID_KEY", "\"com.northghost.afvclient.CARRIER_ID_KEY\"")

    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packagingOptions {
        resources.excludes.add ("lib/arm64-v8a/libopenvpn.so")
        resources.excludes.add ("lib/arm64-v8a/libopvpnutil.so")
        resources.excludes.add ("lib/arm64-v8a/libjbcrypto.so")
        resources.excludes.add ("lib/arm64-v8a/libstlport_shared.so")
        resources.excludes.add ("lib/armeabi-v7a/libopenvpn.so")
        resources.excludes.add ("lib/armeabi-v7a/libopvpnutil.so")
        resources.excludes.add ("lib/armeabi-v7a/libjbcrypto.so")
        resources.excludes.add ("lib/armeabi-v7a/libstlport_shared.so")
        resources.excludes.add ("lib/x86/libopenvpn.so")
        resources.excludes.add ("lib/x86/libopvpnutil.so")
        resources.excludes.add ("lib/x86/libjbcrypto.so")
        resources.excludes.add ("lib/x86/libstlport_shared.so")
        resources.excludes.add ("lib/x86_64/libopenvpn.so")
        resources.excludes.add ("lib/x86_64/libopvpnutil.so")
        resources.excludes.add ("lib/x86_64/libjbcrypto.so")
        resources.excludes.add ("lib/x86_64/libstlport_shared.so")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-process:2.7.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("com.android.installreferrer:installreferrer:1.1")
    implementation("com.google.android.gms:play-services-ads:22.1.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation ("com.facebook.android:facebook-android-sdk:17.0.0")

    implementation ("co.pango:sdk-core:5.0.1")
    implementation ("co.pango:sdk-hydra:5.0.1")
    implementation ("co.pango:sdk-openvpn:5.0.1")
    implementation ("co.pango:sdk-wireguard:5.0.1")

    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))

    implementation ("com.airbnb.android:lottie:4.1.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.core:core-ktx:1.13.1")


}