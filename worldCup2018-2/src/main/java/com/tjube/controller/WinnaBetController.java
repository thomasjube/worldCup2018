package com.tjube.controller;

import java.io.IOException;
import java.util.HashMap;
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

import com.tjube.model.Action;
import com.tjube.model.Game;
import com.tjube.model.Player;
import com.tjube.model.PlayerStats;
import com.tjube.model.WinnaBet;
import com.tjube.service.GameService;
import com.tjube.service.PlayerService;
import com.tjube.service.PlayerStatsService;
import com.tjube.service.PouleService;
import com.tjube.service.TeamService;
import com.tjube.service.WinnaBetService;

@Controller
@RequestMapping("/winnaBet")
@Configuration
@ComponentScan("com.tjube.service")
public class WinnaBetController
{
	private static final Logger logger = Logger.getLogger(WinnaBetController.class);

	public WinnaBetController()
	{
		System.out.println("WinnaBetController()");
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
	public ModelAndView homeWinnaBet(ModelAndView model)
		throws IOException
	{
		return new ModelAndView("redirect:winnaBet/");
	}

	@RequestMapping(value = "/")
	public ModelAndView listGame(ModelAndView model)
		throws IOException
	{
		model.setViewName("winnaBetHome");
		return model;
	}

	@RequestMapping(value = "/newWinnaBet", method = RequestMethod.GET)
	public ModelAndView newWinnaBet(ModelAndView model)
	{
		WinnaBet winnaBet = new WinnaBet();
		model.addObject("winnaBet", winnaBet);
		model.setViewName("WinnaBetForm");
		return model;
	}

	@RequestMapping(value = "/saveWinnaBet", method = RequestMethod.POST)
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
		return new ModelAndView("redirect:/winnaBet/");
	}

	@RequestMapping(value = "/deleteWinnaBet", method = RequestMethod.GET)
	public ModelAndView deleteGame(HttpServletRequest request)
	{
		int gameId = Integer.parseInt(request.getParameter("id"));
		gameService.deleteGame(gameId);
		return new ModelAndView("redirect:/winnaBet/");
	}

	@RequestMapping(value = "/editWinnaBet", method = RequestMethod.GET)
	public ModelAndView editGameGet(HttpServletRequest request)
	{
		int gameId = Integer.parseInt(request.getParameter("id"));
		Game game = gameService.getGame(gameId);

		ModelAndView model = new ModelAndView("gameEdit");
		model.addObject("game", game);
		model.addObject("titulars", gameService.getTitulars(game));
		model.addObject("substitutes", gameService.getSubstitutes(game));
		model.addObject("editForm", new GameEditForm(game));

		int indexMax = 0;
		if (game.getScore1() != null && game.getScore2() != null)
		{
			indexMax = game.getScore1();
			if (indexMax < game.getScore2())
				indexMax = game.getScore2();
		}
		model.addObject("indexMax", indexMax);
		return model;
	}

