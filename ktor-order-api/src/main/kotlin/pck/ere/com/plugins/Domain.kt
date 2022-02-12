package pck.ere.com.plugins

import io.ktor.http.*
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Order(val uuid: String, val user: User, val equity: Equity, val side: StockSide, val price: Double)

@Serializable
data class User(val uuid: String, val name: String, val email: String)

@Serializable
data class Equity(val name: String, val orderName: String, val total: Long)

@Serializable
enum class StockSide { SELL, BUY }

interface BaseResponse<T : Any>

@Serializable
data class SuccessResponse<T : Any>(
    @Contextual val statusCode: HttpStatusCode,
    val data: T? = null,
    val message: String? = null
) : BaseResponse<T>

@Serializable
data class UnSuccessResponse<T : Any>(
    @Contextual val statusCode: HttpStatusCode,
    val exception: T? = null,
) : BaseResponse<T>