package pck.ere.com.plugins

import io.ktor.application.*
import io.ktor.features.*
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.koin.logger.SLF4JLogger
import org.litote.kmongo.coroutine.CoroutineDatabase

fun Application.configureModules(db: CoroutineDatabase) {
    install(DefaultHeaders)
    install(CallLogging)

    val module = module {
        single { OrderRepository(db) }
        single<OrderService> { OrderServiceImpl(get()) }
    }

    install(Koin) {
        SLF4JLogger()
        modules(module)
    }
}
