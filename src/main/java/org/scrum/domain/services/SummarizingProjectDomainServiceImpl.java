package org.scrum.domain.services;

import org.scrum.domain.project.Feature;
import org.scrum.domain.project.Project;
import org.scrum.domain.project.Release;
import org.springframework.stereotype.Service;

/*
 * Naming template: Action.ing Entity-name Service-type
 */
@Service
public class SummarizingProjectDomainServiceImpl implements ISummarizingProjectDomainService {
	@Override
	public Project countingReleases(Project project) {
		Integer releaseCount = (project.getReleases() == null) ? 0 : project.getReleases().size();
		project.setReleaseCount(releaseCount);
		return project;
	}
	@Override
	public Project countingFeatures(Project project) {
		
		Integer fc = 0;
		
		//
		for(Release r: project.getReleases())
			for(Feature f: r.getFeatures())
				fc++;
		project.setFeatureCount(fc);
		
		//
		Long featureCount = (project.getReleases() == null) ? 0 : 
			project.getReleases().stream()
				.flatMap(r -> r.getFeatures().stream()).count();
		project.setFeatureCount(featureCount.intValue());
		//
		
		return project;
	}
}
