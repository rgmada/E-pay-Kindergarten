package org.scrum.domain.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@NotNull(message = "The attendance must be linked to a child!") 
	private Child child;
	
	@NotNull(message = "Date of attendance cannot be empty!") 
	@Past(message = "Date of attendance cannot be in the future!")
	@Temporal(TemporalType.DATE)
	private Date dateOfAttendance;

	private StateAttendance state;
	
	
	public Attendance(Integer id, 
			@NotNull(message = "The attendance can't have any child!") 
			Child child,
			@NotNull(message = "Date of attendance cannot be empty!") 
			@Past(message = "Date of attendance cannot be in the future!") 
			Date dateOfAttendance,
			StateAttendance state) {
		super();
		this.id = id;
		this.child = child;
		this.dateOfAttendance = dateOfAttendance;
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public Date getDateOfAttendance() {
		return dateOfAttendance;
	}

	public void setDateOfAttendance(Date dateOfAttendance) {
		this.dateOfAttendance = dateOfAttendance;
	}

	public StateAttendance getState() {
		return state;
	}

	public void setState(StateAttendance state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attendance other = (Attendance) obj;
		return Objects.equals(id, other.id);
	}

	public Attendance() {
		super();
	
	}


	
	
}
