plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.dokka)
}

android {
    namespace = "com.ivoafsilva.mobiweb.mobilesdk"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    kotlin {
        explicitApi()
    }
}

dependencies {

    // AndroidX ktx
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // Network
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Dependency Injection
    implementation(libs.koin.core)

    // Unit Testing
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.koin.test)
}

// Define a task to copy the release AAR file
val copyAarToDistFolder by tasks.registering(Copy::class) {
    dependsOn(tasks.named("bundleReleaseAar")) // Make sure the release task runs before this one

    from(listOf(layout.buildDirectory.asFile.get().absolutePath, "outputs", "aar").joinToString(File.separator))

    into("$rootDir/dist")

    rename("${project.name}-release.aar", "mobiweb_iafsilva_MobileSdk.aar")
}

afterEvaluate {
    // Ensure that the copyAar task runs after the bundleReleaseAar task
    tasks.named("bundleReleaseAar") {
        finalizedBy(copyAarToDistFolder)
    }
}
