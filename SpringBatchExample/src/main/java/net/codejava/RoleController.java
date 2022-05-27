package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.codejava.Repository.RoleRepository;
import net.codejava.Repository.UserRepository;
import net.codejava.model.Role;
import net.codejava.model.User;
import net.codejava.tes.RoleJavaRepository;
import net.codejava.tes.TestJavRepository;

@RestController
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	private RoleRepository Rrepo;
	@Autowired
	private UserRepository Urepo;
	@Autowired
	private TestJavRepository Tj;
	@Autowired
	private RoleJavaRepository Rr;

	@GetMapping("/getAll")
	public List<User> findAllq()
	{
		return Urepo.findAll();
	}
	
	@GetMapping("/allR")
	public List<Role> find()
	{
		return Rrepo.findAll();
	}
	
	
}
