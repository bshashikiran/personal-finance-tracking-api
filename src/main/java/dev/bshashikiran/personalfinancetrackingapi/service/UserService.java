package dev.bshashikiran.personalfinancetrackingapi.service;

import dev.bshashikiran.personalfinancetrackingapi.dto.UserDto;
import dev.bshashikiran.personalfinancetrackingapi.model.User;

public interface UserService {

    User getPersonalDetails(Long mobile);

    String saveUser(UserDto userDto);
    
}
