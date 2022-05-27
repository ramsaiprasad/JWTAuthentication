package net.codejava.Call;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.codejava.Repository.JUserRepository;
import net.codejava.model.JUser;

@Service
public class UserCallService {
	
	
	  private static final org.jboss.logging.Logger LOGGER = LoggerFactory.logger(UserCallService.class);

	  
	  @Autowired
	  private JUserRepository Urepo;
	  
	  @Async
	    public CompletableFuture<String> getAllCars(Long id ) throws InterruptedException {

	        LOGGER.info("Request to get a list of cars");
	        
	          System.out.println(Thread.currentThread().getName());
	          
	          System.out.println(Thread.currentThread().getThreadGroup().getName());
	         JUser cars = Urepo.findById((id)).get();
	        return CompletableFuture.completedFuture(cars.getEmail());
	    }
	  @Async
	  public  CompletableFuture<Boolean> veryLongMethod()  {

	      try {
	          Thread.sleep(2000L);
	          System.out.println(Thread.currentThread().getName());
	      } catch (InterruptedException e) {
	          e.printStackTrace();
	      }

	      return CompletableFuture.completedFuture(true);
	  }
	  
	  public JUser finduser(Long id) {
		  String uri="http://localhost:8090/api/test/fi/"+id;
			RestTemplate template=new RestTemplate();
			
			JUser user=template.getForObject(uri,JUser.class);
			return user;
			
		}
}
