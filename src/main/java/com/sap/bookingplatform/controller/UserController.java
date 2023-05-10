package com.sap.bookingplatform.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.bookingplatform.data.entities.Account;
import com.sap.bookingplatform.data.entities.Address;
import com.sap.bookingplatform.data.entities.Preference;
import com.sap.bookingplatform.data.entities.Users;
import com.sap.bookingplatform.service.UserService;

@RestController
@RequestMapping("/platform")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Users> getUser(@PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(userId));
    }

    @PostMapping("/user")
    public ResponseEntity<Users> addUser(@Valid @RequestBody Users user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(user));
    }

    @PostMapping("/user/{id}/preference")
    public ResponseEntity<Users> addPreference(@RequestBody Preference preference, @PathVariable int id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addPreference(preference, id));
    }

    @GetMapping("/user/{id}/preference")
    public Preference getPreference(@PathVariable int id) {
        return userService.getPreference(id);
    }

    @PostMapping("/user/{id}/account")
    public ResponseEntity<Users> addAccount(@RequestBody Account account, @PathVariable int id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addAccount(account, id));
    }

    @GetMapping("/user/{id}/account")
    public Account getAccount(@PathVariable int id) {
        return userService.getAccount(id);
    }

    @PostMapping("/user/{id}/address")
    public ResponseEntity<Users> addAAddress(@RequestBody Address address, @PathVariable int id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addAAddress(address, id));
    }

    @GetMapping("/user/{id}/address")
    public Address getAddress(@PathVariable int id) {
        return userService.getAddress(id);
    }

}
