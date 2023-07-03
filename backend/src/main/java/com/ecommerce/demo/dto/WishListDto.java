package com.ecommerce.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class WishListDto {
    private @NotNull Integer productId;
}
