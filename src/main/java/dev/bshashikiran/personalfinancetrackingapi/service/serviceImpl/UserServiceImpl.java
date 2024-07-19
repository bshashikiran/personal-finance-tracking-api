package dev.bshashikiran.personalfinancetrackingapi.service.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.bshashikiran.personalfinancetrackingapi.dto.AuthenticationResponse;
import dev.bshashikiran.personalfinancetrackingapi.dto.LoginDto;
import dev.bshashikiran.personalfinancetrackingapi.exception.InvalidPasswordException;
import dev.bshashikiran.personalfinancetrackingapi.exception.UserNotFoundException;
import dev.bshashikiran.personalfinancetrackingapi.model.UserCredentials;
import dev.bshashikiran.personalfinancetrackingapi.model.UserPersonal;
import dev.bshashikiran.personalfinancetrackingapi.repository.UserCredentialsRepo;
import dev.bshashikiran.personalfinancetrackingapi.repository.UserPersonalRepo;
import dev.bshashikiran.personalfinancetrackingapi.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserPersonalRepo userPersonalRepo;

    @Autowired
    private UserCredentialsRepo userCredentialsRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<AuthenticationResponse> authenticateUser(LoginDto loginDto) {
        logger.info("Authenticating User...");
        try {
            UserCredentials userCredentials = userCredentialsRepo.findByUserName(loginDto.getUserName());
            if(userCredentials != null) {
                if(passwordEncoder.matches(loginDto.getPassword(), userCredentials.getUserPassword())) {
                    return ResponseEntity.ok(new AuthenticationResponse(200, "Login Successful"));
                } else {
                    throw new InvalidPasswordException("Incorrect Password");
                }
            } else {
                throw new UserNotFoundException("Username not found");
            }
        } 
        catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AuthenticationResponse(404, e.getMessage()));
        }
        catch (InvalidPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthenticationResponse(401, e.getMessage()));
        }
        catch (Exception e) {
            logger.error("Exception occured while authenticating user : {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthenticationResponse(500, e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<AuthenticationResponse> saveUser(LoginDto loginDto) {
        logger.info("Saving user record...");
        try {
            UserCredentials userCredentials = new UserCredentials();
            userCredentials.setUserName(loginDto.getUserName());
            userCredentials.setUserPassword(passwordEncoder.encode(loginDto.getPassword()));
            userCredentialsRepo.save(userCredentials);
            return ResponseEntity.ok(new AuthenticationResponse(200, "User Record Saved Successfully"));
        } catch (Exception e) {
            logger.error("Exception occured while saving user record: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthenticationResponse(500, e.getMessage()));
        }
    }

    @Override
    public UserPersonal getPersonalDetails(Long mobile) {
        logger.info("Getting User Personal Details...");
        UserPersonal user = new UserPersonal();
        try {
            user = userPersonalRepo.findByMobile(mobile);
            return user;
        } catch (Exception e) {
            logger.info("Exception occured while fetching user personal data : {}", e.getMessage(), e);
            return null;
        }
    }
}
