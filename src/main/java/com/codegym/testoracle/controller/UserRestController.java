package com.codegym.testoracle.controller;

import com.codegym.testoracle.model.dto.UserInfoForm;
import com.codegym.testoracle.model.entity.User;
import com.codegym.testoracle.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserRestController {
    @Autowired
    private IUserService userService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<User> updateUserInfo(@PathVariable Long userId, @RequestBody UserInfoForm userInfoForm) {
        Optional<User> userOptional = this.userService.findById(userId);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userOptional.get();
        user.setEmail(userInfoForm.getEmail());
        user.setAddress(userInfoForm.getAddress());
        user.setBirthDay(userInfoForm.getBirthDay());
        user.setPhoneNumber(userInfoForm.getPhoneNumber());
        this.userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
