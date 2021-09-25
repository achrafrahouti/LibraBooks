package com.rest.restApi.services;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.rest.restApi.entities.CustomUser;
import com.rest.restApi.exceptions.CustomUserNotFoundException;
import com.rest.restApi.exceptions.EmailAlreadyExistsException;

public interface UserService {  
    CustomUser saveCustomUser(@NotNull @Valid  CustomUser user) throws EmailAlreadyExistsException;
    CustomUser findOne(Long id) throws CustomUserNotFoundException;
    CustomUser updateUser(Long id,CustomUser user) throws CustomUserNotFoundException;
    List<CustomUser> findByEmail(String email);
    List<CustomUser> findAll();
    void deleteCustomUser(Long id) throws CustomUserNotFoundException;
}