package com.korit.jpa_study.controller;

import com.korit.jpa_study.dto.Request.AddUserReqDto;
import com.korit.jpa_study.dto.Request.EditUserReqDto;
import com.korit.jpa_study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody AddUserReqDto addUserReqDto) {
        return ResponseEntity.ok(userService.add(addUserReqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getUserAll() {
        return ResponseEntity.ok(userService.getUserAll());
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserByUserId(userId));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editUser(@RequestBody EditUserReqDto editUserReqDto) {
        return ResponseEntity.ok(userService.editUser(editUserReqDto));
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeUser(@RequestParam Integer userId) {
        return ResponseEntity.ok(userService.removeUser(userId));
    }

}
