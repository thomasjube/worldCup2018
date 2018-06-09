package com.tjube.model;

public class StatsWorldCup
{
	private Action action = null;

	private Player player = null;

	private Team team = null;

	private Long goals = new Long(0);

	private Long passes = new Long(0);

	private Long yellowCards = new Long(0);

	private Long redCards = new Long(0);

	public StatsWorldCup()
	{
		// Default constructor
	}

	public Action getAction()
	{
		return action;
	}

	public void setAction(Action action)
	{
		this.action = action;
	}

	public Player getPlayer()
	{
		return player;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}

	public Long getGoals()
	{
		return goals;
	}

	public void setGoals(Long goals)
	{
		this.goals = goals;
	}

	public Long getPasses()
	{
		return passes;
	}

	public void setPasses(Long passes)
	{
		this.passes = passes;
	}

	public Long getYellowCards()
	{
		return yellowCards;
	}

	public void setYellowCards(Long yellowCards)
	{
		this.yellowCards = yellowCards;
	}

	public Long getRedCards()
	{
		return redCards;
	}

	public void setRedCards(Long redCards)
	{
		this.redCards = redCards;
	}

	public Team getTeam()
	{
		return team;
	}

	public void setTeam(Team team)
	{
		this.team = team;
	}
}
