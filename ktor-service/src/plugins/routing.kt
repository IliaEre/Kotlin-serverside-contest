package plugins

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

fun Application.routes(db: CoroutineDatabase) {
    routing {
        route("/v4/order") {
            get("/{uuid}") {
                val uuid = call.parameters["uuid"]
                if (uuid.isNullOrBlank()) {
                    call.respond(UnSuccessResponse(HttpStatusCode.BadRequest, "uuid fiend was not found!"))
                } else {
                    db.getCollection<Order>().findOne(Order::uuid eq uuid)?.toDto()
                        ?.let { call.respond(SuccessResponse(HttpStatusCode.OK, it)) }
                        ?: call.respond(UnSuccessResponse<Any>(HttpStatusCode.NotFound))

                }
            }

            post {
                call.receive<OrderDto>().let { orderDto ->
                    val entity = orderDto.toEntity()
                    db.getCollection<Order>().insertOne(entity)
                    call.respond(HttpStatusCode.OK, entity.uuid)
                }
            }
        }
    }
}
