package com.tjube.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StatsWorldCup
{
	private Action action = null;

	private Player player = null;

	private Team team = null;

	private Long goals = new Long(0);

	private Long penalties = new Long(0);

	private Long passes = new Long(0);

	private Long yellowCards = new Long(0);

	private Long redCards = new Long(0);

	private BigDecimal ratio = BigDecimal.ZERO;

	private Long totalGames = new Long(0);

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

		if (goals > 0 && player != null && player.getGamesPlayed() > 0)
		{
			this.ratio = BigDecimal.valueOf(goals).divide(BigDecimal.valueOf(player.getGamesPlayed()), 2,
					RoundingMode.HALF_UP);
			this.totalGames = Long.valueOf(player.getGamesPlayed());
		}
	}

	public Long getPasses()
	{
		return passes;
	}

	public void setPasses(Long passes)
	{
		this.passes = passes;
		if (passes > 0 && player != null && player.getGamesPlayed() > 0)
		{
			this.ratio = BigDecimal.valueOf(passes).divide(BigDecimal.valueOf(player.getGamesPlayed()), 2,
					RoundingMode.HALF_UP);
			this.totalGames = Long.valueOf(player.getGamesPlayed());
		}
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

	public BigDecimal getRatio()
	{
		return ratio;
	}

	public void setRatio(BigDecimal ratio)
	{
		this.ratio = ratio;
	}

	public Long getTotalGames()
	{
		return totalGames;
	}

	public void setTotalGames(Long totalGames)
	{
		this.totalGames = totalGames;
	}

	public Long getPenalties()
	{
		return penalties;
	}

	public void setPenalties(Long penalties)
	{
		this.penalties = penalties;
	}
}
