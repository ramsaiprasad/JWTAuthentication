package net.codejava.Call;

import java.util.concurrent.Callable;




public class CallableWorker implements Callable<String> {
	
	String name;
	
	

	public CallableWorker(String name) {
		super();
		this.name = name;
	}



	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		process();
		Add();
		String meassage=String.format("CallableWorker name: %s is Done", name);
		return meassage;	
	}
	
	private void process(){ 
        for(int taskId=0; taskId < 10; taskId++){
            String message = String.format("CallableWorker name: %s is processing a taskId: %d", name, taskId);
            System.out.println(message);
        }
        
    
	

}
	public void Add() {
		
		int a=0;
		for(int i=0;i<=10;i++)
		{
			a+=i;
			System.out.println(a);
		}
		System.out.println(a);
		
	}
}
