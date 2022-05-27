package net.codejava.Call;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;
@Service
public class SquareCalculator {
	
	private ExecutorService executor=Executors.newFixedThreadPool(3);
	
	public Future<?> Calculate(Integer input)
	{
		return executor.submit(()->
		{
			Thread.sleep(1000);
			return input*input;
		});
	}

	
}
