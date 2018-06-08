package com.tjube.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
		@NamedQuery(name = PlayerStats.QN.GET_STATS_BY_ID, query = "select s from PlayerStats s where s.id =:id "),
		@NamedQuery(name = PlayerStats.QN.GET_STATS_BY_GAME,
				query = "select s from PlayerStats s where s.game =:game "),
		@NamedQuery(name = PlayerStats.QN.GET_ALL_STATS, query = "select s from PlayerStats s"), @NamedQuery(
				name = PlayerStats.QN.GET_GOALS_STATS, query = "select s from PlayerStats s where s.action =:action") })
@Entity
@Table(name = "PLAYER_STATS")
public class PlayerStats
	implements Serializable
{

	//==================================================================================================================================================================================================
	//
	// Query names
	//
	//==================================================================================================================================================================================================

	public static class QN
	{
		public static final String GET_STATS_BY_ID = "PlayerStats.getPlayerStatsById";
		public static final String GET_STATS_BY_GAME = "PlayerStats.getPlayerStatsByGame";
		public static final String GET_ALL_STATS = "PlayerStats.getAllPlayerStats";
		public static final String GET_GOALS_STATS = "PlayerStats.getGoalsPlayerStats";
	}

	private static final long serialVersionUID = -1740184637168312573L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private Action action;

	@Column
	private Integer minute;

	@ManyToOne
	private Game game;

	@ManyToOne
	private Player player;

	public PlayerStats()
	{
	}

	public PlayerStats(Game game2, Player scorerPlayer, Integer minute2, Action action)
	{
		this.game = game2;
		this.player = scorerPlayer;
		this.minute = minute2;
		this.action = action;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Action getAction()
	{
		return action;
	}

	public void setAction(Action action)
	{
		this.action = action;
	}

	public Integer getMinute()
	{
		return minute;
	}

	public void setMinute(Integer minute)
	{
		this.minute = minute;
	}

	public Game getGame()
	{
		return game;
	}

	public void setGame(Game game)
	{
		this.game = game;
	}

	public Player getPlayer()
	{
		return player;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}
}
