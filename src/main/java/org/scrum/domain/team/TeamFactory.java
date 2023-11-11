package org.scrum.domain.team;

import org.scrum.domain.team.Team.Specialization;

public class TeamFactory {
	public Team buildTeam(Integer id) {
		Team team = new Team(id, Specialization.BACKEND);
		return team;
	}
}
