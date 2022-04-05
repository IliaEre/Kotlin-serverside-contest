package pck.ere.com.classicorderservice.domain

import pck.ere.com.classicorderservice.domain.enum.StockSide

data class OrderDto(val uuid: String?, val user: UserDto, val equity: EquityDto, val side: StockSide, val price: Double)
