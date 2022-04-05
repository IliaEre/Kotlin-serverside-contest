package com.ere.pkc.reactiveservice.service

import org.springframework.stereotype.Service
import com.ere.pkc.reactiveservice.domain.OrderDto
import com.ere.pkc.reactiveservice.repository.OrderRepository
import reactor.core.publisher.Mono

interface OrderService {
    fun findByName(uuid: String): Mono<OrderDto>
    fun save(order: OrderDto): Mono<String>
}

@Service
class OrderServiceImpl(
    private val orderMapper: OrderMapper,
    private val orderRepo: OrderRepository
): OrderService {

    override fun findByName(uuid: String): Mono<OrderDto> = orderRepo.findByUuid(uuid).map(orderMapper::toDto)

    override fun save(order: OrderDto): Mono<String> {
        val entity = orderMapper.toEntity(order)
        return orderRepo.save(entity).map {
            entity.uuid
        }
    }

}
