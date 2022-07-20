package com.codegym.testoracle.service.orderDetails;

import com.codegym.testoracle.model.entity.OrderDetails;
import com.codegym.testoracle.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IOrderDetailsService {
    Page<OrderDetails> findAll(Pageable pageable);

    Optional<OrderDetails> findById(Long id);

    void remove(Long id);

    OrderDetails save(OrderDetails orderDetails);
}
