package com.tjube.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tjube.controller.LocalDateTimeAttributeConverter;

@NamedQueries({
		@NamedQuery(name = Game.QN.findNextGameByDate,
				query = "select g from Game g where g.dateTime > :date AND g.score1 is null order by g.dateTime,g.name"),
		@NamedQuery(name = Game.QN.findLastGameByDate,
				query = "select g from Game g where g.dateTime < :date AND g.score1 is not null order by g.dateTime,g.name"),
		@NamedQuery(name = Game.QN.GET_ALL_GAMES_BY_TEAM,
				query = "select g from Game g where g.team1=:team OR g.team2=:team order by g.dateTime,g.name"),
		@NamedQuery(name = Game.QN.GET_ALL_GAMES_BY_POULE,
				query = "select g from Game g where g.poule=:poule order by g.dateTime,g.name"),
		@NamedQuery(name = Game.QN.GET_ALL_GAMES, query = "select g from Game g order by g.dateTime,g.name"),
		@NamedQuery(name = Game.QN.GET_GAME_BY_ID, query = "select g from Game g where g.id=:id"),
		@NamedQuery(name = Game.QN.findNextGameByDateAndPoule,
				query = "select g from Game g where g.dateTime > :date AND g.score1 is null AND g.poule=:poule order by g.dateTime,g.name") })
