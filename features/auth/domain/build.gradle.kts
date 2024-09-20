plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
}

dependencies{
    implementation(projects.storage)

    implementation(projects.features.auth.data)
    implementation(libs.javax.inject)
    implementation(projects.network)
}
