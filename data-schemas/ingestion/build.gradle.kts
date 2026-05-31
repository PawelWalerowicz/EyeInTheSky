plugins {
	java
	id("com.google.protobuf") version "0.10.0"
}

group = "walerowicz"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(25)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("com.google.protobuf:protobuf-java:4.35.0")
}

protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc:4.35.0"
	}
}
