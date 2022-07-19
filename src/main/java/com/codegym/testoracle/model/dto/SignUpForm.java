package com.codegym.testoracle.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpForm {
    private String username;
    private String password;
    private String confirmPassword;
    private String role;
}
