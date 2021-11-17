import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
    id("antlr")
    application
}

group = "me.bristermitten"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    antlr("org.antlr:antlr4:4.9.3")
    implementation("com.yuvalshavit:antlr-denter:1.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

//tasks.generateGrammarSource {
//    maxHeapSize = "64m"
//
//    // Keep a copy of generated sources
//    doLast {
//        println("Copying generated grammar lexer/parser files to main directory.")
//        copy {
//            from("${buildDir}/generated-src/antlr/main")
//            into("generated-src/main/java")
//        }
//    }
//}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "16"
}

application {
    mainClass.set("MainKt")
}
