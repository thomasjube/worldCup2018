package com.tjube.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjube.dao.PlayerStatsDAO;
import com.tjube.model.Action;
import com.tjube.model.Game;
import com.tjube.model.PlayerStats;

@Service
@Transactional
public class PlayerStatsServiceImpl
	implements PlayerStatsService
{
	@Autowired
	private PlayerStatsDAO playerStatsDAO;

	@Override
	@Transactional
	public PlayerStats addPlayerStats(PlayerStats PlayerStats)
	{
		return playerStatsDAO.addPlayerStats(PlayerStats);
	}

	@Override
	@Transactional
	public List<PlayerStats> getAllPlayerStats()
	{
		return playerStatsDAO.getAllPlayerStats();
	}

	@Override
	@Transactional
	public int getAllGoalsPlayerStats()
	{
		return playerStatsDAO.getAllGoalsPlayerStats().size();
	}

	@Override
	@Transactional
	public int getTotalPlayerStats()
	{
		return playerStatsDAO.getAllPlayerStats().size();
	}

	@Override
	@Transactional
	public void deletePlayerStats(Integer playerStatsId)
	{
		playerStatsDAO.deletePlayerStats(playerStatsId);
	}

	@Override
	@Transactional
	public void deletePlayerStats(Game game)
	{
		playerStatsDAO.deletePlayerStats(game);
	}

	@Override
	public void deletePlayerStats(Game game, Collection<Action> actions)
	{
		playerStatsDAO.deletePlayerStats(game, actions);
	}

	@Override
	public PlayerStats getPlayerStats(int empid)
	{
		return playerStatsDAO.getPlayerStats(empid);
	}

	@Override
	public PlayerStats updatePlayerStats(PlayerStats playerStats)
	{
		return playerStatsDAO.updatePlayerStats(playerStats);
	}

	public void setPlayerStatsDAO(PlayerStatsDAO playerStatsDAO)
	{
		this.playerStatsDAO = playerStatsDAO;
	}

}
