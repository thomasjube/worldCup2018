package com.tjube.controller;

import java.io.IOException;
import java.util.Collection;
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

import com.tjube.model.Game;
import com.tjube.model.Poule;
import com.tjube.model.Team;
import com.tjube.service.GameService;
import com.tjube.service.PouleService;
import com.tjube.service.TeamService;

@Controller
@RequestMapping("/poule")
@Configuration
@ComponentScan("com.tjube.service")
public class PouleController
{
	private static final Logger logger = Logger.getLogger(PouleController.class);

	public PouleController()
	{
		System.out.println("PouleController()");
	}

	@Autowired
	private PouleService pouleService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private GameService gameService;

	@RequestMapping(value = "")
	public ModelAndView homePoule(ModelAndView model)
		throws IOException
	{
		return new ModelAndView("redirect:poule/");
	}

	@RequestMapping(value = "/")
	public ModelAndView listPoule(ModelAndView model)
		throws IOException
	{
		List<Poule> listPoule = pouleService.getAllPoules();
		return new ModelAndView("redirect:/poule/showPoule?id="+listPoule.get(0).getId());
	}
	
	@RequestMapping(value = "/showPoule", method = RequestMethod.GET)
	public ModelAndView showPoule(HttpServletRequest request,ModelAndView model)
	{
		int pouleId = Integer.parseInt(request.getParameter("id"));
		Poule poule = pouleService.getPoule(pouleId);
		model.addObject("poule", poule);
		
		Collection<Team> orderTeams = teamService.getOrderTeams(poule);
		model.addObject("orderTeams", orderTeams);
		
		Game nextGame = gameService.getNextGame(poule);
		model.addObject("nextGame", nextGame);
		
		Collection<Game> games = gameService.getGames(poule);
		model.addObject("games", games);
		
		List<Poule> poules = pouleService.getAllPoules();
		model.addObject("poules", poules);
		
		model.setViewName("pouleShow");
		return model;
	}
	
	@RequestMapping(value = "/lastPoule", method = RequestMethod.GET)
	public ModelAndView lastPoule(HttpServletRequest request,ModelAndView model)
	{
		int pouleId = Integer.parseInt(request.getParameter("id"));
		Poule poule = pouleService.getPoule(pouleId);
		
		if(poule == null)
			return new ModelAndView("redirect:/poule/");

		Poule lastPoule = pouleService.getLastPoule(poule);
		return new ModelAndView("redirect:/poule/showPoule?id="+lastPoule.getId());
		
	}
	
	@RequestMapping(value = "/nextPoule", method = RequestMethod.GET)
	public ModelAndView nextPoule(HttpServletRequest request,ModelAndView model)
	{
		int pouleId = Integer.parseInt(request.getParameter("id"));
		Poule poule = pouleService.getPoule(pouleId);
		if(poule == null)
			return new ModelAndView("redirect:/poule/");
		
		Poule nextPoule = pouleService.getNextPoule(poule);
		return new ModelAndView("redirect:/poule/showPoule?id="+nextPoule.getId());
	}

	@RequestMapping(value = "/newPoule", method = RequestMethod.GET)
	public ModelAndView newPoule(ModelAndView model)
	{
		Poule poule = new Poule();
		model.addObject("poule", poule);
		model.setViewName("PouleForm");
		return model;
	}

	@RequestMapping(value = "/savePoule", method = RequestMethod.POST)
	public ModelAndView savePoule(@ModelAttribute Poule poule)
	{
		if (poule.getId() == 0)
		{ // if employee id is 0 then creating the
				// employee other updating the employee
			pouleService.addPoule(poule);
		}
		else
		{
			pouleService.updatePoule(poule);
		}
		return new ModelAndView("redirect:/poule/");
	}

	@RequestMapping(value = "/deletePoule", method = RequestMethod.GET)
	public ModelAndView deletePoule(HttpServletRequest request)
	{
		int pouleId = Integer.parseInt(request.getParameter("id"));
		pouleService.deletePoule(pouleId);
		return new ModelAndView("redirect:/poule/");
	}

	@RequestMapping(value = "/editPoule", method = RequestMethod.GET)
	public ModelAndView editPoule(HttpServletRequest request)
	{
		int pouleId = Integer.parseInt(request.getParameter("id"));
		Poule poule = pouleService.getPoule(pouleId);
		ModelAndView model = new ModelAndView("PouleForm");
		model.addObject("poule", poule);

		return model;
	}
}
