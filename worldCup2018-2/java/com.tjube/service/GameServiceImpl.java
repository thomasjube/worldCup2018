package com.tjube.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tjube.dao.GameDAO;
import com.tjube.model.Game;

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
	public Game updateGame(Game game)
	{
		return gameDAO.updateGame(game);
	}

	public void setGameDAO(GameDAO gameDAO)
	{
		this.gameDAO = gameDAO;
	}

}
