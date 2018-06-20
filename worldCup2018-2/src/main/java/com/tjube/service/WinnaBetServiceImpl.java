package com.tjube.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjube.dao.WinnaBetDAO;
import com.tjube.model.BetClassementLine;
import com.tjube.model.BetName;
import com.tjube.model.Game;
import com.tjube.model.WinnaBet;

@Service
@Transactional
public class WinnaBetServiceImpl
	implements WinnaBetService
{
	@Autowired
	private WinnaBetDAO winnaBetDAO;

	private String mode = "";

	@Override
	public void add(WinnaBet winnaBet)
	{
		winnaBetDAO.add(winnaBet);
		winnaBet.getGame().addWinabet(winnaBet);
	}

	@Override
	public List<WinnaBet> getAllWinnaBets()
	{
		return winnaBetDAO.getAllWinnaBets();
	}

	@Override
	public void deleteWinnaBet(Integer winnaBetId)
	{
		winnaBetDAO.deleteWinnaBet(winnaBetId);
	}

	@Override
	public void deleteWinnaBets(Game game)
	{
		winnaBetDAO.deleteWinnaBets(game);
	}

	@Override
	public WinnaBet getWinnaBet(int winnaBetId)
	{
		return winnaBetDAO.getWinnaBet(winnaBetId);
	}

	@Override
	public WinnaBet updateWinnaBet(WinnaBet winnaBet)
	{
		return winnaBetDAO.updateWinnaBet(winnaBet);
	}

	@Override
	public List<WinnaBet> getWinnaBets(Game game)
	{
		return winnaBetDAO.getWinnaBets(game);
	}

	@Override
	public Collection<WinnaBet> getWinnaBets(BetName betName)
	{
		return winnaBetDAO.getWinnaBets(betName);
	}

	@Override
	public WinnaBet updateWinnaBet(WinnaBet winnaBet, Integer score1, Integer score2)
	{
		return winnaBetDAO.updateWinnaBet(winnaBet, score1, score2);
	}

	@Override
	public void verifyBets(Game game)
	{
		winnaBetDAO.verifyBets(game);
	}

	@Override
	public List<BetClassementLine> getBetClassement(int totalGamesPlayed)
	{
		return winnaBetDAO.getBetClassement(totalGamesPlayed, mode);
	}

	@Override
	public void resetWinnaBet(WinnaBet winnaBet)
	{
		winnaBetDAO.resetWinnaBet(winnaBet);
	}

	@Override
	public String getMode()
	{
		return mode;
	}

	public void setMode(String mode)
	{
		this.mode = mode;
	}
}
