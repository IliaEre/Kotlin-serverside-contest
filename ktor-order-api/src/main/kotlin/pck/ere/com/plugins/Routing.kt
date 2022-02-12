package pck.ere.com.plugins

import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.metrics.micrometer.*
import io.ktor.request.*
import io.ktor.response.*
import io.micrometer.prometheus.PrometheusConfig
import io.micrometer.prometheus.PrometheusMeterRegistry
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val orderService: OrderService by inject()

    routing {
        route("/v3/order") {

            get("/{name}") {
                val name = call.parameters["name"]
                if (name.isNullOrBlank()) {
                    call.respond(UnSuccessResponse(HttpStatusCode.BadRequest, "not found name field"))
                } else {
                    orderService.findOne(name)
                        ?.let { call.respond(SuccessResponse(HttpStatusCode.OK, it)) }
                        ?: call.respond(HttpStatusCode.NotFound, UnSuccessResponse<Any>(HttpStatusCode.NotFound))
                }
            }

            post {
                orderService.save(call.receive())
                    .also { call.respond(HttpStatusCode.Created) }
            }
        }
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