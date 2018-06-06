package com.tjube.service;

import java.util.List;

import com.tjube.model.Game;

public interface GameService
{
	public void addGame(Game game);

	public List<Game> getAllGames();

	public void deleteGame(Integer gameId);

	public Game getGame(int gameid);

	public Game updateGame(Game game);
}
