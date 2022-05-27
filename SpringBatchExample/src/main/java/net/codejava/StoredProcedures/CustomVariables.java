package net.codejava.StoredProcedures;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.codejava.model.Followers;

@Entity
@Table(name="Custom_Variable")
public class CustomVariables {
	public List<FieldValues> getFieldvalues() {
		return Fieldvalues;
	}

	public void setFieldvalues(List<FieldValues> fieldvalues) {
		Fieldvalues = fieldvalues;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String Custom_Variable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustom_Variable() {
		return Custom_Variable;
	}

	public void setCustom_Variable(String custom_Variable) {
		Custom_Variable = custom_Variable;
	}

	public CustomVariables(Long id, String custom_Variable) {
		super();
		this.id = id;
		Custom_Variable = custom_Variable;
	}

	public CustomVariables() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@OneToMany(mappedBy="customVariables",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<FieldValues> Fieldvalues;
	
	

}
