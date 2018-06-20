package com.tjube.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.tjube.model.Game;
import com.tjube.model.Poule;
import com.tjube.model.Team;

public interface GameDAO
{
	public void addGame(Game game);

	public List<Game> getAllGames();

	public void deleteGame(Integer gameId);

	public Game updateGame(Game game);

	public Game getGame(int gameId);

	public Game getNextGame();

	public Game getLastGame();

	public List<Game> getGames(Team team);

	public Game getNextGame(Poule poule);

	public List<Game> getGames(Poule poule);

	public List<Game> getGames(LocalDateTime date);
}
