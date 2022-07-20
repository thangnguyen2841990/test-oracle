package com.codegym.testoracle.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "order_details")
public class OrderDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "some_seq_gen_order_details")
    @SequenceGenerator(name = "some_seq_gen_order_details", sequenceName = "SOME_SEQ_ORDER_DETAILS", allocationSize = 1)
    private Long id;

    private Double totalPayment;

    @OneToOne
    private User user;



}
