package com.tjube.controller;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tjube.model.Game;
import com.tjube.model.Team;
import com.tjube.service.GameService;
import com.tjube.service.PlayerService;
import com.tjube.service.PlayerStatsService;
import com.tjube.service.PouleService;
import com.tjube.service.TeamService;

@Controller
@RequestMapping("/")
@Configuration
@ComponentScan("com.tjube.service")
@Transactional
public class ApplicationController
{

	public ApplicationController()
	{
		System.out.println("ApplicationController()");
	}

	@Autowired
	private GameService gameService;

	@Autowired
	private PlayerStatsService playerStatsService;

	@Autowired
	private PouleService pouleService;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private TeamService teamService;

	@RequestMapping(value = { "/", "" })
	public ModelAndView home(ModelAndView model)
		throws IOException
	{
		Game game = gameService.getLastGame();
		model.addObject("lastGame", game);

		Game nextGame = gameService.getNextGame();
		model.addObject("nextGame", nextGame);

		int totalGoals = playerStatsService.getAllGoalsPlayerStats();
		model.addObject("totalGoals", totalGoals);

		int totalPlayers = playerService.getTotalPlayers();
		model.addObject("totalPlayers", totalPlayers);

		int totalTeams = teamService.getTotalTeams();
		model.addObject("totalTeams", totalTeams);

		int totalGames = gameService.getTotalGames();
		model.addObject("totalGames", totalGames);

		Team france = teamService.getTeam("France");
		List<Game> games = gameService.getGames(france);
		model.addObject("franceGames", games);

		model.setViewName("indexHome");
		return model;
	}
}
