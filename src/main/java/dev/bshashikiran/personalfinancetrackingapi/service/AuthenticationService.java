package dev.bshashikiran.personalfinancetrackingapi.service;

import dev.bshashikiran.personalfinancetrackingapi.dto.LoginDto;
import dev.bshashikiran.personalfinancetrackingapi.dto.LoginResponse;

public interface AuthenticationService {

    LoginResponse signup(LoginDto registerUserDto);

    LoginResponse authenticate(LoginDto loginUserDto);
    
}