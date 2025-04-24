plugins {
    id("java")
    id("tech.medivh.plugin.publisher") version "1.2.2"
}

group = "tech.medivh"
version = "1.0.0"

repositories {
    mavenCentral()
}



dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
