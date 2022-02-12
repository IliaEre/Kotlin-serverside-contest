package pck.ere.com.plugins;

import com.mongodb.client.result.InsertOneResult
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineDatabase
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ConfigureRoutingTest {

    private val service = mockk<OrderService>()
    private val repo = mockk<OrderRepository>()
    private val db = mockk<CoroutineDatabase>()

    private val exceptedOrder = Order(
        UUID.randomUUID().toString(),
        User("uuid", "Osetr", "some_email@email.em"),
        Equity("APPL", "apple order", 1L),
        StockSide.BUY,
        2.0
    )

    private fun Application.testModule() {
        configureRouting()
        configureSerialization()
        configureModules(db)
    }

    @Test
    fun `should return order`() {
        val uuid = exceptedOrder.uuid

        val mockModule = module {
            single { service }
            single { repo }
        }

        coEvery { repo.findOne(any()) } returns exceptedOrder
        coEvery { service.findOne(any()) } returns exceptedOrder

        withTestApplication( { testModule() } ) {
            loadKoinModules(mockModule)

            handleRequest(HttpMethod.Get, "/v3/order/$uuid").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }

        coVerify { service.findOne(uuid) }
    }

    @Test
    fun `should not found order`() {
        val externalUuid = "some_uuid"

        val mockModule = module {
            single { service }
            single { repo }
        }

        coEvery { service.findOne(any()) } returns null

        withTestApplication( { testModule() } ) {
            loadKoinModules(mockModule)

            handleRequest(HttpMethod.Get, "/v3/order/$externalUuid").apply {
                assertEquals(HttpStatusCode.NotFound, response.status())
            }
        }

        coVerify { service.findOne(externalUuid) }
    }

    @Test
    fun `should save new order`() {
        val mockModule = module {
            single { service }
            single { repo }
        }

        coEvery { service.save(any()) } returns InsertOneResult.acknowledged(null)

        withTestApplication( { testModule() } ) {
            loadKoinModules(mockModule)

            handleRequest(HttpMethod.Post, "/v3/order") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                setBody(Json.encodeToString(exceptedOrder))
            }.apply {
                assertEquals(HttpStatusCode.Created, response.status())
            }
        }

        coVerify { service.save(exceptedOrder) }
    }

}
