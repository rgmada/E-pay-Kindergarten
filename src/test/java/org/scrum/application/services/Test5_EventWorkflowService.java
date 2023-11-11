package org.scrum.application.services;

import java.util.Date;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scrum.domain.project.ProjectCurrentReleaseView;
import org.scrum.spring.AppScanConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/*
 * WORKFLOW Business Service Tests
 * Strategy: Event-based
 * 
 */
//JUnit.5
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppScanConfig.class)
@Component
public class Test5_EventWorkflowService implements ApplicationListener<WorkflowResponseEvent> {
	
	private static Logger logger = Logger.getLogger(Test5_EventWorkflowService.class.getName());
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	/* Event-based Business Workflow Service
	 * 
	 */
	@Test
	public void test1_WorkflowServiceFacade() {
		logger.info("Start Event processing Test ...");	
		logger.info("Start workflow...");	
		Date tomorow = new Date(new Date().getTime() + 1000*60*60*24*1);
		//
		applicationEventPublisher.publishEvent(new WorkflowCallEvent(
					this,
					new Object[] {"Planned.Test.Project", tomorow},
					WorkflowAction.PLAN_NEW_PROJECT
				));
	}
	
	private Integer projectId = null;
	private Integer featureCount = 0;
	@Override
	public void onApplicationEvent(WorkflowResponseEvent event) {
		logger.info("Processing Workflow Event...");	
		
		// 1... response event management
		if (event.getWorkflowAction().equals(WorkflowAction.PLAN_NEW_PROJECT)) {
			projectId = (Integer) event.getWorkflowParameters()[0];
			System.out.println("Project planned: " + projectId);
			//
			applicationEventPublisher.publishEvent(new WorkflowCallEvent(
					this,
					new Object[] {projectId, "Test.Feature_A", "Planned.Test"},
					WorkflowAction.ADD_FEATURE_TO_PROJECT
			));
		}
		// 2..
		if (event.getWorkflowAction().equals(WorkflowAction.ADD_FEATURE_TO_PROJECT)
				&& projectId != null) {
			Integer releaseId =  (Integer) event.getWorkflowParameters()[0];
			System.out.println("Feature added to release: " + releaseId);
			System.out.println("Planning release for projectID: " + projectId);
			// more features?
			featureCount++;
			if(featureCount.equals(1)) {
				applicationEventPublisher.publishEvent(new WorkflowCallEvent(
						this,
						new Object[] {projectId, "Test.Feature_B", "Planned.Test"},
						WorkflowAction.ADD_FEATURE_TO_PROJECT));
			}else if(featureCount.equals(2)) {
				applicationEventPublisher.publishEvent(new WorkflowCallEvent(
						this,
						new Object[] {projectId, "Test.Feature_C", "Planned.Test"},
						WorkflowAction.ADD_FEATURE_TO_PROJECT));
				
			}
			//
			Date futureDateOf45Day = new Date(new Date().getTime() + 1000*60*60*24*45);
			applicationEventPublisher.publishEvent(new WorkflowCallEvent(
					this,
					new Object[] {projectId, futureDateOf45Day},
					WorkflowAction.PLAN_CURRENT_RELEASE
			));
		}
		// 3..
		if (event.getWorkflowAction().equals(WorkflowAction.PLAN_CURRENT_RELEASE)
				&& projectId != null) {
			Integer releaseId =  (Integer) event.getWorkflowParameters()[0];
			System.out.println("Release planned: " + releaseId);
			//
			applicationEventPublisher.publishEvent(new WorkflowCallEvent(
					this,
					new Object[] {projectId},
					WorkflowAction.GET_PROJECT_SUMMARY_DATA
			));
		}
		// 4..
		if (event.getWorkflowAction().equals(WorkflowAction.GET_PROJECT_SUMMARY_DATA)) {
			ProjectCurrentReleaseView viewData =  (ProjectCurrentReleaseView) event.getWorkflowParameters()[0];
			System.out.println("ProjectCurrentReleaseView: " + viewData);
		}
		
	}
	//
}