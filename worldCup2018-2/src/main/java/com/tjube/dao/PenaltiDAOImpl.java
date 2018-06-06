package com.tjube.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjube.model.Penalti;

@Repository
public class PenaltiDAOImpl
	implements PenaltiDAO
{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addPenalti(Penalti game)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(game);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Penalti> getAllPenaltis()
	{
		return sessionFactory.getCurrentSession().createQuery("from Penalti").list();
	}

	@Override
	public void deletePenalti(Integer employeeId)
	{
		Penalti game = (Penalti) sessionFactory.getCurrentSession().load(Penalti.class, employeeId);
		if (null != game)
		{
			this.sessionFactory.getCurrentSession().delete(game);
		}

	}

	@Override
	public Penalti getPenalti(int empid)
	{
		return (Penalti) sessionFactory.getCurrentSession().get(Penalti.class, empid);
	}

	@Override
	public Penalti updatePenalti(Penalti game)
	{
		sessionFactory.getCurrentSession().update(game);
		return game;
	}

}
