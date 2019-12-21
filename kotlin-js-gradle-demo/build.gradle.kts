plugins {
  kotlin("js") version "1.3.61"
}

group = "de.rieckpil.learning"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  jcenter()
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
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
  sourceSets["main"].dependencies {
    implementation(npm("react", "16.8.3"))
  }
}
