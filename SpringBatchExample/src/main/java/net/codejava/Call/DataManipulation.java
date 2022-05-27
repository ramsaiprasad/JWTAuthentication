package net.codejava.Call;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.web.client.RestTemplate;

import net.codejava.Comp.Tert;

public class DataManipulation implements Callable<Tert>{
 List<Long> ids=new ArrayList<>();

 

	@Override
	public Tert call() throws Exception {
		// TODO Auto-generated method stub
//		ids.add((long)1);
//		ids.add((long)2);
//		ids.add((long)3);
//		ids.add((long)4);
//		ids.add((long)5);
		RestTemplate template=new RestTemplate();
	  //List<Object> obj=new ArrayList<>();//datatobeSupply
	  String uri="http://localhost:8090/api/test/datatobeSupply/200";
	  
		  
		 Tert object=template.getForObject(uri,Tert.class);
		 //obj.add(object);
		  
	 
		
		return object;
	}
	

}
