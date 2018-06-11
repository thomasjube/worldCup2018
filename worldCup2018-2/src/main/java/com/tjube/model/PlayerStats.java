package com.tjube.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
		@NamedQuery(name = PlayerStats.QN.GET_STATS_BY_ID, query = "select s from PlayerStats s where s.id =:id "),
		@NamedQuery(name = PlayerStats.QN.RETRIEVE_STATS_BY_PLAYER_BY_GAME,
				query = "select game1,st.action,st.minute from PlayerStats st LEFT OUTER JOIN st.game game1 where st.action in (:actions) and st.player =:player group by game1, st.action, st.minute"),
		@NamedQuery(name = PlayerStats.QN.RETRIEVE_STATS_WORLD_CUP_FOR_ACTION,
				query = "select pl,count(st) AS RESULT FROM PlayerStats st LEFT OUTER JOIN st.player pl where st.action=:action GROUP BY pl ORDER BY RESULT DESC, pl.name ASC"),
		@NamedQuery(name = PlayerStats.QN.RETRIEVE_STATS_WORLD_CUP_FOR_ACTION_AND_GAME_AND_TEAM,
				query = "select pl FROM PlayerStats st LEFT OUTER JOIN st.player pl where st.action=:action and st.game=:game and st.player.team=:team GROUP BY pl ORDER BY pl.name ASC"),
		@NamedQuery(name = PlayerStats.QN.RETRIEVE_STATS_WORLD_CUP_FOR_ACTION_AND_GAME,
				query = "select st.player.id,st2 FROM PlayerStats st, PlayerStats st2 where st.minute = st2.minute and st.action =:action and st2.action =:action2 and st.game=:game ORDER BY st.minute ASC"),
		@NamedQuery(name = PlayerStats.QN.RETRIEVE_STATS_BY_PLAYER,
				query = "select count(st) from PlayerStats st where st.action =:action and st.player =:player "),
		@NamedQuery(name = PlayerStats.QN.RETRIEVE_STATS_BY_PLAYER_WITH_MINUTES,
				query = "select count(st), SUM(st.minute) from PlayerStats st where st.action =:action and st.player =:player "),
		@NamedQuery(name = PlayerStats.QN.GET_STATS_BY_GAME,
				query = "select s from PlayerStats s where s.game =:game "),
		@NamedQuery(name = PlayerStats.QN.GET_STATS_BY_GAME_AND_ACTIONS,
				query = "select s from PlayerStats s where s.game =:game and s.action in(:actions)"),
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
		public static final String GET_STATS_BY_GAME_AND_ACTIONS = "PlayerStats.getPlayerStatsByGameAndActions";
		public static final String GET_ALL_STATS = "PlayerStats.getAllPlayerStats";
		public static final String GET_GOALS_STATS = "PlayerStats.getGoalsPlayerStats";
		public static final String RETRIEVE_STATS_BY_PLAYER = "PlayerStats.retrieveStatByPlayer";
		public static final String RETRIEVE_STATS_BY_PLAYER_BY_GAME = "PlayerStats.retrieveStatByPlayerByGame";
		public static final String RETRIEVE_STATS_BY_PLAYER_WITH_MINUTES = "PlayerStats.retrieveStatByPlayerWithMinutes";
		public static final String RETRIEVE_STATS_WORLD_CUP_FOR_ACTION = "PlayerStats.retrieveStatsWorldCupForAction";
		public static final String RETRIEVE_STATS_WORLD_CUP_FOR_ACTION_AND_GAME_AND_TEAM = "PlayerStats.retrieveStatsWorldCupForActionAndGameAndTeam";
		public static final String RETRIEVE_STATS_WORLD_CUP_FOR_ACTION_AND_GAME = "PlayerStats.retrieveStatsWorldCupForActionAndGame";
	}

	private static final long serialVersionUID = -1740184637168312573L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	@Enumerated(EnumType.STRING)
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
