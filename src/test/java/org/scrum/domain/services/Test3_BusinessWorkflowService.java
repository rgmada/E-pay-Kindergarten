package org.scrum.domain.services;

import java.util.Date;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scrum.domain.project.ProjectCurrentReleaseView;
import org.scrum.spring.AppScanConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/*
 * WORKFLOW Business Service Tests
 * Strategy: Simple-Facade
 * 
 */
//JUnit.5
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppScanConfig.class)
public class Test3_BusinessWorkflowService {
	private static Logger logger = Logger.getLogger(Test3_BusinessWorkflowService.class.getName());
	
	/* Simple-Facade Business Service
	 * Injection from scanning 
	 * @Service("PlanningProjectBusinessWorkflowServiceImpl")
	 * Bean-implementation class
	 */
	@Autowired @Qualifier("PlanningProjectBusinessWorkflowServiceFacade")
	private IPlanningProjectBusinessWorkflowService planningProjectBusinessWorkflowFacadeService;
	
	@Test
	public void test1_WorkflowServiceFacade() {
		logger.info("Domain Service implementation instance:: " + planningProjectBusinessWorkflowFacadeService);
		logger.info("Dpmain Service implementation class:: " 
					+ planningProjectBusinessWorkflowFacadeService.getClass().getName());
		
		Date tomorow = new Date(new Date().getTime() + 1000*60*60*24*1);
		// 1.
		Integer projectId = planningProjectBusinessWorkflowFacadeService.planNewProject("Planned.Test.Project", tomorow);
		// 2...
		planningProjectBusinessWorkflowFacadeService.addFeatureToProject(projectId, "Test.Feature_A", "Planned.Test");
		planningProjectBusinessWorkflowFacadeService.addFeatureToProject(projectId, "Test.Feature_B", "Planned.Test");
		planningProjectBusinessWorkflowFacadeService.addFeatureToProject(projectId, "Test.Feature_C", "Planned.Test");
		// 3.
		Date futureDateOf45Day = new Date(new Date().getTime() + 1000*60*60*24*45);
		planningProjectBusinessWorkflowFacadeService.planCurrentRelease(projectId, futureDateOf45Day);
		// 4.
		ProjectCurrentReleaseView viewData = planningProjectBusinessWorkflowFacadeService.getProjectSummaryData(projectId);
		// 
		logger.info(viewData.toString());	
	}
}
