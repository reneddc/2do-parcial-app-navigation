plugins {
    alias(libs.plugins.calyr.android.library)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.calyr.framework"
}

dependencies {
    implementation(libs.retrofit)
    //networking bundle
    implementation(libs.bundles.networking)
    implementation(project(":data"))
    implementation(project(":domain"))
}