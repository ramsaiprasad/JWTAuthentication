package net.codejava.StoredProcedures;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.codejava.model.JUser;

@Entity
@Table(name="Field_Values")
public class FieldValues {
	
	public CustomVariables getCustomVariables() {
		return customVariables;
	}

	public void setCustomVariables(CustomVariables customVariables) {
		this.customVariables = customVariables;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
		
	private String Field_Values;
	
	

	public FieldValues(Long id, String field_Values) {
		super();
		this.id = id;
		Field_Values = field_Values;
	}

	public FieldValues() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 
	public String getField_Values() {
		return Field_Values;
	}

	public void setField_Values(String field_Values) {
		Field_Values = field_Values;
	}
		
	@ManyToOne
	@JoinColumn(name="Custom_Field_Id")
	private CustomVariables customVariables;
	
    @ManyToOne
    @JoinColumn(name="USer_ID")
    private JUser user;



	public JUser getUser() {
		return user;
	}

	public void setUser(JUser user) {
		this.user = user;
	}
    

}
