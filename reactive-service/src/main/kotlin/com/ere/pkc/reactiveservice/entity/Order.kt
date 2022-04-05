package com.ere.pkc.reactiveservice.entity

import org.springframework.data.mongodb.core.mapping.Document
import com.ere.pkc.reactiveservice.domain.enum.StockSide

@Document("order")
data class Order(val uuid: String, val user: User, val equity: Equity, val side: StockSide, val price: Double)
