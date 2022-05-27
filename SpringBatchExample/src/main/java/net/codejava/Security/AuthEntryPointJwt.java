package net.codejava.Security;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import net.codejava.Repository.JUserRepository;
import net.codejava.Request.LoginRequest;
import net.codejava.model.JUser;
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);
	@Autowired
	private JUserRepository Urepo;
	
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		
		logger.error("Unauthorized error: {}", authException.getMessage());
		System.out.println(authException.getMessage());
//		if(authException.getMessage().contains("Bad credentials")) {
//			
//			String username=request.getParameter("username");
//			
//			JUser user=Urepo.findByUsername(username).get();
//			
//			user.setFailed_attempts(1);
//			Urepo.save(user);	
//		}
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
	}

}