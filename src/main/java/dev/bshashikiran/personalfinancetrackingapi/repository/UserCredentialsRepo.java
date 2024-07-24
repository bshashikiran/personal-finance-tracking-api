package dev.bshashikiran.personalfinancetrackingapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.bshashikiran.personalfinancetrackingapi.model.UserCredentials;

public interface UserCredentialsRepo extends JpaRepository<UserCredentials, Long> {

    Optional<UserCredentials> findByUserName(String userName);
}
