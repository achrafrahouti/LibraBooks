package com.rest.restApi.reposotiry;

import java.util.List;

import com.rest.restApi.entities.CustomUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomUserRepository extends JpaRepository<CustomUser,Long> {
    
    List<CustomUser> findByEmail(String email);
}