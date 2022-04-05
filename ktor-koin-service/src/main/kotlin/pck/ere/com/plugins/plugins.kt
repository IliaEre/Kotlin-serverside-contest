package pck.ere.com.plugins

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.metrics.micrometer.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.micrometer.prometheus.PrometheusConfig
import io.micrometer.prometheus.PrometheusMeterRegistry

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        jackson { enable(SerializationFeature.INDENT_OUTPUT) }
        json()
    }
}

fun Application.configureMetrics() {
    val appMicrometerRegistry = PrometheusMeterRegistry(PrometheusConfig.DEFAULT)
    install(MicrometerMetrics) {
        registry = appMicrometerRegistry
    }

    routing {
        get("/health") { call.respond("""{"status":"OK""}""") }
        get("/metrics") { call.respond(appMicrometerRegistry.scrape()) }
    }
}
