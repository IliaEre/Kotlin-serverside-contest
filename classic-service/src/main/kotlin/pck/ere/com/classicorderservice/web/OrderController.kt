package pck.ere.com.classicorderservice.web

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pck.ere.com.classicorderservice.domain.OrderDto
import pck.ere.com.classicorderservice.service.OrderService

@RestController
@RequestMapping("/v1/order")
class OrderController(private val orderService: OrderService) {

    @GetMapping("/{name}")
    fun findByName(@PathVariable name: String): ResponseEntity<OrderDto> =
        orderService.findByName(name)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()


    @PostMapping
    fun save(@RequestBody order: OrderDto) = orderService.save(order)

}