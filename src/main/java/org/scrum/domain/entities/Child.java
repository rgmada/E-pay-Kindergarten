package org.scrum.domain.entities;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
public class Child {

	@Id
	private String cnp;
	
	@NotNull(message = "First name is required for proper identification!") 
	private String firstName;
	
	@NotNull(message = "Last name is required for proper identification!") 
	private String lastName;
	
	
	@NotNull(message = "Birth Date is required!")
	@Past(message = "Birth Date cannot be in the future!")
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	private String gender;
	private String group;
	

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
		Child other = (Child) obj;
		return Objects.equals(cnp, other.cnp);
	}


	public Child() {
		super();
	}

	public Child(String cnp, 
			@NotNull(message = "First name is required for proper identification!") 
			String firstName,
			@NotNull(message = "Last name is required for proper identification!") 
			String lastName,
			@NotNull(message = "Birth Date is required!") 
			@Past(message = "Birth Date cannot be in the future!") 
			Date birthDate,
			String gender, String group) {
		super();
		this.cnp = cnp;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.group = group;
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	
	
	
	public Integer getAge(Child child) {
		
		LocalDateTime currentDate = LocalDateTime.now();
		
		LocalDateTime childBithDate = convertToDateToLocalDateTime(child.getBirthDate());
		return currentDate.getYear() - childBithDate.getYear();
		
	}
	
	
    private static LocalDateTime convertToDateToLocalDateTime(Date date) {
        // Convert Date to Instant
        Instant instant = date.toInstant();

        // Convert Instant to LocalDateTime
        // ZoneId.systemDefault() gets the system default time zone.
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
	
	
}
