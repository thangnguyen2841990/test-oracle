package com.codegym.testoracle.service.category;

import com.codegym.testoracle.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICategoryService {
    Page<Category> findAll(Pageable pageable);

    Optional<Category> findById(Long id);

    void remove(Long id);

    Category save(Category category);
}
