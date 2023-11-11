package org.scrum.application.services;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.scrum.domain.project.ProjectAuditView;
import org.scrum.domain.services.AuditingPlanningProjectBusinessWorkflowServiceImpl;
import org.scrum.domain.services.IAuditingPlanningProjectBusinessWorkflowService;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Service;

@Service("AuditingPlanningProjectApplicationServiceImpl")
public class AuditingPlanningProjectApplicationServiceImpl
	extends
		AuditingPlanningProjectBusinessWorkflowServiceImpl
	implements 
		IAuditingPlanningProjectBusinessWorkflowService, 
		MethodInterceptor, AfterReturningAdvice{
	
	private static Logger logger = Logger.getLogger(AuditingPlanningProjectApplicationServiceImpl.class.getName());

	// AOP Invocation Logic
	@Override
	public Object invoke(MethodInvocation ic) throws Throwable {
		// Before invoking target object
		logger.info(">>>> SpringAOP.INTERCEPTION: Entering MethodInvocation for: " 
					+ ic.getMethod().getName()
					+ ", " + ic.getStaticPart().getClass() 
					+ ", " + ic.getThis().getClass());
		try {
			if (ic.getMethod().getName().equals("addFeatureToProject")) {
				// Decorated Business Logic
				Integer projectId = (Integer) ic.getArguments()[0];
				String featureName = (String) ic.getArguments()[1];
				auditProjectFeature(projectId, featureName);
			}
			// invoke target
			return ic.proceed();
		} finally {
			logger.info(">>>> SpringAOP.INTERCEPTION: Exiting MethodInvocation for: " + ic.getMethod().getName());
		}
	}
	
	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) 
		throws Throwable {
		// target is already invoked
		logger.info(">>>> SpringAOP.INTERCEPTION: Execute afterReturning for: "  + method.getName());
		if (method.getName().equals("addFeatureToProject")) {
			Integer newProjectId = (Integer) returnValue;
			
			// Decorated Business Logic
			Integer projectId = (Integer) args[0];
			String featureName = (String) args[1];
			auditProjectFeature(projectId, featureName);
	
			logger.info(">>>...>>> Spring.AOP.DECORATED PROJECT with features: " + newProjectId);
		}
		
	}
}
