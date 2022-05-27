package net.codejava.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.codejava.UserNotFoundException;
import net.codejava.Comp.Tert;
import net.codejava.Comp.TertRepository;
import net.codejava.Repository.JUserRepository;
import net.codejava.Repository.StatusesRepository;
import net.codejava.Verification.EmailVerification;
import net.codejava.Verification.EmailVerificationRepository;
import net.codejava.model.JUser;
import net.codejava.model.Statuses;
import net.codejava.model.Testjav;
import net.codejava.tes.RoleJavaRepository;
import net.codejava.tes.TestJavRepository;
@Service
public class UserService {
	

	 @Autowired
	    private TestJavRepository userRepository;
   @Autowired
	    private RoleJavaRepository roleRepository;
   @Autowired
   private JUserRepository Urepo;
   @Autowired
   private StatusesRepository Srepo;
	   
	    /** Create a new User */
	    public ResponseEntity<Object> createUser(Testjav model) {
	        Testjav user = new Testjav();
	        if (userRepository.findByEmail(model.getEmail()).isPresent()) {
	            return ResponseEntity.badRequest().body("The Email is already Present, Failed to Create new User");
	        } else {
	            user.setFirstName(model.getFirstName());
	            user.setLastName(model.getLastName());
	            user.setMobile(model.getMobile());
	            user.setEmail(model.getEmail());
	            user.setRoles(model.getRoles());

	            Testjav savedUser = userRepository.save(user);
	            if (userRepository.findById(savedUser.getId()).isPresent())
	                return ResponseEntity.ok("User Created Successfully");
	            else return ResponseEntity.unprocessableEntity().body("Failed Creating User as Specified");
	        }
	    }

	    /** Update an Existing User */
	    @Transactional
	    public ResponseEntity<Object> updateUser(Testjav user, Long id) {
	        if(userRepository.findById(id).isPresent()) {
	        	Testjav newUser = userRepository.findById(id).get();
	            newUser.setFirstName(user.getFirstName());
	            newUser.setLastName(user.getLastName());
	            newUser.setMobile(user.getMobile());
	            newUser.setEmail(user.getEmail());
	            newUser.setRoles(user.getRoles());
	            Testjav savedUser = userRepository.save(newUser);
	            if(userRepository.findById(savedUser.getId()).isPresent())
	                return  ResponseEntity.accepted().body("User updated successfully");
	            else return ResponseEntity.unprocessableEntity().body("Failed updating the user specified");
	        } else return ResponseEntity.unprocessableEntity().body("Cannot find the user specified");
	    }
	    /** Delete an User*/
	    public ResponseEntity<Object> deleteUser(Long id) {
	        if (userRepository.findById(id).isPresent()) {
	            userRepository.deleteById(id);
	            if (userRepository.findById(id).isPresent())
	                return ResponseEntity.unprocessableEntity().body("Failed to Delete the specified User");
	            else return ResponseEntity.ok().body("Successfully deleted the specified user");
	        } else return ResponseEntity.badRequest().body("Cannot find the user specified");
	    }
	    
	    
	    public void resetUpdatePassword(String token,String email) {
	    	
	    	JUser user=Urepo.findByEmail(email);
	    	
	    	if(user != null)
	    	{
	    		user.setResetPasswordToke(token);
	    		Urepo.save(user);
	    	}
	    	else {
	    		throw new UserNotFoundException("Not Found with email"+email);
	    	}
	    	    	
	    	
	    }
	    
	    public JUser get(String resetPasswordToken) {
	    	return Urepo.findByResetPasswordToken(resetPasswordToken);
	    }
	    public void updatePassword(JUser user,String newPassword)
	    {
	    	BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	    	String EncodedPassword=passwordEncoder.encode(newPassword);
	    	user.setPassword(EncodedPassword);
	    	user.setResetPasswordToke(null);
	    	
	    	Urepo.save(user);
	    }
	    public ResponseEntity<String> deleteStatuse(Long id)
	    {
	    	if(Srepo.findById(id).get().equals(null))
	    	{
	    		return new ResponseEntity<String>("not Deleted",HttpStatus.OK);
	    	}
	    	else {
	    	Statuses stat=Srepo.findById(id).get();
	    	if(stat!=null)
	    	{
	    	Srepo.delete(stat);
	    	System.out.println("deleted");
	    	return new ResponseEntity<String>("Deleted",HttpStatus.OK);
	    	}
	    	else {
	    		return new ResponseEntity<String>("not Deleted",HttpStatus.OK);
	    	 }
	    	}
	    	
	    }
	    
	    @Autowired
	    private EmailVerificationRepository em;
	    public void  sendVerifyLink(String email,String verificationToken,Integer Otp)
	    {
	    	EmailVerification verify=new EmailVerification();
	    	verify.setEmail(email);
	    	verify.setToken(verificationToken);
	    	verify.setOtp(Otp);
	    	verify.setStartTime(new Date());
	    	em.save(verify);
	    	
	    }
	    public JUser getonej(Long id)
	    {
	    	JUser user=Urepo.findById(id).get();
	    	return user;
	    }
	    @Autowired
	    private TertRepository Trepo;
	    public void saveTert(Tert tert)
	    {
	    	Trepo.save(tert);
	    	
	    }
	    
	    
	    
}
