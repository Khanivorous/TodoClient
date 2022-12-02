plugins {
    java
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.7")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.0")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.14.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform() {
        excludeTags ("Integration")
    }
    testLogging {
        events ("started", "skipped", "passed", "failed", "STANDARD_OUT", "STANDARD_ERROR")
        showStackTraces = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        debug {
            events ("started", "skipped", "passed", "failed", "STANDARD_OUT", "STANDARD_ERROR")
        }
    }
}

tasks.register<Test>("integrationTests") {
    useJUnitPlatform() {
        includeTags ("Integration")
        excludeTags ("Application")
    }
    testLogging {
        events ("started", "skipped", "passed", "failed", "STANDARD_OUT", "STANDARD_ERROR")
        showStackTraces = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        debug {
            events ("started", "skipped", "passed", "failed", "STANDARD_OUT", "STANDARD_ERROR")
        }
    }

}

tasks.register<Test>("applicationTests") {
    useJUnitPlatform() {
        includeTags ("Application")
        excludeTags ("Integration")
    }
    testLogging {
        events ("started", "skipped", "passed", "failed", "STANDARD_OUT", "STANDARD_ERROR")
        showStackTraces = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        debug {
            events ("started", "skipped", "passed", "failed", "STANDARD_OUT", "STANDARD_ERROR")
        }
    }
}