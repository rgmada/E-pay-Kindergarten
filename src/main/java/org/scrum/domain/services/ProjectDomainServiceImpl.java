package org.scrum.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.scrum.domain.project.Feature;
import org.scrum.domain.project.Project;
import org.scrum.domain.project.Release;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// SDI Bean Component  
@Component("ProjectDomainService")
public class ProjectDomainServiceImpl implements IProjectDomainService {

	@Override
	public List<Feature> getProjectFeatures(Project project) {
		List<Feature> projectFeatures = new ArrayList<>();
		for (Release r : project.getReleases())
			projectFeatures.addAll(r.getFeatures());
		return projectFeatures;
	}

	@Override
	public Integer getProjectFeaturesCount(Project project) {
		List<Feature> projectFeatures = getProjectFeatures(project);
		return projectFeatures.size();
	}

	@Override
	public List<Feature> getProjectFeatures(Integer projectID) {
		Project project = entityRepository.getById(projectID);
		return getProjectFeatures(project);
	}

	@Override
	public Integer getProjectFeaturesCount(Integer projectID) {
		Project project = entityRepository.getById(projectID);
		return getProjectFeaturesCount(project);
	}

	// Dependency
	@Autowired
	private IProjectEntityRepository entityRepository;

	@Override
	public void setProjectEntityRepository(IProjectEntityRepository repository) {
		this.entityRepository = repository;
	}

	public ProjectDomainServiceImpl() {
		super();
	}

	public ProjectDomainServiceImpl(IProjectEntityRepository entityRepository) {
		super();
		this.entityRepository = entityRepository;
	}

	@Override
	public Feature getProjectFeature(Project project, String featureName) {
		List<Feature> projectFeatures = getProjectFeatures(project);
		List<Feature> features = projectFeatures.stream().filter(f -> f.getName().equals(featureName)).collect(Collectors.toList());
		if (features.size() > 0)
			return features.get(0);
		
		return null;
	}

	public IProjectEntityRepository getEntityRepository() {
		return entityRepository;
	}

}
