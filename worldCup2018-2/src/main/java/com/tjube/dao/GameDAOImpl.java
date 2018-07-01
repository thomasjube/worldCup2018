package com.tjube.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tjube.model.Game;
import com.tjube.model.Player;
import com.tjube.model.Poule;
import com.tjube.model.Team;

@Repository
public class GameDAOImpl
	implements GameDAO
{
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@PersistenceContext(unitName = "JpaPersistenceUnit2")
	private EntityManager m_entityManager = null;

	@Override
	public void addGame(Game game)
	{
		m_entityManager.persist(game);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Game> getAllGames()
	{
		TypedQuery<Game> query = m_entityManager.createNamedQuery(Game.QN.GET_ALL_GAMES, Game.class);
		return query.getResultList();
	}

	@Override
	public void deleteGame(Integer gameId)
	{
		Game game = getGame(gameId);

		if (!m_entityManager.contains(game))
			game = m_entityManager.merge(game);

		m_entityManager.remove(game);

	}

	@Override
	public Game getGame(int empid)
	{
		TypedQuery<Game> query = m_entityManager.createNamedQuery(Game.QN.GET_GAME_BY_ID, Game.class);
		query.setParameter("id", empid);

		return JPAUtils.getSingleResult(query);
	}

	@Override
	public List<Game> getGames(Team team)
	{
		TypedQuery<Game> query = m_entityManager.createNamedQuery(Game.QN.GET_ALL_GAMES_BY_TEAM, Game.class);
		query.setParameter("team", team);

		return query.getResultList();
	}

	@Override
	public List<Game> getGames(Poule poule)
	{

		TypedQuery<Game> query = m_entityManager.createNamedQuery(Game.QN.GET_ALL_GAMES_BY_POULE, Game.class);
		query.setParameter("poule", poule);

		return query.getResultList();
	}

	@Override
	public List<Game> getGames(LocalDateTime date)
	{

		TypedQuery<Game> query = m_entityManager.createNamedQuery(Game.QN.GET_ALL_GAMES_BEFORE_DATE, Game.class);
		query.setParameter("date", date);

		return query.getResultList();
	}

	@Override
	public Game getLastGame()
	{
		LocalDateTime today = LocalDateTime.now();
		TypedQuery<Game> query = m_entityManager.createNamedQuery(Game.QN.findLastGameByDate, Game.class);

		query.setParameter("date", today);
		query.setMaxResults(1);

		return JPAUtils.getSingleResult(query);
	}

	@Override
	public Game getNextGame()
	{
		LocalDateTime today = LocalDateTime.now();
		TypedQuery<Game> query = m_entityManager.createNamedQuery(Game.QN.findNextGameByDate, Game.class);
		query.setParameter("date", today);
		query.setMaxResults(1);

		return JPAUtils.getSingleResult(query);
	}

	@Override
	public Game getNextGame(Poule poule)
	{
		LocalDateTime today = LocalDateTime.now();
		TypedQuery<Game> query = m_entityManager.createNamedQuery(Game.QN.findNextGameByDateAndPoule, Game.class);
		query.setParameter("date", today);
		query.setParameter("poule", poule);
		query.setMaxResults(1);

		return JPAUtils.getSingleResult(query);
	}

	@Override
	public Game updateGame(Game game)
	{
		Game gameToUpdate = getGame(game.getId());
		//		gameToUpdate.setDateTime(game.getDateTime());
		gameToUpdate.setGameInPoule(game.getGameInPoule());
		gameToUpdate.setName(game.getName());
		gameToUpdate.setPenalti(game.getPenalti());
		gameToUpdate.setPlayerStats(game.getPlayerStats());
		//		gameToUpdate.setPoule(game.getPoule());
		gameToUpdate.setProlong(game.getProlong());
		gameToUpdate.setScore1(game.getScore1());
		gameToUpdate.setScore1_penalti(game.getScore1_penalti());
		gameToUpdate.setScore1_prolong(game.getScore1_prolong());
		gameToUpdate.setScore2(game.getScore2());
		gameToUpdate.setScore2_penalti(game.getScore2_penalti());
		gameToUpdate.setScore2_prolong(game.getScore2_prolong());
		gameToUpdate.setStade(game.getStade());
		//		gameToUpdate.setTeam1(game.getTeam1());
		//		gameToUpdate.setTeam2(game.getTeam2());

		return gameToUpdate;
	}

	@Override
	public void updateGame(Game game, Player captainTeam1, Player captainTeam2)
	{
		Game gameToUpdate = getGame(game.getId());
		gameToUpdate.setCaptainTeam1(captainTeam1);
		gameToUpdate.setCaptainTeam2(captainTeam2);
	}

	@Override
	public List<Game> getFinalPhaseGames() {
		TypedQuery<Game> query = m_entityManager.createNamedQuery(Game.QN.GET_ALL_FINAL_PHASE_GAMES, Game.class);

		return query.getResultList();
	}

}
