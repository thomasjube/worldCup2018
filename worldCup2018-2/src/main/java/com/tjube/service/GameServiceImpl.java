package com.tjube.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjube.dao.GameDAO;
import com.tjube.model.Game;
import com.tjube.model.Poule;
import com.tjube.model.Team;

@Service
@Transactional
public class GameServiceImpl
	implements GameService
{
	@Autowired
	private GameDAO gameDAO;

	@Override
	@Transactional
	public void addGame(Game game)
	{
		gameDAO.addGame(game);
	}

	@Override
	@Transactional
	public List<Game> getAllGames()
	{
		return gameDAO.getAllGames();
	}
	
	@Override
	@Transactional
	public List<Game> getGames(Team team) {
		return gameDAO.getGames(team);
	}
	
	@Override
	@Transactional
	public List<Game> getGames(Poule poule) {
		return gameDAO.getGames(poule);
	}
	
	@Override
	@Transactional
	public int getTotalGames()
	{
		return gameDAO.getAllGames().size();
	}

	@Override
	@Transactional
	public void deleteGame(Integer gameId)
	{
		gameDAO.deleteGame(gameId);
	}

	@Override
	public Game getGame(int empid)
	{
		return gameDAO.getGame(empid);
	}
	
	@Override
	public Game getNextGame()
	{
		return gameDAO.getNextGame();
	}
	
	@Override
	public Game getNextGame(Poule poule)
	{
		return gameDAO.getNextGame(poule);
	}
	
	@Override
	public Game getLastGame()
	{
		return gameDAO.getLastGame();
	}

	@Override
	public Game updateGame(Game game)
	{
		return gameDAO.updateGame(game);
	}

	public void setGameDAO(GameDAO gameDAO)
	{
		this.gameDAO = gameDAO;
	}

	public Game updateGame(int id, int score1, int score2, boolean isProlong, int scoreProlong1, int scoreProlong2, boolean isPeno, int scorePeno1, int scorePeno2) {
		Game newGame = getGame(id); 
		Team winner = new Team();
		newGame.setScore1(score1);
		newGame.setScore2(score2);
		newGame.setProlong(isProlong);
		if(score1 < score2)
		{
			winner = newGame.getTeam2();
		}
		else if(score1 > score2)
		{
			winner = newGame.getTeam1();
		}
		else
		{
			if(isProlong)
			{
				newGame.setScore1_prolong(scoreProlong1);
				newGame.setScore2_prolong(scoreProlong2);
				if(scoreProlong1 > scoreProlong2)
				{
					winner = newGame.getTeam1();
				}
				else if(scoreProlong1 < scoreProlong2)
				{
					winner = newGame.getTeam2();
				}
				else
				{

					newGame.setScore1_penalti(scorePeno1);
					newGame.setScore2_penalti(scorePeno2);
					if(scorePeno1 > scorePeno2)
					{
						winner = newGame.getTeam1();
					}
					else
					{
						winner = newGame.getTeam2();
					}
				}
					
			}
		}
		return updateGame(newGame);
	}

	@Override
	public Game resetGame(Game game) {
		Game newGame = game; 
		newGame.setScore1(null);
		newGame.setScore2(null);
		newGame.setProlong(false);
		
		return updateGame(newGame);
	}

	
}
