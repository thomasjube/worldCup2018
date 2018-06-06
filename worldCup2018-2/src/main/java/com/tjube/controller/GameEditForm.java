package com.tjube.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.AutoPopulatingList;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.tjube.model.Game;

public class GameEditForm {

	private Integer id;
	
	private Integer score1;
	
	private Integer score2;
	
	private boolean prolong = false;
	
	private Integer scoreProlong1 = 0;
	
	private Integer scoreProlong2 = 0;
	
	private boolean peno = false;
	
	private Integer scorePeno1 = 0;
	
	private Integer scorePeno2 = 0;
	
	private List<ScorerPlayer> scorerPlayers1 = new AutoPopulatingList<ScorerPlayer>(ScorerPlayer.class);

	private List<ScorerPlayer> scorerPlayers2 = new AutoPopulatingList<ScorerPlayer>(ScorerPlayer.class);
	
	public GameEditForm() {
		// TODO Auto-generated constructor stub
	}

	public GameEditForm(Game game) {
		this.id = game.getId();
		this.score1 = game.getScore1();
		this.score2 = game.getScore2();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
			binder.setAutoGrowNestedPaths(true);
	}
	
	public class ScorerPlayer{
		
		private Integer idPlayer;
		private Integer idPasser;
		private Integer minute;
		
		public ScorerPlayer() {
			// TODO Auto-generated constructor stub
		}
		
		public Integer getIdPlayer() {
			return idPlayer;
		}
		public void setIdPlayer(Integer idPlayer) {
			this.idPlayer = idPlayer;
		}
		public Integer getMinute() {
			return minute;
		}
		public void setMinute(Integer minute) {
			this.minute = minute;
		}
		
		public Integer getIdPasser() {
			return idPasser;
		}

		public void setIdPasser(Integer idPasser) {
			this.idPasser = idPasser;
		}
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScore1() {
		return score1;
	}

	public void setScore1(Integer score1) {
		this.score1 = score1;
	}

	public Integer getScore2() {
		return score2;
	}

	public void setScore2(Integer score2) {
		this.score2 = score2;
	}

	public List<ScorerPlayer> getScorerPlayers1() {
		return scorerPlayers1;
	}

	public void setScorerPlayers1(List<ScorerPlayer> scorerPlayers1) {
		this.scorerPlayers1 = scorerPlayers1;
	}

	public List<ScorerPlayer> getScorerPlayers2() {
		return scorerPlayers2;
	}

	public void setScorerPlayers2(List<ScorerPlayer> scorerPlayers2) {
		this.scorerPlayers2 = scorerPlayers2;
	}

	public boolean isProlong() {
		return prolong;
	}

	public void setProlong(boolean prolong) {
		this.prolong = prolong;
	}

	public Integer getScoreProlong1() {
		return scoreProlong1;
	}

	public void setScoreProlong1(Integer scoreProlong1) {
		this.scoreProlong1 = scoreProlong1;
	}

	public Integer getScoreProlong2() {
		return scoreProlong2;
	}

	public void setScoreProlong2(Integer scoreProlong2) {
		this.scoreProlong2 = scoreProlong2;
	}

	public boolean isPeno() {
		return peno;
	}

	public void setPeno(boolean peno) {
		this.peno = peno;
	}

	public Integer getScorePeno1() {
		return scorePeno1;
	}

	public void setScorePeno1(Integer scorePeno1) {
		this.scorePeno1 = scorePeno1;
	}

	public Integer getScorePeno2() {
		return scorePeno2;
	}

	public void setScorePeno2(Integer scorePeno2) {
		this.scorePeno2 = scorePeno2;
	}
	
}
