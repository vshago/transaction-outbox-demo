package com.example.demo.model;

import lombok.Data;

@Data
public class CreateOrderRq {
    private Integer productId;
    private Integer quantity;
}
