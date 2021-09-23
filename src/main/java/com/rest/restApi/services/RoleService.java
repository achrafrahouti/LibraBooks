package com.rest.restApi.services;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.rest.restApi.entities.Role;
import com.rest.restApi.exceptions.RoleNotExistsException;

public interface RoleService {
            
    Role saveRole(@NotNull @Valid Role role);
    List<Role> getAll();
    Role getRole(Long Id) throws RoleNotExistsException;
    List<Role> getRoleByName(String name);
	void deleteRole(final Long roleId) throws RoleNotExistsException;

    
}