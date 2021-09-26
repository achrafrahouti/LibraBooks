package com.rest.restApi.controllers;

import java.util.ArrayList;
import java.util.List;

import com.rest.restApi.entities.CustomUser;
import com.rest.restApi.exceptions.CustomUserNotFoundException;
import com.rest.restApi.exceptions.EmailAlreadyExistsException;
import com.rest.restApi.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {

    private Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Retrieve a list of users")
    @GetMapping("/users")
    public ResponseEntity<List<CustomUser>> getAll() {

        LOGGER.info("Retrieve Request to get All users");
        try {
            List<CustomUser> users = new ArrayList<CustomUser>();

            userService.findAll().forEach(users::add);

            if (users.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "save a user")
    @PostMapping(value = "/users", consumes = { "application/json" })
    public ResponseEntity<CustomUser> create(@RequestBody CustomUser user) throws EmailAlreadyExistsException {
        try {
            CustomUser savedUser = userService.saveCustomUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (EmailAlreadyExistsException e) {
            throw new EmailAlreadyExistsException("The Email " + user.getEmail() + " already exist");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @ApiOperation(value = "Retrieve a user by id")
    @GetMapping("/users/{id}")
    public ResponseEntity<CustomUser> getById(@PathVariable("id") Long id) throws CustomUserNotFoundException {
        LOGGER.info("Request to get one user");
        CustomUser user = userService.findOne(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Update a user")
    @PutMapping("/users/{id}")
    public ResponseEntity<CustomUser> update(@PathVariable("id") Long id, @RequestBody CustomUser user)
            throws CustomUserNotFoundException {

        LOGGER.info("Received request to update a  User  {}", id);
        CustomUser updated = userService.updateUser(id, user);
        return new ResponseEntity<>(updated, HttpStatus.OK);

    }

    @ApiOperation(value = "Delete a user by Id")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) throws CustomUserNotFoundException {
        try {
            userService.deleteCustomUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomUserNotFoundException e) {
            throw new CustomUserNotFoundException("No User with id = " + id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}