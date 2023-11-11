package org.scrum.application.services;

// Workflow event
public class WorkflowResponseEvent extends WorkflowEvent{
	private static final long serialVersionUID = 1L;

	//
	public WorkflowResponseEvent(Object source, Object[] workflowParameters, WorkflowAction workflowAction) {
		super(source, workflowParameters, workflowAction);
	}
}