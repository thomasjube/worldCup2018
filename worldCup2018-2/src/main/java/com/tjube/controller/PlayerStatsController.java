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

import com.tjube.model.PlayerStats;
import com.tjube.service.PlayerStatsService;

@Controller
@RequestMapping("/goal")
@Configuration
@ComponentScan("com.tjube.service")
public class PlayerStatsController
{
	private static final Logger logger = Logger.getLogger(PlayerStatsController.class);

	public PlayerStatsController()
	{
		System.out.println("PlayerStatsController()");
	}

	@Autowired
	private PlayerStatsService playerStatsService;

	@RequestMapping(value = "")
	public ModelAndView homeGoal(ModelAndView model)
		throws IOException
	{
		return new ModelAndView("redirect:goal/");
	}

	@RequestMapping(value = "/")
	public ModelAndView listGoal(ModelAndView model)
		throws IOException
	{
		List<PlayerStats> listPlayerStats = playerStatsService.getAllPlayerStats();
		model.addObject("listPlayerStats", listPlayerStats);
		model.setViewName("home");
		return model;
	}

	@RequestMapping(value = "/newPlayerStats", method = RequestMethod.GET)
	public ModelAndView newGoal(ModelAndView model)
	{
		PlayerStats playerStats = new PlayerStats();
		model.addObject("playerStats", playerStats);
		model.setViewName("PlayerStatsForm");
		return model;
	}

	@RequestMapping(value = "/savePlayerStats", method = RequestMethod.POST)
	public ModelAndView saveGoal(@ModelAttribute PlayerStats playerStats)
	{
		if (playerStats.getId() == 0)
		{ // if employee id is 0 then creating the
				// employee other updating the employee
			playerStatsService.addPlayerStats(playerStats);
		}
		else
		{
			playerStatsService.updatePlayerStats(playerStats);
		}
		return new ModelAndView("redirect:/playerStats/");
	}

	@RequestMapping(value = "/deletePlayerStats", method = RequestMethod.GET)
	public ModelAndView deleteGoal(HttpServletRequest request)
	{
		int gameId = Integer.parseInt(request.getParameter("id"));
		playerStatsService.deletePlayerStats(gameId);
		return new ModelAndView("redirect:/playerStats/");
	}

	@RequestMapping(value = "/editPlayerStats", method = RequestMethod.GET)
	public ModelAndView editGame(HttpServletRequest request)
	{
		int playerStatsId = Integer.parseInt(request.getParameter("id"));
		PlayerStats playerStats = playerStatsService.getPlayerStats(playerStatsId);
		ModelAndView model = new ModelAndView("PlayerStatsForm");
		model.addObject("playerStats", playerStats);

		return model;
	}
}
