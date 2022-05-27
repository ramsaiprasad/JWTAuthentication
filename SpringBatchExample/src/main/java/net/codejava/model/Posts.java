package net.codejava.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="posts")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Posts {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private byte[] content;
	private String description;
	private Date uploadedTime;
	
	
	
	
	public Posts() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Posts(Long id, byte[] content, String description, Date uploadedTime) {
		super();
		this.id = id;
		this.content = content;
		this.description = description;
		this.uploadedTime = uploadedTime;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getUploadedTime() {
		return uploadedTime;
	}
	public void setUploadedTime(Date uploadedTime) {
		this.uploadedTime = uploadedTime;
	}
	@ManyToOne
	@JoinColumn(name="user_ID")
	private JUser user;

	public JUser getUser() {
		return user;
	}


	public void setUser(JUser user) {
		this.user = user;
	}
	

}
