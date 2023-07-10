import org.jetbrains.kotlin.konan.properties.loadProperties

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0-beta05" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "8.1.0-beta05" apply false

    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}

val properties = loadProperties("local.properties")
val myNexusUserName = properties["myNexusUserName"] as String
val myNexusPassword = properties["myNexusPassword"] as String
val myNexusStagingProfileId = properties["myNexusStagingProfileId"] as String

ext["defaultKeyId"] = properties["defaultKeyId"] as String
ext["defaultSecretKey"] = properties["defaultSecretKey"] as String
ext["defaultPassword"] = properties["defaultPassword"] as String

nexusPublishing {
    repositories {
        sonatype {
            stagingProfileId.set(myNexusStagingProfileId)
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
            username.set(myNexusUserName)
            password.set(myNexusPassword)
        }
    }
}