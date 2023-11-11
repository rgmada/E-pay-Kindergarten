package org.scrum.domain.entities;

import org.scrum.domain.entities.Team.Specialization;

public class TeamFactory {
	public Team buildTeam(Integer id) {
		Team team = new Team(id, Specialization.BACKEND);
		return team;
	}
}
