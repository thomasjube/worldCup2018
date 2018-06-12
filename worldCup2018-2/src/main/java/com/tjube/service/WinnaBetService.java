package com.tjube.service;

import java.util.Collection;
import java.util.List;

import com.tjube.model.BetClassementLine;
import com.tjube.model.BetName;
import com.tjube.model.Game;
import com.tjube.model.WinnaBet;

public interface WinnaBetService
{
	public void add(WinnaBet winnaBet);

	public List<WinnaBet> getAllWinnaBets();

	public void deleteWinnaBet(Integer gameId);

	public void deleteWinnaBets(Game game);

	public WinnaBet getWinnaBet(int gameid);

	public WinnaBet updateWinnaBet(WinnaBet game);

	public List<WinnaBet> getWinnaBets(Game game);

	public Collection<WinnaBet> getWinnaBets(BetName betName);

	WinnaBet updateWinnaBet(WinnaBet winnaBet, Integer score1, Integer score2);

	public void verifyBets(Game game);

	public List<BetClassementLine> getBetClassement();
}
