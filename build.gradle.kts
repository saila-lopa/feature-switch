
plugins {
	java
	id("org.springframework.boot") version "3.0.4-SNAPSHOT"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.moneylion.assignment"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
	maven { url = uri("https://plugins.gradle.org/m2/") }

}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("com.h2database:h2")

	implementation("com.zaxxer:HikariCP:4.0.3")

	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")

	implementation("org.springframework.boot:spring-boot-starter-validation")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("junit:junit:4.13.1")

}

tasks.withType<Test> {
	useJUnitPlatform()
}