package com.rest.restApi.reposotiry;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.rest.restApi.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
    
    List<Role> findByName(String name);
}