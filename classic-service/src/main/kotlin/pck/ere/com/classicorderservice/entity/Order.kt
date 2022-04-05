package pck.ere.com.classicorderservice.entity

import org.springframework.data.mongodb.core.mapping.Document
import pck.ere.com.classicorderservice.domain.enum.StockSide

@Document("order")
data class Order(val uuid: String, val user: User, val equity: Equity, val side: StockSide, val price: Double)
