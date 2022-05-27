package net.codejava.model;

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
@Table(name="FollowingTable")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Following {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private long followingId;
	
	@ManyToOne
	@JoinColumn(name="user_ID")
	private JUser user;
	public long getFollowingid() {
		return followingId;
	}
	public void setFollowingid(long followingId) {
		this.followingId = followingId;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public Following(Long id, long followingId) {
		super();
		this.id = id;
		this.followingId = followingId;
	}
	public Following() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JUser getUser() {
		return user;
	}
	public void setUser(JUser user) {
		this.user = user;
	}
	
	

}
