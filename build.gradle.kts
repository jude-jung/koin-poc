val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val kotest_version: String by project
val kotestKoin_version: String by project
val koin_version: String by project

plugins {
    application
    kotlin("jvm") version "1.7.10"
}

group = "com.example"
version = "0.0.1"
application {
    mainClass.set("com.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    // Koin Core features
    implementation("io.insert-koin:koin-core:$koin_version")
    // Koin for Ktor
    implementation("io.insert-koin:koin-ktor:$koin_version")
    // SLF4J Logger
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")
    // Koin Test features
    testImplementation("io.insert-koin:koin-test:$koin_version")
    testImplementation("io.kotest:kotest-runner-junit5:$kotest_version")
    // Kotest Koin
    testImplementation("io.kotest.extensions:kotest-extensions-koin:${kotestKoin_version}")
}