package net.codejava.model;

import java.util.List;

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
@Table(name="FollowersTable")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Followers {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private long followerId;
	
	public Followers(Long id, long followerId) {
		super();
		this.id = id;
		this.followerId = followerId;
	}

	public long getFollowerid() {
		return followerId;
	}

	public void setFollowerid(long followerId) {
		this.followerId = followerId;
	}

	public JUser getUser() {
		return user;
	}

	public void setUser(JUser user) {
		this.user = user;
	}


	@ManyToOne
	@JoinColumn(name="user_ID")
	private JUser user;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Followers() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
