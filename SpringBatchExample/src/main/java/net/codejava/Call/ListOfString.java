package net.codejava.Call;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ListOfString implements Callable<List<String>> {

	@Override
	public List<String> call() throws Exception {
		// TODO Auto-generated method stub
		List<String> a=new ArrayList<>();
		return a;
	}
	
	

}
