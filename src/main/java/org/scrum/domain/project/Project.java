package org.scrum.domain.project;
import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.scrum.domain.team.ProjectManager;

@Entity
public class Project implements Serializable, Comparable<Project>{
	/* internal stucture: member fields*/
	@Min(1) @NotNull(message = "ProjectNo is required!")
	@Id @GeneratedValue
	private Integer projectNo;
	
	@NotNull(message = "Project Name is required!") 
	@Size(min=1, message = "Project must have an explicit name!")
	private String name;
	
	@NotNull(message = "StartDate is required!")
	@Future(message = "StartDate must be a future date!")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Transient
	private ProjectManager projectManager;
	
	//@OneToMany(mappedBy="project", cascade = ALL, fetch = EAGER, orphanRemoval = true)
	@OneToMany(mappedBy="project", cascade = CascadeType.ALL, fetch = EAGER,
			orphanRemoval = true)
	private List<Release> releases = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL, fetch = EAGER, orphanRemoval = true)
	private Release currentRelease;
	
	public Integer getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(Integer projectNo) {
		this.projectNo = projectNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startName) {
		this.startDate = startName;
	}

	public List<Release> getReleases() {
		return releases;
	}
	public void setReleases(List<Release> releases) {
		this.releases = releases;
	}
	public Release getCurrentRelease() {
		return currentRelease;
	}
	public void setCurrentRelease(Release currentRelease) {
		this.currentRelease = currentRelease;
	}	

	public ProjectManager getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}
	
	@Override
	public String toString() {
		return "\nProject [projectNo=" + projectNo + ", name=" + name + ", startDate=" + startDate + ", releases="
				+ releases + "]";
	}
	
	
	/* Constructors */
	public Project(Integer projectNo, String name, Date startDate) {
		super();
		this.projectNo = projectNo;
		this.name = name;
		this.startDate = startDate;
	}

	public Project() {
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((projectNo == null) ? 0 : projectNo.hashCode());
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
		Project other = (Project) obj;
		if (projectNo == null) {
			if (other.projectNo != null)
				return false;
		} else if (!projectNo.equals(other.projectNo))
			return false;
		return true;
	}
	public Project(Integer nrProiect, String numeProiect) {
		super();
		this.projectNo = nrProiect;
		this.name = numeProiect;
	}
	
	@Override
	public int compareTo(Project obj) {
		return this.projectNo.compareTo(((Project)obj).getProjectNo());
	}
	
	@AssertTrue(message = "Release List must not be empty!")
	public boolean isValid() {
		if (this.releases == null || this.releases.isEmpty())
			return false;
		return true;
	}
	
	/*
	 * Computed fields: releaseCount, featureCount
	 * - transient
	 * - encapsulated: only getters?
	 * - computing rule: SummaringProjectDomainService
	 */
	@Transient
	protected Integer releaseCount = 0;
	@Transient
	protected Integer featureCount = 0;

	public Integer getReleaseCount() {
		return releaseCount;
	}
	public Integer getFeatureCount() {
		return featureCount;
	}
	public void setReleaseCount(Integer releaseCount) {
		this.releaseCount = releaseCount;
	}
	public void setFeatureCount(Integer feaureCount) {
		this.featureCount = feaureCount;
	}
}

// https://www.baeldung.com/javax-validation-method-constraints
// https://www.baeldung.com/javax-validation
// https://www.baeldung.com/spring-mvc-custom-validator

