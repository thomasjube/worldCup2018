package com.tjube.controller;

import java.util.ArrayList;
import java.util.List;

import com.tjube.model.Action;
import com.tjube.model.Game;
import com.tjube.model.PlayerStats;

public class GameEditForm
{

	private Integer id;

	private Integer score1;

	private Integer score2;

	private boolean prolong = false;

	private Integer scoreProlong1 = 0;

	private Integer scoreProlong2 = 0;

	private boolean peno = false;

	private Integer scorePeno1 = 0;

	private Integer scorePeno2 = 0;

	private List<Integer> scorerPlayers1 = new ArrayList<>();
	private List<Integer> scorerPlayers2 = new ArrayList<>();

	private List<Integer> passerPlayers1 = new ArrayList<>();
	private List<Integer> passerPlayers2 = new ArrayList<>();

	private List<Integer> scorerPlayersMinute1 = new ArrayList<>();
	private List<Integer> scorerPlayersMinute2 = new ArrayList<>();

	private List<Integer> scorerPlayersProlong1 = new ArrayList<>();
	private List<Integer> scorerPlayersProlong2 = new ArrayList<>();

	private List<Integer> passerPlayersProlong1 = new ArrayList<>();
	private List<Integer> passerPlayersProlong2 = new ArrayList<>();

	private List<Integer> scorerPlayersMinuteProlong1 = new ArrayList<>();
	private List<Integer> scorerPlayersMinuteProlong2 = new ArrayList<>();

	private List<Action> cartonType = new ArrayList<>();
	private List<Integer> cartonPlayers = new ArrayList<>();
	private List<Integer> cartonMinute = new ArrayList<>();
	private List<Integer> cartonTeam = new ArrayList<>();

	public GameEditForm()
	{
		// TODO Auto-generated constructor stub
	}

