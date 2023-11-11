 package org.scrum.domain.services;

import java.util.Date;
import java.util.logging.Logger;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.scrum.domain.project.Project;
import org.scrum.domain.project.ProjectCurrentReleaseView;
import org.scrum.spring.AppScanConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/*
 * VALIDATION Service Tests
 * 
 * Strategies
 * - Simple Facade
 * - Event-based from test
 * - Event-based from Workflow service
 * https://www.logicbig.com/tutorials/spring-framework/spring-core/integration-testing.html
 */

//JUnit.5
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppScanConfig.class)
@TestMethodOrder(OrderAnnotation.class)
public class Test6_ValidatingDomainService {
	private static Logger logger = Logger.getLogger(Test6_ValidatingDomainService.class.getName());
	
	// Support Services
	@Autowired
	private IProjectEntityRepository entityRepository;
	
	/*
	 * Validation Flow built in-place 
	 * Validation Service as Simple-Facade
	 */
	@Autowired
	IValidatingProjectDomainService validatorService;
	
	
    @Test @Order(1) // @Disabled
	public void test1_ValidatingProjectDomainService() {
		logger.info("Validation Service object :: " + validatorService);
		logger.info("Validation Service class:: " + validatorService.getClass().getName());
		
		Project project = entityRepository.getById(3);
		
		project.setName(null);
		System.out.println("Project to be validated: " + project);
		
		try {
			validatorService.validateWithException(project);
		}catch(Exception ex) {
			logger.severe(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	
	/*
	 * Validation Flow by event-forwarding pattern
	 * ValidatingProjectDomainService as event-consuming service
	 */
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Test  @Order(2) // @Disabled
	public void test2_ValidatingProjectDomainService() {
		// entityFactory.initDomainServiceEntities();
		Project project = entityRepository.getById(3);
		
		project.setName(null);
		project.setReleases(null);
		System.out.println("Project to be validated: " + project);
		
		// Invoke Validator service by event
		try {
			applicationEventPublisher
				.publishEvent(new DomainEvent(this, project));
		}catch(Exception ex) {
			logger.severe(ex.getMessage());
			
		}
	}
	
	/*
	 * Validation Service invoked by DomainEvent
	 * - Validation service: ValidatingProjectDomainServiceImpl
	 * - DomainEvent source: PlanningProjectBusinessWorkflowServiceImpl
	 * ValidatingProjectDomainService as event-consuming service
	 */
	@Autowired @Qualifier("PlanningProjectBusinessWorkflowServiceFacade")
	private IPlanningProjectBusinessWorkflowService planningProjectBusinessWorkflowFacadeService;
	
	@Test  @Order(3) @Disabled
	public void test3_AuditWorkflowService() {
		logger.info("Workflow Service implementation instance:: " + planningProjectBusinessWorkflowFacadeService);
		logger.info("Workflow Service implementation class:: " + planningProjectBusinessWorkflowFacadeService.getClass().getName());
		
		try {
			// generate exceptions
			Integer projectId = planningProjectBusinessWorkflowFacadeService.planNewProject("Planned.Test.Project", new Date());
			
			planningProjectBusinessWorkflowFacadeService.addFeatureToProject(projectId, "Test.Feature_A", "Planned.Test");
			planningProjectBusinessWorkflowFacadeService.addFeatureToProject(projectId, "Test.Feature_B", "Planned.Test");
			planningProjectBusinessWorkflowFacadeService.addFeatureToProject(projectId, "Test.Feature_C", "Planned.Test");
			Date futureDateOf45Day = new Date(new Date().getTime() + 1000*60*60*24*45);
			planningProjectBusinessWorkflowFacadeService.planCurrentRelease(projectId, futureDateOf45Day);
			ProjectCurrentReleaseView viewData = planningProjectBusinessWorkflowFacadeService.getProjectSummaryData(projectId);
			logger.info(viewData.toString());
		}catch(Exception ex) {
			logger.info("EXCEPTION from business workflow::: " + ex.getMessage());
		}
	}
	
	
	/* Integrated test:
	 * - PlanningProjectBusinessWorkflowService as proxy-AOP
	 * - AuditingPlanningProjectBusinessWorkflowService with aspect-AOP
	 * - ValidatingProjectDomainService as event-consuming service
	 */
	@Autowired @Qualifier("proxyAuditedApplicationService")
	private IPlanningProjectBusinessWorkflowService auditedService;
	
	@Test  @Order(4) @Disabled
	public void test4_IntegratedServices() {
		logger.info("Workflow Service implementation instance:: " + auditedService);
		logger.info("Workflow Service implementation class:: " + auditedService.getClass().getName());
		
		try {
			// generate exceptions
			Integer projectId = auditedService.planNewProject("Planned.Test.Project", new Date());
			
			auditedService.addFeatureToProject(projectId, "Test.Feature_A", "Planned.Test");
			auditedService.addFeatureToProject(projectId, "Test.Feature_B", "Planned.Test");
			auditedService.addFeatureToProject(projectId, "Test.Feature_C", "Planned.Test");
			Date futureDateOf45Day = new Date(new Date().getTime() + 1000*60*60*24*45);
			auditedService.planCurrentRelease(projectId, futureDateOf45Day);
			ProjectCurrentReleaseView viewData = auditedService.getProjectSummaryData(projectId);
			logger.info(viewData.toString());
		}catch(Exception ex) {
			logger.info("EXCEPTION from business workflow::: " + ex.getMessage());
		}
	}
}