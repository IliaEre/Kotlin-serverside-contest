package plugins

import kotlinx.serialization.Serializable

@Serializable
data class Order(val uuid: String, val user: User, val equity: Equity, val side: StockSide, val price: Double)

@Serializable
data class User(val uuid: String, val name: String, val email: String)

@Serializable
data class Equity(val name: String, val orderName: String, val total: Long)

@Serializable
enum class StockSide { SELL, BUY }

