buildscript {
    repositories {
        // other repositories...
        mavenCentral()
    }

    dependencies {
        val nav_version = "2.7.6"
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.50")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false

    id("com.google.dagger.hilt.android") version "2.50" apply false


}