@Entity
@Table(name = "GAME")
public class Game
	implements Serializable
{

	//==================================================================================================================================================================================================
	//
	// Query names
	//
	//==================================================================================================================================================================================================

	public static class QN
	{
		public static final String findNextGameByDateAndPoule = "Game.findNextGameByDateAndPoule";
		public static final String findNextGameByDate = "Game.findNextGameByDate";
		public static final String findLastGameByDate = "Game.findLastGameByDate";
		public static final String GET_ALL_GAMES_BY_TEAM = "Game.getAllGamesByTeam";
		public static final String GET_ALL_GAMES_BY_POULE = "Game.getAllGamesByPoule";
		public static final String GET_GAME_BY_ID = "Game.getGameById";
		public static final String GET_ALL_GAMES = "Game.getAllGames";
	}

	private static final long serialVersionUID = 1123828229175369276L;

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	static class GoalComparator
		implements Comparator<PlayerStats>
	{
		@Override
		public int compare(PlayerStats c1, PlayerStats c2)
		{
			return c1.getMinute().compareTo(c2.getMinute());
		}
	}

	@Id
	private int id;

	@Column
	private String name;

	@ManyToOne
	private Team team1;

	@ManyToOne
	private Team team2;

	@ManyToOne
	private Poule poule;

	@Column
	private Integer score1;

	@Column
	private Integer score2;

	@Column
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime dateTime;

	@Column
	private String stade;

	@Column
	private Boolean gameInPoule;

	@Column
	private Boolean prolong;

	@Column
	private Integer score1_prolong;

	@Column
	private Integer score2_prolong;

	@Column
	private Boolean penalti;

	@Column
	private Integer score1_penalti;

	@Column
	private Integer score2_penalti;

	@OneToMany(mappedBy = "game", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<PlayerStats> playerStats = new ArrayList<>();

	public Game()
	{
		// TODO Auto-generated constructor stub
	}

	public Game(Integer id, String name, Team team1, Team team2, Poule poule, LocalDateTime date, String stade,
			Boolean gameInPoule)
	{
		super();
		this.id = id;
		this.name = name;
		this.team1 = team1;
		this.team2 = team2;
		this.poule = poule;
		this.dateTime = date;
		this.stade = stade;
		this.gameInPoule = gameInPoule;
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

	public Team getTeam1()
	{
		return team1;
	}

	public void setTeam1(Team team1)
	{
		this.team1 = team1;
	}

	public Team getTeam2()
	{
		return team2;
	}

	public void setTeam2(Team team2)
	{
		this.team2 = team2;
	}

	public Poule getPoule()
	{
		return poule;
	}

	public void setPoule(Poule poule)
	{
		this.poule = poule;
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

	public LocalDateTime getDateTime()
	{
		return dateTime;
	}

	public void setDateTime(LocalDateTime date)
	{
		this.dateTime = date;
	}

	public String getStade()
	{
		return stade;
	}

	public void setStade(String stade)
	{
		this.stade = stade;
	}

	public Boolean getGameInPoule()
	{
		return gameInPoule;
	}

	public void setGameInPoule(Boolean gameInPoule)
	{
		this.gameInPoule = gameInPoule;
	}

	public Boolean getProlong()
	{
		return prolong;
	}

	public void setProlong(Boolean prolong)
	{
		this.prolong = prolong;
	}

	public Integer getScore1_prolong()
	{
		return score1_prolong;
	}

	public void setScore1_prolong(Integer score1_prolong)
	{
		this.score1_prolong = score1_prolong;
	}

	public Integer getScore2_prolong()
	{
		return score2_prolong;
	}

	public void setScore2_prolong(Integer score2_prolong)
	{
		this.score2_prolong = score2_prolong;
	}

	public Boolean getPenalti()
	{
		return penalti;
	}

	public void setPenalti(Boolean penalti)
	{
		this.penalti = penalti;
	}

	public Integer getScore1_penalti()
	{
		return score1_penalti;
	}

	public void setScore1_penalti(Integer score1_penalti)
	{
		this.score1_penalti = score1_penalti;
	}

	public Integer getScore2_penalti()
	{
		return score2_penalti;
	}

	public void setScore2_penalti(Integer score2_penalti)
	{
		this.score2_penalti = score2_penalti;
	}

	public Collection<PlayerStats> getPlayerStats()
	{
		return playerStats;
	}

	public void setPlayerStats(Collection<PlayerStats> playerStats)
	{
		this.playerStats = playerStats;
	}

	public List<PlayerStats> getGoalsTeam1()
	{
		Collection<PlayerStats> results = new ArrayList<>();
		for (PlayerStats playerStat : playerStats)
		{
			if (playerStat.getPlayer().getTeam() == team1 && playerStat.getAction() == Action.GOAL)
				results.add(playerStat);
		}
		List<PlayerStats> goalList = new ArrayList<>(results);
		Collections.sort(goalList, new GoalComparator());
		return goalList;
	}

	public List<PlayerStats> getGoalsTeam2()
	{
		Collection<PlayerStats> results = new ArrayList<>();
		for (PlayerStats playerStat : playerStats)
		{
			if (playerStat.getPlayer().getTeam() == team2 && playerStat.getAction() == Action.GOAL)
				results.add(playerStat);
		}
		List<PlayerStats> goalList = new ArrayList<>(results);
		Collections.sort(goalList, new GoalComparator());
		return goalList;
	}

	public LocalDate getDate()
	{
		return dateTime.toLocalDate();
	}

	public LocalTime getTime()
	{
		return dateTime.toLocalTime();
	}

	public Team getWinner()
	{
		if (this.score1 == this.score2)
		{
			if (this.score1_prolong == this.score2_prolong)
			{
				if (this.score1_penalti < this.score2_penalti)
					return this.team2;
				else
					return this.team1;
			}
			else if (this.score1_prolong < this.score2_prolong)
				return this.team2;
			else
				return this.team1;
		}
		else if (this.score1 < this.score2)
			return this.team2;
		else
			return this.team1;
	}

	public Team getLooser()
	{
		if (this.score1 == this.score2)
		{
			if (this.score1_prolong == this.score2_prolong)
			{
				if (this.score1_penalti < this.score2_penalti)
					return this.team2;
				else
					return this.team1;
			}
			else if (this.score1_prolong < this.score2_prolong)
				return this.team2;
			else
				return this.team1;
		}
		else if (this.score1 < this.score2)
			return this.team2;
		else
			return this.team1;
	}

	public void addPlayerStat(PlayerStats stat)
	{
		this.playerStats.add(stat);
	}

}
