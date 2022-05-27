package net.codejava.Verification;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="VerificationTable")
public class EmailVerification {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	
	private String token;
	
	private Integer Otp;
	
	
	private Date startTime;
	
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public EmailVerification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmailVerification(Long id, String email, String token) {
		super();
		this.id = id;
		this.email = email;
		this.token = token;
	}
	
	
	

	public Integer getOtp() {
		return Otp;
	}

	public void setOtp(Integer otp) {
		Otp = otp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	
	

}
