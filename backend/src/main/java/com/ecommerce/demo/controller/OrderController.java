package com.ecommerce.demo.controller;

import com.ecommerce.demo.dto.CheckoutItemDto;
import com.ecommerce.demo.dto.StripeResponseDto;
import com.ecommerce.demo.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponseDto> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
        Session session = orderService.createSession(checkoutItemDtoList);
        StripeResponseDto stripeResponse = new StripeResponseDto(session.getId());
        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
    }
}
