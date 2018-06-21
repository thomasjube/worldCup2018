package com.tjube.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjube.dao.GameDAO;
import com.tjube.dao.PlayerStatsDAO;
import com.tjube.model.Game;
import com.tjube.model.Player;
import com.tjube.model.PlayerStats;
import com.tjube.model.Poule;
import com.tjube.model.Team;

@Service
@Transactional
public class GameServiceImpl
	implements GameService
{
	@Autowired
	private GameDAO gameDAO;

	@Autowired
	private PlayerStatsDAO playerStatsDAO;

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
	public List<Game> getGames(Team team)
	{
		return gameDAO.getGames(team);
	}

	@Override
	@Transactional
	public List<Game> getGames(LocalDateTime date)
	{
		return gameDAO.getGames(date);
	}

	@Override
	@Transactional
	public List<Game> getGames(Poule poule)
	{
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

	@Override
	public void updateGame(Game game, Player captainTeam1, Player captainTeam2)
	{
		gameDAO.updateGame(game, captainTeam1, captainTeam2);
	}

	public void setGameDAO(GameDAO gameDAO)
	{
		this.gameDAO = gameDAO;
	}

	@Override
	public Game updateGame(int id, int score1, int score2, boolean isProlong, int scoreProlong1, int scoreProlong2,
			boolean isPeno, int scorePeno1, int scorePeno2)
	{
		Game newGame = getGame(id);
		Team winner = new Team();
		newGame.setScore1(score1);
		newGame.setScore2(score2);
		newGame.setProlong(isProlong);
		newGame.setPenalti(isPeno);
		if (score1 < score2)
		{
			winner = newGame.getTeam2();
		}
		else if (score1 > score2)
		{
			winner = newGame.getTeam1();
		}
		else
		{
			if (isProlong)
			{
				newGame.setScore1_prolong(scoreProlong1);
				newGame.setScore2_prolong(scoreProlong2);
				if (scoreProlong1 > scoreProlong2)
				{
					winner = newGame.getTeam1();
				}
				else if (scoreProlong1 < scoreProlong2)
				{
					winner = newGame.getTeam2();
				}
				else
				{

					newGame.setScore1_penalti(scorePeno1);
					newGame.setScore2_penalti(scorePeno2);
					if (scorePeno1 > scorePeno2)
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
	public Game resetGame(Game game)
	{
		Game newGame = game;
		newGame.setScore1(null);
		newGame.setScore2(null);
		newGame.setProlong(false);
		newGame.setPlayerStats(new ArrayList<PlayerStats>());

		return updateGame(newGame);
	}

	@Override
	public void updateQuarts(Game game)
	{
		if (game.getId() == 49)
		{
			Game quart = getGame(57);
			quart.setTeam1(game.getWinner());
		}
		else if (game.getId() == 50)
		{
			Game quart = getGame(57);
			quart.setTeam2(game.getWinner());
		}
		else if (game.getId() == 51)
		{
			Game quart = getGame(58);
			quart.setTeam1(game.getWinner());
		}
		else if (game.getId() == 52)
		{
			Game quart = getGame(58);
			quart.setTeam2(game.getWinner());
		}
		else if (game.getId() == 53)
		{
			Game quart = getGame(59);
			quart.setTeam1(game.getWinner());
		}
		else if (game.getId() == 54)
		{
			Game quart = getGame(59);
			quart.setTeam2(game.getWinner());
		}
		else if (game.getId() == 55)
		{
			Game quart = getGame(60);
			quart.setTeam1(game.getWinner());
		}
		else if (game.getId() == 56)
		{
			Game quart = getGame(60);
			quart.setTeam2(game.getWinner());
		}
	}

	@Override
	public void updateDemis(Game game)
	{
		if (game.getId() == 57)
		{
			Game demi = getGame(61);
			demi.setTeam1(game.getWinner());
		}
		else if (game.getId() == 58)
		{
			Game demi = getGame(61);
			demi.setTeam2(game.getWinner());
		}
		else if (game.getId() == 59)
		{
			Game demi = getGame(62);
			demi.setTeam1(game.getWinner());
		}
		else if (game.getId() == 60)
		{
			Game demi = getGame(62);
			demi.setTeam2(game.getWinner());
		}
	}

	@Override
	public void updateFinale(Game game)
	{
		if (game.getId() == 57)
		{
			Game petiteFinale = getGame(63);
			Game finale = getGame(64);
			petiteFinale.setTeam1(game.getLooser());
			finale.setTeam1(game.getWinner());
		}
		else if (game.getId() == 58)
		{
			Game petiteFinale = getGame(63);
			Game finale = getGame(64);
			petiteFinale.setTeam2(game.getLooser());
			finale.setTeam2(game.getWinner());
		}
	}

	@Override
	public Map<Integer, Collection<Player>> getTitulars(Game game)
	{
		return playerStatsDAO.getTitulars(game);
	}

	@Override
	public Map<Integer, Collection<Integer>> getTitularsForEditCompo(Game game)
	{
		return playerStatsDAO.getTitularsForEditCompo(game);
	}

	@Override
	public Map<Integer, PlayerStats> getSubstitutes(Game game)
	{
		return playerStatsDAO.getSubstitutes(game);
	}
}
