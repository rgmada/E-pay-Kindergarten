package org.scrum.domain.services;

import org.scrum.domain.project.Project;

public interface ISummarizingProjectDomainService {

	Project countingReleases(Project project);

	Project countingFeatures(Project project);
	
	// List<Project> countingReleases(List<Project> projects);
}