	public GameEditForm(Game game)
	{
		this.id = game.getId();
		this.score1 = game.getScore1();
		this.score2 = game.getScore2();
		this.scoreProlong1 = game.getScore1_prolong();
		this.scoreProlong2 = game.getScore2_prolong();
		this.scorePeno1 = game.getScore1_penalti();
		this.scorePeno2 = game.getScore2_penalti();

		if (scorePeno1 != null && scorePeno2 != null && (scorePeno1 != 0 || scorePeno2 != 0))
			peno = true;

		for (PlayerStats scorer : game.getGoalsTeam1())
		{
			if (scorer.getMinute() <= 90)
			{
				scorerPlayers1.add(scorer.getPlayer() != null ? scorer.getPlayer().getId() : null);
				scorerPlayersMinute1.add(scorer.getMinute());
			}
			else
			{
				prolong = true;
				scorerPlayersProlong1.add(scorer.getPlayer() != null ? scorer.getPlayer().getId() : null);
				scorerPlayersMinuteProlong1.add(scorer.getMinute());
			}

			boolean foundPasser1 = false;
			for (PlayerStats passer : game.getPassTeam1())
			{
				if (passer.getMinute() == scorer.getMinute())
				{
					if (passer.getMinute() <= 90)
						passerPlayers1.add(passer.getPlayer() != null ? passer.getPlayer().getId() : null);
					else
						passerPlayersProlong1.add(passer.getPlayer() != null ? passer.getPlayer().getId() : null);

					foundPasser1 = true;
				}
			}

			if (!foundPasser1)
			{
				if (scorer.getMinute() <= 90)
					passerPlayers1.add(null);
				else
					passerPlayersProlong1.add(null);
			}

		}

		for (PlayerStats scorer : game.getGoalsTeam2())
		{
			if (scorer.getMinute() <= 90)
			{
				scorerPlayers2.add(scorer.getPlayer() != null ? scorer.getPlayer().getId() : null);
				scorerPlayersMinute2.add(scorer.getMinute());
			}
			else
			{
				prolong = true;
				scorerPlayersProlong2.add(scorer.getPlayer() != null ? scorer.getPlayer().getId() : null);
				scorerPlayersMinuteProlong2.add(scorer.getMinute());
			}

			boolean foundPasser2 = false;
			for (PlayerStats passer : game.getPassTeam2())
			{
				if (passer.getMinute() == scorer.getMinute())
				{
					if (passer.getMinute() <= 90)
						passerPlayers2.add(passer.getPlayer() != null ? passer.getPlayer().getId() : null);
					else
						passerPlayersProlong2.add(passer.getPlayer() != null ? passer.getPlayer().getId() : null);

					foundPasser2 = true;
				}
			}

			if (!foundPasser2)
			{
				if (scorer.getMinute() <= 90)
					passerPlayers2.add(null);
				else
					passerPlayersProlong2.add(null);
			}
		}

		for (PlayerStats carton : game.getCartons())
		{
			cartonPlayers.add(carton.getPlayer().getId());
			cartonMinute.add(carton.getMinute());
			cartonType.add(carton.getAction());
			cartonTeam.add(carton.getPlayer().getTeam().getId());
		}

	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
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

	public List<Integer> getScorerPlayers1()
	{
		return scorerPlayers1;
	}

	public void setScorerPlayers1(List<Integer> scorerPlayers1)
	{
		this.scorerPlayers1 = scorerPlayers1;
	}

	public List<Integer> getScorerPlayers2()
	{
		return scorerPlayers2;
	}

	public void setScorerPlayers2(List<Integer> scorerPlayers2)
	{
		this.scorerPlayers2 = scorerPlayers2;
	}

	public List<Integer> getPasserPlayers1()
	{
		return passerPlayers1;
	}

	public void setPasserPlayers1(List<Integer> passerPlayers1)
	{
		this.passerPlayers1 = passerPlayers1;
	}

	public List<Integer> getPasserPlayers2()
	{
		return passerPlayers2;
	}

	public void setPasserPlayers2(List<Integer> passerPlayers2)
	{
		this.passerPlayers2 = passerPlayers2;
	}

	public List<Integer> getScorerPlayersMinute1()
	{
		return scorerPlayersMinute1;
	}

	public void setScorerPlayersMinute1(List<Integer> scorerPlayersMinute1)
	{
		this.scorerPlayersMinute1 = scorerPlayersMinute1;
	}

	public List<Integer> getScorerPlayersMinute2()
	{
		return scorerPlayersMinute2;
	}

	public void setScorerPlayersMinute2(List<Integer> scorerPlayersMinute2)
	{
		this.scorerPlayersMinute2 = scorerPlayersMinute2;
	}

	public boolean isProlong()
	{
		return prolong;
	}

	public void setProlong(boolean prolong)
	{
		this.prolong = prolong;
	}

	public Integer getScoreProlong1()
	{
		return scoreProlong1;
	}

	public void setScoreProlong1(Integer scoreProlong1)
	{
		this.scoreProlong1 = scoreProlong1;
	}

	public Integer getScoreProlong2()
	{
		return scoreProlong2;
	}

	public void setScoreProlong2(Integer scoreProlong2)
	{
		this.scoreProlong2 = scoreProlong2;
	}

	public boolean isPeno()
	{
		return peno;
	}

	public void setPeno(boolean peno)
	{
		this.peno = peno;
	}

	public Integer getScorePeno1()
	{
		return scorePeno1;
	}

	public void setScorePeno1(Integer scorePeno1)
	{
		this.scorePeno1 = scorePeno1;
	}

	public Integer getScorePeno2()
	{
		return scorePeno2;
	}

	public void setScorePeno2(Integer scorePeno2)
	{
		this.scorePeno2 = scorePeno2;
	}

	public List<Integer> getCartonTeam()
	{
		return cartonTeam;
	}

	public void setCartonTeam(List<Integer> cartonTeam)
	{
		this.cartonTeam = cartonTeam;
	}

	public List<Integer> getCartonMinute()
	{
		return cartonMinute;
	}

	public void setCartonMinute(List<Integer> cartonMinute)
	{
		this.cartonMinute = cartonMinute;
	}

	public List<Integer> getCartonPlayers()
	{
		return cartonPlayers;
	}

	public void setCartonPlayers(List<Integer> cartonPlayers)
	{
		this.cartonPlayers = cartonPlayers;
	}

	public List<Action> getCartonType()
	{
		return cartonType;
	}

	public void setCartonType(List<Action> cartonType)
	{
		this.cartonType = cartonType;
	}

	public List<Integer> getScorerPlayersProlong1()
	{
		return scorerPlayersProlong1;
	}

	public void setScorerPlayersProlong1(List<Integer> scorerPlayersProlong1)
	{
		this.scorerPlayersProlong1 = scorerPlayersProlong1;
	}

	public List<Integer> getScorerPlayersProlong2()
	{
		return scorerPlayersProlong2;
	}

	public void setScorerPlayersProlong2(List<Integer> scorerPlayersProlong2)
	{
		this.scorerPlayersProlong2 = scorerPlayersProlong2;
	}

	public List<Integer> getPasserPlayersProlong1()
	{
		return passerPlayersProlong1;
	}

	public void setPasserPlayersProlong1(List<Integer> passerPlayersProlong1)
	{
		this.passerPlayersProlong1 = passerPlayersProlong1;
	}

	public List<Integer> getPasserPlayersProlong2()
	{
		return passerPlayersProlong2;
	}

	public void setPasserPlayersProlong2(List<Integer> passerPlayersProlong2)
	{
		this.passerPlayersProlong2 = passerPlayersProlong2;
	}

	public List<Integer> getScorerPlayersMinuteProlong1()
	{
		return scorerPlayersMinuteProlong1;
	}

	public void setScorerPlayersMinuteProlong1(List<Integer> scorerPlayersMinuteProlong1)
	{
		this.scorerPlayersMinuteProlong1 = scorerPlayersMinuteProlong1;
	}

	public List<Integer> getScorerPlayersMinuteProlong2()
	{
		return scorerPlayersMinuteProlong2;
	}

	public void setScorerPlayersMinuteProlong2(List<Integer> scorerPlayersMinuteProlong2)
	{
		this.scorerPlayersMinuteProlong2 = scorerPlayersMinuteProlong2;
	}

}
