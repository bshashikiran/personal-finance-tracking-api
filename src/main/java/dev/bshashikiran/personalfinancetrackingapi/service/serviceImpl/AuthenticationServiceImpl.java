package dev.bshashikiran.personalfinancetrackingapi.service.serviceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.bshashikiran.personalfinancetrackingapi.dto.LoginDto;
import dev.bshashikiran.personalfinancetrackingapi.dto.LoginResponse;
import dev.bshashikiran.personalfinancetrackingapi.model.UserCredentials;
import dev.bshashikiran.personalfinancetrackingapi.repository.UserCredentialsRepo;
import dev.bshashikiran.personalfinancetrackingapi.service.AuthenticationService;
import dev.bshashikiran.personalfinancetrackingapi.service.JwtService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserCredentialsRepo userCredentialsRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public LoginResponse signup(LoginDto dto) {
        logger.info("Registering user...");
        try {
            UserCredentials user = new UserCredentials();
            user.setUserName(dto.getUserName());
            user.setUserPassword(passwordEncoder.encode(dto.getPassword()));
            userCredentialsRepo.save(user);
            return authenticate(dto);
        } catch (Exception e) {
            logger.error("Exception occured while registering the user : {}", e.getMessage(), e);
        }
        return null;
    }

    @Override
    public LoginResponse authenticate(LoginDto dto) {
        logger.info("Authenticating user...");
        LoginResponse loginResponse = new LoginResponse();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword()));
            Optional<UserCredentials> userCredentialsOptional =  userCredentialsRepo.findByUserName(dto.getUserName());
            if(userCredentialsOptional.isPresent()) {
                UserCredentials authenticatedUser = userCredentialsOptional.get();
                String jwtToken = jwtService.generateToken(authenticatedUser);
                Long expiresIn = jwtService.getExpirationTime();

                loginResponse.setExpiresIn(expiresIn);
                loginResponse.setAuthToken(jwtToken);
                return loginResponse;
            } else {
                logger.warn("User Credentials not found");
            }
        } catch (Exception e) {
            logger.error("Exception occured while authenticating the user : {}", e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Boolean authenticateUserToken(String authToken) {
        logger.info("Authenticating User Token...");
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(jwtService.extractUsername(authToken));
            return jwtService.isTokenValid(authToken, userDetails);
        } catch (Exception e) {
            logger.error("Exception occured while authenticating user token : {}", e.getMessage(), e);
        }
        return false;
    }
    
}
