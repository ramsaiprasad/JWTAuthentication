package net.codejava.ApproveRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import net.codejava.model.JUser;

@Entity
@Table(name="FollowerRequest")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class FollowerRequest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String request;
	private String requestType;
	@Column(nullable=true)
	private long requestSender;
	
	
	public FollowerRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FollowerRequest(Long id, String request,String requestType,long requestSender){
		super();
		this.id = id;
		this.request = request;
		this.requestType=requestType;
		this.requestSender=requestSender;
	}
	
	
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public long getRequestSender() {
		return requestSender;
	}
	public void setRequestSender(long requestSender) {
		this.requestSender = requestSender;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	
	@ManyToOne
	@JoinColumn(name="user_Id")
	private JUser user;


	public JUser getUser() {
		return user;
	}
	public void setUser(JUser user) {
		this.user = user;
	}
	

}
