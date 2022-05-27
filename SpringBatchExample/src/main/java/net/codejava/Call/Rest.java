package net.codejava.Call;

import java.util.concurrent.Callable;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import net.codejava.model.JUser;
import net.codejava.service.UserService;
@Component
public class Rest implements Callable<JUser>{
	 private static final org.jboss.logging.Logger LOGGER = LoggerFactory.logger(UserCallService.class);

	private long id;
	
	public Rest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Rest(long id) {
		super();
		this.id = id;
	}
	@Override
	public JUser call() throws Exception {
		
		
		
		
		// TODO Auto-generated method stub
		String uri="http://localhost:8090/api/test/fi/"+id;
		RestTemplate template=new RestTemplate();
		
		JUser user=template.getForObject(uri,JUser.class);
		System.out.println(Thread.currentThread().getName());
		return user;
	}
	

}
