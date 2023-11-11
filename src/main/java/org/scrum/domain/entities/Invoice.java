package org.scrum.domain.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@NotNull(message = "The Invoice must be linked to a child!") 
	private Child child;

	@NotNull(message = "Value of Invoice cannot be empty!") 
	private Double value;
	
	@NotNull(message = "Issue date cannot be empty!") 
	@Future(message = "Issue date cannot be in the future!")
	private Date issueDate;
	
	@NotNull(message = "Due Date cannot be empty!") 
	@Past(message = "Due Date cannot be in the past!")
	private Date dueDate;
	
	@OneToMany
	private List<Attendance> AttendanceDays = new ArrayList<Attendance>();
	
	private StateInvoice state;

	public Invoice(Integer id, 
			@NotNull(message = "The Invoice must be linked to a child!") 
			Child child,
			@NotNull(message = "Value of Invoice cannot be empty!!") 
			Double value,
			@NotNull(message = "Issue date cannot be empty!") 
			@Future(message = "Issue date cannot be in the future!") 
			Date issueDate,
			@NotNull(message = "Due Date cannot be empty!") 
			@Past(message = "Due Date cannot be in the past!") 
			Date dueDate,
			List<Attendance> attendanceDays, StateInvoice state) {
		super();
		this.id = id;
		this.child = child;
		this.value = value;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		AttendanceDays = attendanceDays;
		this.state = state;
	}

	public Invoice() {
		super();

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

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public List<Attendance> getAttendanceDays() {
		return AttendanceDays;
	}

	public void setAttendanceDays(List<Attendance> attendanceDays) {
		AttendanceDays = attendanceDays;
	}

	public StateInvoice getState() {
		return state;
	}

	public void setState(StateInvoice state) {
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
		Invoice other = (Invoice) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
