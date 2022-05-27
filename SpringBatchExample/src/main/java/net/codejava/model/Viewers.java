package net.codejava.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Viewers")
public class Viewers {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long nickName;
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(
			name="followed_stream",
			joinColumns=@JoinColumn(name="viewers_id"),
			inverseJoinColumns=@JoinColumn(name="stream_id")
			)
	List<Streaming> followedStream= new ArrayList<>();

	public Viewers(Long nickName) {
		super();
		this.nickName = nickName;
	}
	
	public void followStream(Streaming stream)
	{
		followedStream.add(stream);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNickName() {
		return nickName;
	}

	public void setNickName(Long nickName) {
		this.nickName = nickName;
	}
	
	   

}
