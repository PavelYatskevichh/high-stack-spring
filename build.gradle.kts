plugins {
	java
	id("io.spring.dependency-management")
}

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

group = "com.yatskevich"
