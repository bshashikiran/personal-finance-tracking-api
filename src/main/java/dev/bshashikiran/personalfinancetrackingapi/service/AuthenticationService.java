package dev.bshashikiran.personalfinancetrackingapi.service;

import dev.bshashikiran.personalfinancetrackingapi.dto.LoginDto;
import dev.bshashikiran.personalfinancetrackingapi.dto.LoginResponse;
import dev.bshashikiran.personalfinancetrackingapi.model.UserCredentials;

public interface AuthenticationService {

    UserCredentials signup(LoginDto registerUserDto);

    LoginResponse authenticate(LoginDto loginUserDto);
    
}