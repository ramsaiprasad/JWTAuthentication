package net.codejava.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="porfileph")
public class ProfilePhoto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private byte[] content;
	private Date uploadDate;
	private long size; 
	
	public ProfilePhoto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ProfilePhoto(Long id, String name, byte[] content, Date uploadDate, long size) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.uploadDate = uploadDate;
		this.size = size;
	}
	
	
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
   
	@OneToOne(fetch=FetchType.LAZY,optional=false,
			cascade=CascadeType.ALL)
	private JUser user;
	public JUser getUser() {
		return user;
	}
	public void setUser(JUser user) {
		this.user = user;
	}
}
