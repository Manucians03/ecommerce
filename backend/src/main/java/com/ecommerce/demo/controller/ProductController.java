package com.ecommerce.demo.controller;

import com.ecommerce.demo.dto.ProductDto;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
    @PostMapping("/edit/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String productId, @RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.updateProduct(productDto, productId), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }
}
