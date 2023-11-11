package org.scrum.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Team implements Serializable{
	@Id
	private Integer teamID;
	
	private Specialization specialization;
	private String abilities;
	
	@OneToMany
	private List<Member> members = new ArrayList<Member>();
	
	@OneToOne
	private TeamLeader teamLeader;
	
	// properties from bean accessors
	public Team(Integer idEchipa, Specialization specializare, String competente) {
		super();
		this.teamID = idEchipa;
		this.specialization = specializare;
		this.abilities = competente;
	}
	public Integer getTeamID() {
		return teamID;
	}
	public void setTeamID(Integer teamID) {
		this.teamID = teamID;
	}
	public Specialization getSpecialization() {
		return specialization;
	}
	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}
	public String getAbilities() {
		return abilities;
	}
	public void setAbilities(String abilities) {
		this.abilities = abilities;
	}
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	public TeamLeader getTeamLeader() {
		return teamLeader;
	}
	public void setTeamLeader(TeamLeader teamLeader) {
		this.teamLeader = teamLeader;
	}
	public Team() {
		super();
	}
	public Team(Integer teamID, Specialization specialization) {
		super();
		this.teamID = teamID;
		this.specialization = specialization;
	}
	// polimorfism parametrizare
	public void addMember(Member member){
		this.members.add(member);
	}
	
	public enum Specialization {
		BACKEND, FRONTEND, DATABASE;
	}	
}

