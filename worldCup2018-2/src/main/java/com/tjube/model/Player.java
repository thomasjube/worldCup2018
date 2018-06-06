package com.tjube.model;

import java.io.Serializable;
import java.util.Collection;

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

import com.tjube.model.Team;

@NamedQueries({
	@NamedQuery(
	name = "findGoals",
	query = "select p from Player p where p.team =:team AND p.poste like 'G%' order by p.name asc"
	),
	@NamedQuery(
	name = "findDefensers",
	query = "select p from Player p where p.team =:team AND p.poste like 'D%' order by p.name asc"
	),
	@NamedQuery(
	name = "findMiddles",
	query = "select p from Player p where p.team =:team AND p.poste like 'M%' order by p.name asc"
	),
	@NamedQuery(
	name = "findStrikers",
	query = "select p from Player p where p.team =:team AND (p.poste like 'A%' or p.poste = 'BU')order by p.name asc"
	)
})

@Entity
@Table(name = "PLAYER")
public class Player
	implements Serializable
{

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
    
    @ManyToOne(fetch=FetchType.EAGER)
    private Team team;
    
    @OneToMany(fetch=FetchType.EAGER)
    private Collection<PlayerStats> playerStats;

    public Player() {
		// TODO Auto-generated constructor stub
	}
    
	public Player(String name, String firstName, String poste,Integer number, Team team) {
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

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}

	public Collection<PlayerStats> getPlayerStats() {
		return playerStats;
	}
	
	public void setPlayerStats(Collection<PlayerStats> playerStats) {
		this.playerStats = playerStats;
	}
}
