package org.scrum.domain.project;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Release implements Serializable{
	/* internal member fields*/
	@Id @GeneratedValue
	private Integer releaseId;
	private String codeName; // NEW born
	private String indicative; // vers 1.1
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date publishDate; // dataEstimarePublicare
	
	@ManyToOne
	private Project project;

	@OneToMany(cascade = ALL, fetch=FetchType.EAGER, orphanRemoval = true)
	private List<Feature> features = new ArrayList<>();
	
	public Integer getReleaseId() {
		return releaseId;
	}
	public void setReleaseId(Integer releaseId) {
		this.releaseId = releaseId;
	}
	
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	
	public String getIndicative() {
		return indicative;
	}
	public void setIndicative(String indicative) {
		this.indicative = indicative;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}

	public List<Feature> getFeatures() {
		return features;
	}
	public void setFeatures(List<Feature> features) {
		this.features = features;
	}
	
	/* constructors*/
	public Release() {
		super();
	}
	
	public Release(Integer releaseId, String codeName, String indicative,
			String description, Date publishDate, Project project) {
		super();
		this.releaseId = releaseId;
		this.codeName = codeName;
		this.indicative = indicative;
		this.description = description;
		this.publishDate = publishDate;
		this.project = project;
	}

	public Release(Integer releaseId, String codeName, Date publishDate,
			Project project) {
		super();
		this.releaseId = releaseId;
		this.codeName = codeName;
		this.publishDate = publishDate;
		this.project = project;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((releaseId == null) ? 0 : releaseId.hashCode());
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
		Release other = (Release) obj;
		if (releaseId == null) {
			if (other.releaseId != null)
				return false;
		} else if (!releaseId.equals(other.releaseId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "\n\tRelease [releaseId=" + releaseId + ", codeName=" + codeName
				+ ", indicative=" + indicative + ", features: " + features + "]";
	}
	
	public void addFeature(String feature) {
		//Integer featureID = (this.features.size() == 0) ? 1 : this.features.size();
		this.features.add(new Feature(null, feature));
	}	
	
	public void addFeature(String feature, String description) {
		//Integer featureID = (this.features.size() == 0) ? 1 : this.features.size();
		this.features.add(new Feature(null, feature, description));
	}	
}