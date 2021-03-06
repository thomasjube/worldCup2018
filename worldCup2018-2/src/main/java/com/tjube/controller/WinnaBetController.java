package com.tjube.controller;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tjube.model.BetClassementLine;
import com.tjube.model.BetName;
import com.tjube.model.Game;
import com.tjube.model.WinnaBet;
import com.tjube.service.GameService;
import com.tjube.service.WinnaBetService;

@Controller
@RequestMapping("/winabet")
@Configuration
@ComponentScan("com.tjube.service")
public class WinnaBetController
{
	private static final Logger logger = Logger.getLogger(WinnaBetController.class);

	static class WinnaBetsComparator
		implements Comparator<WinnaBet>
	{
		@Override
		public int compare(WinnaBet c1, WinnaBet c2)
		{
			if (c2.getGoodScore().compareTo(c1.getGoodScore()) == 0)
			{
				return c2.getGoodResult().compareTo(c1.getGoodResult());
			}
			else
				return c2.getGoodScore().compareTo(c1.getGoodScore());
		}
	}

	public WinnaBetController()
	{
		System.out.println("WinaBetController()");
	}

	@Autowired
	private GameService gameService;

	@Autowired
	private WinnaBetService winnaBetService;

	@RequestMapping(value = "")
	public ModelAndView homeWinabet(ModelAndView model)
	{
		return new ModelAndView("redirect:winabet/");
	}

	@RequestMapping(value = "/")
	public ModelAndView winabetShow(ModelAndView model)
	{
		Collection<Game> games = gameService
				.getGames(winnaBetService.getMode().equalsIgnoreCase("demo") ? LocalDateTime.now()
						: LocalDateTime.now().plusHours(2));

		List<BetClassementLine> lines = winnaBetService.getBetClassement(games.size());

		model.addObject("lines", lines);

		model.setViewName("winnabetShow");
		return model;
	}

	@RequestMapping(value = "/refresh")
	public ModelAndView winabetRefresh(ModelAndView model)
	{
		Collection<Game> games = gameService
				.getGames(winnaBetService.getMode().equalsIgnoreCase("demo") ? LocalDateTime.now()
						: LocalDateTime.now().plusHours(2));

		for (Game game : games)
		{
			winnaBetService.verifyBets(game);
		}

		return new ModelAndView("redirect:/winabet/");
	}

	@RequestMapping(value = "/game")
	public ModelAndView winabetShowGame(HttpServletRequest request, ModelAndView model)
	{
		int gameId = 1;
		if (request.getParameter("id") != null)
			gameId = Integer.parseInt(request.getParameter("id"));

		Game game = gameService.getGame(gameId);
		model.addObject("game", game);

		List<Game> games = gameService.getAllGames();
		model.addObject("games", games);

		List<WinnaBet> winnabets = winnaBetService.getWinnaBets(game);
		Collections.sort(winnabets, new WinnaBetsComparator());

		model.addObject("winnabets", winnabets);

		model.setViewName("winnabetShowGame");
		return model;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView winabetEditGet(HttpServletRequest request, ModelAndView model)
	{
		int gameId = 1;
		if (request.getParameter("id") != null)
			gameId = Integer.parseInt(request.getParameter("id"));

		Game game = gameService.getGame(gameId);
		model.addObject("game", game);

		EnumSet<BetName> betNames = EnumSet.allOf(BetName.class);
		model.addObject("betNames", betNames);

		List<Game> games = gameService.getAllGames();
		model.addObject("games", games);

		List<WinnaBet> winnabets = winnaBetService.getWinnaBets(game);
		model.addObject("winnabets", winnabets);

		WinnabetForm form = new WinnabetForm(game);
		model.addObject("form", form);

		model.setViewName("winnabetEdit");
		return model;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView winabetEditPost(@ModelAttribute("form") WinnabetForm form)
	{
		Game game = gameService.getGame(form.getGameId());

		winnaBetService.deleteWinnaBets(game);

		for (int i = 0; i < form.getBetNames().size(); i++)
		{
			Boolean toRemove = form.getRemoveWinabets().get(i);
			if (toRemove == null || !toRemove)
			{
				BetName betName = form.getBetNames().get(i);
				Integer resultTeam1 = form.getResultsTeam1().get(i);
				Integer resultTeam2 = form.getResultsTeam2().get(i);

				winnaBetService.add(new WinnaBet(betName, game, resultTeam1, resultTeam2));
			}
		}

		return new ModelAndView("redirect:/winabet/game?id=" + game.getId());
	}
}
