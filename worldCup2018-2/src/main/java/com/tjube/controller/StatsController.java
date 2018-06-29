package com.tjube.controller;

import java.util.Collections;
import java.util.Comparator;
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

	static class StatWorldCupGoalsComparator
		implements Comparator<StatsWorldCup>
	{
		@Override
		public int compare(StatsWorldCup s1, StatsWorldCup s2)
		{
			if (s1.getGoals().compareTo(s2.getGoals()) == 0)
			{
				if (s1.getRatio().compareTo(s2.getRatio()) == 0)
					return s1.getPlayer().getName().compareTo(s2.getPlayer().getName());
				else
				{
					if (s1.getRatio().compareTo(s2.getRatio()) > 0)
						return -1;
					else
						return 1;
				}
			}
			else
			{
				if (s1.getGoals().compareTo(s2.getGoals()) > 0)
					return -1;
				else
					return 1;
			}
		}
	}

	static class StatWorldCupPassesComparator
		implements Comparator<StatsWorldCup>
	{
		@Override
		public int compare(StatsWorldCup s1, StatsWorldCup s2)
		{
			if (s1.getPasses().compareTo(s2.getPasses()) == 0)
			{
				if (s1.getRatio().compareTo(s2.getRatio()) == 0)
					return s1.getPlayer().getName().compareTo(s2.getPlayer().getName());
				else
				{
					if (s1.getRatio().compareTo(s2.getRatio()) > 0)
						return -1;
					else
						return 1;
				}
			}
			else
			{
				if (s1.getGoals().compareTo(s2.getGoals()) > 0)
					return -1;
				else
					return 1;
			}
		}
	}

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
		Collections.sort(goalsStats, new StatWorldCupGoalsComparator());
		model.addObject("goalsStats", goalsStats);

		List<StatsWorldCup> passesStats = teamService.retrieveWorldCupStatsPasses(10);
		Collections.sort(passesStats, new StatWorldCupPassesComparator());
		model.addObject("passesStats", passesStats);

		List<StatsWorldCup> yellowCardsStats = teamService.retrieveWorldCupStatsYellowCard(10);
		model.addObject("yellowCardsStats", yellowCardsStats);

		List<StatsWorldCup> redCardsStats = teamService.retrieveWorldCupStatsRedCard(10);
		model.addObject("redCardsStats", redCardsStats);

		List<StatsWorldCup> bestAttackTeams = teamService.retrieveWorldCupStatsBestAttack(10);
		model.addObject("bestAttackTeams", bestAttackTeams);

		List<StatsWorldCup> worstDefenseTeams = teamService.retrieveWorldCupStatsWorstDefense(10);
		model.addObject("worstDefenseTeams", worstDefenseTeams);

		int countPenalties = teamService.retrieveCountPenalties().size();
		int countCSC = teamService.retrieveCountCSC().size();

		model.addObject("countPenalties", countPenalties);
		model.addObject("countCSC", countCSC);

		model.setViewName("statsShow");

		return model;
	}
}
