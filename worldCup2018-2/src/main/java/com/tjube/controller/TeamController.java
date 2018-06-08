package com.tjube.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import com.tjube.model.PlayerStatsSituation;
import com.tjube.model.Team;
import com.tjube.service.PlayerService;
import com.tjube.service.TeamService;

@Controller
@RequestMapping("/team")
@Configuration
@ComponentScan("com.tjube.service")
public class TeamController
{
	private static final Logger logger = Logger.getLogger(TeamController.class);

	public TeamController()
	{
		System.out.println("TeamController()");
	}

	@Autowired
	private TeamService teamService;

	@Autowired
	private PlayerService playerService;

	@RequestMapping(value = "")
	public ModelAndView homeTeam(ModelAndView model)
		throws IOException
	{
		return new ModelAndView("redirect:team/");
	}

	@RequestMapping(value = "/")
	public ModelAndView listTeam(ModelAndView model)
		throws IOException
	{
		List<Team> listTeam = teamService.getAllTeams();
		model.addObject("listTeam", listTeam);
		model.setViewName("teamHome");
		return model;
	}

	@RequestMapping(value = "/newTeam", method = RequestMethod.GET)
	public ModelAndView newTeam(ModelAndView model)
	{
		Team team = new Team();
		model.addObject("team", team);
		model.setViewName("TeamForm");
		return model;
	}

	@RequestMapping(value = "/showTeam", method = RequestMethod.GET)
	public ModelAndView showPoule(HttpServletRequest request, ModelAndView model)
	{
		int teamId = Integer.parseInt(request.getParameter("id"));
		Team team = teamService.getTeam(teamId);
		model.addObject("team", team);

		List<Player> goals = playerService.getGoals(team);
		model.addObject("goals", goals);

		List<Player> defensers = playerService.getDefensers(team);
		model.addObject("defensers", defensers);

		List<Player> middles = playerService.getMiddles(team);
		model.addObject("middles", middles);

		List<Player> strikers = playerService.getStrikers(team);
		model.addObject("strikers", strikers);

		Map<Integer, PlayerStatsSituation> result = teamService.retrieveAllStatsTeam(team);
		model.addObject("statsTeam", result);

		model.setViewName("teamShow");
		return model;
	}

	@RequestMapping(value = "/saveTeam", method = RequestMethod.POST)
	public ModelAndView saveTeam(@ModelAttribute Team team)
	{
		if (team.getId() == 0)
		{ // if employee id is 0 then creating the
				// employee other updating the employee
			teamService.addTeam(team);
		}
		else
		{
			teamService.updateTeam(team);
		}
		return new ModelAndView("redirect:/team/");
	}

	@RequestMapping(value = "/deleteTeam", method = RequestMethod.GET)
	public ModelAndView deleteTeam(HttpServletRequest request)
	{
		int teamId = Integer.parseInt(request.getParameter("id"));
		teamService.deleteTeam(teamId);
		return new ModelAndView("redirect:/team/");
	}

	@RequestMapping(value = "/editTeam", method = RequestMethod.GET)
	public ModelAndView editTeam(HttpServletRequest request)
	{
		int teamId = Integer.parseInt(request.getParameter("id"));
		Team team = teamService.getTeam(teamId);
		ModelAndView model = new ModelAndView("TeamForm");
		model.addObject("team", team);

		return model;
	}
}
