dependencies {
    compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
//    compileOnly("org.spigotmc:spigot-api:1.21.1-R0.1-SNAPSHOT")
    compileOnly("io.netty:netty-all:4.1.118.Final")
    compileOnly("org.jetbrains:annotations:26.0.2")
}
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(11))
}
tasks.build {
    dependsOn(tasks.named("shadowJar"))
}

dependencies{
//    implementation fileTree(dir: 'libs', include: ['*.jar'])//

    implementation(fileTree("libs") { include("*.jar") })


}