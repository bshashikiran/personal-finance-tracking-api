package dev.bshashikiran.personalfinancetrackingapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.bshashikiran.personalfinancetrackingapi.dto.UserDto;
import dev.bshashikiran.personalfinancetrackingapi.model.User;
import dev.bshashikiran.personalfinancetrackingapi.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getPersonalDetails")
    public ResponseEntity<User> getPersonalDetails(@RequestParam("mobile") Long mobile) {
        return ResponseEntity.ok(userService.getPersonalDetails(mobile));
    }
    
    @PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.saveUser(userDto));
    }
    



}
