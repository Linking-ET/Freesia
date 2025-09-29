plugins {
    id("java")
    id("eclipse")
    id("org.jetbrains.gradle.plugin.idea-ext") version "1.0.1"
    id("io.github.goooler.shadow") version "8.1.2"
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11)) // 强制使用 Java 8 编译
    }
}

group = "meow.kikir"
version = project.version

allprojects {
    group = "meow.kikir"
    version = rootProject.version

    apply(plugin = "java")
    apply(plugin = "eclipse")
    apply(plugin = "io.github.goooler.shadow")
    apply(plugin = "org.jetbrains.gradle.plugin.idea-ext")

    repositories {
        mavenCentral()

        maven {
            name = "FabricMC"
            url = uri("https://maven.fabricmc.net/")
        }

        maven {
            name = "papermc-repo"
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }

        maven {
            name = "spigot-repo"
            url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        }

        maven {
            name = "opencollab"
            url = uri("https://repo.opencollab.dev/maven-releases/")
        }

        maven {
            name = "opencollab-snapshot"
            url = uri("https://repo.opencollab.dev/maven-snapshots/")
        }


        maven {
            name = "sonatype"
            url = uri("https://oss.sonatype.org/content/groups/public/")
        }

        maven {
            url = uri("https://repo.codemc.io/repository/maven-releases/")
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
}

tasks.test {
    useJUnitPlatform()
}
