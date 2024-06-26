package com.ecommerce.demo.service;

import com.ecommerce.demo.dto.cart.CartDto;
import com.ecommerce.demo.dto.cart.CartItemDto;
import com.ecommerce.demo.dto.cart.CartResponseDto;
import com.ecommerce.demo.exception.CustomException;
import com.ecommerce.demo.exception.ProductNotExistException;
import com.ecommerce.demo.model.Cart;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.model.User;
import com.ecommerce.demo.repository.CartRepository;
import com.ecommerce.demo.repository.ProductRepository;
// import com.ecommerce.demo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final AuthenticationService authenticationService;
    private final ProductRepository productRepository;
    public Cart addToCart(@NotNull CartDto cartDto, String token) {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Product product = productRepository.findById(cartDto.getProductId())
                .orElseThrow(()->new ProductNotExistException("Product is not valid " + cartDto.getProductId()));
        Cart cartExists = cartRepository.findByUserIdAndProductId(user.getId(), cartDto.getProductId());
        if (cartExists != null) {
            cartExists.setQuantity(cartExists.getQuantity() + cartDto.getQuantity());
            return cartRepository.save(cartExists);
        } else {
            Cart cart = new Cart();
            cart.setProduct(product);
            cart.setUser(user);
            cart.setQuantity(cartDto.getQuantity());
            cart.setCreatedDate(new Date());
            return cartRepository.save(cart);
        }
    }

    public CartResponseDto getAllItemsFromCart(String token) {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        return this.listCartItems(user);
    }

    private CartResponseDto listCartItems(User user) {
        final List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        Double totalCost =0D;
        for(Cart cart: cartList){
            CartItemDto cartItemDto = new CartItemDto();
            cartItemDto.setProduct(cart.getProduct());
            cartItemDto.setQuantity(cart.getQuantity());
            cartItemDto.setId(cart.getId());
            totalCost+=cartItemDto.getQuantity() * cart.getProduct().getPrice();

            cartItems.add(cartItemDto);
        }
        CartResponseDto cartResponseDto = new CartResponseDto();
        cartResponseDto.setTotalCost(totalCost);
        cartResponseDto.setCartItems(cartItems);
        return cartResponseDto;
    }

    public Cart deleteCartItem(String cartItemId, String token) {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Cart cart = cartRepository.findById(Integer.parseInt(cartItemId))
                .orElseThrow(() -> new CustomException("Cart item with id: " + cartItemId + " is not valid"));
        if(cart.getUser() != user)
            throw new CustomException("Cart item with id: "+ cartItemId + "does not belong to user");
        cartRepository.delete(cart);
        return cart;
    }
}