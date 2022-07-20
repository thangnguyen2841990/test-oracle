package com.codegym.testoracle.controller;

import com.codegym.testoracle.model.dto.OrderRequest;
import com.codegym.testoracle.model.entity.Order;
import com.codegym.testoracle.model.entity.OrderDetails;
import com.codegym.testoracle.model.entity.Product;
import com.codegym.testoracle.model.entity.User;
import com.codegym.testoracle.service.cart.IOrderService;
import com.codegym.testoracle.service.orderDetails.IOrderDetailsService;
import com.codegym.testoracle.service.product.IProductService;
import com.codegym.testoracle.service.user.IUserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/orders")
public class OrderRestController {

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderDetailsService orderDetailsService;

    @Autowired
    private IProductService productService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<Order> createNewCart(@PathVariable Long customerId, @RequestBody OrderRequest order, @RequestParam(value = "productId") Long productId) {
        User customer = this.userService.findById(customerId).get();
        Product product = this.productService.findById(productId).get();
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setUser(customer);
        orderDetails.setTotalPayment(0.0);
        this.orderDetailsService.save(orderDetails);
        Order newOrder = new Order();
        newOrder.setCustomer(customer);
        newOrder.setQuantity(order.getQuantity());
        newOrder.setDateCreated(new Date());
        newOrder.setProduct(product);
        newOrder.setPayment(product.getPrice() * order.getQuantity());
        newOrder.setOrderDetails(orderDetails);
        this.orderService.save(newOrder);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/update/orderDetails/{orderDetailsId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderDetailsId, @RequestBody OrderRequest orderRequest, @RequestParam(value = "productId") Long productId) {
        Optional<OrderDetails> orderDetailsOptional = orderDetailsService.findById(orderDetailsId);
        if (!orderDetailsOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Product product = this.productService.findById(productId).get();
        Order newOrder = new Order();
        newOrder.setCustomer(orderDetailsOptional.get().getUser());
        newOrder.setQuantity(orderRequest.getQuantity());
        newOrder.setDateCreated(new Date());
        newOrder.setProduct(product);
        newOrder.setPayment(product.getPrice() * orderRequest.getQuantity());
        newOrder.setOrderDetails(orderDetailsOptional.get());
        this.orderService.save(newOrder);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/delete/order/{orderId}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long orderId) {
        Optional<Order> orderOptional = orderService.findById(orderId);
        if (!orderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.orderService.remove(orderId);
        return new ResponseEntity<>(orderOptional.get(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/addQuantity/order/{orderId}")
    public ResponseEntity<Order> addQuantity(@PathVariable Long orderId) {
        Optional<Order> orderOptional = this.orderService.findById(orderId);
        if (!orderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Order order = orderOptional.get();
        if (order.getQuantity() >= 0) {
            order.setQuantity(order.getQuantity() + 1);
            order.setPayment(order.getQuantity() * order.getProduct().getPrice());
            this.orderService.save(order);
        }
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/subQuantity/order/{orderId}")
    public ResponseEntity<Order> subQuantity(@PathVariable Long orderId) {
        Optional<Order> orderOptional = this.orderService.findById(orderId);
        if (!orderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Order order = orderOptional.get();
        if (order.getQuantity() >= 1) {
            order.setQuantity(order.getQuantity() - 1);
            order.setPayment(order.getQuantity() * order.getProduct().getPrice());
            this.orderService.save(order);
        }
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
