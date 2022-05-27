package net.codejava.ApproveRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.codejava.model.JUser;

@Entity
@Table(name="myrequesttable")
public class MyrequetsTable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String request;
	private String requestType;
	@Column(nullable=true)
	private long requestSentTo;
	
	public MyrequetsTable(Long id, String request, String requestType, long requestSentTo) {
		super();
		this.id = id;
		this.request = request;
		this.requestType = requestType;
		this.requestSentTo = requestSentTo;
	}
	public MyrequetsTable() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public long getRequestSentTo() {
		return requestSentTo;
	}
	public void setRequestSentTo(long requestSentTo) {
		this.requestSentTo = requestSentTo;
	}
	
	@ManyToOne
	@JoinColumn(name="userId")
	private JUser user;

	public JUser getUser() {
		return user;
	}
	public void setUser(JUser user) {
		this.user = user;
	}
	

}
