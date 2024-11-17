plugins {
    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("jacoco")
    id("org.flywaydb.flyway")
}

group = "com.yatskevich"
version = "0.0.1"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
    testCompileOnly {
        extendsFrom(configurations.testAnnotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
//    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")
//    implementation("org.springframework.kafka:spring-kafka")

    compileOnly("org.projectlombok:lombok")
    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("org.springframework.kafka:spring-kafka-test")
//    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
    testCompileOnly("org.projectlombok:lombok")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
    }
}

flyway {
    url = "jdbc:postgresql://localhost:5432/test_db"
    user = "user"
    password = "pass"
    baselineOnMigrate = true
    locations = arrayOf("classpath:db/migration")
    schemas = arrayOf("content_creation")
    createSchemas = true
}
