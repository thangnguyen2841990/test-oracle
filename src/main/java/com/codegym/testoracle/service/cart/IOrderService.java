package com.codegym.testoracle.service.cart;

import com.codegym.testoracle.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IOrderService {
    Page<Order> findAll(Pageable pageable);

    Optional<Order> findById(Long id);

    Order save(Order order);

    void remove (Long id);
}
