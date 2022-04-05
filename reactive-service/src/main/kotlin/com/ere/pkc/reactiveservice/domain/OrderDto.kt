package com.ere.pkc.reactiveservice.domain

import com.ere.pkc.reactiveservice.domain.enum.StockSide

data class OrderDto(val uuid: String?, val user: UserDto, val equity: EquityDto, val side: StockSide, val price: Double)
