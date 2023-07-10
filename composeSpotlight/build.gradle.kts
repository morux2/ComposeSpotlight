plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    id("maven-publish")
    id("signing")
}

android {
    namespace = "jp.morux2.composeSpotlight"
    compileSdk = 33

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        aarMetadata {
            minCompileSdk = 28
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose  = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
}

val defaultKeyId = rootProject.ext["defaultKeyId"] as String
val defaultSecretKey = rootProject.ext["defaultSecretKey"] as String
val defaultPassword = rootProject.ext["defaultPassword"] as String

signing {
    useInMemoryPgpKeys(
        defaultKeyId,
        defaultSecretKey,
        defaultPassword,
    )
    sign(publishing.publications)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "io.github.morux2"
            artifactId = "compose-spotlight"
            version = "1.0"

            pom {
                name.set("Compose Spotlight")
                description.set("Implemented Spotlight with JetpackCompose")
                url.set("https://github.com/morux2/ComposeSpotlight")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("morux2")
                        name.set("Kurumi Morimoto")
                        email.set("k-morimoto@keio.jp")
                    }
                }
                scm {
                    connection.set("scm:git:github.com/morux2/ComposeSpotlight.git")
                    developerConnection.set("scm:git:ssh://github.com/morux2/ComposeSpotlight.git")
                    url.set("https://github.com/morux2/ComposeSpotlight")
                }
            }

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}