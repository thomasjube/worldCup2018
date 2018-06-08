package com.tjube.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.tjube.model.Game;
import com.tjube.model.PlayerStatsSituation;
import com.tjube.model.Poule;
import com.tjube.model.Team;

public interface TeamService
{
	public void addTeam(Team team);

	public List<Team> getAllTeams();

	public void deleteTeam(Integer teamId);

	public Team getTeam(int teamid);

	public Team updateTeam(Team team);

	public Team updateTeamPlayers(Team team);

	public int getTotalTeams();

	public Team getTeam(String string);

	public Collection<Team> getOrderTeams(Poule poule);

	public void updateTeams(Game game);

	public void updateTeamsPositions(Poule poule);

	public Collection<Team> getTeamsByPoint(Poule poule);

	void updateTeamsForReset(Game game);

	public Map<Integer, PlayerStatsSituation> retrieveAllStatsTeam(Team team);

}
