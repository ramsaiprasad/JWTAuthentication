package net.codejava;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import net.codejava.Repository.JUserRepository;
import net.codejava.model.JUser;
import net.codejava.service.UserService;
@Component
public class CustomeFailureLoginHandler extends SimpleUrlAuthenticationFailureHandler {
      @Autowired
      private UserService userservice;
      @Autowired
      private JUserRepository Urepo;
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String email=request.getParameter("email");
		JUser user=Urepo.findByEmail(email);
		if(user != null)
		{
			 System.out.println("User failed To Login");
		}
		else {
			System.out.println("Email Not Exist");
		}
		super.setDefaultFailureUrl("/signin");
		super.onAuthenticationFailure(request, response, exception);
	}
	
	
	

}
