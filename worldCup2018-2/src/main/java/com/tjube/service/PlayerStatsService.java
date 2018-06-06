package com.tjube.service;

import java.util.List;

import com.tjube.model.PlayerStats;

public interface PlayerStatsService
{
	public void addPlayerStats(PlayerStats playerStats);

	public List<PlayerStats> getAllPlayerStats();

	public void deletePlayerStats(Integer goalId);

	public PlayerStats getPlayerStats(int playerStatsid);

	public PlayerStats updatePlayerStats(PlayerStats playerStats);
	
	public int getTotalPlayerStats();
}
