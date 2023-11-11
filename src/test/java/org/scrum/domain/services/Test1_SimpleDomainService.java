package org.scrum.domain.services;

import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scrum.spring.AppScanConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/*
 * DOMAIN Service Tests
 * Strategy: Simple-Facade
 * 
 * https://www.logicbig.com/tutorials/spring-framework/spring-core/integration-testing.html
 */

//JUnit.5
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppScanConfig.class)
public class Test1_SimpleDomainService {
	private static Logger logger = Logger.getLogger(Test1_SimpleDomainService.class.getName());

	@Autowired @Qualifier("ProjectDomainService")
	private IProjectDomainService service;
	
	@Test
	public void test() { 
		logger.info("Service implementation object :: " + service);
		logger.info("Service implementation class :: " + service.getClass().getName());
		//
		Integer featureCount = service.getProjectFeaturesCount(1);
		assertTrue("Features not counting...", featureCount > 0);
		logger.info("Feature count autowired xml:: " + featureCount);
	}
	
}
