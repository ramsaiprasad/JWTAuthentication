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

import net.codejava.Repository.UserRepository;
import net.codejava.model.Testjav;
import net.codejava.model.User;
import net.codejava.service.UserService;
import net.codejava.tes.TestJavRepository;

@RestController
@RequestMapping("/userM")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
    private TestJavRepository userRepository;

    
    @PostMapping("/user/create")
    public ResponseEntity<Object> createUser(@RequestBody Testjav user) {
        return userService.createUser(user);
    }
    @GetMapping("/user/details/{id}")
    public Testjav getUser(@PathVariable Long id) {
        if(userRepository.findById(id).isPresent())
            return userRepository.findById(id).get();
        else return  null;
    }
    @GetMapping("/user/all")
    public List<Testjav> getUsers() {
        return userRepository.findAll();
    }
    @PutMapping("/user/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody Testjav user) {
        return userService.updateUser(user, id);
    }
    @DeleteMapping("user/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }


}
