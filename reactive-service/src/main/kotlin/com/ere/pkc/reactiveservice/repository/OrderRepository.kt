package com.ere.pkc.reactiveservice.repository

import com.ere.pkc.reactiveservice.entity.Order
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface OrderRepository: ReactiveMongoRepository<Order, String> {
    fun findByUuid(uuid: String): Mono<Order>
}
