plugins {
	java
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.saiful"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

sourceSets {
	create("testIntegration") {
		java.srcDir("src/testIntegration/java")
		resources.srcDir("src/testIntegration/resources")
		compileClasspath += sourceSets["main"].output + sourceSets["test"].output
		runtimeClasspath += output + compileClasspath
	}
}

configurations["testIntegrationImplementation"].extendsFrom(configurations["testImplementation"])
configurations["testIntegrationRuntimeOnly"].extendsFrom(configurations["runtimeOnly"])

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("org.assertj:assertj-core:3.26.3")

	// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")


}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.register<Test>("integrationTest"){
	description =" Runs the integration tests"
	group ="verification"
	testClassesDirs = sourceSets["testIntegration"].output.classesDirs
	classpath = sourceSets["testIntegration"].runtimeClasspath
	shouldRunAfter("test")
	useJUnitPlatform()
}
