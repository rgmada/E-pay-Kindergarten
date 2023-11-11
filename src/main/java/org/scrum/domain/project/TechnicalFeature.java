package org.scrum.domain.project;

import javax.persistence.Entity;

@Entity
public class TechnicalFeature extends Feature {
	
	private String architecturalLevel; // prezentare (UI), model si/sau logica afacerii, persistenta, baza de date
	private String technicalAbilitiesRequired; // LP: Java, C#, JavaScript, BD: SQL, MongoDB, FMks: EJB, JPA, JSF, Angular, JQuery
	private String architecturalFlowDescription; // colaborare inter-niveluri: UI - catre - Fmk.Logica/Model - catre - Sistem BD
	
	
	public String getArchitecturalLevel() {
		return architecturalLevel;
	}

	public void setArchitecturalLevel(String architecturalLevel) {
		this.architecturalLevel = architecturalLevel;
	}

	public String getTechnicalAbilitiesRequired() {
		return technicalAbilitiesRequired;
	}

	public void setTechnicalAbilitiesRequired(String technicalAbilitiesRequired) {
		this.technicalAbilitiesRequired = technicalAbilitiesRequired;
	}

	public String getArchitecturalFlowDescription() {
		return architecturalFlowDescription;
	}

	public void setArchitecturalFlowDescription(String architecturalFlowDescription) {
		this.architecturalFlowDescription = architecturalFlowDescription;
	}

	@Override
	public void setCategory(FeatureCategory category) {
		throw new Error("Proprietatea categorie nu poate fi schimbata!");
	}
	
	public TechnicalFeature() {
		super();
		this.category = FeatureCategory.TECHNICAL;
	}

	public TechnicalFeature(Integer featureID, String name, String description,
			String architecturalLevel, String technicalAbilitiesRequired,
			String architecturalFlowDescription) {
		super(featureID, name, description);
		this.architecturalLevel = architecturalLevel;
		this.technicalAbilitiesRequired = technicalAbilitiesRequired;
		this.architecturalFlowDescription = architecturalFlowDescription;
		this.category = FeatureCategory.TECHNICAL;
	}

	public TechnicalFeature(Integer featureID, String name, String description) {
		super(featureID, name, description);
	}
}
