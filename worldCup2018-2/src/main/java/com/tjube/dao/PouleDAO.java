package com.tjube.dao;

import java.util.List;

import com.tjube.model.Poule;

public interface PouleDAO
{
	public void addPoule(Poule poule);

	public List<Poule> getAllPoules();

	public void deletePoule(Integer pouleId);

	public Poule updatePoule(Poule poule);

	public Poule getPoule(int pouleId);

	public Poule getNextPoule(Poule poule);
	public Poule getLastPoule(Poule poule);
}
