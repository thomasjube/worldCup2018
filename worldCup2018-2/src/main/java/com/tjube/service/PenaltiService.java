package com.tjube.service;

import java.util.List;

import com.tjube.model.Penalti;

public interface PenaltiService
{
	public void addPenalti(Penalti penalti);

	public List<Penalti> getAllPenaltis();

	public void deletePenalti(Integer penaltiId);

	public Penalti getPenalti(int penaltiid);

	public Penalti updatePenalti(Penalti penalti);
}
