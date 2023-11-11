package org.scrum.domain.sprint;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.scrum.domain.project.Feature;
import org.scrum.domain.team.Member;

@Entity
public class Task implements Serializable{
	@Id @GeneratedValue
	private Integer taskID;
	private String name;
	private String description;
	
	// timing
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	private Integer estimatedTime; // initial, exprimat in ore
	private Integer remainingTime; // actualizat, exprimat in ore
	private Integer realTime;	
	
	private TaskStatus taskStatus;
	
	private Feature feature;
	
	// assessment
	@ManyToOne
	private Member responsible;
	
	private TaskCategory taskCategory;
	
	// Burn down
	@Transient
	private Map<Date, Integer> burnDownRecords = new HashMap<>();
	
	
	public Integer getTaskID() {
		return taskID;
	}
	public void setTaskID(Integer taskID) {
		this.taskID = taskID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Integer getEstimatedTime() {
		return estimatedTime;
	}
	public void setEstimatedTime(Integer estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	public Integer getRemainingTime() {
		return remainingTime;
	}
	public void setRemainingTime(Integer remainingTime) {
		this.remainingTime = remainingTime;
		burnDownRecords.put(new Date(), remainingTime);
	}
	public Integer getRealTime() {
		return realTime;
	}
	public void setRealTime(Integer realTime) {
		this.realTime = realTime;
	}
	public TaskStatus getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}
	public Member getResponsible() {
		return responsible;
	}
	public void setResponsible(Member responsible) {
		this.responsible = responsible;
	}
	public TaskCategory getTaskCategory() {
		return taskCategory;
	}
	public void setTaskCategory(TaskCategory taskCategory) {
		this.taskCategory = taskCategory;
	}
	public Map<Date, Integer> getBurnDownRecords() {
		return burnDownRecords;
	}
	public void setBurnDownRecords(Map<Date, Integer> burnDownRecords) {
		this.burnDownRecords = burnDownRecords;
	}
	
	public Feature getFeature() {
		return feature;
	}
	public void setFeature(Feature feature) {
		this.feature = feature;
	}
	//-------------------------------------------------------------------------
	public Task(Integer taskID, String name, String description,
			Date startDate, Integer estimatedTime, TaskStatus taskStatus,
			TaskCategory taskCategory) {
		super();
		this.taskID = taskID;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.estimatedTime = estimatedTime;
		this.taskStatus = taskStatus;
		this.taskCategory = taskCategory;
	}
	public Task(Integer taskID, String name, Date startDate,
			Integer estimatedTime) {
		super();
		this.taskID = taskID;
		this.name = name;
		this.startDate = startDate;
		this.estimatedTime = estimatedTime;
	}
	public Task(Integer taskID, String name,
			Date startDate, Integer estimatedTime, Member responsible) {
		super();
		this.taskID = taskID;
		this.name = name;
		this.startDate = startDate;
		this.estimatedTime = estimatedTime;
		this.responsible = responsible;
	}
	public Task() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taskID == null) ? 0 : taskID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (taskID == null) {
			if (other.taskID != null)
				return false;
		} else if (!taskID.equals(other.taskID))
			return false;
		return true;
	}
	
}
