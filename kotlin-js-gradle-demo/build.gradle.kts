plugins {
  kotlin("js") version "1.3.61"
}

group = "de.rieckpil.learning"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  jcenter()
}

dependencies {
  implementation(kotlin("stdlib-js"))
  implementation("org.jetbrains.kotlinx", "kotlinx-html-js", "0.6.12")
  testImplementation(kotlin("test-js"))
}

kotlin {
  target {
    browser {
    }
  }
}
