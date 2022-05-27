package net.codejava.model.Views;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.codejava.model.Statuses;

@Entity
@Table(name="StatusViews")
public class StatusViews {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long viewerid;
	
	
	public StatusViews() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StatusViews(Long id, Long viewerid) {
		super();
		this.id = id;
		this.viewerid = viewerid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getViewerid() {
		return viewerid;
	}
	public void setViewerid(Long viewerid) {
		this.viewerid = viewerid;
	}
	@ManyToOne
	@JoinColumn(name="Status_Id")
	private Statuses statuses;


	public Statuses getStatuses() {
		return statuses;
	}
	public void setStatuses(Statuses statuses) {
		this.statuses = statuses;
	}
	
	
	
	

}
