package pck.ere.com.plugins

import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val orderService: OrderService by inject()

    routing {
        route("/v3/order") {
            get("/{uuid}") {
                val uuid = call.parameters["uuid"]
                if (uuid.isNullOrBlank()) {
                    call.respond(UnSuccessResponse(HttpStatusCode.BadRequest, "uuid param was not found."))
                } else {
                    orderService.findOne(uuid)
                        ?.let { call.respond(SuccessResponse(HttpStatusCode.OK, it)) }
                        ?: call.respond(HttpStatusCode.NotFound, UnSuccessResponse<Any>(HttpStatusCode.NotFound))
                }
            }

            post {
                orderService.save(call.receive())
                    .also { call.respond(HttpStatusCode.OK, it) }
            }
        }
    }
}
