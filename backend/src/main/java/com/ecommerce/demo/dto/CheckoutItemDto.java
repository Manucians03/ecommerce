package com.ecommerce.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckoutItemDto {
    private Integer quantity;
    private Integer productId;
}

