package net.codejava.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import net.codejava.model.Views.StatusViews;

@Entity
@Table(name="statusTable")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Statuses {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private byte[] content;
	private Date uploadedTime;
	private String description;
	@Column(nullable=true)
	private Long useridd;
	
	
	
	
	
	public long getUseridd() {
		return useridd;
	}
	public void setUseridd(Long useridd) {
		this.useridd = useridd;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public JUser getUser() {
		return user;
	}
	public void setUser(JUser user) {
		this.user = user;
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
	public Date getUploadedTime() {
		return uploadedTime;
	}
	public void setUploadedTime(Date uploadedTime) {
		this.uploadedTime = uploadedTime;
	}
	
	
	
	
	public Statuses() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Statuses(Long id, byte[] content, Date uploadedTime,String description) {
		super();
		this.id = id;
		this.content = content;
		this.uploadedTime = uploadedTime;
		this.description=description;
	}
	
	@ManyToOne
	@JoinColumn(name="user_ID")
	@JsonBackReference
	private JUser user;
	
	@OneToMany(mappedBy="statuses" ,cascade=CascadeType.ALL,orphanRemoval=true)
	private List<StatusViews> statusviews;





	public List<StatusViews> getStatusviews() {
		return statusviews;
	}
	public void setStatusviews(List<StatusViews> statusviews) {
		this.statusviews = statusviews;
	}
	
	 

}
