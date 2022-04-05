package pck.ere.com.classicorderservice.service

import org.springframework.stereotype.Service
import pck.ere.com.classicorderservice.domain.OrderDto
import pck.ere.com.classicorderservice.repository.OrderRepository

interface OrderService {
    fun findByName(uuid: String): OrderDto?
    fun save(order: OrderDto): String
}

@Service
class OrderServiceImpl(
    private val orderMapper: OrderMapper,
    private val orderRepo: OrderRepository
): OrderService {

    override fun findByName(uuid: String): OrderDto? {
        val order = orderRepo.findByUuid(uuid)
        return order?.let { orderMapper.toDto(it) }
    }

    override fun save(order: OrderDto): String {
        val entity = orderMapper.toEntity(order)
        orderRepo.save(entity)
        return entity.uuid
    }

}
