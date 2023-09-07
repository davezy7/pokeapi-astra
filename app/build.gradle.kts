plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("kotlin-kapt")
  id("com.google.dagger.hilt.android")
}

android {
  namespace = "devin.astra.pokeapi"
  compileSdk = 34

  defaultConfig {
    applicationId = "devin.astra.pokeapi"
    minSdk = 24
    targetSdk = 33
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
    debug {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
      buildConfigField("String", "BASE_URL", "\"https://pokeapi.co/api/v2/\"")
    }
  }
  buildFeatures {
    viewBinding = true
    buildConfig = true
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

  implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

  // Retrofit
  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-gson:2.9.0")

  // OkHTTP
  implementation(platform("com.squareup.okhttp3:okhttp-bom:4.10.0"))
  implementation("com.squareup.okhttp3:okhttp")
  implementation("com.squareup.okhttp3:logging-interceptor")

  // Paging 3
  implementation("androidx.paging:paging-runtime-ktx:3.2.0")

  // Hilt
  implementation("com.google.dagger:hilt-android:2.47")
  kapt("com.google.dagger:hilt-compiler:2.47")

  // Coroutine Lifecycle Scopes
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

  // Kotlin coroutines
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

  // Coil
  implementation("io.coil-kt:coil:2.4.0")

  // Room Database
  val roomVersion = "2.6.0-beta01"
  implementation("androidx.room:room-ktx:$roomVersion")
  implementation("androidx.room:room-runtime:$roomVersion")
  implementation("androidx.room:room-paging:$roomVersion")
  kapt("androidx.room:room-compiler:$roomVersion")

  implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")

  implementation("com.facebook.shimmer:shimmer:0.5.0")

  implementation("androidx.core:core-ktx:1.9.0")
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("com.google.android.material:material:1.9.0")
  implementation("androidx.constraintlayout:constraintlayout:2.1.4")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}