package com.tjube.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tjube.model.Action;
import com.tjube.model.Game;
import com.tjube.model.Player;
import com.tjube.model.PlayerStats;
import com.tjube.model.PlayerStatsSituation;
import com.tjube.model.StatsWorldCup;

@Repository
public class PlayerStatsDAOImpl
	implements PlayerStatsDAO
{

	@PersistenceContext(unitName = "JpaPersistenceUnit2")
	private EntityManager m_entityManager = null;

	static class PlayerComparator
		implements Comparator<Player>
	{
		@Override
		public int compare(Player p1, Player p2)
		{
			switch (p1.getPoste())
			{
				case "G":
				{
					if (p2.getPoste().equalsIgnoreCase("G"))
						return 0;
					else
						return -1;
				}
				case "D":
				{
					if (p2.getPoste().equalsIgnoreCase("G"))
						return 1;
					else if (p2.getPoste().equalsIgnoreCase("D"))
						return 0;
					else
						return -1;
				}
				case "M":
				{
					if (p2.getPoste().equalsIgnoreCase("G") || p2.getPoste().equalsIgnoreCase("D"))
						return 1;
					else if (p2.getPoste().equalsIgnoreCase("M"))
						return 0;
					else
						return -1;
				}
				case "A":
				{
					if (p2.getPoste().equalsIgnoreCase("G") || p2.getPoste().equalsIgnoreCase("D")
							|| p2.getPoste().equalsIgnoreCase("M"))
						return 1;
					else if (p2.getPoste().equalsIgnoreCase("A"))
						return 0;
					else
						return -1;
				}
				default:
					return 0;
			}
		}
	}

	@Override
	public PlayerStats addPlayerStats(PlayerStats playerStats)
	{
		m_entityManager.persist(playerStats);

		//				playerStats.getGame().addPlayerStat(playerStats);

		return playerStats;
	}

	@Override
	public List<PlayerStats> getAllPlayerStats()
	{
		TypedQuery<PlayerStats> query = m_entityManager.createNamedQuery(PlayerStats.QN.GET_ALL_STATS,
				PlayerStats.class);
		return query.getResultList();
	}

	@Override
	public List<PlayerStats> getAllGoalsPlayerStats()
	{
		TypedQuery<PlayerStats> query = m_entityManager.createNamedQuery(PlayerStats.QN.GET_GOALS_STATS,
				PlayerStats.class);

		query.setParameter("action", Action.GOAL);

		return query.getResultList();
	}

	@Override
	public void deletePlayerStats(Integer playerStatsId)
	{
		PlayerStats playerStats = getPlayerStats(playerStatsId);

		if (!m_entityManager.contains(playerStats))
			playerStats = m_entityManager.merge(playerStats);

		playerStats.getPlayer().removestat(playerStats);
		playerStats.getGame().removestat(playerStats);

		m_entityManager.remove(playerStats);

		Query query = m_entityManager.createQuery("DELETE FROM PLAYER_STATS WHERE id =: id");
		query.setParameter("id", playerStats.getId());

		query.executeUpdate();

	}

	@Override
	public void deletePlayerStats(Game game)
	{
		Collection<PlayerStats> playerStats = getPlayerStats(game);

		for (PlayerStats stat : playerStats)
		{
			stat.getPlayer().removestat(stat);
			stat.getGame().removestat(stat);

			if (!m_entityManager.contains(stat))
				stat = m_entityManager.merge(stat);
			m_entityManager.remove(stat);
		}

		Query query = m_entityManager.createQuery("DELETE FROM PlayerStats WHERE game =:game");
		query.setParameter("game", game);

		query.executeUpdate();
	}

	@Override
	public void deletePlayerStats(Game game, Collection<Action> actions)
	{
		if (actions == null || actions.isEmpty())
			return;

		Collection<PlayerStats> playerStats = getPlayerStats(game, actions);

		for (PlayerStats stat : playerStats)
		{
			stat.getPlayer().removestat(stat);
			stat.getGame().removestat(stat);

			if (!m_entityManager.contains(stat))
				stat = m_entityManager.merge(stat);
			m_entityManager.remove(stat);
		}

		Query query = m_entityManager.createQuery("DELETE FROM PlayerStats WHERE game =:game AND action in(:actions)");
		query.setParameter("game", game);
		query.setParameter("actions", actions);

		query.executeUpdate();
	}

	@Override
	public Collection<PlayerStats> getPlayerStats(Game game)
	{
		TypedQuery<PlayerStats> query = m_entityManager.createNamedQuery(PlayerStats.QN.GET_STATS_BY_GAME,
				PlayerStats.class);
		query.setParameter("game", game);

		return query.getResultList();
	}

	@Override
	public Collection<PlayerStats> getPlayerStats(Game game, Collection<Action> actions)
	{
		if (actions == null || actions.isEmpty())
			return new ArrayList<>();

		TypedQuery<PlayerStats> query = m_entityManager.createNamedQuery(PlayerStats.QN.GET_STATS_BY_GAME_AND_ACTIONS,
				PlayerStats.class);
		query.setParameter("game", game);
		query.setParameter("actions", actions);

		return query.getResultList();
	}

	@Override
	public PlayerStats getPlayerStats(int playerStatsid)
	{
		TypedQuery<PlayerStats> query = m_entityManager.createNamedQuery(PlayerStats.QN.GET_STATS_BY_ID,
				PlayerStats.class);
		query.setParameter("id", playerStatsid);

		return JPAUtils.getSingleResult(query);
	}

	@Override
	public PlayerStats updatePlayerStats(PlayerStats playerStats)
	{
		return playerStats;
	}

	@Override
	public PlayerStatsSituation retrieveAllStatsPlayer(Player player)
	{
		PlayerStatsSituation result = new PlayerStatsSituation();

		Long minutes = new Long(0);

		TypedQuery<Object[]> typedQuery = m_entityManager
				.createNamedQuery(PlayerStats.QN.RETRIEVE_STATS_BY_PLAYER_BY_GAME, Object[].class);
		typedQuery.setParameter("player", player);
		typedQuery.setParameter("actions", Arrays.asList(Action.TITULAR, Action.CHANGEMENT_IN, Action.CHANGEMENT_OUT));

		Map<Game, Integer> mapGameMinute = new HashMap<>();

		for (Object[] values : typedQuery.getResultList())
		{
			Game game = values[0] != null ? (Game) values[0] : null;
			Action action = values[1] != null ? (Action) values[1] : null;
			Integer minute = values[2] != null ? (Integer) values[2] : null;

			int minuteTotalGame = 0;
			if (game.getProlong())
				minuteTotalGame = 120;
			else
				minuteTotalGame = 90;

			int minuteGame = 0;
			if (mapGameMinute.get(game) != null)
				minuteGame = mapGameMinute.get(game);
			else
				mapGameMinute.put(game, 0);

			if (action != null)
			{
				switch (action)
				{
					case TITULAR:
					{
						minuteGame += minuteTotalGame;
						break;
					}
					case CHANGEMENT_IN:
					{
						minuteGame += (minuteTotalGame - minute);
						break;
					}
					case CHANGEMENT_OUT:
					{
						minuteGame += minute - minuteTotalGame;
						break;
					}
					default:
						break;
				}
			}
			if (minuteGame > 0)
				minutes += minuteGame;

			mapGameMinute.put(game, minuteGame);
		}

		//		minutes = new Long(90) * (Long) query.getSingleResult();
		result.setPlayingMinutes(minutes);

		Query query = m_entityManager.createNamedQuery(PlayerStats.QN.RETRIEVE_STATS_BY_PLAYER);
		query.setParameter("player", player);
		query.setParameter("action", Action.GOAL);
		result.setGoals((Long) query.getSingleResult());

		query = m_entityManager.createNamedQuery(PlayerStats.QN.RETRIEVE_STATS_BY_PLAYER);
		query.setParameter("player", player);
		query.setParameter("action", Action.PASS);
		result.setPasses((Long) query.getSingleResult());

		query = m_entityManager.createNamedQuery(PlayerStats.QN.RETRIEVE_STATS_BY_PLAYER);
		query.setParameter("player", player);
		query.setParameter("action", Action.YELLOW_CARD);
		result.setYellowCard((Long) query.getSingleResult());

		query = m_entityManager.createNamedQuery(PlayerStats.QN.RETRIEVE_STATS_BY_PLAYER);
		query.setParameter("player", player);
		query.setParameter("action", Action.RED_CARD);
		result.setRedCard((Long) query.getSingleResult());

		return result;
	}

	@Override
	public List<StatsWorldCup> retrieveWorldCupStatsGoal(int maxResults)
	{

		TypedQuery<Object[]> query = m_entityManager
				.createNamedQuery(PlayerStats.QN.RETRIEVE_STATS_WORLD_CUP_FOR_ACTION, Object[].class);

		query.setParameter("action", Action.GOAL);
		query.setMaxResults(maxResults);

		List<StatsWorldCup> results = new ArrayList<>();

		for (Object[] values : query.getResultList())
		{
			StatsWorldCup result = new StatsWorldCup();

			Player player = values[0] != null ? (Player) values[0] : null;
			Long goals = values[1] != null ? (Long) values[1] : null;

			result.setAction(Action.GOAL);
			result.setPlayer(player);
			result.setGoals(goals);

			results.add(result);
		}

		return results;
	}

	@Override
	public List<StatsWorldCup> retrieveWorldCupStatsPasses(int maxResults)
	{
		TypedQuery<Object[]> query = m_entityManager
				.createNamedQuery(PlayerStats.QN.RETRIEVE_STATS_WORLD_CUP_FOR_ACTION, Object[].class);

		query.setParameter("action", Action.PASS);
		query.setMaxResults(maxResults);

		List<StatsWorldCup> results = new ArrayList<>();

		for (Object[] values : query.getResultList())
		{
			StatsWorldCup result = new StatsWorldCup();

			Player player = values[0] != null ? (Player) values[0] : null;
			Long passes = values[1] != null ? (Long) values[1] : null;

			result.setAction(Action.PASS);
			result.setPlayer(player);
			result.setPasses(passes);

			results.add(result);
		}

		return results;
	}

	@Override
	public List<StatsWorldCup> retrieveWorldCupStatsYellowCard(int maxResults)
	{
		TypedQuery<Object[]> query = m_entityManager
				.createNamedQuery(PlayerStats.QN.RETRIEVE_STATS_WORLD_CUP_FOR_ACTION, Object[].class);

		query.setParameter("action", Action.YELLOW_CARD);
		query.setMaxResults(maxResults);

		List<StatsWorldCup> results = new ArrayList<>();

		for (Object[] values : query.getResultList())
		{
			StatsWorldCup result = new StatsWorldCup();

			Player player = values[0] != null ? (Player) values[0] : null;
			Long yellowCards = values[1] != null ? (Long) values[1] : null;

			result.setAction(Action.YELLOW_CARD);
			result.setPlayer(player);
			result.setYellowCards(yellowCards);

			results.add(result);
		}

		return results;
	}

	@Override
	public List<StatsWorldCup> retrieveWorldCupStatsRedCard(int maxResults)
	{
		TypedQuery<Object[]> query = m_entityManager
				.createNamedQuery(PlayerStats.QN.RETRIEVE_STATS_WORLD_CUP_FOR_ACTION, Object[].class);

		query.setParameter("action", Action.RED_CARD);
		query.setMaxResults(maxResults);

		List<StatsWorldCup> results = new ArrayList<>();

		for (Object[] values : query.getResultList())
		{
			StatsWorldCup result = new StatsWorldCup();

			Player player = values[0] != null ? (Player) values[0] : null;
			Long redCards = values[1] != null ? (Long) values[1] : null;

			result.setAction(Action.RED_CARD);
			result.setPlayer(player);
			result.setRedCards(redCards);

			results.add(result);
		}

		return results;
	}

	@Override
	public Map<Integer, Collection<Player>> getTitulars(Game game)
	{
		Map<Integer, Collection<Player>> results = new HashMap<>();

		TypedQuery<Player> query = m_entityManager
				.createNamedQuery(PlayerStats.QN.RETRIEVE_STATS_WORLD_CUP_FOR_ACTION_AND_GAME_AND_TEAM, Player.class);

		query.setParameter("action", Action.TITULAR);
		query.setParameter("game", game);
		query.setParameter("team", game.getTeam1());

		List<Player> players = new ArrayList(query.getResultList());
		if (!players.isEmpty())
			Collections.sort(players, new PlayerComparator());

		query = m_entityManager.createNamedQuery(PlayerStats.QN.RETRIEVE_STATS_WORLD_CUP_FOR_ACTION_AND_GAME_AND_TEAM,
				Player.class);

		query.setParameter("action", Action.TITULAR);
		query.setParameter("game", game);
		query.setParameter("team", game.getTeam2());

		List<Player> players2 = new ArrayList(query.getResultList());
		if (!players2.isEmpty())
			Collections.sort(players2, new PlayerComparator());

		results.put(game.getTeam1().getId(), players);
		results.put(game.getTeam2().getId(), players2);

		return results;
	}

	@Override
	public Map<Integer, Collection<Integer>> getTitularsForEditCompo(Game game)
	{
		Map<Integer, Collection<Integer>> results = new HashMap<>();

		TypedQuery<Player> query = m_entityManager
				.createNamedQuery(PlayerStats.QN.RETRIEVE_STATS_WORLD_CUP_FOR_ACTION_AND_GAME_AND_TEAM, Player.class);

		query.setParameter("action", Action.TITULAR);
		query.setParameter("game", game);
		query.setParameter("team", game.getTeam1());

		List<Player> players = new ArrayList(query.getResultList());
		List<Integer> playersId = new ArrayList<>();
		for (Player player : players)
			playersId.add(player.getId());

		query = m_entityManager.createNamedQuery(PlayerStats.QN.RETRIEVE_STATS_WORLD_CUP_FOR_ACTION_AND_GAME_AND_TEAM,
				Player.class);

		query.setParameter("action", Action.TITULAR);
		query.setParameter("game", game);
		query.setParameter("team", game.getTeam2());

		List<Player> players2 = new ArrayList(query.getResultList());
		List<Integer> playersId2 = new ArrayList<>();
		for (Player player : players2)
			playersId2.add(player.getId());

		results.put(game.getTeam1().getId(), playersId);
		results.put(game.getTeam2().getId(), playersId2);

		return results;
	}

	@Override
	public Map<Integer, PlayerStats> getSubstitutes(Game game)
	{
		Map<Integer, PlayerStats> results = new HashMap<>();

		TypedQuery<Object[]> query = m_entityManager
				.createNamedQuery(PlayerStats.QN.RETRIEVE_STATS_WORLD_CUP_FOR_ACTION_AND_GAME, Object[].class);

		query.setParameter("action", Action.CHANGEMENT_OUT);
		query.setParameter("action2", Action.CHANGEMENT_IN);
		query.setParameter("game", game);

		for (Object[] values : query.getResultList())
		{
			Integer playerId = values[0] != null ? (Integer) values[0] : null;
			PlayerStats stats = values[1] != null ? (PlayerStats) values[1] : null;

			results.put(playerId, stats);
		}

		return results;
	}

}
