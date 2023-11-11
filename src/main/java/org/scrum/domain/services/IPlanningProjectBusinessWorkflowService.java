package org.scrum.domain.services;

import java.util.Date;

import org.scrum.domain.project.ProjectCurrentReleaseView;

/*
 * Business Workflow Steps/Actions:
 * (1) Create new project with default template: projectName, startDate
 * (2) Add features to project: projectId, releaseId, featureName, featureDescription
 * (3) Set currentRelease.publishDate
 * (4) Get project summary data: ProjectCurrentReleaseView
 */
public interface IPlanningProjectBusinessWorkflowService {

	// (1) Create new project with default template: projectName, startDate
	Integer planNewProject(String projectName, Date startDate);

	// (2) Create new project with default template: projectName, startDate
	Integer addFeatureToProject(Integer projectId, String featureName, String featureDescription);

	// (3) Create new project with default template: projectName, startDate
	Integer planCurrentRelease(Integer projectId, Date publishDate);

	// (4) Get project summary data: ProjectCurrentReleaseView
	ProjectCurrentReleaseView getProjectSummaryData(Integer projectId);

}