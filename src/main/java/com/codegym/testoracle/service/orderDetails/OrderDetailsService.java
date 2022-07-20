package com.codegym.testoracle.service.orderDetails;

import com.codegym.testoracle.model.entity.OrderDetails;
import com.codegym.testoracle.repository.IOrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailsService implements IOrderDetailsService{
    @Autowired
    private IOrderDetailsRepository orderDetailsRepository;

    @Override
    public Page<OrderDetails> findAll(Pageable pageable) {
        return orderDetailsRepository.findAll(pageable);
    }

    @Override
    public Optional<OrderDetails> findById(Long id) {
        return orderDetailsRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        orderDetailsRepository.deleteById(id);
    }

    @Override
    public OrderDetails save(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }
}
