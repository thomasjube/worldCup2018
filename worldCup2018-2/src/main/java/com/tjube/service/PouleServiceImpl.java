package com.tjube.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjube.dao.PouleDAO;
import com.tjube.model.Poule;

@Service
@Transactional
public class PouleServiceImpl
	implements PouleService
{
	@Autowired
	private PouleDAO pouleDAO;

	@Override
	@Transactional
	public void addPoule(Poule poule)
	{
		pouleDAO.addPoule(poule);
	}

	@Override
	@Transactional
	public List<Poule> getAllPoules()
	{
		return pouleDAO.getAllPoules();
	}

	@Override
	@Transactional
	public void deletePoule(Integer pouleId)
	{
		pouleDAO.deletePoule(pouleId);
	}

	@Override
	public Poule getPoule(int empid)
	{
		return pouleDAO.getPoule(empid);
	}
	
	@Override
	public Poule getNextPoule(Poule poule)
	{
		return pouleDAO.getNextPoule(poule);
	}
	
	@Override
	public Poule getLastPoule(Poule poule)
	{
		return pouleDAO.getLastPoule(poule);
	}

	@Override
	public Poule updatePoule(Poule poule)
	{
		return pouleDAO.updatePoule(poule);
	}

	public void setPouleDAO(PouleDAO pouleDAO)
	{
		this.pouleDAO = pouleDAO;
	}

}
