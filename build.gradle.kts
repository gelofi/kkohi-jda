plugins {
    id("java")
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

application.mainClass = "org.java.kkohi"
group = "org.java.kkohi"
version = "1.0-SNAPSHOT"

val jdaVersion = "5.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("net.dv8tion:JDA:$jdaVersion")
    implementation("ch.qos.logback:logback-classic:1.5.6")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.isIncremental = true

    // Set this to the version of java you want to use,
    // the minimum required for JDA is 1.8
    sourceCompatibility = "22"
}