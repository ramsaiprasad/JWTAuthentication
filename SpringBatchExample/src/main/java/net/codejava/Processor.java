package net.codejava;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;

import org.springframework.stereotype.Component;
@Component
public class Processor implements ItemProcessor<UserBatch,UserBatch> {
	
	private static final Map<String,String> DEPT_NAMES = new HashMap<>();
	
	public Processor()
	{
		DEPT_NAMES.put("100", "Mech");
		DEPT_NAMES.put("200", "CSE");
		DEPT_NAMES.put("300", "EEE");
		DEPT_NAMES.put("400", "ECE");
	}
	
	
	
	@Override
	public UserBatch process(UserBatch userbatch) throws Exception {
		
		
       int sal=userbatch.getSalary();
       int leav=userbatch.getLeave();
       if(leav > 0)
       {
    	    leav=leav * 100;
    	     sal=sal-leav;
    	      userbatch.setSalary(sal);
    	       String deptcode=userbatch.getDept();
 	 	       String dept=DEPT_NAMES.get(deptcode);
   	 		     userbatch.setDept(dept);
       }
		String deptcode=userbatch.getDept();
		
		
		String dept=DEPT_NAMES.get(deptcode);
		
		userbatch.setDept(dept);
		
		return userbatch;
	}

}
