plugins {
    id("com.android.application")
}

android {
    namespace = "com.esprit.rentacar"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.esprit.rentacar"
        minSdk = 24
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
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.paypal.sdk:paypal-android-sdk:2.16.0")
    implementation ("androidx.room:room-runtime:2.2.0")
    annotationProcessor ("androidx.room:room-compiler:2.2.0")
    implementation ("com.loopj.android:android-async-http:1.4.11")
    implementation ("androidx.browser:browser:1.3.0")
    implementation ("com.itextpdf:itextg:5.5.10")
    implementation ("com.sun.mail:android-mail:1.6.2")
    implementation ("com.sun.mail:android-activation:1.6.2")
    implementation ("com.stripe:stripe-android:16.0.0")
}