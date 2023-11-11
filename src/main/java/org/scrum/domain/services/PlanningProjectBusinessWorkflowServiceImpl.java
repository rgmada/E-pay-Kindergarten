package org.scrum.domain.services;

import java.util.Date;

import org.scrum.domain.project.Project;
import org.scrum.domain.project.ProjectCurrentReleaseView;
import org.scrum.domain.project.Release;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("PlanningProjectBusinessWorkflowServiceFacade")
public class PlanningProjectBusinessWorkflowServiceImpl 
		implements IPlanningProjectBusinessWorkflowService {
	// Support Services
	@Autowired
	private IProjectEntityRepository entityRepository;
	
	@Autowired @Qualifier("ProjectEntityFactoryBase")
	private IProjectEntityFactory entityFactory;
	
	@Autowired
	private IConsolidatingProjectCurrentReleaseViewDomainService consolidatingProjectService;
	
	// (1) Create new project with default template: projectName, startDate
	@Override
	public Integer planNewProject(String projectName, Date startDate) {
		Project project = entityFactory.buildProjectWith2R(projectName, startDate, 3);
		
		// Validate Event to invoke Validation Service
		// applicationEventPublisher.publishEvent(new DomainEvent(this, project));
		
		entityRepository.add(project);
		return project.getProjectNo();
	}
	
	// (2) Create new project with default template: projectName, startDate
	@Override
	public Integer addFeatureToProject(Integer projectId, String featureName, String featureDescription) {
		Project project = entityRepository.getById(projectId);
		project.getCurrentRelease().addFeature(featureName);
		return project.getCurrentRelease().getReleaseId();
	}
	
	// (3) Create new project with default template: projectName, startDate
	@Override
	public Integer planCurrentRelease(Integer projectId, Date publishDate) {
		Project project = entityRepository.getById(projectId);
		Release currentRelease = project.getCurrentRelease();
		
		currentRelease.setPublishDate(publishDate);
		return project.getCurrentRelease().getReleaseId();
	}
	
	// (4) Get project summary data: ProjectCurrentReleaseView
	@Override
	public ProjectCurrentReleaseView getProjectSummaryData(Integer projectId) {
		Project project = entityRepository.getById(projectId);
		return consolidatingProjectService.getProjectCurrentReleaseViewDataOf(project);
	}
}
