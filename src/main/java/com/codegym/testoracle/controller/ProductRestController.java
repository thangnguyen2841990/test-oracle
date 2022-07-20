package com.codegym.testoracle.controller;

import com.codegym.testoracle.model.entity.Product;
import com.codegym.testoracle.model.entity.User;
import com.codegym.testoracle.service.product.IProductService;
import com.codegym.testoracle.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductRestController {
    @Autowired
    private IProductService productService;
    @Autowired
    private IUserService userService;


    @PreAuthorize("hasRole('ROLE_SHOP')")
    @PostMapping("/user/{userId}")
    public ResponseEntity<Product> createNewProduct(@PathVariable Long userId, @RequestBody Product product) {
        Optional<User> userOptional = this.userService.findById(userId);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userOptional.get();
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setQuantity(product.getQuantity());
        newProduct.setDescription(product.getDescription());
        newProduct.setCategory(product.getCategory());
        newProduct.setUser(user);
        newProduct.setImage(product.getImage());
        this.productService.save(newProduct);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
}
