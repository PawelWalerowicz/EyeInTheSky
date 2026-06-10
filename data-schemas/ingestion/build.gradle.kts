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
	implementation(libs.protobuf.java)
}

protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc:4.35.0"
	}

	generateProtoTasks {
		all().forEach { task ->
			task.generateDescriptorSet = true
			task.descriptorSetOptions.includeImports = true
			task.descriptorSetOptions.includeSourceInfo = true
			task.descriptorSetOptions.path = layout.buildDirectory
				.file("descriptors/schema.desc")
				.get()
				.asFile
				.absolutePath
		}
	}
}