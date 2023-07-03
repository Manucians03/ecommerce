package com.ecommerce.demo.controller;

import com.ecommerce.demo.dto.ProductDto;
import com.ecommerce.demo.model.User;
import com.ecommerce.demo.model.WishList;
import com.ecommerce.demo.dto.WishListDto;
import com.ecommerce.demo.service.AuthenticationService;
import com.ecommerce.demo.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishListController {
    private final WishListService wishListService;
    private final AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<WishList> addToWishList(@RequestBody WishListDto wishListDto, @RequestParam("token") String token) {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        WishList wishList = new WishList();
        wishList.setUserId(user.getId());
        wishList.setProductId(wishListDto.getProductId());
        wishList.setCreatedDate(new Date());
        return new ResponseEntity<>(wishListService.createWishList(wishList), HttpStatus.CREATED);
    }

    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable String token) {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        return new ResponseEntity<>(wishListService.getWishListForUser(user.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{wishListId}")
    public ResponseEntity<String> deleteWishList(@PathVariable Integer wishListId) {
        wishListService.deleteWishList(wishListId);
        return new ResponseEntity<>("Wishlist deleted successfully", HttpStatus.OK);
    }
}