package com.tjube.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tjube.model.Penalti;

@Repository
public class PenaltiDAOImpl
	implements PenaltiDAO
{

	@PersistenceContext(unitName = "JpaPersistenceUnit2")
	private EntityManager m_entityManager = null;

	@Override
	public void addPenalti(Penalti penalti)
	{
		m_entityManager.persist(penalti);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Penalti> getAllPenaltis()
	{
		TypedQuery<Penalti> query = m_entityManager.createNamedQuery(Penalti.QN.GET_ALL_PENALTIS, Penalti.class);
		return query.getResultList();
	}

	@Override
	public void deletePenalti(Integer employeeId)
	{
		Penalti game = getPenalti(employeeId);

		if (!m_entityManager.contains(game))
			game = m_entityManager.merge(game);

		m_entityManager.remove(game);

	}

	@Override
	public Penalti getPenalti(int empid)
	{
		TypedQuery<Penalti> query = m_entityManager.createNamedQuery(Penalti.QN.GET_PENALTI_BY_ID, Penalti.class);
		query.setParameter("id", empid);

		return JPAUtils.getSingleResult(query);
	}

	@Override
	public Penalti updatePenalti(Penalti game)
	{
		return game;
	}

}
