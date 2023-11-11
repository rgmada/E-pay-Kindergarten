package org.scrum.domain.services;

import java.util.Collection;

import org.scrum.domain.project.Project;

public interface IProjectEntityRepository {
	public Integer getNextID() ;
	
	//
	public Project getById(Integer id);
	public Project get(Project sample);
	public Collection<Project> toCollection(); // getAll
	
	//
	public Project add(Project entity);
	public Collection<Project> addAll(Collection<Project> entities);
	public boolean remove(Project entity);
	public boolean removeAll(Collection<Project> entities);	
	
	//
	public int size();
	
}
