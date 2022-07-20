package com.codegym.testoracle.controller;

import com.codegym.testoracle.model.entity.Category;
import com.codegym.testoracle.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryRestController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<Category>> findAll(@PageableDefault(value = 10)Pageable pageable) {
        return new ResponseEntity<>(this.categoryService.findAll(pageable), HttpStatus.OK );
    }
    @PreAuthorize("hasRole('ROLE_SHOP')")
    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return new ResponseEntity<>(this.categoryService.save(category), HttpStatus.CREATED );
    }
    @PreAuthorize("hasRole('ROLE_SHOP')")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Category> delete(@PathVariable Long categoryId) {
        Optional<Category> categoryOptional = this.categoryService.findById(categoryId);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.categoryService.remove(categoryId);
        return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
    }
 }
