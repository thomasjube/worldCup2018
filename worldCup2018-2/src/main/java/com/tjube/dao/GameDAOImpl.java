package com.tjube.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjube.model.Game;
import com.tjube.model.Poule;
import com.tjube.model.Team;

@Repository
public class GameDAOImpl
	implements GameDAO
{

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addGame(Game game)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(game);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Game> getAllGames()
	{
		return sessionFactory.getCurrentSession().createQuery("from Game order by dateTime asc,name asc").list();
	}

	@Override
	public void deleteGame(Integer employeeId)
	{
		Game game = (Game) sessionFactory.getCurrentSession().load(Game.class, employeeId);
		if (null != game)
		{
			this.sessionFactory.getCurrentSession().delete(game);
		}

	}

	@Override
	public Game getGame(int empid)
	{
		return (Game) sessionFactory.getCurrentSession().get(Game.class, empid);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Game> getGames(Team team) {
		
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findGamesByTeam");
		query.setParameter("team", team);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Game> getGames(Poule poule) {
		
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findGamesByPoule");
		query.setParameter("poule", poule);
		
		return query.list();
	}
	
	@Override
	public Game getLastGame()
	{
		LocalDateTime today = LocalDateTime.now();
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findLastGameByDate");
		
		query.setParameter("date", today);
		query.getFirstResult();
		return query.setMaxResults(1).list().isEmpty() ? null : (Game)query.setMaxResults(1).list().get(0);
	}
	
	@Override
	public Game getNextGame()
	{
		LocalDateTime today = LocalDateTime.now();
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findNextGameByDate");
		
		query.setParameter("date", today);
		query.getFirstResult();
		return query.setMaxResults(1).list().isEmpty() ? null : (Game)query.setMaxResults(1).list().get(0);
	}
	
	@Override
	public Game getNextGame(Poule poule)
	{
		LocalDateTime today = LocalDateTime.now();
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findNextGameByDateAndPoule");
		
		query.setParameter("date", today);
		query.setParameter("poule", poule);
		query.getFirstResult();
		return query.setMaxResults(1).list().isEmpty() ? null : (Game)query.setMaxResults(1).list().get(0);
	}

	@Override
	public Game updateGame(Game game)
	{
		sessionFactory.getCurrentSession().update(game);
		return game;
	}

}
