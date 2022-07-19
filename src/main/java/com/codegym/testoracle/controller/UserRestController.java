package com.codegym.testoracle.controller;

import com.codegym.testoracle.model.entity.User;
import com.codegym.testoracle.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserRestController {
    @Autowired
    private IUserService userService;

}
