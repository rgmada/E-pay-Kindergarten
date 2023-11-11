package org.scrum.domain.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.scrum.domain.project.Project;
import org.scrum.domain.project.ProjectCurrentReleaseView;
import org.springframework.stereotype.Service;

@Service
public class ConsolidatingProjectCurrentReleaseViewBusinessServiceImpl implements IConsolidatingProjectCurrentReleaseViewDomainService {
	@Override
	public ProjectCurrentReleaseView getProjectCurrentReleaseViewDataOf(Project project) {
		return new ProjectCurrentReleaseView(
				project.getProjectNo(), project.getName(), 
				project.getCurrentRelease().getReleaseId(), 
				project.getCurrentRelease().getCodeName(), 
				project.getCurrentRelease().getFeatures().size());
	}
	
	@Override
	public List<ProjectCurrentReleaseView> getProjectCurrentReleaseViewDataOf(Collection<Project> projects) {
		 List<ProjectCurrentReleaseView> viewDataList = 
				 projects.stream()
				 	.map(project -> getProjectCurrentReleaseViewDataOf(project))
				 	.collect(Collectors.toList());
		 
		 return viewDataList;
	}
}
