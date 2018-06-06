package com.tjube.dao;

import java.util.List;

import com.tjube.model.Game;

public interface GameDAO
{
	public void addGame(Game game);

	public List<Game> getAllGames();

	public void deleteGame(Integer gameId);

	public Game updateGame(Game game);

	public Game getGame(int gameId);
}
