package com.tjube.controller;

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

import com.tjube.model.WinnaBet;
import com.tjube.service.GameService;
import com.tjube.service.PlayerService;
import com.tjube.service.PlayerStatsService;
import com.tjube.service.PouleService;
import com.tjube.service.TeamService;
import com.tjube.service.WinnaBetService;

@Controller
@RequestMapping("/winabet")
@Configuration
@ComponentScan("com.tjube.service")
public class WinnaBetController
{
	private static final Logger logger = Logger.getLogger(WinnaBetController.class);

	public WinnaBetController()
	{
		System.out.println("WinaBetController()");
	}

	@Autowired
	private GameService gameService;

	@Autowired
	private WinnaBetService winnaBetService;

	@Autowired
	private PouleService pouleService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private PlayerStatsService playerStatsService;

	@RequestMapping(value = "")
	public ModelAndView homeWinabet(ModelAndView model)
	{
		return new ModelAndView("redirect:winnaBet/");
	}

	@RequestMapping(value = "/")
	public ModelAndView winabetShow(ModelAndView model)
	{
		model.setViewName("winnabetShow");
		return model;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView newWinnaBet(HttpServletRequest request, ModelAndView model)
	{
		int gameId = 1;
		if (request.getParameter("id") == null)
			gameId = Integer.parseInt(request.getParameter("id"));

		WinnaBet winnaBet = new WinnaBet();
		model.addObject("winnaBet", winnaBet);
		model.setViewName("winnabetEdit");
		return model;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView saveWinnaBet(@ModelAttribute WinnaBet winnaBet)
	{
		if (winnaBet.getId() == 0)
		{ // if employee id is 0 then creating the
			// employee other updating the employee
			winnaBetService.add(winnaBet);
		}
		else
		{
			winnaBetService.updateWinnaBet(winnaBet);
		}

		return new ModelAndView("redirect:/redirect:winnaBet/");
	}
}
