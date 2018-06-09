package com.tjube.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tjube.model.StatsWorldCup;
import com.tjube.service.PlayerService;
import com.tjube.service.TeamService;

@Controller
@RequestMapping("/stats")
@Configuration
@ComponentScan("com.tjube.service")
public class StatsController
{
	private static final Logger LOGGER = Logger.getLogger(StatsController.class);

	public StatsController()
	{
		// default constructor
	}

	@Autowired
	private TeamService teamService;

	@Autowired
	private PlayerService playerService;

	@RequestMapping(value = "")
	public ModelAndView homeStats(ModelAndView model)
	{
		return new ModelAndView("redirect:stats/");
	}

	@RequestMapping(value = "/")
	public ModelAndView showStats(ModelAndView model)
	{
		List<StatsWorldCup> goalsStats = teamService.retrieveWorldCupStatsGoal(10);
		model.addObject("goalsStats", goalsStats);

		List<StatsWorldCup> passesStats = teamService.retrieveWorldCupStatsPasses(10);
		model.addObject("passesStats", passesStats);

		List<StatsWorldCup> yellowCardsStats = teamService.retrieveWorldCupStatsYellowCard(10);
		model.addObject("yellowCardsStats", yellowCardsStats);

		List<StatsWorldCup> redCardsStats = teamService.retrieveWorldCupStatsRedCard(10);
		model.addObject("redCardsStats", redCardsStats);

		List<StatsWorldCup> bestAttackTeams = teamService.retrieveWorldCupStatsBestAttack(10);
		model.addObject("bestAttackTeams", bestAttackTeams);

		List<StatsWorldCup> worstDefenseTeams = teamService.retrieveWorldCupStatsWorstDefense(10);
		model.addObject("worstDefenseTeams", worstDefenseTeams);

		model.setViewName("statsShow");

		return model;
	}
}
