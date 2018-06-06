package com.tjube.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjube.dao.TeamDAO;
import com.tjube.model.Game;
import com.tjube.model.Poule;
import com.tjube.model.Team;

@Service
@Transactional
public class TeamServiceImpl
	implements TeamService
{
	@Autowired
	private TeamDAO teamDAO;

	@Override
	@Transactional
	public void addTeam(Team team)
	{
		teamDAO.addTeam(team);
	}

	@Override
	@Transactional
	public List<Team> getAllTeams()
	{
		return teamDAO.getAllTeams();
	}
	
	@Override
	@Transactional
	public Team getTeam(String string) {
		return teamDAO.getTeam(string);
	}
	
	@Override
	@Transactional
	public Collection<Team> getOrderTeams(Poule poule) {
		return teamDAO.getOrderTeams(poule);
	}
	
	@Override
	@Transactional
	public int getTotalTeams() {
		return teamDAO.getAllTeams().size();
	}

	@Override
	@Transactional
	public void deleteTeam(Integer teamId)
	{
		teamDAO.deleteTeam(teamId);
	}

	@Override
	public Team getTeam(int empid)
	{
		return teamDAO.getTeam(empid);
	}

	@Override
	public Team updateTeam(Team team)
	{
		return teamDAO.updateTeam(team);
	}

	public void setTeamDAO(TeamDAO teamDAO)
	{
		this.teamDAO = teamDAO;
	}


	@Override
	@Transactional
	public void updateTeams(Game game) {
		Team team1 = game.getTeam1();
		Team team2 = game.getTeam2();
		
		if(game.getScore1() == game.getScore2() && game.getGameInPoule())
		{
			
			team1.setGameDraw(team1.getGameDraw() + 1);
			team2.setGameDraw(team2.getGameDraw() + 1);
			team1.setPoint(team1.getPoint() + 1);
			team2.setPoint(team2.getPoint() + 1);
			
			team1.setBut_mis(team1.getBut_mis() + game.getScore1());
			team1.setBut_pris(team1.getBut_pris() +game.getScore2());
			team2.setBut_mis(team2.getBut_mis() +game.getScore2());
			team2.setBut_pris(team2.getBut_pris() +game.getScore1());
			
			
			//nul en poule
		}
		else if(game.getScore1() < game.getScore2() && game.getGameInPoule())
		{
			//victoire eq2 en poule
			team1.setGameLost(team1.getGameLost() + 1);
			team2.setGameWin(team2.getGameWin() + 1);
			team2.setPoint(team2.getPoint() + 3);
			
			team1.setBut_mis(team1.getBut_mis() + game.getScore1());
			team1.setBut_pris(team1.getBut_pris() +game.getScore2());
			team2.setBut_mis(team2.getBut_mis() +game.getScore2());
			team2.setBut_pris(team2.getBut_pris() +game.getScore1());
		}
		else if(game.getScore1() > game.getScore2() && game.getGameInPoule())
		{
			//victoire eq1 en poule
			team2.setGameLost(team2.getGameLost() + 1);
			team1.setGameWin(team1.getGameWin() + 1);
			team1.setPoint(team1.getPoint() + 3);
			
			team1.setBut_mis(team1.getBut_mis() + game.getScore1());
			team1.setBut_pris(team1.getBut_pris() +game.getScore2());
			team2.setBut_mis(team2.getBut_mis() +game.getScore2());
			team2.setBut_pris(team2.getBut_pris() +game.getScore1());
		}
		team1.setGamePlayed(team1.getGamePlayed() + 1);
		team2.setGamePlayed(team2.getGamePlayed() + 1);
		team1.setDiff(team1.getBut_mis() - team1.getBut_pris());
		team2.setDiff(team2.getBut_mis() - team2.getBut_pris());
		updateTeam(team1);
		updateTeam(team2);
	}
	
	@Override
	@Transactional
	public void updateTeamsForReset(Game game) {
		Team team1 = game.getTeam1();
		Team team2 = game.getTeam2();
		
		if(game.getScore1() == game.getScore2() && game.getGameInPoule())
		{
			
			team1.setGameDraw(team1.getGameDraw() - 1);
			team2.setGameDraw(team2.getGameDraw() - 1);
			team1.setPoint(team1.getPoint() - 1);
			team2.setPoint(team2.getPoint() - 1);
			
			team1.setBut_mis(team1.getBut_mis() - game.getScore1());
			team1.setBut_pris(team1.getBut_pris() -game.getScore2());
			team2.setBut_mis(team2.getBut_mis() -game.getScore2());
			team2.setBut_pris(team2.getBut_pris() -game.getScore1());
			
			
			//nul en poule
		}
		else if(game.getScore1() < game.getScore2() && game.getGameInPoule())
		{
			//victoire eq2 en poule
			team1.setGameLost(team1.getGameLost() - 1);
			team2.setGameWin(team2.getGameWin() - 1);
			team2.setPoint(team2.getPoint() - 3);
			
			team1.setBut_mis(team1.getBut_mis() - game.getScore1());
			team1.setBut_pris(team1.getBut_pris() -game.getScore2());
			team2.setBut_mis(team2.getBut_mis() -game.getScore2());
			team2.setBut_pris(team2.getBut_pris() -game.getScore1());
		}
		else if(game.getScore1() > game.getScore2() && game.getGameInPoule())
		{
			//victoire eq1 en poule
			team2.setGameLost(team2.getGameLost() - 1);
			team1.setGameWin(team1.getGameWin() - 1);
			team1.setPoint(team1.getPoint() - 3);
			
			team1.setBut_mis(team1.getBut_mis() - game.getScore1());
			team1.setBut_pris(team1.getBut_pris() -game.getScore2());
			team2.setBut_mis(team2.getBut_mis() -game.getScore2());
			team2.setBut_pris(team2.getBut_pris() -game.getScore1());
		}
		team1.setGamePlayed(team1.getGamePlayed() - 1);
		team2.setGamePlayed(team2.getGamePlayed() - 1);
		team1.setDiff(team1.getBut_mis() + team1.getBut_pris());
		team2.setDiff(team2.getBut_mis() + team2.getBut_pris());
		updateTeam(team1);
		updateTeam(team2);
	}
	
	@Override
	@Transactional
	public void updateTeamsPositions(Poule poule)
	{
		Collection<Team> teams = getTeamsByPoint(poule);
		int i=1;
		for(Team team : teams)
		{
			team.setPosition_poule(i);
			updateTeam(team);
			i++;
		}
	}
	
	@Override
	public Collection<Team> getTeamsByPoint(Poule poule) {
		return teamDAO.getTeamsByPoint(poule);
	}
}
