package net.codejava.Call;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.codejava.Repository.JUserRepository;
import net.codejava.model.JUser;
import net.codejava.service.UserService;


public  class TestCallble implements Callable<JUser>  {
	@Autowired
	private UserService service;
	@Autowired
	private JUserRepository Urepo;
    private  Integer in;
    
    
	public TestCallble(Integer in) {
		this.in = in;
	}


	


	public TestCallble() {
		super();
		// TODO Auto-generated constructor stub
	}





	@Override
	public JUser call() throws Exception {
		// TODO Auto-generated method stub
		Long id=(long)in;
		
		System.out.println(id);
		RestTemplate temlate=new RestTemplate();
		String url="http://localhost:8090/api/test/fi/2";
		JUser user=temlate.getForObject(url,JUser.class);
		return user ;
	}





	

}
