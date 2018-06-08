package com.tjube.model;

public class PlayerStatsSituation
{
	private Long goals = new Long(0);
	private Long passes = new Long(0);

	private Long yellowCard = new Long(0);
	private Long redCard = new Long(0);

	private Long playingMinutes = new Long(0);

	public PlayerStatsSituation()
	{
		// TODO Auto-generated constructor stub
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

	public Long getYellowCard()
	{
		return yellowCard;
	}

	public void setYellowCard(Long yellowCard)
	{
		this.yellowCard = yellowCard;
	}

	public Long getRedCard()
	{
		return redCard;
	}

	public void setRedCard(Long redCard)
	{
		this.redCard = redCard;
	}

	public Long getPlayingMinutes()
	{
		return playingMinutes;
	}

	public void setPlayingMinutes(Long playingMinutes)
	{
		this.playingMinutes = playingMinutes;
	}

}
