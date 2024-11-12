package com.rts.tap.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rts.tap.constants.APIConstants;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.model.Role;
import com.rts.tap.service.RoleService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping(APIConstants.BASE_URL)

public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping(APIConstants.ADD_ROLE_URL)
	public String addRole(@RequestBody Role role) {
		try {	        
			roleService.addRole(role);
			return MessageConstants.SUCCESS_MESSAGE;
		} catch (Exception e) {
			return MessageConstants.FAILURE_MESSAGE;
		}
	}
	
	 @GetMapping(APIConstants.GETALL_ROLE_URL)
	    public List<Role> viewAllRole() {
	        return roleService.getAllRole();
	    }
	 
	 
	 @PutMapping(APIConstants.UPDATE_ROLE_URL)
		public String updateRole(@RequestBody Role role) {
			try {
				roleService.updateRole(role);
				return MessageConstants.SUCCESS_MESSAGE;
			} catch (Exception e) {
				return MessageConstants.FAILURE_MESSAGE;
			}

		}
	 
//	 @PutMapping("/{id}")
//	 public ResponseEntity<String> updateShipStatus(@PathVariable("id") int id, @RequestBody Map<String, String> updates) {
//	     String status = updates.get("status");
//	     if (status == null || status.trim().isEmpty()) {
//	         return ResponseEntity.badRequest().body("Status is required");
//	     }
//
//	     try {
//	         Ship ship = serviceimpl.getShip(id);
//	         if (ship == null) {
//	             return ResponseEntity.notFound().build();
//	         }
//	         ship.setShipStatus(status);
//	         serviceimpl.updateShip(ship); 
//	         return ResponseEntity.ok("Ship status updated successfully");
//	     } catch (Exception e) {
//	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating ship status");
//	     }
//	 }



//	 @DeleteMapping("/{id}")
//	    public ResponseEntity<String> deleteShip(@PathVariable("id") int id) {
//	        try {
//	            serviceimpl.deleteShip(id);
//	            return ResponseEntity.ok("Ship deleted successfully");
//	        } catch (Exception e) {
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting ship");
//	        }
//	 }
	
	
    
}

