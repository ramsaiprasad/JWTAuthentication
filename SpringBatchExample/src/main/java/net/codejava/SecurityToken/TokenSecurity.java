package net.codejava.SecurityToken;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TokensSecurity")
public class TokenSecurity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String sessionToken;
	
	private Date sessionCreatedTime;
	@Column(nullable=true)
	private long userId;
	

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public Date getSessionCreatedTime() {
		return sessionCreatedTime;
	}

	public void setSessionCreatedTime(Date sessionCreatedTime) {
		this.sessionCreatedTime = sessionCreatedTime;
	}

	public TokenSecurity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TokenSecurity(Long id, String sessionToken, Date sessionCreatedTime,long userId) {
		super();
		this.id = id;
		this.sessionToken = sessionToken;
		this.sessionCreatedTime = sessionCreatedTime;
		this.userId=userId;
	}

	
	
	
	

}
