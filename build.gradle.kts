import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.61"

    `maven-publish`
    signing
    `java-library`
}

group = "ch.confinale"
version = "1.0-SNAPSHOT"
base.archivesBaseName = "address-formatter-kt"

java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.yaml:snakeyaml:1.25")
    implementation("com.github.spullara.mustache.java:compiler:0.9.6")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.5.2")

//    implementation("org.jetbrains.kotlin:kotlin-reflect")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
    dependsOn("javadoc")
    from(tasks.javadoc.get().destinationDir)
}


artifacts {
    add("archives", sourcesJar)
    add("archives", javadocJar)
}

publishing {
    publications {
        create<MavenPublication>("address-formatter-kt") {
            groupId = "ch.confinale"
            artifactId = "address-formatter-kt"
            version = "1.0"

            from(components["java"])
            artifact(sourcesJar.get())
            artifact(javadocJar.get())

            pom {
                name.set("address-formatter-kt")
                description.set("A library to format addresses in its locale format")
                url.set("http://www.example.com/library")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/mit-license.php")
                    }
                }
                developers {
                    developer {
                        id.set("cn-wolski")
                        name.set("Michael Wolski")
                        email.set("michael.wolski@confinale.ch")
                    }
                }
                scm {
                    connection.set("scm:git:git:github.com:confinale/addressr.git")
                    developerConnection.set("scm:git:ssh:git@github.com:confinale/addressr.git")
                    url.set("https://github.com/confinale/addressr.git/")
                }
            }
        }
    }
    repositories {
        //		maven {
//			url "https://oss.sonatype.org/service/local/staging/deploy/maven2"
//			credentials {
//				username sonatypeUsername
//						password sonatypePassword
//			}
//		}
	}
}

signing {
    useGpgCmd()
    sign(configurations.archives.get())
}
