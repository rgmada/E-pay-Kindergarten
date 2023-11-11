package org.scrum.domain.services;


import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.runner.RunWith;
import org.scrum.application.services.Test4_AuditingApplicationService;
import org.scrum.application.services.Test5_EventWorkflowService;

@RunWith(JUnitPlatform.class)
//@Suite
@SelectClasses({ 
	Test1_SimpleDomainService.class, 
	Test2_ComputingDomainServices.class,
	Test3_BusinessWorkflowService.class,
	Test4_AuditingApplicationService.class,
	Test5_EventWorkflowService.class,
	Test6_ValidatingDomainService.class 
})
public class AllTests {

}

/*
* Junit5 docs: https://junit.org/junit5/docs/current/user-guide/#overview
*/