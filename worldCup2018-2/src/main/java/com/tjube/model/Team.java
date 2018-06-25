package com.tjube.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(name = Team.QN.findTeamByName, query = "select t from Team t where t.name =:name"),
		@NamedQuery(name = Team.QN.findTeamsByPoule,
				query = "select t from Team t where t.poule =:poule order by t.position_poule"),
		@NamedQuery(name = Team.QN.getTeamsByPoint,
				query = "select t from Team t where t.poule = :poule order by t.point DESC, t.diff DESC, t.but_mis DESC, t.but_pris ASC, t.id"),
		@NamedQuery(name = Team.QN.GET_TEAM_BY_ID, query = "select t from Team t where t.id =:id"),
		@NamedQuery(name = Team.QN.GET_ALL_TEAMS, query = "select t from Team t order by t.name"),
		@NamedQuery(name = Team.QN.RETRIEVE_TEAM_ORDER_BEST_ATTACK,
				query = "select t from Team t where t.but_mis > 0 order by t.allButMis DESC, t.name ASC"),
		@NamedQuery(name = Team.QN.RETRIEVE_TEAM_ORDER_WORST_DEFENSE,
				query = "select t from Team t order by t.allButPris DESC, t.name ASC") })
@Entity
@Table(name = "TEAM")
public class Team
	implements Serializable
{

	//==================================================================================================================================================================================================
	//
	// Query names
	//
	//==================================================================================================================================================================================================

	public static class QN
	{
		public static final String findTeamsByPoule = "Team.findTeamsByPoule";
		public static final String findTeamByName = "Team.findTeamByName";
		public static final String getTeamsByPoint = "Team.getTeamsByPoint";
		public static final String GET_TEAM_BY_ID = "Team.getTeamById";
		public static final String GET_ALL_TEAMS = "Team.getAllTeams";
		public static final String RETRIEVE_TEAM_ORDER_BEST_ATTACK = "Team.retrieveTeamOrderByBestAttack";
		public static final String RETRIEVE_TEAM_ORDER_WORST_DEFENSE = "Team.retrieveTeamOrderByWorstDefense";
	}

	private static final long serialVersionUID = -5028311133164527096L;

	static class PlayerComparator
		implements Comparator<Player>
	{
		@Override
		public int compare(Player c1, Player c2)
		{
			return c1.getNumber().compareTo(c2.getNumber());
		}
	}

	@Id
	private int id;

	@Column
	private String name;

	@Column
	private Integer position_poule;

	@ManyToOne(fetch = FetchType.LAZY)
	private Poule poule;

	@OneToMany(fetch = FetchType.LAZY)
	private Collection<Player> players = new ArrayList<>();

	@Column
	private Integer gamePlayed = 0;

	@Column
	private Integer point = 0;

	@Column
	private Integer gameWin = 0;

	@Column
	private Integer gameDraw = 0;

	@Column
	private Integer gameLost = 0;

	@Column
	private Integer diff = 0;

	@Column
	private Integer but_mis = 0;

	@Column
	private Integer but_pris = 0;

	@Column
	private Integer allButMis = 0;

	@Column
	private Integer allButPris = 0;

	public Team()
	{
		// default constructor
	}

	public Team(Integer id, String name, Integer positionPoule, Poule poule)
	{
		super();
		this.id = id;
		this.name = name;
		this.position_poule = positionPoule;
		this.poule = poule;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Integer getPosition_poule()
	{
		return position_poule;
	}

	public void setPosition_poule(Integer position_poule)
	{
		this.position_poule = position_poule;
	}

	public Poule getPoule()
	{
		return poule;
	}

	public void setPoule(Poule poule)
	{
		this.poule = poule;
	}

	public Collection<Player> getPlayers()
	{
		Set<Player> playersSet = new HashSet<>(players);
		List<Player> playerOrdered = new ArrayList<>(playersSet);
		Collections.sort(playerOrdered, new PlayerComparator());
		return playerOrdered;
	}

	public void setPlayers(Collection<Player> players)
	{
		this.players = players;
	}

	public Integer getGamePlayed()
	{
		return gamePlayed;
	}

	public void setGamePlayed(Integer gamePlayed)
	{
		this.gamePlayed = gamePlayed;
	}

	public Integer getPoint()
	{
		return point;
	}

	public void setPoint(Integer point)
	{
		this.point = point;
	}

	public Integer getGameWin()
	{
		return gameWin;
	}

	public void setGameWin(Integer gameWin)
	{
		this.gameWin = gameWin;
	}

	public Integer getGameDraw()
	{
		return gameDraw;
	}

	public void setGameDraw(Integer gameDraw)
	{
		this.gameDraw = gameDraw;
	}

	public Integer getGameLost()
	{
		return gameLost;
	}

	public void setGameLost(Integer gameLost)
	{
		this.gameLost = gameLost;
	}

	public Integer getDiff()
	{
		return diff;
	}

	public void setDiff(Integer diff)
	{
		this.diff = diff;
	}

	public Integer getBut_mis()
	{
		return but_mis;
	}

	public void setBut_mis(Integer but_mis)
	{
		this.but_mis = but_mis;
	}

	public Integer getBut_pris()
	{
		return but_pris;
	}

	public void setBut_pris(Integer but_pris)
	{
		this.but_pris = but_pris;
	}

	public Integer getAllButMis()
	{
		return allButMis;
	}

	public void setAllButMis(Integer allButMis, boolean reset)
	{
		if (reset)
			this.allButMis -= allButMis;
		else
			this.allButMis += allButMis;
	}

	public void setAllButMis(Integer allButMis)
	{
		this.allButMis = allButMis;
	}

	public Integer getAllButPris()
	{
		return allButPris;
	}

	public void setAllButPris(Integer allButPris, boolean reset)
	{
		if (reset)
			this.allButPris -= allButPris;
		else
			this.allButPris += allButPris;
	}

	public void setAllButPris(Integer allButPris)
	{
		this.allButPris = allButPris;
	}

	@Override
	public int hashCode()
	{
		return new Long(getId()).hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;

		if (!(obj instanceof Team))
			return false;

		return getId() == ((Team) obj).getId();
	}
}
