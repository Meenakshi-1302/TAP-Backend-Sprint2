package com.rts.tap.dao;

import java.util.List;
import com.rts.tap.model.Role;

public interface RoleDao {

	void save(Role role);
	List<Role> getAllRole();
	void update(Role role);
	Role getRoleById(Long id);
	
}

