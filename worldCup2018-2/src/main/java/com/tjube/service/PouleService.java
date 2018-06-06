package com.tjube.service;

import java.util.List;

import com.tjube.model.Poule;

public interface PouleService
{
	public void addPoule(Poule poule);

	public List<Poule> getAllPoules();

	public void deletePoule(Integer pouleId);

	public Poule getPoule(int pouleid);

	public Poule updatePoule(Poule poule);

	public Poule getLastPoule(Poule poule);
	public Poule getNextPoule(Poule poule);
}
