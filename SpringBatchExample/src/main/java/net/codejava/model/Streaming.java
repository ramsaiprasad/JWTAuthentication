package net.codejava.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Stream")
public class Streaming {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ManyToMany(mappedBy="followedStream",fetch=FetchType.EAGER)
	@JsonIgnoreProperties("followedStream")
	List<Viewers> viewersList=new ArrayList<>();
	
	public Streaming(String name)
	{
		this.name=name;
	}

	public void addViewers(Viewers viewers)
	{
		viewersList.add(viewers);
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

	public Streaming() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Viewers> getViewersList() {
		return viewersList;
	}

	public void setViewersList(List<Viewers> viewersList) {
		this.viewersList = viewersList;
	}

}
