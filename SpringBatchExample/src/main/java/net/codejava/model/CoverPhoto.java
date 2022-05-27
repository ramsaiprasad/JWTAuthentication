package net.codejava.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="coverPhoto")
public class CoverPhoto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(nullable=true)
	private Date uploadedDate;
	@Column(nullable=true)
	private long size;
	@Column(nullable=true)
	private byte[] content;
	public CoverPhoto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CoverPhoto(Long id, String name, Date uploadedDate, long size, byte[] content) {
		super();
		this.id = id;
		this.name = name;
		this.uploadedDate = uploadedDate;
		this.size = size;
		this.content = content;
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
	public Date getUploadedDate() {
		return uploadedDate;
	}
	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	@OneToOne(fetch=FetchType.EAGER,optional=false,
			cascade=CascadeType.ALL)
	private JUser user;
	public JUser getUser() {
		return user;
	}
	public void setUser(JUser user) {
		this.user = user;
	}
}
