package com.ecommerce.demo.dto.cart;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class CartDto {
    private @NotNull Integer productId;
    private @NotNull Integer quantity;
}
