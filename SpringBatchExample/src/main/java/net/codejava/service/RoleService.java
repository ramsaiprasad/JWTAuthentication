package net.codejava.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.codejava.model.RoleJava;
import net.codejava.model.Testjav;
import net.codejava.tes.RoleJavaRepository;
import net.codejava.tes.TestJavRepository;

@Service
public class RoleService {
	
      @Autowired
	    private TestJavRepository userRepository;
      @Autowired
	    private RoleJavaRepository roleRepository;

	 

	    /** Create a new role  */
	    @Transactional
	    public ResponseEntity<Object> addRole(RoleJava role)  {

	    	RoleJava newRole = new RoleJava();
	        newRole.setName(role.getName());
	        newRole.setDescription(role.getDescription());
	        List<RoleJava> roleList = new ArrayList<>();
	        roleList.add(newRole);
	        for(int i=0; i< role.getUsers().size(); i++){
	            if(!userRepository.findByEmail(role.getUsers().get(i).getEmail()).isPresent()) {
	                Testjav newUser = role.getUsers().get(i);
	                newUser.setRoles(roleList);
	                Testjav savedUser = userRepository.save(newUser);
	                if(! userRepository.findById(savedUser.getId()).isPresent())
	                    return ResponseEntity.unprocessableEntity().body("Role Creation Failed");
	            }
	             else  return   ResponseEntity.unprocessableEntity().body("User with email Id is already Present");
	        }
	        return ResponseEntity.ok("Successfully created Role");
	    }


	    /** Delete a specified role given the id */
	    public ResponseEntity<Object> deleteRole(Long id) {
	        if(roleRepository.findById(id).isPresent()){
	            roleRepository.deleteById(id);
	            if(roleRepository.findById(id).isPresent()){
	                return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
	            } else return ResponseEntity.ok().body("Successfully deleted specified record");
	        } else
	            return ResponseEntity.unprocessableEntity().body("No Records Found");
	    }


	    /** Update a Role */
	    public ResponseEntity<Object> updateRole(Long id, RoleJava role) {
	        if(roleRepository.findById(id).isPresent()){
	        	RoleJava newRole = roleRepository.findById(id).get();
	            newRole.setName(role.getName());
	            newRole.setDescription(role.getDescription());
	            RoleJava savedRole = roleRepository.save(newRole);
	            if(roleRepository.findById(savedRole.getId()).isPresent())
	                return ResponseEntity.accepted().body("Role saved successfully");
	            else return ResponseEntity.badRequest().body("Failed to update Role");

	        } else return ResponseEntity.unprocessableEntity().body("Specified Role not found");
	    }
	}



