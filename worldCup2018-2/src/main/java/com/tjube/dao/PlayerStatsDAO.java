package com.tjube.dao;

import java.util.List;

import com.tjube.model.PlayerStats;

public interface PlayerStatsDAO
{
	public void addPlayerStats(PlayerStats playerStats);

	public List<PlayerStats> getAllPlayerStats();

	public void deletePlayerStats(Integer playerStatsId);

	public PlayerStats updatePlayerStats(PlayerStats playerStats);

	public PlayerStats getPlayerStats(int playerStatsId);
}
