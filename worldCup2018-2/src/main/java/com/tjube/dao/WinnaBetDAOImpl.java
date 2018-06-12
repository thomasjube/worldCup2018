package com.tjube.dao;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

import com.tjube.model.BetClassementLine;
import com.tjube.model.BetName;
import com.tjube.model.Game;
import com.tjube.model.WinnaBet;

@Repository
public class WinnaBetDAOImpl
	implements WinnaBetDAO
{
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	static class BetClassementLineComparator
		implements Comparator<BetClassementLine>
	{
		@Override
		public int compare(BetClassementLine c1, BetClassementLine c2)
		{
			if (c2.getPoints().compareTo(c1.getPoints()) == 0)
			{
				if (c2.getNbScore().compareTo(c1.getNbScore()) == 0)
					return c2.getNbResults().compareTo(c1.getNbResults());
				else
					return c2.getNbScore().compareTo(c1.getNbScore());
			}
			else
				return c2.getPoints().compareTo(c1.getPoints());
		}
	}

	@PersistenceContext(unitName = "JpaPersistenceUnit2")
	private EntityManager m_entityManager = null;

	@Override
	public void add(WinnaBet winnaBet)
	{
		m_entityManager.persist(winnaBet);
	}

	@Override
	public List<WinnaBet> getAllWinnaBets()
	{
		TypedQuery<WinnaBet> query = m_entityManager.createNamedQuery(WinnaBet.QN.GET_ALL_WINNABET, WinnaBet.class);
		return query.getResultList();
	}

	@Override
	public void deleteWinnaBet(Integer id)
	{
		WinnaBet winnaBet = getWinnaBet(id);

		if (!m_entityManager.contains(winnaBet))
			winnaBet = m_entityManager.merge(winnaBet);

		m_entityManager.remove(winnaBet);
	}

	@Override
	public void deleteWinnaBets(Game game)
	{
		List<WinnaBet> result = getWinnaBets(game);

		for (WinnaBet winnaBet : result)
		{
			if (!m_entityManager.contains(winnaBet))
				winnaBet = m_entityManager.merge(winnaBet);

			winnaBet.getGame().removeWinabet(winnaBet);

			m_entityManager.remove(winnaBet);
		}

		Query query = m_entityManager.createQuery("DELETE FROM WinnaBet WHERE game =:game");
		query.setParameter("game", game);

		query.executeUpdate();
	}

	@Override
	public WinnaBet getWinnaBet(int id)
	{
		TypedQuery<WinnaBet> query = m_entityManager.createNamedQuery(WinnaBet.QN.GET_WINNABET_BY_ID, WinnaBet.class);
		query.setParameter("id", id);

		return JPAUtils.getSingleResult(query);
	}

	@Override
	public WinnaBet updateWinnaBet(WinnaBet winnaBet)
	{
		WinnaBet winnaBetToUpdate = getWinnaBet(winnaBet.getId());

		winnaBetToUpdate.setGame(winnaBet.getGame());
		winnaBetToUpdate.setGoodResult(winnaBet.getGoodResult());
		winnaBetToUpdate.setGoodScore(winnaBet.getGoodScore());
		winnaBetToUpdate.setName(winnaBet.getName());
		winnaBetToUpdate.setScore1(winnaBet.getScore1());
		winnaBetToUpdate.setScore2(winnaBet.getScore2());

		return winnaBetToUpdate;
	}

	@Override
	public List<WinnaBet> getWinnaBets(Game game)
	{
		TypedQuery<WinnaBet> query = m_entityManager.createNamedQuery(WinnaBet.QN.getWinnaBetByGame, WinnaBet.class);
		query.setParameter("game", game);

		return query.getResultList();
	}

	@Override
	public Collection<WinnaBet> getWinnaBets(BetName betName)
	{
		TypedQuery<WinnaBet> query = m_entityManager.createNamedQuery(WinnaBet.QN.getWinnaBetByName, WinnaBet.class);
		query.setParameter("name", betName);

		return query.getResultList();
	}

	@Override
	public WinnaBet updateWinnaBet(WinnaBet winnaBet, Integer score1, Integer score2)
	{
		winnaBet.setScore1(score1);
		winnaBet.setScore2(score2);
		return winnaBet;
	}

	@Override
	public void resetWinnaBet(WinnaBet winnaBet)
	{
		if (!m_entityManager.contains(winnaBet))
			winnaBet = m_entityManager.merge(winnaBet);

		winnaBet.setGoodResult(false);
		winnaBet.setGoodScore(false);
	}

	@Override
	public void verifyBets(Game game)
	{
		Collection<WinnaBet> winnaBets = getWinnaBets(game);
		for (WinnaBet winnaBet : winnaBets)
		{
			if (winnaBet.getScore1() == game.getScore1() && winnaBet.getScore2() == game.getScore2())
			{
				winnaBet.setGoodScore(true);
				winnaBet.setGoodResult(false);
			}
			else if ((winnaBet.getScore1() > winnaBet.getScore2() && game.getScore1() > game.getScore2())
					|| (winnaBet.getScore1() < winnaBet.getScore2() && game.getScore1() < game.getScore2())
					|| (winnaBet.getScore1() == winnaBet.getScore2() && game.getScore1() == game.getScore2()))
			{
				winnaBet.setGoodResult(true);
				winnaBet.setGoodScore(false);
			}
		}
	}

	@Override
	public List<BetClassementLine> getBetClassement()
	{
		List<BetClassementLine> results = new ArrayList<>();

		Map<BetName, Long> playerScore = new HashMap<>();
		Map<BetName, Long> playerResult = new HashMap<>();

		TypedQuery<Object[]> query = m_entityManager.createNamedQuery(WinnaBet.QN.GET_NB_SCORE, Object[].class);

		for (Object[] values : query.getResultList())
		{
			BetName name = values[0] != null ? (BetName) values[0] : null;
			Long nbScore = values[1] != null ? (Long) values[1] : null;

			playerScore.put(name, nbScore);
		}

		query = m_entityManager.createNamedQuery(WinnaBet.QN.GET_NB_RESULT, Object[].class);

		for (Object[] values : query.getResultList())
		{
			BetName name = values[0] != null ? (BetName) values[0] : null;
			Long nbResult = values[1] != null ? (Long) values[1] : null;

			playerResult.put(name, nbResult);
		}

		for (BetName name : BetName.values())
		{
			Long score = playerScore.get(name) != null ? playerScore.get(name) : new Long(0);
			Long result = playerResult.get(name) != null ? playerResult.get(name) : new Long(0);
			results.add(new BetClassementLine(name, score, result));
		}

		Collections.sort(results, new BetClassementLineComparator());

		return results;
	}

}
