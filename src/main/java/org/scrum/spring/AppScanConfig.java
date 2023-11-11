package org.scrum.spring;

import java.util.logging.Logger;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.scrum.domain.services.IAuditingPlanningProjectBusinessWorkflowService;
import org.scrum.domain.services.IPlanningProjectBusinessWorkflowService;
import org.scrum.domain.services.IProjectEntityFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ComponentScan(basePackages = {
		"org.scrum.domain.services",
		"org.scrum.application.services"
	})
public class AppScanConfig {
	private static Logger logger = Logger.getLogger(AppScanConfig.class.getName());
	
	// AOP Auditing
	@Autowired @Qualifier("PlanningProjectBusinessWorkflowServiceFacade")
	private IPlanningProjectBusinessWorkflowService planningProjectBusinessWorkflowService;
	
	@Autowired @Qualifier("AuditingPlanningProjectApplicationServiceImpl")
	private IAuditingPlanningProjectBusinessWorkflowService auditApplicationService;
	
	@Bean(name="proxyAuditedApplicationService")
	public IPlanningProjectBusinessWorkflowService initAuditedApplicationService() {
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvice((AfterReturningAdvice)auditApplicationService);
		pf.setTarget(planningProjectBusinessWorkflowService);
		IPlanningProjectBusinessWorkflowService proxyAuditedApplicationService = 
				(IPlanningProjectBusinessWorkflowService) pf.getProxy();
		
		return proxyAuditedApplicationService;
	}
	
	/*
	@Bean 
	public LocalValidatorFactoryBean initSpringvalidator() {
		logger.info("... Init LocalValidatorFactoryBean CONTEXT.");
		return new LocalValidatorFactoryBean();
	}
	*/
	
	@Bean
	public Validator initValidator() {
		logger.info("... Init VALIDATION CONTEXT.");
		
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		
		return validator;
	}
}