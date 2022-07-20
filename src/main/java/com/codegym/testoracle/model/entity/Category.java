package com.codegym.testoracle.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
@Data
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator="some_seq_gen_category")
    @SequenceGenerator(name="some_seq_gen_category", sequenceName="SOME_SEQ_CATEGORY", allocationSize=1)
    private Long id;

    private String name;

    public Category(String name) {
        this.name = name;
    }
}
