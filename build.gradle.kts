plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.allopen") version "1.8.21"
    id("jacoco")
    id("pl.allegro.tech.build.axion-release") version "1.14.0"
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("javax.xml.bind:jaxb-api:2.4.0-b180830.0359")
    implementation("com.amazonaws:aws-lambda-java-core:1.2.0")
    implementation("com.google.code.gson:gson:2.8.+")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.arrow-kt:arrow-core:1.1.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
}
exec {
    commandLine("git", "pull", "origin", "main:main")
}
group = "com.demo"
project.version = scmVersion.version.replace("-SNAPSHOT", "")

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    kotlinOptions.javaParameters = true
}
ext {
    set("mainClassName", "Main")
}

tasks {
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        manifest {
            attributes(mapOf("Main-Class" to "mainClassName"))
        }
    }
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        xml.required.set(true)
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = BigDecimal.valueOf(.1)
            }
        }
    }
}

println("My project version - ${project.version}")
