package com.ecommerce.demo.service;

import com.ecommerce.demo.dto.CheckoutItemDto;
import com.ecommerce.demo.model.Product;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;  
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ecommerce.demo.repository.ProductRepository;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductRepository productRepository;

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;
    @Value("${BASE_URL}")
    private String baseUrl;
    public Session createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
        String successUrl = baseUrl + "/success";
        String failureUrl = baseUrl + "/failure";
        Stripe.apiKey = apiKey;
        List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();
        for(CheckoutItemDto checkoutItemDto: checkoutItemDtoList) {
            Product product = productRepository.findById(checkoutItemDto.getProductId()).orElseThrow();
            sessionItemList.add(createSessionLineItem(product, checkoutItemDto));
        }
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failureUrl)
                .setSuccessUrl(successUrl)
                .addAllLineItem(sessionItemList)
                .build();
        return Session.create(params);
    }

    private SessionCreateParams.LineItem createSessionLineItem(Product product, CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem
                .builder()
                .setPriceData(createPriceData(product))
                .setQuantity(Long.parseLong(String.valueOf(checkoutItemDto.getQuantity())))
                .build();
    }

    private SessionCreateParams.LineItem.PriceData createPriceData(Product product) {
        return SessionCreateParams.LineItem.PriceData
                .builder()
                .setCurrency("usd")
                .setUnitAmount((long)(product.getPrice().longValue()*100))
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(product.getName())
                                .build()
                )
                .build();
    }
}