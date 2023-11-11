package org.scrum.domain.project;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class BusinessFeature extends Feature implements Serializable{

	private String functionalCategory; // basic, improvement
	private String useCaseDescription; // scenariu-flux dialog utilizator final - aplicatie
	public String getFunctionalCategory() {
		return functionalCategory;
	}
	public void setFunctionalCategory(String functionalCategory) {
		this.functionalCategory = functionalCategory;
	}
	public String getUseCaseDescription() {
		return useCaseDescription;
	}
	public void setUseCaseDescription(String useCaseDescription) {
		this.useCaseDescription = useCaseDescription;
	}
	public BusinessFeature(String functionalCategory, String useCaseDescription) {
		super();
		this.functionalCategory = functionalCategory;
		this.useCaseDescription = useCaseDescription;
	}
	public BusinessFeature() {
		super();
	}
	@Override
	public String toString() {
		return "BusinessFeature [functionalCategory=" + functionalCategory
				+ ", useCaseDescription=" + useCaseDescription + "]";
	}
}
