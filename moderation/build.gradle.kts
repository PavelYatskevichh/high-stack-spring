plugins {
    id("hs.service-conventions")
}

version = "0.0.1"

flyway {
    schemas = arrayOf("moderation")
}
