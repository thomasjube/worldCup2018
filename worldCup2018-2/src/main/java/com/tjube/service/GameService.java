package com.tjube.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.tjube.model.Game;
import com.tjube.model.Player;
import com.tjube.model.PlayerStats;
import com.tjube.model.Poule;
import com.tjube.model.Team;

public interface GameService
{
	public void addGame(Game game);

	public List<Game> getAllGames();

	public void deleteGame(Integer gameId);

	public Game getGame(int gameid);

	public Game updateGame(Game game);

	public void updateGame(Game game, Player captainTeam1, Player captainTeam2);

	public Game getNextGame();

	public Game getLastGame();

	int getTotalGames();

	List<Game> getGames(LocalDateTime date);

	public List<Game> getGames(Team team);

	public Game getNextGame(Poule poule);

	public Collection<Game> getGames(Poule poule);

	Game updateGame(int id, int score1, int score2, boolean isProlong, int scoreProlong1, int scoreProlong2,
			boolean isPeno, int scorePeno1, int scorePeno2);

	public Game resetGame(Game game);

	public void updateQuarts(Game game, boolean reset);

	public void updateDemis(Game game, boolean reset);

	public void updateFinale(Game game, boolean reset);

	public Map<Integer, Collection<Player>> getTitulars(Game game);

	public Map<Integer, Collection<Integer>> getTitularsForEditCompo(Game game);

	public Map<Integer, PlayerStats> getSubstitutes(Game game);

}
