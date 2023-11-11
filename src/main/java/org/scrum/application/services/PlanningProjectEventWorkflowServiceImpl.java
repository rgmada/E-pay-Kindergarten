package org.scrum.application.services;

import java.util.Date;
import java.util.logging.Logger;

import org.scrum.domain.project.ProjectCurrentReleaseView;
import org.scrum.domain.services.PlanningProjectBusinessWorkflowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service("PlanningProjectEventWorkflowServiceFacade")
public class PlanningProjectEventWorkflowServiceImpl 
		extends PlanningProjectBusinessWorkflowServiceImpl 
		implements ApplicationListener<WorkflowCallEvent>{
	
	private static Logger logger = Logger.getLogger(PlanningProjectEventWorkflowServiceImpl.class.getName());
	
	// Event Producer Service
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	// Event Consumer Service
	@Override
	public void onApplicationEvent(WorkflowCallEvent event) {
		logger.info("Workflow Service: processing event!");
		//
		if (event.getWorkflowAction().equals(WorkflowAction.PLAN_NEW_PROJECT)) {
			logger.info("Workflow Service: processing PLAN_NEW_PROJECT event!");
			Integer projectId = this.planNewProject(
					(String)event.getWorkflowParameters()[0], 
					(Date)event.getWorkflowParameters()[1]);
			//
			applicationEventPublisher.publishEvent(new WorkflowResponseEvent(
					this, 
					new Object[] {projectId}, 
					WorkflowAction.PLAN_NEW_PROJECT));
		}
		//
		if (event.getWorkflowAction().equals(WorkflowAction.ADD_FEATURE_TO_PROJECT)) {
			logger.info("Workflow Service: processing ADD_FEATURE_TO_PROJECT event!");
			Integer releaseId = this.addFeatureToProject(
					(Integer)event.getWorkflowParameters()[0], 
					(String)event.getWorkflowParameters()[1],
					(String)event.getWorkflowParameters()[2]);
			applicationEventPublisher.publishEvent(new WorkflowResponseEvent(
					this, 
					new Object[] {releaseId}, 
					WorkflowAction.ADD_FEATURE_TO_PROJECT));
		}
		//
		if (event.getWorkflowAction().equals(WorkflowAction.PLAN_CURRENT_RELEASE)) {
			logger.info("Workflow Service: processing PLAN_CURRENT_RELEASE event!");
			Integer releaseId = this.planCurrentRelease(
					(Integer)event.getWorkflowParameters()[0], 
					(Date)event.getWorkflowParameters()[1]);
			applicationEventPublisher.publishEvent(new WorkflowResponseEvent(
					this, 
					new Object[] {releaseId}, 
					WorkflowAction.PLAN_CURRENT_RELEASE));
		}
		//
		if (event.getWorkflowAction().equals(WorkflowAction.GET_PROJECT_SUMMARY_DATA)) {
			logger.info("Workflow Service: processing GET_PROJECT_SUMMARY_DATA event!");
			ProjectCurrentReleaseView viewData = this.getProjectSummaryData(
					(Integer)event.getWorkflowParameters()[0]);
			applicationEventPublisher.publishEvent(new WorkflowResponseEvent(
					this, 
					new Object[] {viewData}, 
					WorkflowAction.GET_PROJECT_SUMMARY_DATA));
		}
		
	}
	//
}
