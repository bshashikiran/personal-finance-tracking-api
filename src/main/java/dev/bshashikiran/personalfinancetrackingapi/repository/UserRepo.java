package dev.bshashikiran.personalfinancetrackingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.bshashikiran.personalfinancetrackingapi.model.User;


public interface UserRepo extends JpaRepository<User, Long> {

    User findByMobile(Long mobile);

}
