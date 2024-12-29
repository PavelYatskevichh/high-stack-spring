plugins {
    id("hs.service-conventions")
}

version = "0.0.1"

val diffMatchPatchVersion: String by project

dependencies {
    implementation("org.bitbucket.cowwoc:diff-match-patch:${diffMatchPatchVersion}")
    implementation(project(":content-creation-api-client"))
}

flyway {
    schemas = arrayOf("content_creation")
}
