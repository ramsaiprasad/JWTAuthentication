package net.codejava.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AccountLock_Information")
public class AccountLock {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
    private int Attempts;
    @Column(nullable=true)
    private int isLocked;
    @Column(nullable=true)
    private String username;
    
    
   
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public AccountLock(Long id, int attempts, int isLocked, String username) {
		super();
		Id = id;
		Attempts = attempts;
		this.isLocked = isLocked;
		this.username = username;
	}

	public AccountLock() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public int getAttempts() {
		return Attempts;
	}
	public void setAttempts(int attempts) {
		Attempts = attempts;
	}
	public int getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(int isLocked) {
		this.isLocked = isLocked;
	}
	   
	
}
