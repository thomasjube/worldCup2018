package com.tjube.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjube.dao.PenaltiDAO;
import com.tjube.model.Penalti;

@Service
@Transactional
public class PenaltiServiceImpl
	implements PenaltiService
{
	@Autowired
	private PenaltiDAO penaltiDAO;

	@Override
	@Transactional
	public void addPenalti(Penalti penalti)
	{
		penaltiDAO.addPenalti(penalti);
	}

	@Override
	@Transactional
	public List<Penalti> getAllPenaltis()
	{
		return penaltiDAO.getAllPenaltis();
	}

	@Override
	@Transactional
	public void deletePenalti(Integer penaltiId)
	{
		penaltiDAO.deletePenalti(penaltiId);
	}

	@Override
	public Penalti getPenalti(int empid)
	{
		return penaltiDAO.getPenalti(empid);
	}

	@Override
	public Penalti updatePenalti(Penalti penalti)
	{
		return penaltiDAO.updatePenalti(penalti);
	}

	public void setPenaltiDAO(PenaltiDAO penaltiDAO)
	{
		this.penaltiDAO = penaltiDAO;
	}

}
