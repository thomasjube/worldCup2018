package com.tjube.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
		@NamedQuery(name = WinnaBet.QN.GET_WINNABET_BY_ID, query = "select wb from WinnaBet wb where wb.id =:id "),
		@NamedQuery(name = WinnaBet.QN.getWinnaBetByName, query = "select wb from WinnaBet wb where wb.name =:name"),
		@NamedQuery(name = WinnaBet.QN.getWinnaBetByGame, query = "select wb from WinnaBet wb where wb.game =:game "),
		@NamedQuery(name = WinnaBet.QN.GET_NB_WINABET,
				query = "select wb.name,count(wb) as score from WinnaBet wb where wb.game.dateTime < :date GROUP BY wb.name"),
		@NamedQuery(name = WinnaBet.QN.GET_NB_SCORE,
				query = "select wb.name,count(wb) as score from WinnaBet wb WHERE wb.goodScore = TRUE GROUP BY wb.name"),
		@NamedQuery(name = WinnaBet.QN.GET_NB_RESULT,
				query = "select wb.name,count(wb) as score from WinnaBet wb WHERE wb.goodResult = TRUE GROUP BY wb.name"),
		@NamedQuery(name = WinnaBet.QN.GET_ALL_WINNABET, query = "select wb from WinnaBet wb") })
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

	private static final long serialVersionUID = 6062932609547214678L;

	public static class QN
	{
		public static final String GET_ALL_WINNABET = "WinnaBet.getAllWinnaBets";
		public static final String GET_WINNABET_BY_ID = "WinnaBet.getWinnaBetById";
		public static final String getWinnaBetByGame = "WinnaBet.getWinnaBetByGame";
		public static final String getWinnaBetByName = "WinnaBet.getWinnaBetByName";
		public static final String GET_NB_WINABET = "WinnaBet.getNbWinaBet";
		public static final String GET_NB_SCORE = "WinnaBet.getNbScore";
		public static final String GET_NB_RESULT = "WinnaBet.getNbResult";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	@Enumerated(EnumType.STRING)
	private BetName name;

	@ManyToOne(fetch = FetchType.LAZY)
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

	public WinnaBet(BetName name, Game game, int score1, int score2)
	{
		this.name = name;
		this.game = game;
		this.score1 = score1;
		this.score2 = score2;
		goodScore = false;
		goodResult = false;
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
