package org.scrum.domain.services;

import java.util.Collection;
import java.util.List;

import org.scrum.domain.project.Project;
import org.scrum.domain.project.ProjectCurrentReleaseView;

public interface IConsolidatingProjectCurrentReleaseViewDomainService {

	ProjectCurrentReleaseView getProjectCurrentReleaseViewDataOf(Project project);

	List<ProjectCurrentReleaseView> getProjectCurrentReleaseViewDataOf(Collection<Project> projects);

}