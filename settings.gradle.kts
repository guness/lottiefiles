pluginManagement {
    plugins {
        id("de.fayard.refreshVersions").version("0.40.1")
    }
}

plugins {
    id("de.fayard.refreshVersions")
}

refreshVersions {
    rejectVersionIf {
        candidate.stabilityLevel.isLessStableThan(current.stabilityLevel)
    }
    extraArtifactVersionKeyRules(file("refreshVersions-extra-rules.txt"))
}

rootProject.name = "LottieFiles"
include(":app")
