package org.scrum.domain.services;

import java.util.Set;

import org.scrum.domain.project.Project;

public interface IValidatingProjectDomainService {
	//
	Set<String> validate(Project project);
	//
	void validateWithException(Project project) throws Exception;
	//
	boolean validateProjectAggregate(Project p);
}