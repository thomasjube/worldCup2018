package com.tjube.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(name = Player.QN.GET_PLAYER_BY_ID, query = "select p from Player p where p.id =:id"),
		@NamedQuery(name = Player.QN.GET_ALL_PLAYERS, query = "select p from Player p order by p.name asc"),
		@NamedQuery(name = Player.QN.findGoals,
				query = "select p from Player p where p.team =:team AND p.poste like 'G%' order by p.name asc"),
		@NamedQuery(name = Player.QN.findDefensers,
				query = "select p from Player p where p.team =:team AND p.poste like 'D%' order by p.name asc"),
		@NamedQuery(name = Player.QN.findMiddles,
				query = "select p from Player p where p.team =:team AND p.poste like 'M%' order by p.name asc"),
		@NamedQuery(name = Player.QN.findStrikers,
				query = "select p from Player p where p.team =:team AND (p.poste like 'A%' or p.poste = 'BU')order by p.name asc") })

@Entity
@Table(name = "PLAYER")
public class Player
	implements Serializable
{

	//==================================================================================================================================================================================================
	//
	// Query names
	//
	//==================================================================================================================================================================================================

	public static class QN
	{
		public static final String findGoals = "Player.findGoals";
		public static final String findDefensers = "Player.findDefensers";
		public static final String findMiddles = "Player.findMiddles";
		public static final String findStrikers = "Player.findStrikers";
		public static final String GET_PLAYER_BY_ID = "Player.getPlayerById";
		public static final String GET_ALL_PLAYERS = "Player.getAllPlayers";
	}

	private static final long serialVersionUID = -8989803132904605862L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String name;

	@Column
	private String firstName;

	@Column
	private String poste;

	@Column
	private Integer number;

	@ManyToOne(fetch = FetchType.EAGER)
	private Team team;

	@OneToMany(mappedBy = "player", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<PlayerStats> playerStats = new ArrayList<>();

	public Player()
	{
		// TODO Auto-generated constructor stub
	}

	public Player(String name, String firstName, String poste, Integer number, Team team)
	{
		super();
		this.name = name;
		this.firstName = firstName;
		this.poste = poste;
		this.team = team;
		this.number = number;
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

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getPoste()
	{
		return poste;
	}

	public void setPoste(String poste)
	{
		this.poste = poste;
	}

	public Team getTeam()
	{
		return team;
	}

	public void setTeam(Team team)
	{
		this.team = team;
	}

	public Integer getNumber()
	{
		return number;
	}

	public void setNumber(Integer number)
	{
		this.number = number;
	}

	public Collection<PlayerStats> getPlayerStats()
	{
		return playerStats;
	}

	public void setPlayerStats(Collection<PlayerStats> playerStats)
	{
		this.playerStats = playerStats;
	}

	public void addPlayerStat(PlayerStats stat)
	{
		this.playerStats.add(stat);
	}

	public void removestat(PlayerStats playerStat)
	{
		this.playerStats.remove(playerStat);
	}
}
