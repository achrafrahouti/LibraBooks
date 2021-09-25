package com.rest.restApi.services.servicesimpl;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.rest.restApi.entities.CustomUser;
import com.rest.restApi.exceptions.CustomUserNotFoundException;
import com.rest.restApi.exceptions.EmailAlreadyExistsException;
import com.rest.restApi.reposotiry.CustomUserRepository;
import com.rest.restApi.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
public class UserServiceImpl implements UserService{

    private static final Logger LOGGER=LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private CustomUserRepository userRepository;

    @Override
    public CustomUser saveCustomUser(@NotNull @Valid CustomUser user) throws EmailAlreadyExistsException {
        LOGGER.info("saving a User {}",user);
        if(!userRepository.findByEmail(user.getEmail()).isEmpty()){
            throw new EmailAlreadyExistsException();
        }
        return userRepository.save(user);
    }

    @Override
    public CustomUser findOne(Long id) throws CustomUserNotFoundException {
        LOGGER.info("get  a User by  id = "+id);
       if(!userRepository.existsById(id))
       throw new CustomUserNotFoundException("No User with id = "+id);
       return userRepository.getById(id);
    }

    @Override
    public List<CustomUser> findByEmail(String email) {
        LOGGER.info("get  a User by email ="+email);
        return userRepository.findByEmail(email);
    }

    @Override
    public List<CustomUser> findAll() {
        LOGGER.info("get all users");
        return userRepository.findAll();
    }

    @Override
    public void deleteCustomUser(Long id) throws CustomUserNotFoundException {
        LOGGER.info("Delete a User by Id = "+id);
        if(!userRepository.existsById(id))
                    throw new CustomUserNotFoundException();
        userRepository.deleteById(id);
    }

    @Override
    public CustomUser updateUser(Long id, CustomUser user) throws CustomUserNotFoundException {
        LOGGER.info("Updating {}",user);
        return userRepository.findById(id).map(x->{
                                                    x.setFirstName(user.getFirstName());
                                                    x.setLastName(user.getLastName());
                                                    x.setEmail(user.getEmail());
                                                    x.setPassword(user.getPassword());
                                                    x.setRoles(user.getRoles());
                                                    return userRepository.save(x);
                    }).orElseThrow(()->new CustomUserNotFoundException(String.format("User  With id = %s don't exist", id)));
    }
    
}