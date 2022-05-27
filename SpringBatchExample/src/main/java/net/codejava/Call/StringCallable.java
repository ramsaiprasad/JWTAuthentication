package net.codejava.Call;

import java.util.concurrent.Callable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
@Component
public class StringCallable implements Callable<String> {

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		String a="Hello";
		return a;
	}

}
