package dev.bshashikiran.personalfinancetrackingapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.bshashikiran.personalfinancetrackingapi.dto.Response;
import dev.bshashikiran.personalfinancetrackingapi.dto.LoginDto;
import dev.bshashikiran.personalfinancetrackingapi.model.UserCredentials;
import dev.bshashikiran.personalfinancetrackingapi.model.UserPersonal;
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

    @GetMapping("/me")
    public ResponseEntity<UserCredentials> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserCredentials currentUser = (UserCredentials) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @PostMapping("/authenticateUser")
    public ResponseEntity<Response> authenticateUser(@RequestBody LoginDto loginDto) {
        return userService.authenticateUser(loginDto);
    }

    @PostMapping("/saveUser")
    public ResponseEntity<Response> saveUser(@RequestBody LoginDto loginDto) {
        return userService.saveUser(loginDto);
    }

    @GetMapping("/getPersonalData")
    public ResponseEntity<UserPersonal> getPersonalDetails(@RequestParam("mobile") Long mobile) {
        return ResponseEntity.ok(userService.getPersonalDetails(mobile));
    }
    

}
