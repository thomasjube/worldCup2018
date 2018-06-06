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

import com.tjube.model.Player;
import com.tjube.model.Poule;
@NamedQueries({
	@NamedQuery(
	name = "findTeamByName",
	query = "select t from Team t where t.name =:name"
	),
	@NamedQuery(
	name = "findTeamsByPoule",
	query = "select t from Team t where t.poule =:poule order by t.position_poule"
	)
})
@Entity
@Table(name = "TEAM")
public class Team
	implements Serializable
{
	private static final long serialVersionUID = -5028311133164527096L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String name;
	
	@Column
    private Integer position_poule;
    
    @ManyToOne
    private Poule poule;
    
    @OneToMany(fetch=FetchType.EAGER)
    private Collection<Player> players;
    
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

    public Team() {
		// TODO Auto-generated constructor stub
	}
    
	public Team(String name, Integer position_poule, Poule poule) {
		super();
		this.name = name;
		this.position_poule = position_poule;
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

	public Integer getPosition_poule() {
		return position_poule;
	}

	public void setPosition_poule(Integer position_poule) {
		this.position_poule = position_poule;
	}

	public Poule getPoule() {
		return poule;
	}

	public void setPoule(Poule poule) {
		this.poule = poule;
	}

	public Collection<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Collection<Player> players) {
		this.players = players;
	}

	public Integer getGamePlayed() {
		return gamePlayed;
	}

	public void setGamePlayed(Integer gamePlayed) {
		this.gamePlayed = gamePlayed;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getGameWin() {
		return gameWin;
	}

	public void setGameWin(Integer gameWin) {
		this.gameWin = gameWin;
	}

	public Integer getGameDraw() {
		return gameDraw;
	}

	public void setGameDraw(Integer gameDraw) {
		this.gameDraw = gameDraw;
	}

	public Integer getGameLost() {
		return gameLost;
	}

	public void setGameLost(Integer gameLost) {
		this.gameLost = gameLost;
	}

	public Integer getDiff() {
		return diff;
	}

	public void setDiff(Integer diff) {
		this.diff = diff;
	}

	public Integer getBut_mis() {
		return but_mis;
	}

	public void setBut_mis(Integer but_mis) {
		this.but_mis = but_mis;
	}

	public Integer getBut_pris() {
		return but_pris;
	}

	public void setBut_pris(Integer but_pris) {
		this.but_pris = but_pris;
	}
	
	

}
