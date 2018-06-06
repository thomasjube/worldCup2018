package com.tjube.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjube.model.Game;
import com.tjube.model.Poule;

@Repository
public class PouleDAOImpl
	implements PouleDAO
{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addPoule(Poule poule)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(poule);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Poule> getAllPoules()
	{
		return sessionFactory.getCurrentSession().createQuery("from Poule").list();
	}

	@Override
	public void deletePoule(Integer pouleId)
	{
		Poule poule = (Poule) sessionFactory.getCurrentSession().load(Poule.class, pouleId);
		if (null != poule)
		{
			this.sessionFactory.getCurrentSession().delete(poule);
		}

	}

	@Override
	public Poule getPoule(int empid)
	{
		return (Poule) sessionFactory.getCurrentSession().get(Poule.class, empid);
	}
	
	@Override
	public Poule getLastPoule(Poule poule) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findLastPoule");
		
		query.setParameter("pouleId", poule.getId());
		query.getFirstResult();
		return query.setMaxResults(1).list().isEmpty() ? null : (Poule)query.setMaxResults(1).list().get(0);
	}
	
	@Override
	public Poule getNextPoule(Poule poule) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("findNextPoule");
		
		query.setParameter("pouleId", poule.getId());
		query.getFirstResult();
		return query.setMaxResults(1).list().isEmpty() ? null : (Poule)query.setMaxResults(1).list().get(0);
	}

	@Override
	public Poule updatePoule(Poule poule)
	{
		sessionFactory.getCurrentSession().update(poule);
		return poule;
	}

}
