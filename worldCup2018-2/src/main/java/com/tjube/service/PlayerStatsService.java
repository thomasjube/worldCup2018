package com.tjube.service;

import java.util.List;

import com.tjube.model.Game;
import com.tjube.model.PlayerStats;

public interface PlayerStatsService
{
	public PlayerStats addPlayerStats(PlayerStats playerStats);

	public List<PlayerStats> getAllPlayerStats();

	public void deletePlayerStats(Integer goalId);

	public void deletePlayerStats(Game game);

	public PlayerStats getPlayerStats(int playerStatsid);

	public PlayerStats updatePlayerStats(PlayerStats playerStats);

	public int getTotalPlayerStats();

	public int getAllGoalsPlayerStats();
}
