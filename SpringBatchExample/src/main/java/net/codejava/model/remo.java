package net.codejava.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="recieve")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class remo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String messagee;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessagee() {
		return messagee;
	}
	public void setMessagee(String messagee) {
		this.messagee = messagee;
	}
	public remo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public remo(Long id, String messagee) {
		super();
		this.id = id;
		this.messagee = messagee;
	}
	public Postman getPostman() {
		return postman;
	}
	public void setPostman(Postman postman) {
		this.postman = postman;
	}
	@OneToOne
    @PrimaryKeyJoinColumn
	private Postman postman;

}
