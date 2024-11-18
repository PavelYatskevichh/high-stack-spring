plugins {
	java
	id("io.spring.dependency-management")
}

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
}

group = "com.yatskevich"
