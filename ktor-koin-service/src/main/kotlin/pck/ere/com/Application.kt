package pck.ere.com

import io.ktor.application.*
import pck.ere.com.plugins.*

fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureRouting()
    configureSerialization()
    configureMetrics()
    val db = createConnection()
    configureModules(db)
}
