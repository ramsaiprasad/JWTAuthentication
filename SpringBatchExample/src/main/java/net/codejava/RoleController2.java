package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.codejava.model.RoleJava;
import net.codejava.service.RoleService;
import net.codejava.tes.RoleJavaRepository;
@RestController
@RequestMapping("/rolee")
public class RoleController2 {
	@Autowired
	private RoleService roleService;
	@Autowired
    private RoleJavaRepository roleRepository;

   

    @PostMapping("/role/create")
    public ResponseEntity<Object> createRole(@RequestBody RoleJava role) {
        return  roleService.addRole(role);
    }
    @DeleteMapping("/role/delete/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable Long id) {
        return roleService.deleteRole(id);
    }
    @GetMapping("/role/details/{id}")
    public RoleJava getRole(@PathVariable Long id) {
        if(roleRepository.findById(id).isPresent())
            return roleRepository.findById(id).get();
        else return null;
    }
    @GetMapping("/role/all")
    public List<RoleJava> getRoles() {
        return roleRepository.findAll();
    }
    @PutMapping("/role/update/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable Long id, @RequestBody RoleJava role) {
        return roleService.updateRole(id, role);
    }

}
