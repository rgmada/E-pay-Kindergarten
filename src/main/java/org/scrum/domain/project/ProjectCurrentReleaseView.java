package org.scrum.domain.project;

/**
 * 
 * Domain Entity View
 */
public class ProjectCurrentReleaseView {
	// Encapsulated tuple-field structure
	private Integer projectNo;
	private String name;
	//
	private Integer currentReleaseId;
	private String releaseCodeName;
	//
	private Integer releaseFeatureCount = 0;
	//
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
	public Integer getCurrentReleaseId() {
		return currentReleaseId;
	}
	public void setCurrentReleaseId(Integer currentReleaseId) {
		this.currentReleaseId = currentReleaseId;
	}
	public String getReleaseCodeName() {
		return releaseCodeName;
	}
	public void setReleaseCodeName(String releaseCodeName) {
		this.releaseCodeName = releaseCodeName;
	}
	public Integer getReleaseFeatureCount() {
		return releaseFeatureCount;
	}
	public void setReleaseFeatureCount(Integer releaseFeatureCount) {
		this.releaseFeatureCount = releaseFeatureCount;
	}
	// Tuple Constructor
	public ProjectCurrentReleaseView(Integer projectNo, String name, Integer currentReleaseId, String releaseCodeName,
			Integer releaseFeatureCount) {
		super();
		this.projectNo = projectNo;
		this.name = name;
		this.currentReleaseId = currentReleaseId;
		this.releaseCodeName = releaseCodeName;
		this.releaseFeatureCount = releaseFeatureCount;
	}
	// to Print
	@Override
	public String toString() {
		return "ProjectCurrentReleaseView [projectNo=" + projectNo + ", name=" + name + ", currentReleaseId="
				+ currentReleaseId + ", releaseCodeName=" + releaseCodeName + ", releaseFeatureCount="
				+ releaseFeatureCount + "]";
	}
}
