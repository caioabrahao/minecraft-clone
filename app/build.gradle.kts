val lwjglVersion = "3.4.3"
val jomlVersion = "1.10.9"
val lwjglNatives = "natives-windows"

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit)
    implementation(libs.guava)

    implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))

	implementation("org.lwjgl:lwjgl")
	implementation("org.lwjgl:lwjgl-glfw")
	implementation("org.lwjgl:lwjgl-opengl")
	implementation("org.lwjgl:lwjgl-stb")
	implementation("org.lwjgl:lwjgl::$lwjglNatives")
	implementation("org.lwjgl:lwjgl-glfw::$lwjglNatives")
	implementation("org.lwjgl:lwjgl-opengl::$lwjglNatives")
	implementation("org.lwjgl:lwjgl-stb::$lwjglNatives")
	implementation("org.joml:joml:$jomlVersion")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    // Define the main class for the application.
    mainClass = "br.com.caioabrahao.voxelengine.Main"
}
