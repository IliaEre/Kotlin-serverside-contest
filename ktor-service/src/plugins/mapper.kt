package plugins

import java.util.*

fun Order.toDto(): OrderDto = with(this) { OrderDto(uuid, user.toDto(), equity.toDto(), side, price) }
fun OrderDto.toEntity(): Order = with(this) {
    Order(UUID.randomUUID().toString(), user.toEntity(), equity.toEntity(), side, price)
}

private fun User.toDto(): UserDto = with(this) { UserDto(name, email) }
private fun Equity.toDto(): EquityDto = with(this) { EquityDto(name, orderName, total) }
private fun UserDto.toEntity(): User = with(this) { User(name, email) }
private fun EquityDto.toEntity(): Equity = with(this) { Equity(name, orderName, total) }
