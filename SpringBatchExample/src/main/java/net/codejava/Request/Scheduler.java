package net.codejava.Request;



import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import net.codejava.Repository.StatusesRepository;
import net.codejava.model.Statuses;
import net.codejava.service.UserService;

@Service
@Component
public class Scheduler {
	@Autowired
	private UserService serv;
	 @Autowired
	   private StatusesRepository Srepo;
	@Scheduled(initialDelay=5000,fixedRate=15000)
	public void fixedRateJobWithInotialDelay() {
		
		org.slf4j.Logger logger=  LoggerFactory.getLogger(this.getClass());
		logger.info("fixed rate job with initial delay");
		long pause=5000;
		long start=System.currentTimeMillis();
		do {
			if(start+pause < System.currentTimeMillis())
			{
				break;
			}
			
		}while(true);
		
		logger.info("processing time was {}",pause/1000);
		logger.info("<fixed rate initila Delay"+new Date());
		
		
	}
	@Scheduled(initialDelay=5000,fixedRate=15000)
	public void fixedRateJobWithInotialDela() {
		
		org.slf4j.Logger logger=  LoggerFactory.getLogger(this.getClass());
		logger.info("fixed rate job with initial delay");
		long pause=5000;
		long start=System.currentTimeMillis();
		do {
			if(start+pause < System.currentTimeMillis())
			{
				break;
			}
			
		}while(true);
		
		logger.info("processing time was {}",pause/1000);
		logger.info("<fixed rate initila Delay and ramsaipreasadda  fsjvkjsrnf"+new Date());
		
	}
	/*@Scheduled(initialDelay=5000,fixedRate=15000)
	public void fixedRateJobWithInotialDel() {
		org.slf4j.Logger logger=  LoggerFactory.getLogger(this.getClass());
		List<Statuses> stt=Srepo.findByUploadedTime(new Date());
		if(stt == null)
				{
		            serv.deleteStatuse((long)3);
				}
		else {
			
			logger.info("Waiting....");
		} 
	}*/
	
}