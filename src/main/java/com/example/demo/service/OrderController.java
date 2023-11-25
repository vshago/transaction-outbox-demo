package com.example.demo.service;

import com.example.demo.model.CreateOrderRq;
import com.example.demo.model.CreateOrderRs;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public CreateOrderRs createOrder(@RequestBody CreateOrderRq createOrderRq) {
        String uuid = orderService.createOrderAndSendEvent(
                createOrderRq.getProductId(), createOrderRq.getQuantity());

        return new CreateOrderRs(uuid);
    }
}
