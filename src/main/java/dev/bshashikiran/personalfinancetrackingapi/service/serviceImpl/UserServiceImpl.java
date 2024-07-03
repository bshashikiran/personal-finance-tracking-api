package dev.bshashikiran.personalfinancetrackingapi.service.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.bshashikiran.personalfinancetrackingapi.dto.UserDto;
import dev.bshashikiran.personalfinancetrackingapi.model.User;
import dev.bshashikiran.personalfinancetrackingapi.repository.UserRepo;
import dev.bshashikiran.personalfinancetrackingapi.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepo userRepo;

    @Override
    public User getPersonalDetails(Long mobile) {
        logger.info("Inside UserServiceImpl : getPersonalDetails");
        User user = new User();
        try {
            user = userRepo.findByMobile(mobile);
            return user;
        } catch (Exception e) {
            logger.info("Exception occured while fetching user personal data : {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public String saveUser(UserDto userDto) {
        logger.info("Inside UserServiceImpl : saveUser");
        String responseMsg = "";
        User user = new User();
        try {
            user.setUserName(userDto.getUserName());
            user.setEmail(userDto.getEmail());
            user.setMobile(userDto.getMobileNumber());
            if(userRepo.save(user).getUserId() != null) {
                responseMsg = "Saved user details successfully.";
            } else {
                responseMsg = "Failed to save user details.";
            }
            logger.info(responseMsg);
        } catch (Exception e) {
            logger.info("Exception occured while saving user data : {}", e.getMessage(), e);
            responseMsg = "Exception occured while saving user data.";
        }
        return responseMsg;
    }
    
}
