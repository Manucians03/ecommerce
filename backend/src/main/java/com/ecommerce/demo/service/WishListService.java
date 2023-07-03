package com.ecommerce.demo.service;

import com.ecommerce.demo.dto.ProductDto;
import com.ecommerce.demo.model.WishList;
import com.ecommerce.demo.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ecommerce.demo.repository.ProductRepository;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {
    private final WishListRepository wishListRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;

    public WishList createWishList(WishList wishList) {
        return wishListRepository.save(wishList);
    }

    public List<ProductDto> getWishListForUser(Integer userId) {
        final List<WishList> wishLists = wishListRepository.findAllByUserIdOrderByCreatedDateDesc(userId);
        return wishLists.stream()
                .map(wishList -> productService.mapToDto(productRepository.findById(wishList.getProductId()).orElseThrow()))
                .collect(Collectors.toList());
    }

    public void deleteWishList(Integer wishListId) {
        wishListRepository.deleteById(wishListId);
    }
}