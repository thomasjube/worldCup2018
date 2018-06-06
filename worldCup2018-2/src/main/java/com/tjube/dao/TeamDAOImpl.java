package com.tjube.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjube.model.Poule;
import com.tjube.model.Team;

@Repository
public class TeamDAOImpl
	implements TeamDAO
{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addTeam(Team game)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(game);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Team> getAllTeams()
	{
		return sessionFactory.getCurrentSession().createQuery("from Team order by name").list();
	}

	@Override
	public void deleteTeam(Integer employeeId)
	{
		Team game = (Team) sessionFactory.getCurrentSession().load(Team.class, employeeId);
		if (null != game)
		{
			this.sessionFactory.getCurrentSession().delete(game);
		}

	}

	@Override
	public Team getTeam(int empid)
	{
		return (Team) sessionFactory.getCurrentSession().get(Team.class, empid);
	}
	
	@Override
	public Team getTeam(String string) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findTeamByName");
		query.setParameter("name", string);
		
		return (Team) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Team> getOrderTeams(Poule poule) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findTeamsByPoule");
		query.setParameter("poule", poule);
		return query.list();
	}

	@Override
	public Team updateTeam(Team game)
	{
		sessionFactory.getCurrentSession().update(game);
		return game;
	}

	@Override
	public Collection<Team> getTeamsByPoint(Poule poule) {
		return sessionFactory.getCurrentSession().createQuery("from Team t where t.poule = :poule order by t.point DESC, t.diff DESC, t.but_mis DESC, t.but_pris ASC, t.id").setParameter("poule", poule).list();
	}

}
