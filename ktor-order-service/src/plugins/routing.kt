package plugins

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*
import io.micrometer.prometheus.PrometheusMeterRegistry
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

fun Application.routes(db: CoroutineDatabase) {

    routing {
        route("/v1/order") {
            
            get("/{name}") {
                val name = call.parameters["name"]
                if (name.isNullOrBlank()) {
                    call.respond(UnSuccessResponse(HttpStatusCode.BadRequest, "not found name field"))
                } else {
                    db.getCollection<Order>().findOne(Order::uuid eq name)
                        ?.let { call.respond(SuccessResponse(HttpStatusCode.OK, it)) }
                        ?: call.respond(UnSuccessResponse<Any>(HttpStatusCode.NotFound))

                }
            }

            post {
                call.receive<Order>().let { order ->
                    db.getCollection<Order>().insertOne(order)
                    call.respond(HttpStatusCode.Created)
                }
            }
        }
    }
}

fun Application.metricRoutes(appMicrometerRegistry: PrometheusMeterRegistry) {
    routing {
        route("/") {
            get { call.respond("Hello, welcome to ktor app") }
            get("/health") { call.respond("""{"status":"OK""}""") }
        }
        get("/metrics") {
            call.respond(appMicrometerRegistry.scrape())
        }
    }
}
