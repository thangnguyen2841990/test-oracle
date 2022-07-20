package com.codegym.testoracle.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator="some_seq_gen_cart")
    @SequenceGenerator(name="some_seq_gen_cart", sequenceName="SOME_SEQ_CART", allocationSize=1)
    private Long id;

    private Integer quantity;

    private Double payment;

    private Date dateCreated;

    @OneToOne
    private OrderDetails orderDetails;

    @ManyToOne
    private Product product;

    @OneToOne
    private User customer;
}
