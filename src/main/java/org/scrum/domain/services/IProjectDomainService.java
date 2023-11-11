package org.scrum.domain.services;

import java.util.List;

import org.scrum.domain.project.Feature;
import org.scrum.domain.project.Project;

public interface IProjectDomainService {
	public List<Feature> getProjectFeatures(Project project);
	public Integer getProjectFeaturesCount(Project project);
	
	public List<Feature> getProjectFeatures(Integer projectID);
	public Integer getProjectFeaturesCount(Integer projectID);
	
	public Feature getProjectFeature(Project project, String featureName);
	
	// Dependencies to get IDs
	public void setProjectEntityRepository(IProjectEntityRepository repository);
}
