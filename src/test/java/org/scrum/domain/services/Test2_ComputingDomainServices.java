package org.scrum.domain.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scrum.domain.project.Project;
import org.scrum.domain.project.ProjectCurrentReleaseView;
import org.scrum.spring.AppScanConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/*
 * COMPUTATION Business Service Tests
 * Strategy: Simple-Facade
 * 
 * https://www.logicbig.com/tutorials/spring-framework/spring-core/integration-testing.html
 */

//JUnit.5
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppScanConfig.class)
@TestMethodOrder(OrderAnnotation.class)
public class Test2_ComputingDomainServices {
	private static Logger logger = Logger.getLogger(Test2_ComputingDomainServices.class.getName());
	
	// Support Services
	@Autowired
	private IProjectEntityRepository entityRepository;
	
	// Computing Services
	@Autowired
	private ISummarizingProjectDomainService summarizingProjectdomainService;
	
	@Autowired
	private IConsolidatingProjectCurrentReleaseViewDomainService consolidatingProjectDomainService;
	
	// Test Business Logic
	@Test @Order(1) // @Disabled
	public void test1_SummarizingProjectDomainService() {
		logger.info("Domain Service implementation instance:: " + summarizingProjectdomainService);
		logger.info("Domain Service implementation class:: " + summarizingProjectdomainService.getClass().getName());
		
		Project project = entityRepository.getById(1);
		assertNotNull(project);
		
		project = summarizingProjectdomainService.countingFeatures(project);
		project = summarizingProjectdomainService.countingReleases(project);
		
		logger.info("Computed project: " + project.getName() 
			+ " featureCount = " + project.getFeatureCount()
			+ " releaseCount = " + project.getReleaseCount());
		
		
		assertNotNull(project.getFeatureCount());
		assertTrue(" featureCount not computed!", project.getFeatureCount() > 0);
	}
	
	//
	@Test @Order(2) // @Disabled
	public void test2_ConsolidatingProjectDomainService() {
		logger.info("Service implementation instance:: " + consolidatingProjectDomainService);
		logger.info("Service implementation class:: " + consolidatingProjectDomainService.getClass().getName());
		// entityFactory.initDomainServiceEntities();
		
		ProjectCurrentReleaseView projectView = consolidatingProjectDomainService
				.getProjectCurrentReleaseViewDataOf(entityRepository.getById(1));
		logger.info("Computed ProjectCurrentReleaseView: " + projectView);
		
		List<ProjectCurrentReleaseView> projectViewListData = consolidatingProjectDomainService
				.getProjectCurrentReleaseViewDataOf(entityRepository.toCollection());
		projectViewListData.stream().forEach(p -> logger.info("Computed ProjectCurrentReleaseView:: " + p));
	}
}