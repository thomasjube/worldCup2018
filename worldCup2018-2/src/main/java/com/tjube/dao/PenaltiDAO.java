package com.tjube.dao;

import java.util.List;

import com.tjube.model.Penalti;

public interface PenaltiDAO
{
	public void addPenalti(Penalti game);

	public List<Penalti> getAllPenaltis();

	public void deletePenalti(Integer gameId);

	public Penalti updatePenalti(Penalti game);

	public Penalti getPenalti(int gameId);
}
