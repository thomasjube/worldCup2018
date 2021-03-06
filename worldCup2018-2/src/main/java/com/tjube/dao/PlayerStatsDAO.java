package com.tjube.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.tjube.model.Action;
import com.tjube.model.Game;
import com.tjube.model.Player;
import com.tjube.model.PlayerStats;
import com.tjube.model.PlayerStatsSituation;
import com.tjube.model.StatsWorldCup;

public interface PlayerStatsDAO
{
	public PlayerStats addPlayerStats(PlayerStats playerStats);

	public List<PlayerStats> getAllPlayerStats();

	public void deletePlayerStats(Integer playerStatsId);

	public void deletePlayerStats(Game game);

	public void deletePlayerStats(Game game, Collection<Action> actions);

	public PlayerStats updatePlayerStats(PlayerStats playerStats);

	public PlayerStats getPlayerStats(int playerStatsId);

	Collection<PlayerStats> getPlayerStats(Game game);

	Collection<PlayerStats> getPlayerStats(Game game, Collection<Action> actions);

	public List<PlayerStats> getAllGoalsPlayerStats();

	public PlayerStatsSituation retrieveAllStatsPlayer(Player player);

	// STATISTIQUES

	public List<StatsWorldCup> retrieveWorldCupStatsGoal(int maxResults);

	public List<StatsWorldCup> retrieveWorldCupStatsPasses(int maxResults);

	public List<StatsWorldCup> retrieveWorldCupStatsYellowCard(int maxResults);

	public List<StatsWorldCup> retrieveWorldCupStatsRedCard(int maxResults);

	public Map<Integer, Collection<Player>> getTitulars(Game game);

	public Map<Integer, Collection<Integer>> getTitularsForEditCompo(Game game);

	public Map<Integer, PlayerStats> getSubstitutes(Game game);
}
