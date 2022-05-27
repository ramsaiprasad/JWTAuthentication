package net.codejava.Comp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.codejava.DBOperations.Department;
import net.codejava.DBOperations.DepartmetnRepository;
import net.codejava.Repository.JUserRepository;
import net.codejava.model.JUser;

@Service
public class UserServiceComact {
	
	@Autowired
	private staggingRepository strp;
	@Autowired
	private TertRepository Treo;
	@Autowired
	private JUserRepository Urepo;
	
	Object target;
	Logger logger=LoggerFactory.getLogger(UserServiceComact.class);
	@Async
	public CompletableFuture<List<Tert>> saveTert(MultipartFile multipartfile) throws Exception
	{
		long start=System.currentTimeMillis();
		
		List<Tert>list=parseCsv(multipartfile);
		logger.info("saving information of users",list.size()+""+Thread.currentThread().getName());
		list=Treo.saveAll(list);
		long end=System.currentTimeMillis();
		logger.info("Time taken to execute is",end-start);
		return CompletableFuture.completedFuture(list);
	}
	@Async
    public CompletableFuture<List<Tert>> getAllTero()
    {
    	logger.info("get list of all the Tero"+Thread.currentThread().getName());
    	List<Tert>list=Treo.findAll();
    	List<stagging> stlist=new ArrayList<>();
    	for(Tert tr:list)
    	{
    		stagging stage=new stagging();
    		stage.setName(tr.getName());
    		stage.setInpunch(new Date());
    		stlist.add(stage);
    		
    	}
    	strp.saveAll(stlist);
    
    	return CompletableFuture.completedFuture(list);
    }
	
	@Async
	public  CompletableFuture<Object> getOneTertById(Long id)
	{
		logger.info("Getting the Details Please Wait."+Thread.currentThread().getName());
		Object tert=Treo.findById(id).get();
		return CompletableFuture.completedFuture(tert);
		
		
	}
    
	@Transactional
	@Async
		public CompletableFuture<List<JUser>> getAllTeron()
	{
		logger.info("get list of all the Tero"+Thread.currentThread().getName());
    	List<JUser>list=Urepo.findAll();
    	return CompletableFuture.completedFuture(list);
	}
	@Autowired
	private DepartmetnRepository Drepo;
	private List<Tert> parseCsv(MultipartFile multipartfile) throws Exception{
		final List<Tert> tert=new ArrayList<>();
		try {
			try (final BufferedReader br = new BufferedReader(new InputStreamReader(multipartfile.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    
                    final Tert user = new Tert();
                    user.setName(data[0]);
                    Department department=new Department();
                   
                    department= Drepo.findByDepartmentname(data[1]);
                    
                    user.setDeptId(department.getId());                   
                    tert.add(user);
                }
                return tert;
            }
		}
		catch(final IOException e)
		{
			logger.info("failed to load Csv",e);
			throw new Exception("failed to load CSV file",e);
		}
		
		
		
	}
	@Transactional
	@Async
	public CompletableFuture<Boolean> getSelectedUsers(Long id) throws InterruptedException
	{
		
		  try {
	          Thread.sleep(2000L);
	          System.out.println(Thread.currentThread().getName());
	          logger.info("executing By "+Thread.currentThread().getName());
	  		JUser userlist=Urepo.findById(id).get();
	      } catch (InterruptedException e) {
	          e.printStackTrace();
	      }
		
		return CompletableFuture.completedFuture(true);
		
	}
}
