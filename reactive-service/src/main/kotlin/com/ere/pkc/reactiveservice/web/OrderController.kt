package com.ere.pkc.reactiveservice.web

import org.springframework.web.bind.annotation.*
import com.ere.pkc.reactiveservice.domain.OrderDto
import com.ere.pkc.reactiveservice.service.OrderService
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/v2/order")
class OrderController(private val orderService: OrderService) {

    @GetMapping("/{name}")
    fun findByName(@PathVariable name: String): Mono<OrderDto> = orderService.findByName(name)

    @PostMapping
    fun save(@RequestBody order: OrderDto) = orderService.save(order)

}