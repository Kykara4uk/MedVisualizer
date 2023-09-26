
pluginManagement {
    repositories {
        maven {
            url = uri("https://www.dcm4che.org/maven2")
            isAllowInsecureProtocol = true
        }
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven {
            url = uri("https://www.dcm4che.org/maven2")
            isAllowInsecureProtocol = true
        }
        google()
        mavenCentral()
    }
}

rootProject.name = "MedVisualizer"
include(":app")
 