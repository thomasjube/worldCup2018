package com.tjube.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tjube.model.Poule;

@Repository
public class PouleDAOImpl
	implements PouleDAO
{

	@PersistenceContext(unitName = "JpaPersistenceUnit")
	private EntityManager m_entityManager = null;

	@Override
	public void addPoule(Poule poule)
	{
		m_entityManager.persist(poule);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Poule> getAllPoules()
	{
		TypedQuery<Poule> query = m_entityManager.createNamedQuery(Poule.QN.GET_ALL_POULES, Poule.class);
		return query.getResultList();
	}

	@Override
	public void deletePoule(Integer pouleId)
	{
		Poule poule = getPoule(pouleId);

		if (!m_entityManager.contains(poule))
			poule = m_entityManager.merge(poule);

		m_entityManager.remove(poule);

	}

	@Override
	public Poule getPoule(int empid)
	{
		TypedQuery<Poule> query = m_entityManager.createNamedQuery(Poule.QN.GET_POULE_BY_ID, Poule.class);
		query.setParameter("id", empid);

		List<Poule> results = query.getResultList();
		if (results.size() == 1)
			return results.get(0);

		return null;
	}

	@Override
	public Poule getLastPoule(Poule poule)
	{
		TypedQuery<Poule> query = m_entityManager.createNamedQuery(Poule.QN.findLastPoule, Poule.class);

		query.setParameter("pouleId", poule.getId());
		query.getFirstResult();
		return query.setMaxResults(1).getResultList().isEmpty() ? null
				: (Poule) query.setMaxResults(1).getResultList().get(0);
	}

	@Override
	public Poule getNextPoule(Poule poule)
	{
		TypedQuery<Poule> query = m_entityManager.createNamedQuery(Poule.QN.findNextPoule, Poule.class);

		query.setParameter("pouleId", poule.getId());
		query.getFirstResult();
		return query.setMaxResults(1).getResultList().isEmpty() ? null
				: (Poule) query.setMaxResults(1).getResultList().get(0);
	}

	@Override
	public Poule updatePoule(Poule poule)
	{
		Poule pouleToUpdate = getPoule(poule.getId());
		pouleToUpdate.setName(poule.getName());
		pouleToUpdate.setTeams(poule.getTeams());

		return pouleToUpdate;
	}

}
