package org.scrum.domain.services;

import org.scrum.domain.project.Project;
import org.springframework.context.ApplicationEvent;

public class DomainEvent extends ApplicationEvent{
	private static final long serialVersionUID = 1L;
	//
	private Project message;
	
	public DomainEvent(Object source) {
		super(source);
	}

	public DomainEvent(Object source, Project message) {
		super(source);
		this.message = message;
	}
	
	public Project getMessage() {
		return message;
	}
}

// https://www.baeldung.com/spring-events