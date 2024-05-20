
plugins {
    id("com.android.application")
    
}

android {
    namespace = "com.arr.example"
    compileSdk = 34
    
    defaultConfig {
        applicationId = "com.arr.example"
        minSdk = 21
        targetSdk = 34
        versionCode = 49
        versionName = "1.0"
        
        vectorDrawables { 
            useSupportLibrary = true
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
        
    }
    
}

dependencies {


    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    
    implementation(project(":apklis-api"))
    implementation("io.reactivex.rxjava3:rxjava:3.1.8")
}
