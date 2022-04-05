import io.ktor.application.*
import plugins.configureMetrics
import plugins.configureSerialization
import plugins.connectDatabase
import plugins.routes

fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    configureSerialization()
    configureMetrics()
    takeIf { testing.not() }?.let { routes(connectDatabase()) }
}
