package com.codegym.testoracle.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator="some_seq_gen_role")
    @SequenceGenerator(name="some_seq_gen_role", sequenceName="SOME_SEQ_ROLE", allocationSize=1)
    private Long id;

    private String name;

    public Role(String name) {
        this.name = name;
    }
}
