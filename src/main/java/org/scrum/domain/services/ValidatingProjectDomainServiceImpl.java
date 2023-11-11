package org.scrum.domain.services;

import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.scrum.domain.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class ValidatingProjectDomainServiceImpl 
		implements IValidatingProjectDomainService, ApplicationListener<DomainEvent>{
	private static Logger logger = Logger.getLogger(ValidatingProjectDomainServiceImpl.class.getName());
	
	@Autowired
	private Validator validator;
	
	//
	@Override
	public Set<String> validate(Project project){
		// .validate(entity), .validateProperty(entity, "propName")
		// .validateValue(ClassName.class, "propName", value)
		Set<ConstraintViolation<Project>> violations = validator.validate(project);
		
		logger.info("Violations count: " + violations.size());
		
		
		return violations.stream()
				.map(violation -> violation.getMessage() 
					+ " (" + violation.getInvalidValue() 
					+ ") is an invalid value!" )
				.collect(Collectors.toSet()); 
	}
	
	@Override
	public void validateWithException(Project project) throws Exception{
		Set<String> violations = validate(project);
		logger.info("Violations count (to generate exception): " + violations.size());
		
		if (violations.size() > 0) {
			String violationExceptionMessage = violations.stream()
					.map(violation -> "\n>>> JEE.Spring bean validator exception: " + violation)
					.collect(Collectors.joining(", "));
			try {
				validateProjectAggregate(project);
			}catch(Exception ex) {
				violationExceptionMessage += "\n>>> Local validation: "
						+ ex.getMessage();
			}
			// throw new RuntimeException(violationExceptionMessage);
			throw new Exception(violationExceptionMessage);
		}
	}
	
	
	// Event-based Business Service Integration
	// SpringEvents:: Listening
	@Override
	public void onApplicationEvent(DomainEvent domainEvent) {
		logger.info("Validator object :: " + validator);
		logger.info("Validator class:: " + validator.getClass().getName());
		
		Project buildProject = domainEvent.getMessage();
		logger.info(">>>***Validating_ListeningEvents***>>> for: " + domainEvent.getMessage());
		
		try {
			this.validateWithException(buildProject);
		}catch(Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}
	
	public boolean validateProjectAggregate(Project p) {
		if (p == null)
			throw new RuntimeException("Null Project!");
		if (p.getReleases() == null || p.getReleases().size() == 0)
			throw new RuntimeException("Null Releases!");
		if (p.getCurrentRelease() == null)
			throw new RuntimeException("No current release!");
		if (p.getCurrentRelease().getFeatures() == null || p.getCurrentRelease().getFeatures().isEmpty())
			throw new RuntimeException("Null Features!");
		return true;
	}
}
