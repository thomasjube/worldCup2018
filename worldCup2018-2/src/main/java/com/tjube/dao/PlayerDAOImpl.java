package com.tjube.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tjube.model.Player;
import com.tjube.model.Team;

@Repository
public class PlayerDAOImpl
	implements PlayerDAO
{

	@PersistenceContext(unitName = "JpaPersistenceUnit")
	private EntityManager m_entityManager = null;

	@Override
	public void addPlayer(Player player)
	{
		m_entityManager.persist(player);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Player> getAllPlayers()
	{
		TypedQuery<Player> query = m_entityManager.createNamedQuery(Player.QN.GET_ALL_PLAYERS, Player.class);
		return query.getResultList();
	}

	@Override
	public void deletePlayer(Integer playerId)
	{
		Player player = getPlayer(playerId);

		if (!m_entityManager.contains(player))
			player = m_entityManager.merge(player);

		m_entityManager.remove(player);

	}

	@Override
	public Player getPlayer(int empid)
	{
		TypedQuery<Player> query = m_entityManager.createNamedQuery(Player.QN.GET_PLAYER_BY_ID, Player.class);
		query.setParameter("id", empid);

		List<Player> results = query.getResultList();
		if (results.size() == 1)
			return results.get(0);

		return null;
	}

	@Override
	public Player updatePlayer(Player player)
	{
		Player playerToUpdate = getPlayer(player.getId());
		playerToUpdate.setFirstName(player.getFirstName());
		playerToUpdate.setName(player.getName());
		playerToUpdate.setNumber(player.getNumber());
		playerToUpdate.setPoste(player.getPoste());
		playerToUpdate.setTeam(player.getTeam());
		playerToUpdate.setPlayerStats(player.getPlayerStats());

		return playerToUpdate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Player> getGoals(Team team)
	{
		TypedQuery<Player> query = m_entityManager.createNamedQuery(Player.QN.findGoals, Player.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Player> getDefensers(Team team)
	{
		TypedQuery<Player> query = m_entityManager.createNamedQuery(Player.QN.findDefensers, Player.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Player> getMiddles(Team team)
	{
		TypedQuery<Player> query = m_entityManager.createNamedQuery(Player.QN.findMiddles, Player.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Player> getStrikers(Team team)
	{
		TypedQuery<Player> query = m_entityManager.createNamedQuery(Player.QN.findStrikers, Player.class);
		return query.getResultList();
	}

}
