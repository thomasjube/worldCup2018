package com.tjube.dao;

import java.util.Collection;
import java.util.List;

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

	public PlayerStats updatePlayerStats(PlayerStats playerStats);

	public PlayerStats getPlayerStats(int playerStatsId);

	Collection<PlayerStats> getPlayerStats(Game game);

	public List<PlayerStats> getAllGoalsPlayerStats();

	public PlayerStatsSituation retrieveAllStatsPlayer(Player player);

	// STATISTIQUES

	public List<StatsWorldCup> retrieveWorldCupStatsGoal(int maxResults);

	public List<StatsWorldCup> retrieveWorldCupStatsPasses(int maxResults);

	public List<StatsWorldCup> retrieveWorldCupStatsYellowCard(int maxResults);

	public List<StatsWorldCup> retrieveWorldCupStatsRedCard(int maxResults);
}
