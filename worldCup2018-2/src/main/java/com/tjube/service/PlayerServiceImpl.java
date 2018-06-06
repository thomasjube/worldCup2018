package com.tjube.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjube.dao.PlayerDAO;
import com.tjube.model.Player;
import com.tjube.model.Team;

@Service
@Transactional
public class PlayerServiceImpl
	implements PlayerService
{
	@Autowired
	private PlayerDAO playerDAO;

	@Override
	@Transactional
	public void addPlayer(Player player)
	{
		playerDAO.addPlayer(player);
	}
	
	@Override
	@Transactional
	public List<Player> getAllPlayers()
	{
		return playerDAO.getAllPlayers();
	}
	
	@Override
	@Transactional
	public int getTotalPlayers() {
		return playerDAO.getAllPlayers().size();
	}

	@Override
	@Transactional
	public void deletePlayer(Integer playerId)
	{
		playerDAO.deletePlayer(playerId);
	}

	@Override
	public Player getPlayer(int empid)
	{
		return playerDAO.getPlayer(empid);
	}

	@Override
	public Player updatePlayer(Player player)
	{
		return playerDAO.updatePlayer(player);
	}

	public void setPlayerDAO(PlayerDAO playerDAO)
	{
		this.playerDAO = playerDAO;
	}

	@Override
	public List<Player> getGoals(Team team) {
		return playerDAO.getGoals(team);
	}

	@Override
	public List<Player> getDefensers(Team team) {
		return playerDAO.getDefensers(team);
	}

	@Override
	public List<Player> getMiddles(Team team) {
		return playerDAO.getMiddles(team);
	}

	@Override
	public List<Player> getStrikers(Team team) {
		return playerDAO.getStrikers(team);
	}

}
