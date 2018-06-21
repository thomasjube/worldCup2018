package com.tjube.controller;

import java.util.ArrayList;
import java.util.List;

import com.tjube.model.Game;

public class GameEditCompoForm
{

	private Integer gameId;

	private Integer teamId1;
	private Integer teamId2;

	private boolean[] titular1 = new boolean[23];
	private boolean[] captain1 = new boolean[23];
	private List<Integer> titularId1 = new ArrayList<>();
	private List<Integer> substituteId1 = new ArrayList<>();
	private List<Integer> minute1 = new ArrayList<>();

	private boolean[] titular2 = new boolean[23];
	private boolean[] captain2 = new boolean[23];
	private List<Integer> titularId2 = new ArrayList<>();
	private List<Integer> substituteId2 = new ArrayList<>();
	private List<Integer> minute2 = new ArrayList<>();

	public GameEditCompoForm()
	{
	}

	public GameEditCompoForm(Game game)
	{
		gameId = game.getId();
		teamId1 = game.getTeam1().getId();
		teamId2 = game.getTeam2().getId();
		for (int i = 0; i < 23; i++)
		{
			titular1[i] = false;
			titular2[i] = false;
		}
	}

	public Integer getGameId()
	{
		return gameId;
	}

	public void setGameId(Integer gameId)
	{
		this.gameId = gameId;
	}

	public Integer getTeamId1()
	{
		return teamId1;
	}

	public void setTeamId1(Integer teamId1)
	{
		this.teamId1 = teamId1;
	}

	public Integer getTeamId2()
	{
		return teamId2;
	}

	public void setTeamId2(Integer teamId2)
	{
		this.teamId2 = teamId2;
	}

	public boolean[] getTitular1()
	{
		return titular1;
	}

	public void setTitular1(boolean[] titular1)
	{
		this.titular1 = titular1;
	}

	public List<Integer> getTitularId1()
	{
		return titularId1;
	}

	public void setTitularId1(List<Integer> titularId1)
	{
		this.titularId1 = titularId1;
	}

	public List<Integer> getSubstituteId1()
	{
		return substituteId1;
	}

	public void setSubstituteId1(List<Integer> substituteId1)
	{
		this.substituteId1 = substituteId1;
	}

	public List<Integer> getMinute1()
	{
		return minute1;
	}

	public void setMinute1(List<Integer> minute1)
	{
		this.minute1 = minute1;
	}

	public boolean[] getTitular2()
	{
		return titular2;
	}

	public void setTitular2(boolean[] titular2)
	{
		this.titular2 = titular2;
	}

	public List<Integer> getTitularId2()
	{
		return titularId2;
	}

	public void setTitularId2(List<Integer> titularId2)
	{
		this.titularId2 = titularId2;
	}

	public List<Integer> getSubstituteId2()
	{
		return substituteId2;
	}

	public void setSubstituteId2(List<Integer> substituteId2)
	{
		this.substituteId2 = substituteId2;
	}

	public List<Integer> getMinute2()
	{
		return minute2;
	}

	public void setMinute2(List<Integer> minute2)
	{
		this.minute2 = minute2;
	}

	public boolean[] getCaptain1()
	{
		return captain1;
	}

	public void setCaptain1(boolean[] captain1)
	{
		this.captain1 = captain1;
	}

	public boolean[] getCaptain2()
	{
		return captain2;
	}

	public void setCaptain2(boolean[] captain2)
	{
		this.captain2 = captain2;
	}
}
