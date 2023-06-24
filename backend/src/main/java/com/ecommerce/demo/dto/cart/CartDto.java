package com.ecommerce.demo.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@SuppressWarnings("unused")
@NoArgsConstructor
@Data
public class CartDto {
    private Integer id;
    private @NotNull Integer productId;
    private @NotNull Integer quantity;
}
