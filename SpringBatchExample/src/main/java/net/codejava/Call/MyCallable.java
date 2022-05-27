package net.codejava.Call;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import net.codejava.Repository.JUserRepository;
import net.codejava.model.JUser;

@Component
public class MyCallable implements Callable<JUser> {
	@Autowired
	private JUserRepository Urepo; 
	
	
	@Override
	public JUser call() throws Exception {
		// TODO Auto-generated method stub
	  
		return Urepo.findById((long)8).get();
	}
	

}
