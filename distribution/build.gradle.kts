plugins {
    id("hs.service-conventions")
}

version = "0.0.1"

dependencies {
    implementation(project(":content-creation-api-client"))
}

flyway {
    schemas = arrayOf("distribution")
}
