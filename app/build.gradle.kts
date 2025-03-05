plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.downloader.hmvideodownloader"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.downloader.hmvideodownloader"
        minSdk = 24
        targetSdk = 34
        versionCode = 3
        versionName = "3.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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


}