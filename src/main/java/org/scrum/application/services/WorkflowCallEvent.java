package org.scrum.application.services;

// Workflow event
public class WorkflowCallEvent extends WorkflowEvent{
	private static final long serialVersionUID = 1L;

	//
	public WorkflowCallEvent(Object source, Object[] workflowParameters, WorkflowAction workflowAction) {
		super(source, workflowParameters, workflowAction);
	}
}