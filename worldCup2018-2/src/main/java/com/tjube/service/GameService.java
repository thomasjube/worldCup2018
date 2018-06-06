package com.tjube.service;

import java.util.Collection;
import java.util.List;

import com.tjube.model.Game;
import com.tjube.model.Poule;
import com.tjube.model.Team;

public interface GameService
{
	public void addGame(Game game);

	public List<Game> getAllGames();

	public void deleteGame(Integer gameId);

	public Game getGame(int gameid);

	public Game updateGame(Game game);
	
	public Game getNextGame();
	
	public Game getLastGame();

	int getTotalGames();

	public List<Game> getGames(Team team);

	public Game getNextGame(Poule poule);

	public Collection<Game> getGames(Poule poule);
	
	Game updateGame(int id, int score1, int score2, boolean isProlong, int scoreProlong1, int scoreProlong2, boolean isPeno, int scorePeno1, int scorePeno2);

	public Game resetGame(Game game);
	
}
