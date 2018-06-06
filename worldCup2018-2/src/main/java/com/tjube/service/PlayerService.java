package com.tjube.service;

import java.util.Collection;
import java.util.List;

import com.tjube.model.Player;
import com.tjube.model.Team;

public interface PlayerService
{
	public void addPlayer(Player player);

	public List<Player> getAllPlayers();
	
	public List<Player> getGoals(Team team);

	public List<Player> getDefensers(Team team);

	public List<Player> getMiddles(Team team);
	
	public List<Player> getStrikers(Team team);


	public void deletePlayer(Integer playerId);

	public Player getPlayer(int playerid);

	public Player updatePlayer(Player player);
	
	public int getTotalPlayers();

}
