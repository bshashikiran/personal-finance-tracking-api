package dev.bshashikiran.personalfinancetrackingapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String userName;
    private String email;
    private Long mobileNumber;
}
