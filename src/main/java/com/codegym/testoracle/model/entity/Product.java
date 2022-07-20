package com.codegym.testoracle.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator="some_seq_gen_product")
    @SequenceGenerator(name="some_seq_gen_product", sequenceName="SOME_SEQ_PRODUCT", allocationSize=1)
    private Long id;

    private String name;

    private Double price;

    private Integer quantity;

    private String description;

    private String image;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}
