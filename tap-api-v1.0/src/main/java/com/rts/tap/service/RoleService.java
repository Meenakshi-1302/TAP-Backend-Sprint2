package com.rts.tap.service;

import java.util.List;
import com.rts.tap.model.Role;

public interface RoleService {
	
	void addRole(Role role);
	List<Role> getAllRole();
	void updateRole(Role role);
     
}
