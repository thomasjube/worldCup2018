package com.tjube.controller;

import java.io.IOException;
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

import com.tjube.model.Player;
import com.tjube.service.PlayerService;

@Controller
@RequestMapping("/player")
@Configuration
@ComponentScan("com.tjube.service")
public class PlayerController
{
	private static final Logger logger = Logger.getLogger(PlayerController.class);

	public PlayerController()
	{
		System.out.println("PlayerController()");
	}

	@Autowired
	private PlayerService playerService;

	@RequestMapping(value = "")
	public ModelAndView homePlayer(ModelAndView model)
		throws IOException
	{
		return new ModelAndView("redirect:player/");
	}

	@RequestMapping(value = "/")
	public ModelAndView listPlayer(ModelAndView model)
		throws IOException
	{
		List<Player> listPlayer = playerService.getAllPlayers();
		model.addObject("listPlayer", listPlayer);
		model.setViewName("home");
		return model;
	}

	@RequestMapping(value = "/newPlayer", method = RequestMethod.GET)
	public ModelAndView newPlayer(ModelAndView model)
	{
		Player player = new Player();
		model.addObject("player", player);
		model.setViewName("PlayerForm");
		return model;
	}

	@RequestMapping(value = "/savePlayer", method = RequestMethod.POST)
	public ModelAndView savePlayer(@ModelAttribute Player player)
	{
		if (player.getId() == 0)
		{ // if employee id is 0 then creating the
				// employee other updating the employee
			playerService.addPlayer(player);
		}
		else
		{
			playerService.updatePlayer(player);
		}
		return new ModelAndView("redirect:/player/");
	}

	@RequestMapping(value = "/deletePlayer", method = RequestMethod.GET)
	public ModelAndView deletePlayer(HttpServletRequest request)
	{
		int playerId = Integer.parseInt(request.getParameter("id"));
		playerService.deletePlayer(playerId);
		return new ModelAndView("redirect:/player/");
	}

	@RequestMapping(value = "/editGame", method = RequestMethod.GET)
	public ModelAndView editGame(HttpServletRequest request)
	{
		int playerId = Integer.parseInt(request.getParameter("id"));
		Player player = playerService.getPlayer(playerId);
		ModelAndView model = new ModelAndView("PlayerForm");
		model.addObject("player", player);

		return model;
	}
}
