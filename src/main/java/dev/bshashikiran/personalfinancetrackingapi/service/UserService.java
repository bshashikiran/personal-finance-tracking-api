package dev.bshashikiran.personalfinancetrackingapi.service;

import org.springframework.http.ResponseEntity;

import dev.bshashikiran.personalfinancetrackingapi.dto.Response;
import dev.bshashikiran.personalfinancetrackingapi.dto.LoginDto;
import dev.bshashikiran.personalfinancetrackingapi.model.UserPersonal;

public interface UserService {

    ResponseEntity<Response> authenticateUser(LoginDto loginDto);
    
    ResponseEntity<Response> saveUser(LoginDto loginDto);
    
    UserPersonal getPersonalDetails(Long mobile);
}