	@RequestMapping(value = "/editWinnaBet", method = RequestMethod.POST)
	public ModelAndView editGamePost(HttpServletRequest request, @ModelAttribute("editForm") GameEditForm gameEditForm)
	{
		Game game = gameService.getGame(gameEditForm.getId());
		ModelAndView model = new ModelAndView("gameEdit");
		model.addObject("game", game);

		if (game.getScore1() != null)
			teamService.updateTeamsForReset(game);

		game = gameService.updateGame(gameEditForm.getId(), gameEditForm.getScore1(), gameEditForm.getScore2(),
				gameEditForm.isProlong(), gameEditForm.getScoreProlong1(), gameEditForm.getScoreProlong2(),
				gameEditForm.isPeno(), gameEditForm.getScorePeno1(), gameEditForm.getScorePeno2());

		playerStatsService.deletePlayerStats(game);

		for (int i = 0; i < gameEditForm.getScorerPlayers1().size(); i++)
		{
			Integer minute = gameEditForm.getScorerPlayersMinute1().get(i);
			Player scorerPlayer = playerService.getPlayer(gameEditForm.getScorerPlayers1().get(i));
			PlayerStats playerStat = playerStatsService
					.addPlayerStats(new PlayerStats(game, scorerPlayer, minute, Action.GOAL));

			game.addPlayerStat(playerStat);
			scorerPlayer.addPlayerStat(playerStat);

			Player passer = playerService.getPlayer(gameEditForm.getPasserPlayers1().get(i));
			playerStat = playerStatsService.addPlayerStats(new PlayerStats(game, passer, minute, Action.PASS));

			game.addPlayerStat(playerStat);
			passer.addPlayerStat(playerStat);
		}

		for (int i = 0; i < gameEditForm.getScorerPlayers2().size(); i++)
		{
			Integer minute = gameEditForm.getScorerPlayersMinute2().get(i);
			Player scorerPlayer = playerService.getPlayer(gameEditForm.getScorerPlayers2().get(i));
			PlayerStats playerStat = playerStatsService
					.addPlayerStats(new PlayerStats(game, scorerPlayer, minute, Action.GOAL));

			game.addPlayerStat(playerStat);
			scorerPlayer.addPlayerStat(playerStat);

			Player passer = playerService.getPlayer(gameEditForm.getPasserPlayers2().get(i));
			playerStat = playerStatsService.addPlayerStats(new PlayerStats(game, passer, minute, Action.PASS));

			game.addPlayerStat(playerStat);
			passer.addPlayerStat(playerStat);
		}

		Map<Integer, PlayerStats> mapCartonPlayer = new HashMap<>();
		Map<Integer, PlayerStats> mapCartonRougePlayer = new HashMap<>();

		for (int i = 0; i < gameEditForm.getCartonPlayers().size(); i++)
		{
			Integer minuteCarton = gameEditForm.getCartonMinute().get(i);
			Player playerCarton = playerService.getPlayer(gameEditForm.getCartonPlayers().get(i));
			Action typeCarton = gameEditForm.getCartonType().get(i);

			if (typeCarton == Action.YELLOW_CARD)
			{
				PlayerStats playerStat = playerStatsService
						.addPlayerStats(new PlayerStats(game, playerCarton, minuteCarton, typeCarton));

				game.addPlayerStat(playerStat);
				playerCarton.addPlayerStat(playerStat);

				if (mapCartonPlayer.get(playerCarton.getId()) != null
						&& mapCartonRougePlayer.get(playerCarton.getId()) == null)
				{
					playerStat = playerStatsService
							.addPlayerStats(new PlayerStats(game, playerCarton, minuteCarton, Action.RED_CARD));

					game.addPlayerStat(playerStat);
					playerCarton.addPlayerStat(playerStat);

					mapCartonRougePlayer.put(playerCarton.getId(), playerStat);
				}
				else
				{
					mapCartonPlayer.put(playerCarton.getId(), playerStat);
				}
			}
			else
			{
				if (mapCartonRougePlayer.get(playerCarton.getId()) == null)
				{
					PlayerStats playerStat = playerStatsService
							.addPlayerStats(new PlayerStats(game, playerCarton, minuteCarton, typeCarton));

					game.addPlayerStat(playerStat);
					playerCarton.addPlayerStat(playerStat);
				}
			}
		}

		if (game.getGameInPoule())
		{
			teamService.updateTeams(game);
			teamService.updateTeamsPositions(game.getPoule());
		}
		else if (game.getId() >= 49 && game.getId() <= 56)
			gameService.updateQuarts(game);
		else if (game.getId() >= 57 && game.getId() <= 60)
			gameService.updateDemis(game);
		else if (game.getId() == 61 || game.getId() == 62)
			gameService.updateFinale(game);

		winnaBetService.verifyBets(game);

		return new ModelAndView("redirect:/winnaBet/");
	}

	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public ModelAndView resetWinnaBetGet(HttpServletRequest request)
	{
		WinnaBet winnaBet = winnaBetService.getWinnaBet(Integer.parseInt(request.getParameter("id")));

		return new ModelAndView("redirect:/winnaBet/");
	}
}
