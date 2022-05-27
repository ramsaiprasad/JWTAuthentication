package net.codejava.Comp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.web.client.RestTemplate;



public class ExtendedeCal implements Callable<Object> {
	List<Long> ids=new ArrayList<>();
	@Override
	public Object call() throws Exception {
		ids.add((long)1000);
		ids.add((long)2000);	
		ids.add((long)3000);
	ids.add((long)4000);
	ids.add((long)500);
	
	List<Object> list=new ArrayList<>();
	String ury="http://localhost:8090/api/test/datatobeSupply/";
	for(long id:ids)
	{
		RestTemplate template=new RestTemplate();
		Object obj=template.getForObject(ury+id, Object.class);
		list.add(obj);
		
	}
	return list;
	}
	
	


}
