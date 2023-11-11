package org.scrum.application.services;

import org.springframework.context.ApplicationEvent;

// Workflow event
public class WorkflowEvent extends ApplicationEvent{
	private static final long serialVersionUID = 1L;
	
	private Object[] workflowParameters;
	private WorkflowAction workflowAction;
	
	public Object[] getWorkflowParameters() {
		return workflowParameters;
	}
	public WorkflowAction getWorkflowAction() {
		return workflowAction;
	}

	//
	public WorkflowEvent(Object source, Object[] workflowParameters, WorkflowAction workflowAction) {
		super(source);
		this.workflowParameters = workflowParameters;
		this.workflowAction = workflowAction;
	}
}