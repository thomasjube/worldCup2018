package com.tjube.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tjube.model.Game;
import com.tjube.service.GameService;

@Controller
public class GameController
{
	private static final Logger logger = Logger.getLogger(GameController.class);

	public GameController()
	{
		System.out.println("GameController()");
	}

	@Autowired
	private GameService gameService;

	@RequestMapping(value = "/")
	public ModelAndView listGame(ModelAndView model)
		throws IOException
	{
		List<Game> listGame = gameService.getAllGames();
		model.addObject("listGame", listGame);
		model.setViewName("home");
		return model;
	}

	@RequestMapping(value = "/newGame", method = RequestMethod.GET)
	public ModelAndView newGame(ModelAndView model)
	{
		Game game = new Game();
		model.addObject("game", game);
		model.setViewName("GameForm");
		return model;
	}

	@RequestMapping(value = "/saveGame", method = RequestMethod.POST)
	public ModelAndView saveGame(@ModelAttribute Game game)
	{
		if (game.getId() == 0)
		{ // if employee id is 0 then creating the
			// employee other updating the employee
			gameService.addGame(game);
		}
		else
		{
			gameService.updateGame(game);
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/deleteGame", method = RequestMethod.GET)
	public ModelAndView deleteGame(HttpServletRequest request)
	{
		int gameId = Integer.parseInt(request.getParameter("id"));
		gameService.deleteGame(gameId);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/editGame", method = RequestMethod.GET)
	public ModelAndView editGame(HttpServletRequest request)
	{
		int gameId = Integer.parseInt(request.getParameter("id"));
		Game game = gameService.getGame(gameId);
		ModelAndView model = new ModelAndView("GameForm");
		model.addObject("game", game);

		return model;
	}
}
