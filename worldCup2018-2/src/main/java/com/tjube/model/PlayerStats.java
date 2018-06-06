package com.tjube.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PLAYER_STATS")
public class PlayerStats
	implements Serializable
{

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
	
	public PlayerStats(Game game2, Player scorerPlayer, Integer minute2, Action action) {
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

	public Action getAction() {
		return action;
	}
	
	public void setAction(Action action) {
		this.action = action;
	}
	
	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
}
