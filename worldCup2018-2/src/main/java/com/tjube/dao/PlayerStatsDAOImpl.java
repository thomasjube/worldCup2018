package com.tjube.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjube.model.PlayerStats;

@Repository
public class PlayerStatsDAOImpl
	implements PlayerStatsDAO
{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addPlayerStats(PlayerStats playerStats)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(playerStats);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PlayerStats> getAllPlayerStats()
	{
		return sessionFactory.getCurrentSession().createQuery("from PlayerStats").list();
	}

	@Override
	public void deletePlayerStats(Integer playerStatsId)
	{
		PlayerStats playerStats = (PlayerStats) sessionFactory.getCurrentSession().load(PlayerStats.class, playerStatsId);
		if (null != playerStats)
		{
			this.sessionFactory.getCurrentSession().delete(playerStats);
		}

	}

	@Override
	public PlayerStats getPlayerStats(int playerStatsid)
	{
		return (PlayerStats) sessionFactory.getCurrentSession().get(PlayerStats.class, playerStatsid);
	}

	@Override
	public PlayerStats updatePlayerStats(PlayerStats playerStats)
	{
		sessionFactory.getCurrentSession().update(playerStats);
		return playerStats;
	}

}
