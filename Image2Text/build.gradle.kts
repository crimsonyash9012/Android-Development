// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.2")
        // Add other classpath dependencies if needed
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}