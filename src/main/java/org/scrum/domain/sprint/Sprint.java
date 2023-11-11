package org.scrum.domain.sprint;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.scrum.domain.project.Feature;

@Entity
public class Sprint implements Serializable{
	@Id
	private Integer sprintID;
	private String objective;
	
	@OneToMany
	private List<Feature> features = new ArrayList<>();
	
	@OneToMany(cascade = ALL, fetch = EAGER, orphanRemoval = true)
	private List<Task> tasks = new ArrayList<>();
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	private String review;
	
	
	public Integer getSprintID() {
		return sprintID;
	}

	public void setSprintID(Integer sprintID) {
		this.sprintID = sprintID;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}

	public Sprint() {
		super();
	}

	public Sprint(Integer sprintID, String objective, Date startDate) {
		super();
		this.sprintID = sprintID;
		this.objective = objective;
		this.startDate = startDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sprintID == null) ? 0 : sprintID.hashCode());
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
		Sprint other = (Sprint) obj;
		if (sprintID == null) {
			if (other.sprintID != null)
				return false;
		} else if (!sprintID.equals(other.sprintID))
			return false;
		return true;
	}
	
	/*
	// --------------------------------------------------------------------- //
		private Long getEstimatedTimeFeature(Feature feature){
			Long estimatedTimeFeature = 0l; // 0 long
			for(Task t: feature.getTasks()){
				estimatedTimeFeature += t.getEstimatedTime() * 60 * 60 * 1000;
			}
			return estimatedTimeFeature;
		}
		
		private Long getEstimatedTimeSprint() {
			Long estimatedTimeSprint = 0l; // 0 long
			for (Feature c: this.features){
				estimatedTimeSprint += getEstimatedTimeFeature(c);
			}
			
			return estimatedTimeSprint;
		}	
		
		// prop dataFinalizare
		public Date getFinalDate() {
			Long t1 = this.startDate.getTime() + this.getEstimatedTimeSprint();
			
			Date finalDate = new Date(t1);
			
			return finalDate;
		}	
		// --------------------------------------------------------------------- //
		 */
}
