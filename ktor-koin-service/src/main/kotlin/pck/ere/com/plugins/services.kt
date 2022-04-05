package pck.ere.com.plugins

import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

interface OrderService {
    suspend fun findOne(uuid: String): OrderDto?
    suspend fun save(order: OrderDto): String
}

class OrderServiceImpl(private val orderRepository: OrderRepository): OrderService {
    override suspend fun findOne(uuid: String): OrderDto? = orderRepository.findOne(uuid)?.toDto()
    override suspend fun save(order: OrderDto): String = order.toEntity().let {
        orderRepository.save(it)
        it.uuid
    }
}

class OrderRepository(private val db: CoroutineDatabase) {
    suspend fun findOne(uuid: String) = db.getCollection<Order>().findOne(Order::uuid eq uuid)
    suspend fun save(order: Order) = db.getCollection<Order>().insertOne(order)
}
