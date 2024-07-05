package dev.bshashikiran.personalfinancetrackingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.bshashikiran.personalfinancetrackingapi.model.UserPersonal;

public interface UserPersonalRepo extends JpaRepository<UserPersonal, Long> {

    UserPersonal findByMobile(Long mobile);

}   
