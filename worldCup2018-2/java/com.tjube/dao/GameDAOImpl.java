package com.tjube.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tjube.model.Game;

public class GameDAOImpl
	implements GameDAO
{

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
		return sessionFactory.getCurrentSession().createQuery("from Game").list();
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

	@Override
	public Game updateGame(Game game)
	{
		sessionFactory.getCurrentSession().update(game);
		return game;
	}

}
