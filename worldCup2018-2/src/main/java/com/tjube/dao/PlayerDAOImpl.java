package com.tjube.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjube.model.Player;
import com.tjube.model.Team;

@Repository
public class PlayerDAOImpl
	implements PlayerDAO
{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addPlayer(Player game)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(game);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Player> getAllPlayers()
	{
		return sessionFactory.getCurrentSession().createQuery("from Player").list();
	}
	
	@Override
	public void deletePlayer(Integer employeeId)
	{
		Player game = (Player) sessionFactory.getCurrentSession().load(Player.class, employeeId);
		if (null != game)
		{
			this.sessionFactory.getCurrentSession().delete(game);
		}

	}

	@Override
	public Player getPlayer(int empid)
	{
		return (Player) sessionFactory.getCurrentSession().get(Player.class, empid);
	}

	@Override
	public Player updatePlayer(Player game)
	{
		sessionFactory.getCurrentSession().update(game);
		return game;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Player> getGoals(Team team) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findGoals");
		query.setParameter("team", team);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Player> getDefensers(Team team) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findDefensers");
		query.setParameter("team", team);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Player> getMiddles(Team team) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findMiddles");
		query.setParameter("team", team);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Player> getStrikers(Team team) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findStrikers");
		query.setParameter("team", team);
		return query.list();
	}

}
