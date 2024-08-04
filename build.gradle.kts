plugins {
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
}
application.mainClass = "space.peetseater.bot.Main"
group = "space.peetseater.bot"
version = "1.0-SNAPSHOT"

val jdaVersion = "5.0.1"

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
    sourceCompatibility = "17"
}