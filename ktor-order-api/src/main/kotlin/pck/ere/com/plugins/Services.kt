package pck.ere.com.plugins

import com.mongodb.client.result.InsertOneResult
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

interface OrderService {
    suspend fun findOne(name: String): Order?
    suspend fun save(order: Order): InsertOneResult
}

class OrderServiceImpl(val orderRepository: OrderRepository): OrderService {
    override suspend fun findOne(name: String): Order? = orderRepository.findOne(name)
    override suspend fun save(order: Order) = orderRepository.save(order)
}

class OrderRepository(private val db: CoroutineDatabase) {
    suspend fun findOne(name: String) = db.getCollection<Order>().findOne(Order::uuid eq name)
    suspend fun save(order: Order) = db.getCollection<Order>().insertOne(order)
}
