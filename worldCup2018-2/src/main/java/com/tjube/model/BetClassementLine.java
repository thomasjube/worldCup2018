package com.tjube.model;

public class BetClassementLine
{
	private static final Integer SCORE_POINTS = 5;
	private static final Integer RESULT_POINTS = 3;

	private BetName name = null;

	private Long nbScore = new Long(0);

	private Long nbResults = new Long(0);

	private Long points = new Long(0);

	public BetClassementLine()
	{
		// Default constructor
	}

	public BetClassementLine(BetName name, Long score, Long result)
	{
		this.name = name;
		this.nbScore = score;
		this.nbResults = result;
		this.points = (score * SCORE_POINTS) + (result * RESULT_POINTS);
	}

	public BetName getName()
	{
		return name;
	}

	public void setName(BetName name)
	{
		this.name = name;
	}

	public Long getNbScore()
	{
		return nbScore;
	}

	public void setNbScore(Long nbScore)
	{
		this.nbScore = nbScore;
	}

	public Long getNbResults()
	{
		return nbResults;
	}

	public void setNbResults(Long nbResults)
	{
		this.nbResults = nbResults;
	}

	public Long getPoints()
	{
		return points;
	}

	public void setPoints(Long points)
	{
		this.points = points;
	}

}
