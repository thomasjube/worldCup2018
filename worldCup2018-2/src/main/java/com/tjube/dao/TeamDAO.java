package com.tjube.dao;

import java.util.Collection;
import java.util.List;

import com.tjube.model.Poule;
import com.tjube.model.Team;

public interface TeamDAO
{
	public void addTeam(Team game);

	public List<Team> getAllTeams();

	public void deleteTeam(Integer gameId);

	public Team updateTeam(Team game);

	public Team getTeam(int gameId);

	public Team getTeam(String string);

	public Collection<Team> getOrderTeams(Poule poule);

	public Collection<Team> getTeamsByPoint(Poule poule);

	public Team updateTeamPlayers(Team team);
}
