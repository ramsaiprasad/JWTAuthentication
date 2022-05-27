package net.codejava.Comp;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.codejava.service.UserService;
@Component
public class SvaeCallable implements Callable<Tert> {
private Tert tret;

public SvaeCallable() {
	super();
	// TODO Auto-generated constructor stub
}

	public SvaeCallable(Tert tret) {
	super();
	this.tret = tret;
}

@Autowired
private UserService userservice;
	@Override
	public Tert call() throws Exception {
		// TODO Auto-generated method stub
		
		userservice.saveTert(tret);
		return tret;
	}
	
}
