package org.scrum.application.services;

import java.util.Date;
import java.util.logging.Logger;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.scrum.domain.project.ProjectCurrentReleaseView;
import org.scrum.domain.services.IAuditingPlanningProjectBusinessWorkflowService;
import org.scrum.domain.services.IPlanningProjectBusinessWorkflowService;
import org.scrum.spring.AppScanConfig;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


/*
 * AUDIT Business Service Tests
 * 
 * WORKFLOW Business Service Tests
 * Strategy: AOP
 * 
 */
//JUnit.5
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppScanConfig.class)
@TestMethodOrder(OrderAnnotation.class)
public class Test4_AuditingApplicationService {
	private static Logger logger = Logger.getLogger(Test4_AuditingApplicationService.class.getName());
	
	/*
	 * Audit Flow built in-place 
	 * by MethodInterceptor aspect
	 */
	@Autowired @Qualifier("PlanningProjectBusinessWorkflowServiceFacade")
	private IPlanningProjectBusinessWorkflowService planningProjectBusinessWorkflowService;
	
	@Autowired @Qualifier("AuditingPlanningProjectApplicationServiceImpl")
	private IAuditingPlanningProjectBusinessWorkflowService auditingProjectFeatureDomainService; 
	
	@Test @Order(1) //@Disabled
	public void test1_AuditWorkflowService() {
		logger.info("Workflow Service implementation instance:: " + planningProjectBusinessWorkflowService);
		logger.info("Workflow Service implementation class:: " + planningProjectBusinessWorkflowService.getClass().getName());
		logger.info("Audit Domain Service implementation instance:: " + auditingProjectFeatureDomainService);
		logger.info("Audit Domain Service implementation class:: " + auditingProjectFeatureDomainService.getClass().getName());
		
		// Config AOP
		ProxyFactory pf = new ProxyFactory();
		// declare Advice
		pf.addAdvice((MethodInterceptor) auditingProjectFeatureDomainService);
		// define: target (pointcut)
		pf.setTarget(planningProjectBusinessWorkflowService);
		// get proxy-object that will wrap initial bean-object with an AOP aspect
		IPlanningProjectBusinessWorkflowService proxyService = 
				(IPlanningProjectBusinessWorkflowService) pf.getProxy();
		// invoke business logic on AOP decorated-bean-object
		Date tomorow = new Date(new Date().getTime() + 1000*60*60*24*1);
		Integer projectId = proxyService.planNewProject("Planned.Test.Project", tomorow);
		proxyService.addFeatureToProject(projectId, "Test.Feature_A", "Planned.Test");
		proxyService.addFeatureToProject(projectId, "Test.Feature_B", "Planned.Test");
		proxyService.addFeatureToProject(projectId, "Test.Feature_C", "Planned.Test");
		
		Date futureDateOf45Day = new Date(new Date().getTime() + 1000*60*60*24*45);
		proxyService.planCurrentRelease(projectId, futureDateOf45Day);
		
		ProjectCurrentReleaseView viewData = proxyService.getProjectSummaryData(projectId);
		logger.info(viewData.toString());	
	}
	
	/*
	 * Audit Flow built in-place 
	 * by AfterReturningAdvice aspect
	 */
    @Test @Order(2) //@Disabled
	public void test2_AuditWorkflowService() {
		logger.info("Workflow Service implementation instance:: " + planningProjectBusinessWorkflowService);
		logger.info("Workflow Service implementation class:: " + planningProjectBusinessWorkflowService.getClass().getName());
		logger.info("Audit Domain Service implementation instance:: " + auditingProjectFeatureDomainService);
		logger.info("Audit Domain Service implementation class:: " + auditingProjectFeatureDomainService.getClass().getName());
		
		// Config AOP: Defining an Aspect
		ProxyFactory pf = new ProxyFactory();
		// declare Advice
		pf.addAdvice((AfterReturningAdvice) auditingProjectFeatureDomainService);
		// define: target (pointcut)
		pf.setTarget(planningProjectBusinessWorkflowService);
		// get proxy-object that will wrap initial bean-object with an AOP aspect
		IPlanningProjectBusinessWorkflowService proxyService = (IPlanningProjectBusinessWorkflowService) pf.getProxy();
		
		Date tomorow = new Date(new Date().getTime() + 1000*60*60*24*1);
		// invoke AOP decorated-bean-object
		Integer projectId = proxyService.planNewProject("Planned.Test.Project", tomorow);
		proxyService.addFeatureToProject(projectId, "Test.Feature_A", "Planned.Test");
		proxyService.addFeatureToProject(projectId, "Test.Feature_B", "Planned.Test");
		proxyService.addFeatureToProject(projectId, "Test.Feature_C", "Planned.Test");
		
		Date futureDateOf45Day = new Date(new Date().getTime() + 1000*60*60*24*45);
	
		proxyService.planCurrentRelease(projectId, futureDateOf45Day);
		ProjectCurrentReleaseView viewData = proxyService.getProjectSummaryData(projectId);
		logger.info(viewData.toString());
	}
	
	/*
	 * Audit Flow built by AppScanConfig BeanFactory class
	 * by AfterReturningAdvice aspect
	 */
	@Autowired @Qualifier("proxyAuditedApplicationService")
	private IPlanningProjectBusinessWorkflowService auditedService;
	
	@Test @Order(3) // @Disabled
	public void test3_AuditWorkflowService() {
		logger.info("Audited Domain Service implementation instance:: " + auditedService);
		logger.info("Audited Domain Service implementation class:: " + auditedService.getClass().getName());
		
		Date tomorow = new Date(new Date().getTime() + 1000*60*60*24*1);
		Integer projectId = auditedService.planNewProject("Planned.Test.Project", tomorow);
		
		auditedService.addFeatureToProject(projectId, "Test.Feature_A", "Planned.Test");
		auditedService.addFeatureToProject(projectId, "Test.Feature_B", "Planned.Test");
		auditedService.addFeatureToProject(projectId, "Test.Feature_C", "Planned.Test");
		Date futureDateOf45Day = new Date(new Date().getTime() + 1000*60*60*24*45);
		auditedService.planCurrentRelease(projectId, futureDateOf45Day);
		ProjectCurrentReleaseView viewData = auditedService.getProjectSummaryData(projectId);
		logger.info(viewData.toString());
		
	}
}
