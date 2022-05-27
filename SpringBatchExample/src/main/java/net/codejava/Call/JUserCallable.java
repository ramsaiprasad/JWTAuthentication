package net.codejava.Call;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.Repository.JUserRepository;
import net.codejava.model.JUser;

@Service
public class JUserCallable  {
@Autowired
private JUserRepository Urepo;
	
private ExecutorService executor=Executors.newFixedThreadPool(3);
	
	public Future<JUser> Calculate()
	{
		return executor.submit(()->
		{
			Thread.sleep(1000);
			
			return Urepo.findById((long) 8).get();
		});
	}
	

}
