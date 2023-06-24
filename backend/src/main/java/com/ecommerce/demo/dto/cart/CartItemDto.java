package com.ecommerce.demo.dto.cart;

import com.ecommerce.demo.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemDto {
    private Integer id;
    private Integer quantity;
    private Product product;
}

