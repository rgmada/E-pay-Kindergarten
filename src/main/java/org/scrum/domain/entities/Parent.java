package org.scrum.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Parent {

	@Id
	private String cnp;
	
	@NotNull(message = "First name is required for proper identification!") 
	private String firstName;
	
	@NotNull(message = "Last name is required for proper identification!") 
	private String lastName;
	
	@OneToMany
	private List<Child> childrens = new ArrayList<Child>();
	
	@NotNull(message = "Phone number is required for proper communication with the parent!") 
	private String phone;

	
	@Override
	public int hashCode() {
		return Objects.hash(cnp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parent other = (Parent) obj;
		return Objects.equals(cnp, other.cnp);
	}

	public Parent(String cnp, 
			@NotNull(message = "First name is required for proper identification!") 
			String firstName,
			@NotNull(message = "Last name is required for proper identification!") 
			String lastName,
			List<Child> childrens,
			@NotNull(message = "Phone number is required for proper communication with the parent!") 
			String phoneNumber) {
		super();
		this.cnp = cnp;
		this.firstName = firstName;
		this.lastName = lastName;
		this.childrens = childrens;
		this.phone = phoneNumber;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Child> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Child> childrens) {
		this.childrens = childrens;
	}

	public String getPhoneNumber() {
		return phone;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phone = phoneNumber;
	}
	
	
	public void addChildToParent(Child children){
		this.childrens.add(children);
	}
	
	
}
