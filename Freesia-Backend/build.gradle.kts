repositories{
    maven { url = uri("https://repo.codemc.io/repository/maven-snapshots/") }
}

dependencies {

    compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
//    compileOnly("org.spigotmc:spigot-api:1.21.1-R0.1-SNAPSHOT")
    compileOnly("io.netty:netty-all:4.1.118.Final")
    compileOnly("org.jetbrains:annotations:26.0.2")
    compileOnly(fileTree("libs") { include("*.jar") })
//    implementation("com.github.retrooper:packetevents-spigot:2.8.0")
    implementation("com.github.retrooper:packetevents-velocity:2.5.0")
}
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(11))
}
tasks.build {
    dependsOn(tasks.named("shadowJar"))
}
