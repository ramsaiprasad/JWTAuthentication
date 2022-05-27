package net.codejava.Call;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;

import net.codejava.Repository.JUserRepository;
import net.codejava.model.JUser;

public class Minus {
	@Autowired
	private JUserRepository Urepo;
private ExecutorService executor=Executors.newFixedThreadPool(3);
	
	public Future<?> Calculat(Integer input)
	{
		JUser user=Urepo.findById((long)1).get();
		return executor.submit(()->
		{
			System.out.println(user.getAge());
			Thread.sleep(1000);
			return input*input;
		});
	}

}
