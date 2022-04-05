package pck.ere.com.classicorderservice.repository

import org.springframework.data.mongodb.repository.MongoRepository
import pck.ere.com.classicorderservice.entity.Order

interface OrderRepository: MongoRepository<Order, String> {
    fun findByUuid(uuid: String): Order?
}
