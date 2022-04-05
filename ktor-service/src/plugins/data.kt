package plugins

import kotlinx.serialization.Serializable

/** dto */
@Serializable
data class OrderDto(val uuid: String?, val user: UserDto, val equity: EquityDto, val side: StockSide, val price: Double)

@Serializable
data class UserDto(val name: String, val email: String)

@Serializable
data class EquityDto(val name: String, val orderName: String, val total: Long)

@Serializable
enum class StockSide { SELL, BUY }

/** Entity */
@Serializable
data class Order(val uuid: String, val user: User, val equity: Equity, val side: StockSide, val price: Double)

@Serializable
data class User(val name: String, val email: String)

@Serializable
data class Equity(val name: String, val orderName: String, val total: Long)
