package com.tjube.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tjube.model.PlayerStats;

@Repository
public class PlayerStatsDAOImpl
	implements PlayerStatsDAO
{

	@PersistenceContext(unitName = "JpaPersistenceUnit")
	private EntityManager m_entityManager = null;

	@Override
	public void addPlayerStats(PlayerStats playerStats)
	{
		m_entityManager.persist(playerStats);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PlayerStats> getAllPlayerStats()
	{
		TypedQuery<PlayerStats> query = m_entityManager.createNamedQuery(PlayerStats.QN.GET_ALL_STATS,
				PlayerStats.class);
		return query.getResultList();
	}

	@Override
	public void deletePlayerStats(Integer playerStatsId)
	{
		PlayerStats playerStats = getPlayerStats(playerStatsId);

		if (!m_entityManager.contains(playerStats))
			playerStats = m_entityManager.merge(playerStats);

		m_entityManager.remove(playerStats);

	}

	@Override
	public PlayerStats getPlayerStats(int playerStatsid)
	{
		TypedQuery<PlayerStats> query = m_entityManager.createNamedQuery(PlayerStats.QN.GET_STATS_BY_ID,
				PlayerStats.class);
		query.setParameter("id", playerStatsid);

		List<PlayerStats> results = query.getResultList();
		if (results.size() == 1)
			return results.get(0);

		return null;
	}

	@Override
	public PlayerStats updatePlayerStats(PlayerStats playerStats)
	{
		return playerStats;
	}

}
