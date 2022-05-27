package net.codejava.service;

import java.util.Set;

import net.codejava.model.Viewers;

public class StreamingRequest {
	
	private Long id;
	
	private String name;
	
	public Set<Viewers> viewersList;
	
	

	public StreamingRequest() {
		super();
		// TODO Auto-generated constructor stub
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

}
