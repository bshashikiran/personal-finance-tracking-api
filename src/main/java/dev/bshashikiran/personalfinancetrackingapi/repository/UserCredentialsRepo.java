package dev.bshashikiran.personalfinancetrackingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.bshashikiran.personalfinancetrackingapi.model.UserCredentials;

public interface UserCredentialsRepo extends JpaRepository<UserCredentials, Long> {

    UserCredentials findByUserName(String userName);
}
