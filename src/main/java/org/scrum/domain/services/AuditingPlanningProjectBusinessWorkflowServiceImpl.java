package org.scrum.domain.services;

import java.util.logging.Logger;

import org.scrum.domain.project.ProjectAuditView;
import org.springframework.stereotype.Service;

@Service("AuditingPlanningProjectBusinessWorkflowServiceImpl")
// Auditing PlanningProjectBusinessWorkflowService :: addFeatureToProject
public class AuditingPlanningProjectBusinessWorkflowServiceImpl implements 
		IAuditingPlanningProjectBusinessWorkflowService {
	private static Logger logger = Logger.getLogger(AuditingPlanningProjectBusinessWorkflowServiceImpl.class.getName());
	
	// Business Logic
	@Override
	public void auditProjectFeature(Integer projectId, String featureName) {
		logger.info("AUDIT INFO: " + new ProjectAuditView(
				projectId, featureName, 
				ProjectAuditView.EFeatureOperation.ADDED));
		
	}
}
