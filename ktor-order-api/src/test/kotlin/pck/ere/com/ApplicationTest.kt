package pck.ere.com

import io.ktor.http.*
import io.ktor.server.testing.*
import pck.ere.com.plugins.configureMetrics
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun testRoot() {
        withTestApplication({ configureMetrics() }) {
            handleRequest(HttpMethod.Get, "/health").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }
}
