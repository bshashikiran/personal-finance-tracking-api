package dev.bshashikiran.personalfinancetrackingapi.service;

import org.springframework.http.ResponseEntity;

import dev.bshashikiran.personalfinancetrackingapi.dto.LoginDto;
import dev.bshashikiran.personalfinancetrackingapi.model.UserPersonal;

public interface UserService {

    ResponseEntity<String> authenticateUser(LoginDto loginDto);
    
    ResponseEntity<String> saveUser(LoginDto loginDto);
    
    UserPersonal getPersonalDetails(Long mobile);
}
