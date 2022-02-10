package plugins;

import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.mongodb.reactivestreams.client.FindPublisher
import com.mongodb.reactivestreams.client.MongoCollection
import com.mongodb.reactivestreams.client.MongoDatabase
import io.ktor.http.*
import io.ktor.server.testing.*
import io.mockk.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.json
import java.util.concurrent.Flow
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

public class RoutesTest {

    private val mockMongo = mockk<CoroutineDatabase>()
    private val mongoDatabase = mockk<MongoDatabase>()
    private val collections = mockk<MongoCollection<Order>>()
    private val orderPublisher = mockk<FindPublisher<Order>>()

    private val expectedOrder = Order(
        "test",
        User("test", "test", "test"),
        Equity("test", "test", 1000L),
        StockSide.SELL,
        1.0
    )

    @Test
    fun testGetV1Order() {
        val uuid = "1fef2f"

        every { mockMongo.database } returns mongoDatabase
        every { mongoDatabase.getCollection("order", Order::class.java) } returns collections
        every { collections.find(Order::uuid eq uuid) } returns orderPublisher
        every { orderPublisher.first() } returns orderPublisher
        every { orderPublisher.subscribe(any()) } just Runs

        withTestApplication({ routes(mockMongo) }) {
            handleRequest(HttpMethod.Get, "/v1/order/$uuid").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertNotNull(response.content)
            }
        }
    }

    @Test
    fun testPostV1Order() {
        withTestApplication({ routes(mockMongo) }) {
            handleRequest(HttpMethod.Post, "/v1/order").apply {
                TODO("Please write your test here")
            }
        }
    }
}