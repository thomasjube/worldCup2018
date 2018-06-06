package com.tjube.dao;

import java.util.Collection;
import java.util.List;

import com.tjube.model.Player;
import com.tjube.model.Team;

public interface PlayerDAO
{
	public void addPlayer(Player game);

	public List<Player> getAllPlayers();

	public void deletePlayer(Integer gameId);

	public Player updatePlayer(Player game);

	public Player getPlayer(int gameId);

	public List<Player> getGoals(Team team);

	public List<Player> getDefensers(Team team);

	public List<Player> getMiddles(Team team);

	public List<Player> getStrikers(Team team);

}
