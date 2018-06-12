package com.tjube.controller;

import java.util.ArrayList;
import java.util.List;

import com.tjube.model.BetName;
import com.tjube.model.Game;

public class WinnabetForm
{

	private Integer gameId;

	private List<BetName> betNames = new ArrayList<>();

	private List<Integer> resultsTeam1 = new ArrayList<>();

	private List<Integer> resultsTeam2 = new ArrayList<>();

	private List<Boolean> removeWinabets = new ArrayList<>();

	public WinnabetForm()
	{
		// default
	}

	public WinnabetForm(Game game)
	{
		this.gameId = game.getId();
	}

	public Integer getGameId()
	{
		return gameId;
	}

	public void setGameId(Integer gameId)
	{
		this.gameId = gameId;
	}

	public List<BetName> getBetNames()
	{
		return betNames;
	}

	public void setBetNames(List<BetName> betNames)
	{
		this.betNames = betNames;
	}

	public List<Integer> getResultsTeam1()
	{
		return resultsTeam1;
	}

	public void setResultsTeam1(List<Integer> resultsTeam1)
	{
		this.resultsTeam1 = resultsTeam1;
	}

	public List<Integer> getResultsTeam2()
	{
		return resultsTeam2;
	}

	public void setResultsTeam2(List<Integer> resultsTeam2)
	{
		this.resultsTeam2 = resultsTeam2;
	}

	public List<Boolean> getRemoveWinabets()
	{
		return removeWinabets;
	}

	public void setRemoveWinabets(List<Boolean> removeWinabets)
	{
		this.removeWinabets = removeWinabets;
	}
}
