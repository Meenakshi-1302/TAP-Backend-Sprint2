package com.rts.tap.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rts.tap.dao.RoleDao;
import com.rts.tap.model.Role;
import com.rts.tap.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao repo;

	@Override
	public void addRole(Role role) {
		repo.save(role);	
	}

	@Override
	public List<Role> getAllRole() {
		return repo.getAllRole();
	}

	@Override
	public void updateRole(Role role) {
		repo.update(role);
		
	}

}
