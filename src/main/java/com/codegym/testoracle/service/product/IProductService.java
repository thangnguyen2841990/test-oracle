package com.codegym.testoracle.service.product;

import com.codegym.testoracle.model.entity.Category;
import com.codegym.testoracle.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProductService {
    Page<Product> findAll(Pageable pageable);

    Optional<Product> findById(Long id);

    void remove(Long id);

    Product save(Product product);
}
