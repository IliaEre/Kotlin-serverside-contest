package plugins

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.connection.SocketSettings
import io.ktor.application.*
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import java.util.concurrent.TimeUnit

// lateinit var database: CoroutineDatabase

fun Application.connectDatabase(): CoroutineDatabase {
    val host = environment.config.property("mongo.host").getString()
    val port = environment.config.property("mongo.port").getString()
    val username = environment.config.property("mongo.username").getString()
    val password = environment.config.property("mongo.password").getString()
    val timeout = environment.config.property("mongo.timeout").getString().toInt()

    val link = "mongodb://$username:$password@$host:$port"

    val settings = MongoClientSettings.builder()
        .applicationName("ktor-order")
        .applyConnectionString(ConnectionString(link))
        .applyToSocketSettings { SocketSettings.builder().connectTimeout(timeout, TimeUnit.SECONDS).build() }
        .build()

    val client = KMongo.createClient(settings).coroutine
    return client.getDatabase("orders")
}
