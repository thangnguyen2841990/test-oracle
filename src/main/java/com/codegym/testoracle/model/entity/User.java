package com.codegym.testoracle.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator="some_seq_gen_user")
    @SequenceGenerator(name="some_seq_gen_user", sequenceName="SOME_SEQ_USER", allocationSize=1)
    Long id;

    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    private String address;

    private String birthDay;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role")
    private List<Role> roles;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
