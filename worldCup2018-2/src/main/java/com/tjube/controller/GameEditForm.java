package com.tjube.controller;

import java.util.ArrayList;
import java.util.List;

import com.tjube.model.Game;

public class GameEditForm
{

	private Integer id;

	private Integer score1;

	private Integer score2;

	private boolean prolong = false;

	private Integer scoreProlong1 = 0;

	private Integer scoreProlong2 = 0;

	private boolean peno = false;

	private Integer scorePeno1 = 0;

	private Integer scorePeno2 = 0;

	private List<Integer> scorerPlayers1 = new ArrayList<>();
	private List<Integer> scorerPlayers2 = new ArrayList<>();

	private List<Integer> passerPlayers1 = new ArrayList<>();
	private List<Integer> passerPlayers2 = new ArrayList<>();

	private List<Integer> scorerPlayersMinute1 = new ArrayList<>();
	private List<Integer> scorerPlayersMinute2 = new ArrayList<>();

	public GameEditForm()
	{
		// TODO Auto-generated constructor stub
	}

	public GameEditForm(Game game)
	{
		this.id = game.getId();
		this.score1 = game.getScore1();
		this.score2 = game.getScore2();

	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getScore1()
	{
		return score1;
	}

	public void setScore1(Integer score1)
	{
		this.score1 = score1;
	}

	public Integer getScore2()
	{
		return score2;
	}

	public void setScore2(Integer score2)
	{
		this.score2 = score2;
	}

	public List<Integer> getScorerPlayers1()
	{
		return scorerPlayers1;
	}

	public void setScorerPlayers1(List<Integer> scorerPlayers1)
	{
		this.scorerPlayers1 = scorerPlayers1;
	}

	public List<Integer> getScorerPlayers2()
	{
		return scorerPlayers2;
	}

	public void setScorerPlayers2(List<Integer> scorerPlayers2)
	{
		this.scorerPlayers2 = scorerPlayers2;
	}

	public List<Integer> getPasserPlayers1()
	{
		return passerPlayers1;
	}

	public void setPasserPlayers1(List<Integer> passerPlayers1)
	{
		this.passerPlayers1 = passerPlayers1;
	}

	public List<Integer> getPasserPlayers2()
	{
		return passerPlayers2;
	}

	public void setPasserPlayers2(List<Integer> passerPlayers2)
	{
		this.passerPlayers2 = passerPlayers2;
	}

	public List<Integer> getScorerPlayersMinute1()
	{
		return scorerPlayersMinute1;
	}

	public void setScorerPlayersMinute1(List<Integer> scorerPlayersMinute1)
	{
		this.scorerPlayersMinute1 = scorerPlayersMinute1;
	}

	public List<Integer> getScorerPlayersMinute2()
	{
		return scorerPlayersMinute2;
	}

	public void setScorerPlayersMinute2(List<Integer> scorerPlayersMinute2)
	{
		this.scorerPlayersMinute2 = scorerPlayersMinute2;
	}

	public boolean isProlong()
	{
		return prolong;
	}

	public void setProlong(boolean prolong)
	{
		this.prolong = prolong;
	}

	public Integer getScoreProlong1()
	{
		return scoreProlong1;
	}

	public void setScoreProlong1(Integer scoreProlong1)
	{
		this.scoreProlong1 = scoreProlong1;
	}

	public Integer getScoreProlong2()
	{
		return scoreProlong2;
	}

	public void setScoreProlong2(Integer scoreProlong2)
	{
		this.scoreProlong2 = scoreProlong2;
	}

	public boolean isPeno()
	{
		return peno;
	}

	public void setPeno(boolean peno)
	{
		this.peno = peno;
	}

	public Integer getScorePeno1()
	{
		return scorePeno1;
	}

	public void setScorePeno1(Integer scorePeno1)
	{
		this.scorePeno1 = scorePeno1;
	}

	public Integer getScorePeno2()
	{
		return scorePeno2;
	}

	public void setScorePeno2(Integer scorePeno2)
	{
		this.scorePeno2 = scorePeno2;
	}

}
