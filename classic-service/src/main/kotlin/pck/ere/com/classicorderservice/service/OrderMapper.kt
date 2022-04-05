package pck.ere.com.classicorderservice.service

import org.springframework.stereotype.Component
import pck.ere.com.classicorderservice.domain.EquityDto
import pck.ere.com.classicorderservice.domain.OrderDto
import pck.ere.com.classicorderservice.domain.UserDto
import pck.ere.com.classicorderservice.entity.Equity
import pck.ere.com.classicorderservice.entity.Order
import pck.ere.com.classicorderservice.entity.User
import java.util.UUID

@Component
class OrderMapper {

    fun toDto(order: Order): OrderDto = with(order) { OrderDto(uuid, toDto(user), toDto(equity), side, price) }

    fun toEntity(orderDto: OrderDto): Order = with(orderDto) {
        Order(UUID.randomUUID().toString(), toEntity(user), toEntity(equity), side, price)
    }

    private fun toDto(user: User): UserDto = with(user) { UserDto(name, email) }

    private fun toDto(equity: Equity): EquityDto = with(equity) { EquityDto(name, orderName, total) }

    private fun toEntity(userDto: UserDto): User = with(userDto) { User(name, email) }

    private fun toEntity(equityDto: EquityDto): Equity = with(equityDto) { Equity(name, orderName, total) }

}
