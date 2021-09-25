package com.rest.restApi.services.servicesimpl;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.rest.restApi.entities.Role;
import com.rest.restApi.exceptions.RoleNotExistsException;
import com.rest.restApi.reposotiry.RoleRepository;
import com.rest.restApi.services.RoleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
public class RoleServiceImpl  implements RoleService{
    
    
	private static final Logger LOGGER=LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Autowired
	private RoleRepository rolerepository;
	
	@Override
	@Transactional
	public Role saveRole(@NotNull @Valid Role Role){
		LOGGER.info("Creating {}",Role);

		return rolerepository.save(Role);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Role> getAll() {
		LOGGER.info("Retrieving all Roles");
		return rolerepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Role getRole(Long Id) throws RoleNotExistsException {
		LOGGER.info("Retrieving  {}", Id);
		if(!rolerepository.existsById(Id)) {
			throw new RoleNotExistsException(String.format("there no Role with id =%s", Id));
		}
		return 		rolerepository.getById(Id);
	}

	@Override
	@Transactional
	public void deleteRole(final Long RoleId) throws  RoleNotExistsException{
		LOGGER.info("Deleting {} ",RoleId);
		if(!rolerepository.existsById(RoleId)) {
			throw new RoleNotExistsException(String.format("Role  With id = %s don't exist", RoleId));
		}
		rolerepository.deleteById(RoleId);
		
	}

	@Override
	public List<Role> getRoleByName(String name) {
		LOGGER.info("Get Role BY Name {}",name);
		return rolerepository.findByName(name);
	}
}