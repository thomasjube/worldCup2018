package com.tjube.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BetClassementLine
{
	private static final Integer SCORE_POINTS = 5;
	private static final Integer RESULT_POINTS = 3;

	private BetName name = null;

	private Long nbWinaBet = (long) 0;

	private Long nbScore = new Long(0);

	private Long nbResults = new Long(0);

	private Long points = new Long(0);

	private BigDecimal percentGood = BigDecimal.ZERO;

	private BigDecimal percentParticipation = BigDecimal.ZERO;

	public BetClassementLine()
	{
		// Default constructor
	}

	public BetClassementLine(BetName name, int totalGamesPlayed, Long nbWinaBet, Long score, Long result)
	{
		this.name = name;
		this.nbWinaBet = nbWinaBet;
		this.nbScore = score;
		this.nbResults = result;
		this.points = (score * SCORE_POINTS) + (result * RESULT_POINTS);
		if (nbWinaBet > 0)
		{
			this.percentGood = BigDecimal.valueOf(score).add(BigDecimal.valueOf(result))
					.divide(BigDecimal.valueOf(nbWinaBet), 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
		}
		if (totalGamesPlayed > 0)
			this.percentParticipation = (BigDecimal.valueOf(nbWinaBet))
					.divide(BigDecimal.valueOf(totalGamesPlayed), 2, RoundingMode.HALF_UP)
					.multiply(BigDecimal.valueOf(100));
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

	public Long getNbWinaBet()
	{
		return nbWinaBet;
	}

	public void setNbWinaBet(Long nbWinaBet)
	{
		this.nbWinaBet = nbWinaBet;
	}

	public BigDecimal getPercentGood()
	{
		return percentGood;
	}

	public BigDecimal getPercentParticipation()
	{
		return percentParticipation;
	}

	public void setPercentGood(BigDecimal percentGood)
	{
		this.percentGood = percentGood;
	}

	public void setPercentParticipation(BigDecimal percentParticipation)
	{
		this.percentParticipation = percentParticipation;
	}

}
