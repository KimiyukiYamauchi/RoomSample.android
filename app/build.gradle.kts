plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.roomsample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.roomsample"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.room.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // Roomライブラリの追加（バージョンを直接指定）
//    implementation("androidx.room:room-runtime:2.5.1")
//    annotationProcessor("androidx.room:room-compiler:2.5.1")

    // Optional - RxJavaやLiveDataを使う場合
//    implementation("androidx.room:room-rxjava2:2.5.1")
//    implementation("androidx.room:room-livedata:2.5.1")

}