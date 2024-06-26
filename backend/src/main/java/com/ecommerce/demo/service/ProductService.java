package com.ecommerce.demo.service;

import com.ecommerce.demo.dto.ProductDto;
import com.ecommerce.demo.exception.ProductNotExistException;
import com.ecommerce.demo.model.Category;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.repository.CategoryRepository;
import com.ecommerce.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public Product createProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category does not exist with id: " + productDto.getCategoryId()));
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setName(productDto.getName());
        product.setCategory(category);
        product.setPrice(productDto.getPrice());
        return productRepository.save(product);
    }

    public List<ProductDto> getAllProducts() {
        List<Product> all = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product: all){
            productDtos.add(mapToDto(product));
        }
        return productDtos;
    }

    public ProductDto mapToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

    public ProductDto updateProduct(ProductDto productDto, String productId) {
        Product product = productRepository.findById(Integer.parseInt(productId))
                .orElseThrow(() -> new RuntimeException("Product does not exist with id: " + productId));
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category does not exist with id: " + productDto.getCategoryId()));

        product.setName(productDto.getName());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(category);
        productRepository.save(product);
        return mapToDto(product);
    }

    public Product findById(Integer productId) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty()){
            throw new ProductNotExistException("Product is not valid " +productId);
        }
        return product.get();
    }

    public void deleteProduct(String productId) {
        Product product = productRepository.findById(Integer.parseInt(productId))
                .orElseThrow(() -> new RuntimeException("Product does not exist with id: " + productId));
        productRepository.delete(product);
    }
}

