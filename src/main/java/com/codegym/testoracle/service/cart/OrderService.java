package com.codegym.testoracle.service.cart;

import com.codegym.testoracle.model.entity.Order;
import com.codegym.testoracle.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository cartRepository;

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return cartRepository.findAll(pageable);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return cartRepository.save(order);
    }

    @Override
    public void remove(Long id) {
        cartRepository.deleteById(id);
    }
}
