package org.scrum.domain.project;

public class ProjectAuditView {
	private Integer projectNo;
	private String featureName;
	private EFeatureOperation operation;
	
	public Integer getProjectNo() {
		return projectNo;
	}
	public String getFeatureName() {
		return featureName;
	}
	public EFeatureOperation getOperation() {
		return operation;
	}
	public ProjectAuditView(Integer projectNo, String featureName, EFeatureOperation operation) {
		super();
		this.projectNo = projectNo;
		this.featureName = featureName;
		this.operation = operation;
	}
	
	@Override
	public String toString() {
		return "ProjectAuditView [projectNo=" + projectNo + ", featureName=" + featureName + ", operation=" + operation
				+ "]";
	}

	public enum EFeatureOperation{
		UPDATED, REMOVED, ADDED;
	}	
}
