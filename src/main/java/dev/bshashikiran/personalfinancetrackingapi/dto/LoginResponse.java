package dev.bshashikiran.personalfinancetrackingapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String authToken;
    private Long expiresIn;
}
