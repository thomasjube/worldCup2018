package com.tjube.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "WINNA_BET")
public class WinnaBet
	implements Serializable
{

	//==================================================================================================================================================================================================
	//
	// Query names
	//
	//==================================================================================================================================================================================================

	private static final long serialVersionUID = 1256732982068135780L;

	public static class QN
	{
	}

	@Id
	private int id;

	@Column
	@Enumerated(EnumType.STRING)
	private BetName name;

	@ManyToOne
	private Game game;

	@Column
	private Integer score1 = 0;

	@Column
	private Integer score2 = 0;

	@Column
	private Boolean goodScore;

	@Column
	private Boolean goodResult;

	public WinnaBet()
	{
	}

	public WinnaBet(Integer id, String name, Integer positionPoule, Poule poule)
	{
		super();
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public BetName getName()
	{
		return name;
	}

	public void setName(BetName name)
	{
		this.name = name;
	}

	public Game getGame()
	{
		return game;
	}

	public void setGame(Game game)
	{
		this.game = game;
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

	public Boolean getGoodScore()
	{
		return goodScore;
	}

	public void setGoodScore(Boolean goodScore)
	{
		this.goodScore = goodScore;
	}

	public Boolean getGoodResult()
	{
		return goodResult;
	}

	public void setGoodResult(Boolean goodResult)
	{
		this.goodResult = goodResult;
	}

}
