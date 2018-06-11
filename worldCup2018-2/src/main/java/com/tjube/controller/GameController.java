package com.tjube.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

import com.tjube.model.Action;
import com.tjube.model.Game;
import com.tjube.model.Player;
import com.tjube.model.PlayerStats;
import com.tjube.model.Poule;
import com.tjube.model.Team;
import com.tjube.service.GameService;
import com.tjube.service.PlayerService;
import com.tjube.service.PlayerStatsService;
import com.tjube.service.PouleService;
import com.tjube.service.TeamService;

@Controller
@RequestMapping("/game")
@Configuration
@ComponentScan("com.tjube.service")
public class GameController
{
	private static final Logger logger = Logger.getLogger(GameController.class);

	public GameController()
	{
		System.out.println("GameController()");
	}

	@Autowired
	private GameService gameService;

	@Autowired
	private PouleService pouleService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private PlayerStatsService playerStatsService;

	@RequestMapping(value = "")
	public ModelAndView homeGame(ModelAndView model)
		throws IOException
	{
		return new ModelAndView("redirect:game/");
	}

	@RequestMapping(value = "/")
	public ModelAndView listGame(ModelAndView model)
		throws IOException
	{
		List<Game> listGame = gameService.getAllGames();
		model.addObject("listGame", listGame);

		Map<LocalDate, Collection<Game>> mapGames = new LinkedHashMap<>();
		Game previous = null;
		Collection<Game> games = new ArrayList<>();
		int i = 0;
		for (Game g : listGame)
		{
			if (previous == null)
			{
				games.add(g);
			}
			else if (!previous.getDate().isEqual(g.getDate()))
			{
				ArrayList<Game> gameForDate = new ArrayList<>(games);
				mapGames.put(previous.getDate(), gameForDate);
				games.clear();
				games.add(g);
			}
			else
			{
				games.add(g);
			}

			if (i++ == listGame.size() - 1)
			{
				ArrayList<Game> gameForDate = new ArrayList<>(games);
				mapGames.put(previous.getDate(), gameForDate);
			}

			previous = g;

		}
		model.addObject("mapGames", mapGames);
		model.setViewName("gameHome");
		return model;
	}

	@RequestMapping(value = "/populate")
	public ModelAndView populate(ModelAndView model)
		throws IOException
	{
		Collection<Team> teams = new ArrayList<>();
		Collection<Player> players = new ArrayList<>();

		// populate Teams

		// ---------------------------------------------
		// POULE A
		// ---------------------------------------------

		Poule pouleA = new Poule();
		pouleService.addPoule(pouleA);

		Team russie = new Team(1, "Russie", 1, pouleA);
		Team arabieSaoudite = new Team(2, "Arabie Saoudite", 2, pouleA);
		Team egypte = new Team(3, "Egypte", 3, pouleA);
		Team uruguay = new Team(4, "Uruguay", 4, pouleA);

		teamService.addTeam(russie);
		teamService.addTeam(arabieSaoudite);
		teamService.addTeam(egypte);
		teamService.addTeam(uruguay);

		Player player1 = new Player("Akinfeïev", "Igor", "G", 1, russie);
		Player player2 = new Player("Fernandes", "Mario", "D", 2, russie);
		Player player3 = new Player("Kutepov", "Ilia", "D", 3, russie);
		Player player4 = new Player("Ignachevitch", "Sergueï", "D", 4, russie);
		Player player5 = new Player("Semenov", "Andrey", "D", 5, russie);
		Player player6 = new Player("Cheryshev", "Denis", "M", 6, russie);
		Player player7 = new Player("Kuziaev", "Daler", "M", 7, russie);
		Player player8 = new Player("Gazinsky", "Iury", "M", 8, russie);
		Player player9 = new Player("Dzagoev", "Alan", "M", 9, russie);
		Player player10 = new Player("Smolov", "Fedor", "A", 10, russie);
		Player player11 = new Player("Zobnin", "Roman", "M", 11, russie);
		Player player12 = new Player("Lunev", "Andrei", "G", 12, russie);
		Player player13 = new Player("Kudriashov", "Fedor", "D", 13, russie);
		Player player14 = new Player("Granat", "Vladimir", "D", 14, russie);
		Player player15 = new Player("Miranchuk", "Alexey", "M", 15, russie);
		Player player16 = new Player("Miranchuk", "Anton", "M", 16, russie);
		Player player17 = new Player("Golovin", "Aleksandr", "M", 17, russie);
		Player player18 = new Player("Zhirkov", "Yuri", "M", 18, russie);
		Player player19 = new Player("Samedov", "Alexander", "M", 19, russie);
		Player player20 = new Player("Gabulov", "Vladimir", "G", 20, russie);
		Player player21 = new Player("Erokhin", "Aleksandr", "M", 21, russie);
		Player player22 = new Player("Dzyuba", "Artem", "A", 22, russie);
		Player player23 = new Player("Smolnikov", "Igor", "D", 23, russie);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		russie.setPlayers(players);
		teamService.updateTeamPlayers(russie);

		players.clear();

		player1 = new Player("Abdullah", "AlMauaiouf", "G", 1, arabieSaoudite);
		player2 = new Player("Mansour", "AlHarbi", "D", 2, arabieSaoudite);
		player3 = new Player("Osama", "Hawsawi", "D", 3, arabieSaoudite);
		player4 = new Player("Ali", "AlBulayhi", "D", 4, arabieSaoudite);
		player5 = new Player("Omar", "Othman", "D", 5, arabieSaoudite);
		player6 = new Player("Alburayk", "Mohammed", "D", 6, arabieSaoudite);
		player7 = new Player("Salman", "AlFaraj", "M", 7, arabieSaoudite);
		player8 = new Player("Yahia", "AlShehri", "M", 8, arabieSaoudite);
		player9 = new Player("Hatan", "Bahbir", "M", 9, arabieSaoudite);
		player10 = new Player("Alsahlawi", "Mohammed", "A", 10, arabieSaoudite);
		player11 = new Player("Abdulmalek", "AlKhaibri", "M", 11, arabieSaoudite);
		player12 = new Player("Kanno", "Mohamed", "M", 12, arabieSaoudite);
		player13 = new Player("Yasir", "AlShahrani", "D", 13, arabieSaoudite);
		player14 = new Player("Otayf", "Abdullah", "M", 14, arabieSaoudite);
		player15 = new Player("AlKhaibari", "Abdullah", "M", 15, arabieSaoudite);
		player16 = new Player("Hussain", "AlMoqahwi", "M", 16, arabieSaoudite);
		player17 = new Player("Taiseer", "AlJassam", "M", 17, arabieSaoudite);
		player18 = new Player("Salem", "AkDawsari", "M", 18, arabieSaoudite);
		player19 = new Player("Fahad", "AlMuwallad", "A", 19, arabieSaoudite);
		player20 = new Player("Muhannad", "Asiri", "A", 20, arabieSaoudite);
		player21 = new Player("AlMosailem", "Yasser", "G", 21, arabieSaoudite);
		player22 = new Player("Alowais", "Mohammed", "G", 22, arabieSaoudite);
		player23 = new Player("Motaz", "Hawsawi", "D", 23, arabieSaoudite);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		arabieSaoudite.setPlayers(players);
		teamService.updateTeamPlayers(arabieSaoudite);

		players.clear();

		player1 = new Player("ElHadari", "Essam", "G", 1, egypte);
		player2 = new Player("Ali", "Gabr", "D", 2, egypte);
		player3 = new Player("ElMohamady", "Ahmed", "D", 3, egypte);
		player4 = new Player("Gaber", "Omar", "M", 4, egypte);
		player5 = new Player("Morsy", "Sam", "M", 5, egypte);
		player6 = new Player("Hegazy", "Ahmed", "D", 6, egypte);
		player7 = new Player("Fathi", "Ahmed", "D", 7, egypte);
		player8 = new Player("Tarek", "Hamed", "M", 8, egypte);
		player9 = new Player("Marwan", "Mohsen", "A", 9, egypte);
		player10 = new Player("Salah", "Mohamed", "A", 10, egypte);
		player11 = new Player("Kahraba", "M", "A", 11, egypte);
		player12 = new Player("Ashraf", "Ayman", "D", 12, egypte);
		player13 = new Player("Abdelshafy", "Mohamed", "D", 13, egypte);
		player14 = new Player("Ramadan", "Sobhy", "A", 14, egypte);
		player15 = new Player("Hamdy", "Mahmoud", "D", 15, egypte);
		player16 = new Player("Ekramy", "Sherif", "G", 16, egypte);
		player17 = new Player("Elneny", "Mohamed", "M", 17, egypte);
		player18 = new Player("Shikabala", "", "A", 18, egypte);
		player19 = new Player("Abdalla", "Saïd", "M", 19, egypte);
		player20 = new Player("Samir", "Saad", "D", 20, egypte);
		player21 = new Player("Trezeguet", "M", "M", 21, egypte);
		player22 = new Player("Warda", "Amr", "A", 22, egypte);
		player23 = new Player("ElShenawy", "Mohamed", "G", 23, egypte);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		egypte.setPlayers(players);
		teamService.updateTeamPlayers(egypte);

		players.clear();

		player1 = new Player("Muslera", "Fernando", "G", 1, uruguay);
		player2 = new Player("Gimenez", "Jose", "D", 2, uruguay);
		player3 = new Player("Godin", "Diego", "D", 3, uruguay);
		player4 = new Player("Varela", "Guillermo", "D", 4, uruguay);
		player5 = new Player("Sanchez", "Carlos", "M", 5, uruguay);
		player6 = new Player("Bentancur", "Rodrigo", "M", 6, uruguay);
		player7 = new Player("Rodriguez", "Cristian", "M", 7, uruguay);
		player8 = new Player("Nandez", "Nahitan", "M", 8, uruguay);
		player9 = new Player("Suarez", "Luis", "A", 9, uruguay);
		player10 = new Player("De Arrascaeta", "Giorgian", "A", 10, uruguay);
		player11 = new Player("Stuani", "Christian", "A", 11, uruguay);
		player12 = new Player("Campana", "Martin", "G", 12, uruguay);
		player13 = new Player("Silva", "Gaston", "D", 13, uruguay);
		player14 = new Player("Torreira", "Lucas", "M", 14, uruguay);
		player15 = new Player("Vecino", "Matias", "M", 15, uruguay);
		player16 = new Player("Pereira", "Maximiliano", "D", 16, uruguay);
		player17 = new Player("Laxalt", "Diego", "M", 17, uruguay);
		player18 = new Player("Gomez", "Maximiliano", "A", 18, uruguay);
		player19 = new Player("Coates", "Sebastien", "D", 19, uruguay);
		player20 = new Player("Urretaviscaya", "Jonathan", "A", 20, uruguay);
		player21 = new Player("Cavani", "Edinson", "A", 21, uruguay);
		player22 = new Player("Caceres", "Martin", "D", 22, uruguay);
		player23 = new Player("Silva", "Martin", "G", 23, uruguay);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		uruguay.setPlayers(players);
		teamService.updateTeamPlayers(uruguay);

		teams.add(russie);
		teams.add(arabieSaoudite);
		teams.add(egypte);
		teams.add(uruguay);
		pouleA.setName("A");
		pouleA.setTeams(teams);

		pouleService.updatePoule(pouleA);

		Game game1 = new Game(1, "Match 1", russie, arabieSaoudite, pouleA, LocalDateTime.of(2018, 6, 14, 17, 0),
				"Moscou", true);
		Game game2 = new Game(2, "Match 2", egypte, uruguay, pouleA, LocalDateTime.of(2018, 6, 15, 14, 0),
				"Ekaterinbourg", true);
		Game game17 = new Game(17, "Match 17", russie, egypte, pouleA, LocalDateTime.of(2018, 6, 19, 20, 0),
				"Saint-Pétersbourg", true);
		Game game19 = new Game(19, "Match 19", uruguay, arabieSaoudite, pouleA, LocalDateTime.of(2018, 6, 20, 17, 0),
				"Rostov-sur-le-don", true);
		Game game33 = new Game(33, "Match 33", uruguay, russie, pouleA, LocalDateTime.of(2018, 6, 25, 16, 0), "Samara",
				true);
		Game game34 = new Game(34, "Match 34", arabieSaoudite, egypte, pouleA, LocalDateTime.of(2018, 6, 25, 16, 0),
				"Volgograd", true);

		gameService.addGame(game1);
		gameService.addGame(game2);
		gameService.addGame(game17);
		gameService.addGame(game19);
		gameService.addGame(game33);
		gameService.addGame(game34);

		// ---------------------------------------------
		// POULE B
		// ---------------------------------------------

		teams.clear();

		Poule pouleB = new Poule();
		pouleService.addPoule(pouleB);

		Team portugal = new Team(5, "Portugal", 1, pouleB);
		Team espagne = new Team(6, "Espagne", 2, pouleB);
		Team maroc = new Team(7, "Maroc", 3, pouleB);
		Team iran = new Team(8, "Iran", 4, pouleB);

		teamService.addTeam(portugal);
		teamService.addTeam(espagne);
		teamService.addTeam(maroc);
		teamService.addTeam(iran);

		players.clear();

		player1 = new Player("Rui Patricio", "", "G", 1, portugal);
		player2 = new Player("Alves", "Bruno", "D", 2, portugal);
		player3 = new Player("Pepe", "", "D", 3, portugal);
		player4 = new Player("Fernandes", "Manuel", "M", 4, portugal);
		player5 = new Player("Raphael", "Guerreiro", "D", 5, portugal);
		player6 = new Player("Fonte", "Jose", "D", 6, portugal);
		player7 = new Player("Ronaldo", "Cristiano", "A", 7, portugal);
		player8 = new Player("Moutinho", "Joao", "M", 8, portugal);
		player9 = new Player("Silva", "Andre", "A", 9, portugal);
		player10 = new Player("Mario", "Joao", "M", 10, portugal);
		player11 = new Player("Bernardo", "Silva", "M", 11, portugal);
		player12 = new Player("Lopes", "Anthony", "G", 12, portugal);
		player13 = new Player("Dias", "Ruben", "D", 13, portugal);
		player14 = new Player("William", "", "M", 14, portugal);
		player15 = new Player("Ricardo", "", "D", 15, portugal);
		player16 = new Player("Fernandes", "Bruno", "M", 16, portugal);
		player17 = new Player("Guedes", "Goncalo", "A", 17, portugal);
		player18 = new Player("Gelson", "Martins", "A", 18, portugal);
		player19 = new Player("Rui", "Mario", "D", 19, portugal);
		player20 = new Player("Quaresma", "Ricardo", "A", 20, portugal);
		player21 = new Player("Cedric", "", "D", 21, portugal);
		player22 = new Player("Beto", "", "G", 22, portugal);
		player23 = new Player("Adrien", "Silva", "M", 23, portugal);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		portugal.setPlayers(players);
		teamService.updateTeamPlayers(portugal);

		players.clear();

		player1 = new Player("DeGea", "David", "G", 1, espagne);
		player2 = new Player("Carvajal", "Dani", "D", 2, espagne);
		player3 = new Player("Pique", "Gerard", "D", 3, espagne);
		player4 = new Player("Nacho", "", "D", 4, espagne);
		player5 = new Player("Busquets", "Sergio", "M", 5, espagne);
		player6 = new Player("Iniesta", "Andres", "M", 6, espagne);
		player7 = new Player("Saul", "", "M", 7, espagne);
		player8 = new Player("Koke", "", "M", 8, espagne);
		player9 = new Player("Rodrigo", "", "A", 9, espagne);
		player10 = new Player("Thiago", "", "M", 10, espagne);
		player11 = new Player("Vazquez", "Lucas", "A", 11, espagne);
		player12 = new Player("Odriozola", "Alvaro", "D", 12, espagne);
		player13 = new Player("Arrizabalaga", "Kepa", "G", 13, espagne);
		player14 = new Player("Azpilicueta", "Cesar", "D", 14, espagne);
		player15 = new Player("Ramos", "Sergio", "D", 15, espagne);
		player16 = new Player("Monreal", "Nacho", "D", 16, espagne);
		player17 = new Player("Aspas", "Iago", "A", 17, espagne);
		player18 = new Player("Alba", "Jordi", "D", 18, espagne);
		player19 = new Player("Costa", "Diego", "A", 19, espagne);
		player20 = new Player("Ascensio", "Marco", "M", 20, espagne);
		player21 = new Player("Silva", "David", "A", 21, espagne);
		player22 = new Player("Isco", "", "M", 22, espagne);
		player23 = new Player("Reina", "Pepe", "G", 23, espagne);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		espagne.setPlayers(players);
		teamService.updateTeamPlayers(espagne);

		players.clear();

		player1 = new Player("Bounou", "Yassine", "G", 1, maroc);
		player2 = new Player("Hakimi", "Achraf", "D", 2, maroc);
		player3 = new Player("Mendyl", "Hamza", "D", 3, maroc);
		player4 = new Player("DaCosta", "Manuel", "D", 4, maroc);
		player5 = new Player("Benatia", "Mehdi", "D", 5, maroc);
		player6 = new Player("Saiss", "Romain", "D", 6, maroc);
		player7 = new Player("Ziyach", "Hakim", "M", 7, maroc);
		player8 = new Player("ElAhmadi", "Karim", "M", 8, maroc);
		player9 = new Player("ElKaabi", "Ayoub", "A", 9, maroc);
		player10 = new Player("Belhanda", "Younes", "M", 10, maroc);
		player11 = new Player("Fajr", "Faycal", "M", 11, maroc);
		player12 = new Player("ElKajoui", "Monir", "G", 12, maroc);
		player13 = new Player("Boutaib", "Khalid", "A", 13, maroc);
		player14 = new Player("Boussoufa", "Mbark", "M", 14, maroc);
		player15 = new Player("AitBennasser", "Youssef", "M", 15, maroc);
		player16 = new Player("Amrabat", "Noureddine", "M", 16, maroc);
		player17 = new Player("Dirar", "Nabil", "D", 17, maroc);
		player18 = new Player("Harit", "Amine", "M", 18, maroc);
		player19 = new Player("EnNesyri", "Youssef", "A", 19, maroc);
		player20 = new Player("Bouhaddouz", "Aziz", "A", 20, maroc);
		player21 = new Player("Amrabat", "Sofyan", "M", 21, maroc);
		player22 = new Player("Tagnaouti", "Ahmed", "G", 22, maroc);
		player23 = new Player("Carcela", "Mehdi", "M", 23, maroc);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		maroc.setPlayers(players);
		teamService.updateTeamPlayers(maroc);

		players.clear();

		player1 = new Player("Beiranvand", "Ali", "G", 1, iran);
		player2 = new Player("Torabi", "Mehdi", "M", 2, iran);
		player3 = new Player("Haji Safi", "Ehsan", "D", 3, iran);
		player4 = new Player("Cheshmi", "Roozbeh", "D", 4, iran);
		player5 = new Player("Mohammadi", "Milad", "D", 5, iran);
		player6 = new Player("Ezatolahi", "Saeid", "M", 6, iran);
		player7 = new Player("Shojaei", "Masoud", "M", 7, iran);
		player8 = new Player("Pouraliganji", "Morteza", "D", 8, iran);
		player9 = new Player("Omid", "Ebrahimi", "M", 9, iran);
		player10 = new Player("Karim", "Ansarifard", "A", 10, iran);
		player11 = new Player("Amiri", "Vahid", "M", 11, iran);
		player12 = new Player("Rashid", "Mazaheri", "G", 12, iran);
		player13 = new Player("Khanzadeh", "Mohammad Reza", "D", 13, iran);
		player14 = new Player("Ghoddos", "Saman", "A", 14, iran);
		player15 = new Player("Montazeri", "Pejman", "D", 15, iran);
		player16 = new Player("Reza", "Ghoochannejhad", "D", 16, iran);
		player17 = new Player("Mehdi", "Taremi", "A", 17, iran);
		player18 = new Player("Jahanbakhsh", "Alireza", "A", 18, iran);
		player19 = new Player("Hosseini", "Majid", "A", 19, iran);
		player20 = new Player("Sardar", "Azmoun", "D", 20, iran);
		player21 = new Player("Dejagah", "Ashkan", "A", 21, iran);
		player22 = new Player("Abedzadeh", "Amir", "A", 22, iran);
		player23 = new Player("Ramin", "Rezaeian", "G", 23, iran);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		iran.setPlayers(players);
		teamService.updateTeamPlayers(iran);

		teams.add(portugal);
		teams.add(espagne);
		teams.add(maroc);
		teams.add(iran);

		pouleB.setName("B");
		pouleB.setTeams(teams);

		pouleService.updatePoule(pouleB);

		Game game3 = new Game(3, "Match 3", maroc, iran, pouleB, LocalDateTime.of(2018, 6, 15, 17, 0),
				"Saint-Pétersbourg", true);
		Game game4 = new Game(4, "Match 4", portugal, espagne, pouleB, LocalDateTime.of(2018, 6, 15, 20, 0), "Sotchi",
				true);
		Game game18 = new Game(18, "Match 18", portugal, maroc, pouleB, LocalDateTime.of(2018, 6, 20, 14, 0), "Moscou",
				true);
		Game game20 = new Game(20, "Match 20", iran, espagne, pouleB, LocalDateTime.of(2018, 6, 20, 20, 0), "Kazan",
				true);
		Game game35 = new Game(35, "Match 35", iran, portugal, pouleB, LocalDateTime.of(2018, 6, 25, 20, 0), "Saransk",
				true);
		Game game36 = new Game(36, "Match 36", espagne, maroc, pouleB, LocalDateTime.of(2018, 6, 25, 20, 0),
				"Kaliningrad", true);

		gameService.addGame(game3);
		gameService.addGame(game4);
		gameService.addGame(game18);
		gameService.addGame(game20);
		gameService.addGame(game35);
		gameService.addGame(game36);

		// ---------------------------------------------
		// POULE C
		// ---------------------------------------------

		teams.clear();

		Poule pouleC = new Poule();

		pouleService.addPoule(pouleC);

		Team france = new Team(9, "France", 1, pouleC);
		Team australie = new Team(10, "Australie", 2, pouleC);
		Team perou = new Team(11, "Pérou", 3, pouleC);
		Team danemark = new Team(12, "Danemark", 4, pouleC);

		teamService.addTeam(france);
		teamService.addTeam(australie);
		teamService.addTeam(perou);
		teamService.addTeam(danemark);

		players.clear();

		player1 = new Player("Lloris", "Hugo", "G", 1, france);
		player2 = new Player("Pavard", "Benjamin", "D", 2, france);
		player3 = new Player("Kimpembe", "Presnel", "D", 3, france);
		player4 = new Player("Varane", "Raphael", "D", 4, france);
		player5 = new Player("Umtiti", "Samuel", "D", 5, france);
		player6 = new Player("Pogba", "Paul", "M", 6, france);
		player7 = new Player("Griezmann", "Antoine", "A", 7, france);
		player8 = new Player("Lemar", "Thomas", "A", 8, france);
		player9 = new Player("Giroud", "Olivier", "A", 9, france);
		player10 = new Player("Mbappe", "Kylian", "A", 10, france);
		player11 = new Player("Dembele", "Ousmane", "A", 11, france);
		player12 = new Player("Tolisso", "Corentin", "M", 12, france);
		player13 = new Player("Kante", "Ngolo", "M", 13, france);
		player14 = new Player("Matuidi", "Blaise", "M", 14, france);
		player15 = new Player("Nzonzi", "Steven", "M", 15, france);
		player16 = new Player("Mandanda", "Steve", "G", 16, france);
		player17 = new Player("Rami", "Adil", "D", 17, france);
		player18 = new Player("Fekir", "Nabil", "A", 18, france);
		player19 = new Player("Sidibe", "Djibril", "D", 19, france);
		player20 = new Player("Thauvin", "Florian", "A", 20, france);
		player21 = new Player("Hernandez", "Lucas", "D", 21, france);
		player22 = new Player("Mendy", "Benjamin", "D", 22, france);
		player23 = new Player("Areola", "Alphonse", "G", 23, france);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		france.setPlayers(players);
		teamService.updateTeamPlayers(france);

		players.clear();

		player1 = new Player("Ryan", "Mathew", "G", 1, australie);
		player2 = new Player("Degenek", "Milos", "D", 2, australie);
		player3 = new Player("Meredith", "James", "D", 3, australie);
		player4 = new Player("Cahill", "Tim", "A", 4, australie);
		player5 = new Player("Milligan", "Mark", "D", 5, australie);
		player6 = new Player("Jurman", "Matthew", "D", 6, australie);
		player7 = new Player("Leckie", "Mathew", "A", 7, australie);
		player8 = new Player("Luongo", "Massimo", "M", 8, australie);
		player9 = new Player("Juric", "Tomi", "A", 9, australie);
		player10 = new Player("Kruse", "Robbie", "A", 10, australie);
		player11 = new Player("Nabbout", "Andrew", "A", 11, australie);
		player12 = new Player("Jones", "Brad", "G", 12, australie);
		player13 = new Player("Mooy", "Aaron", "M", 13, australie);
		player14 = new Player("MacLaren", "Jamie", "A", 14, australie);
		player15 = new Player("Jedinak", "Mile", "M", 15, australie);
		player16 = new Player("Behich", "Aziz", "D", 16, australie);
		player17 = new Player("Arzani", "Daniel", "A", 17, australie);
		player18 = new Player("Vukovic", "Danny", "G", 18, australie);
		player19 = new Player("Risdon", "Joshua", "D", 19, australie);
		player20 = new Player("Sainsbury", "Trent", "D", 20, australie);
		player21 = new Player("Petratos", "Dimitrios", "A", 21, australie);
		player22 = new Player("Irvine", "Jackson", "M", 22, australie);
		player23 = new Player("Rogic", "Tom", "M", 23, australie);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		australie.setPlayers(players);
		teamService.updateTeamPlayers(australie);

		players.clear();

		player1 = new Player("Gallese", "Pedro", "G", 1, perou);
		player2 = new Player("Rodriguez", "Alberto", "D", 2, perou);
		player3 = new Player("Corzo", "Aldo", "D", 3, perou);
		player4 = new Player("Santamaria", "Anderson", "D", 4, perou);
		player5 = new Player("Araujo", "Miguel", "D", 5, perou);
		player6 = new Player("Trauco", "Miguel", "D", 6, perou);
		player7 = new Player("Hurtado", "Paolo", "M", 7, perou);
		player8 = new Player("Cueva", "Christian", "M", 8, perou);
		player9 = new Player("Guerrero", "Paolo", "A", 9, perou);
		player10 = new Player("Farfan", "Jefferson", "A", 10, perou);
		player11 = new Player("Ruidiaz", "Raul", "A", 11, perou);
		player12 = new Player("Caceda", "Carlos", "G", 12, perou);
		player13 = new Player("Tapia", "Renato", "M", 13, perou);
		player14 = new Player("Polo", "Andy", "M", 14, perou);
		player15 = new Player("Ramos", "Christian", "D", 15, perou);
		player16 = new Player("Cartagena", "Wilder", "M", 16, perou);
		player17 = new Player("Advincula", "Luis", "D", 17, perou);
		player18 = new Player("Carrillo", "Andre", "A", 18, perou);
		player19 = new Player("Yotun", "Yoshimar", "M", 19, perou);
		player20 = new Player("Flores", "Edison", "A", 20, perou);
		player21 = new Player("Carvallo", "Jose", "G", 21, perou);
		player22 = new Player("Loyola", "Nilson", "D", 22, perou);
		player23 = new Player("Aquino", "Pedro", "M", 23, perou);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		perou.setPlayers(players);
		teamService.updateTeamPlayers(perou);

		players.clear();

		player1 = new Player("Schmeichel", "Kasper", "G", 1, danemark);
		player2 = new Player("Krohn-Dehli", "Michael", "M", 2, danemark);
		player3 = new Player("Vestergaard", "Jannik", "D", 3, danemark);
		player4 = new Player("Kjaer", "Simon", "D", 4, danemark);
		player5 = new Player("Knudsen", "Jonas", "D", 5, danemark);
		player6 = new Player("Christensen", "Andreas", "D", 6, danemark);
		player7 = new Player("Kvist", "William", "M", 7, danemark);
		player8 = new Player("Delaney", "Thomas", "M", 8, danemark);
		player9 = new Player("Jorgensen", "Nicolai", "A", 9, danemark);
		player10 = new Player("Eriksen", "Christian", "M", 10, danemark);
		player11 = new Player("Braithwaite", "Martin", "A", 11, danemark);
		player12 = new Player("Dolberg", "Kasper", "A", 12, danemark);
		player13 = new Player("Jorgensen", "Mathias", "D", 13, danemark);
		player14 = new Player("Dalsgaard", "Henrik", "D", 14, danemark);
		player15 = new Player("Fischer", "Viktor", "A", 15, danemark);
		player16 = new Player("Lossl", "Jonas", "G", 16, danemark);
		player17 = new Player("Stryger", "Larsen Jens", "D", 17, danemark);
		player18 = new Player("Lerager", "Lukas", "M", 18, danemark);
		player19 = new Player("Schone", "Lasse", "M", 19, danemark);
		player20 = new Player("Poulsen", "Yssuf Yurary", "A", 20, danemark);
		player21 = new Player("Cornelius", "Andreas", "A", 21, danemark);
		player22 = new Player("Ronnow", "Frederik", "G", 22, danemark);
		player23 = new Player("Sisto", "Pione", "A", 23, danemark);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		danemark.setPlayers(players);
		teamService.updateTeamPlayers(danemark);

		teams.add(france);
		teams.add(australie);
		teams.add(perou);
		teams.add(danemark);
		pouleC.setName("C");
		pouleC.setTeams(teams);

		pouleService.updatePoule(pouleC);

		Game game5 = new Game(5, "Match 5", france, australie, pouleC, LocalDateTime.of(2018, 6, 16, 12, 0), "Kazan",
				true);
		Game game7 = new Game(7, "Match 7", perou, danemark, pouleC, LocalDateTime.of(2018, 6, 16, 18, 0), "Saransk",
				true);
		Game game21 = new Game(21, "Match 21", danemark, australie, pouleC, LocalDateTime.of(2018, 6, 21, 14, 0),
				"Samara", true);
		Game game22 = new Game(22, "Match 22", france, perou, pouleC, LocalDateTime.of(2018, 6, 21, 17, 0),
				"Ekaterinbourg", true);
		Game game37 = new Game(37, "Match 37", danemark, france, pouleC, LocalDateTime.of(2018, 6, 26, 16, 0), "Moscou",
				true);
		Game game38 = new Game(38, "Match 38", australie, perou, pouleC, LocalDateTime.of(2018, 6, 26, 16, 0), "Sotchi",
				true);

		gameService.addGame(game5);
		gameService.addGame(game7);
		gameService.addGame(game21);
		gameService.addGame(game22);
		gameService.addGame(game37);
		gameService.addGame(game38);

		// ---------------------------------------------
		// POULE D
		// ---------------------------------------------

		teams.clear();

		Poule pouleD = new Poule();
		pouleService.addPoule(pouleD);

		Team argentine = new Team(13, "Argentine", 1, pouleD);
		Team islande = new Team(14, "Islande", 2, pouleD);
		Team croatie = new Team(15, "Croatie", 3, pouleD);
		Team nigeria = new Team(16, "Nigéria", 4, pouleD);

		teamService.addTeam(argentine);
		teamService.addTeam(islande);
		teamService.addTeam(croatie);
		teamService.addTeam(nigeria);

		players.clear();

		player1 = new Player("Guzman", "Nahuel", "G", 1, argentine);
		player2 = new Player("Mercado", "Gabriel", "D", 2, argentine);
		player3 = new Player("Tagliafico", "Nicolas", "D", 3, argentine);
		player4 = new Player("Ansaldi", "Cristian", "D", 4, argentine);
		player5 = new Player("Biglia", "Lucas", "M", 5, argentine);
		player6 = new Player("Fazio", "Federico", "D", 6, argentine);
		player7 = new Player("Banega", "Ever", "M", 7, argentine);
		player8 = new Player("Acuna", "Marcos", "D", 8, argentine);
		player9 = new Player("Higuain", "Gonzalo", "A", 9, argentine);
		player10 = new Player("Messi", "Lionel", "A", 10, argentine);
		player11 = new Player("Di Maria", "Angel", "M", 11, argentine);
		player12 = new Player("Armani", "Franco", "G", 12, argentine);
		player13 = new Player("Meza", "Maximiliano", "M", 13, argentine);
		player14 = new Player("Mascherano", "Javier", "D", 14, argentine);
		player15 = new Player("Lanzini", "Manuel", "M", 15, argentine);
		player16 = new Player("Rojo", "Marcos", "D", 16, argentine);
		player17 = new Player("Otamendi", "Nicolas", "D", 17, argentine);
		player18 = new Player("Salvio", "Eduardo", "D", 18, argentine);
		player19 = new Player("Aguero", "Sergio", "A", 19, argentine);
		player20 = new Player("Lo Celso", "Giovani", "M", 20, argentine);
		player21 = new Player("Dybala", "Paulo", "A", 21, argentine);
		player22 = new Player("Pavon", "Cristian", "M", 22, argentine);
		player23 = new Player("Caballero", "Wilfredo", "G", 23, argentine);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		argentine.setPlayers(players);
		teamService.updateTeamPlayers(argentine);

		players.clear();

		player1 = new Player("Halldorsson", "Hannes", "G", 1, islande);
		player2 = new Player("Saevarsson", "Birkir", "D", 2, islande);
		player3 = new Player("Fridjonsson", "Samuel", "M", 3, islande);
		player4 = new Player("Gudmundsson", "Albert", "M", 4, islande);
		player5 = new Player("Ingason", "Sverrir", "D", 5, islande);
		player6 = new Player("Sigurdsson", "Ragnar", "D", 6, islande);
		player7 = new Player("Gudmundsson", "Johann", "M", 7, islande);
		player8 = new Player("Bjarnason", "Birkir", "M", 8, islande);
		player9 = new Player("Sigurdarson", "Bjorn", "A", 9, islande);
		player10 = new Player("Sigurdsson", "Gylfi", "M", 10, islande);
		player11 = new Player("Finnbogason", "Alfred", "A", 11, islande);
		player12 = new Player("Schram", "Frederik", "G", 12, islande);
		player13 = new Player("Runarsson", "Runar", "G", 13, islande);
		player14 = new Player("Arnason", "Kari", "D", 14, islande);
		player15 = new Player("Eyjolfsson", "Holmar", "D", 15, islande);
		player16 = new Player("Skulason", "Olafur", "M", 16, islande);
		player17 = new Player("Gunnarsson", "Aron", "M", 17, islande);
		player18 = new Player("Magnusson", "Hordur", "D", 18, islande);
		player19 = new Player("Gislason", "Rurik", "M", 19, islande);
		player20 = new Player("Hallfredsson", "Emil", "M", 20, islande);
		player21 = new Player("Traustason", "Arnor", "M", 21, islande);
		player22 = new Player("Bodvarsson", "Jon", "A", 22, islande);
		player23 = new Player("Skulason", "Ari", "D", 23, islande);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		islande.setPlayers(players);
		teamService.updateTeamPlayers(islande);

		players.clear();

		player1 = new Player("Livakovic", "Dominik", "G", 1, croatie);
		player2 = new Player("Vrsaljko", "Sime", "D", 2, croatie);
		player3 = new Player("Strinic", "Ivan", "D", 3, croatie);
		player4 = new Player("Perisic", "Ivan", "A", 4, croatie);
		player5 = new Player("Corluka", "Vedran", "D", 5, croatie);
		player6 = new Player("Lovren", "Dejan", "D", 6, croatie);
		player7 = new Player("Rakitic", "Ivan", "M", 7, croatie);
		player8 = new Player("Kovacic", "Mateo", "M", 8, croatie);
		player9 = new Player("Kramaric", "Andrej", "A", 9, croatie);
		player10 = new Player("Modric", "Luka", "M", 10, croatie);
		player11 = new Player("Brozovic", "Marcelo", "M", 11, croatie);
		player12 = new Player("Kalinic", "Lovre", "G", 12, croatie);
		player13 = new Player("Jedvaj", "Tin", "D", 13, croatie);
		player14 = new Player("Bradaric", "Filip", "M", 14, croatie);
		player15 = new Player("Caleta-Car", "Duje", "D", 15, croatie);
		player16 = new Player("Kalinic", "Nikola", "A", 16, croatie);
		player17 = new Player("Mandzukic", "Mario", "A", 17, croatie);
		player18 = new Player("Rebic", "Ante", "A", 18, croatie);
		player19 = new Player("Badelj", "Milan", "M", 19, croatie);
		player20 = new Player("Pjaca", "Marko", "A", 20, croatie);
		player21 = new Player("Vida", "Domagoj", "D", 21, croatie);
		player22 = new Player("Pivaric", "Josip", "D", 22, croatie);
		player23 = new Player("Subasic", "Danijel", "G", 23, croatie);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		croatie.setPlayers(players);
		teamService.updateTeamPlayers(croatie);

		players.clear();

		player1 = new Player("Ezenwa", "Ikechukwu", "G", 1, nigeria);
		player2 = new Player("Idowu", "Bryan", "D", 2, nigeria);
		player3 = new Player("Echiejile", "Elderson", "D", 3, nigeria);
		player4 = new Player("Ndidi", "Onyinye", "M", 4, nigeria);
		player5 = new Player("Ekong", "William", "D", 5, nigeria);
		player6 = new Player("Balogun", "Leon", "D", 6, nigeria);
		player7 = new Player("Musa", "Ahmed", "A", 7, nigeria);
		player8 = new Player("Etebo", "Oghenekaro", "M", 8, nigeria);
		player9 = new Player("Ighalo", "Odion", "A", 9, nigeria);
		player10 = new Player("Mikel", "John Obi", "M", 10, nigeria);
		player11 = new Player("Moses", "Victor", "A", 11, nigeria);
		player12 = new Player("Shehu", "Abdullahi", "D", 12, nigeria);
		player13 = new Player("Nwankwo", "Simeon", "A", 13, nigeria);
		player14 = new Player("Iheanacho", "Kelechi", "A", 14, nigeria);
		player15 = new Player("Obi", "Joel", "M", 15, nigeria);
		player16 = new Player("Akpeyi", "Daniel", "G", 16, nigeria);
		player17 = new Player("Onazi", "Ogenyi", "M", 17, nigeria);
		player18 = new Player("Iwobi", "Alex", "A", 18, nigeria);
		player19 = new Player("Ogu", "John", "M", 19, nigeria);
		player20 = new Player("Awaziem", "Chidozie", "D", 20, nigeria);
		player21 = new Player("Ebuehu", "Tyronne", "D", 21, nigeria);
		player22 = new Player("Omeruo", "Kenneth", "D", 22, nigeria);
		player23 = new Player("Uzoho", "Francis", "G", 23, nigeria);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		nigeria.setPlayers(players);
		teamService.updateTeamPlayers(nigeria);

		teams.add(argentine);
		teams.add(islande);
		teams.add(croatie);
		teams.add(nigeria);
		pouleD.setName("D");
		pouleD.setTeams(teams);

		pouleService.updatePoule(pouleD);

		Game game6 = new Game(6, "Match 6", argentine, islande, pouleD, LocalDateTime.of(2018, 6, 16, 15, 0), "Moscou",
				true);
		Game game8 = new Game(8, "Match 8", croatie, nigeria, pouleD, LocalDateTime.of(2018, 6, 16, 21, 0),
				"Kaliningrad", true);
		Game game23 = new Game(23, "Match 23", argentine, croatie, pouleD, LocalDateTime.of(2018, 6, 21, 20, 0),
				"Nijni Novgorod", true);
		Game game25 = new Game(25, "Match 25", nigeria, islande, pouleD, LocalDateTime.of(2018, 6, 22, 17, 0),
				"Volgograd", true);
		Game game39 = new Game(39, "Match 39", nigeria, argentine, pouleD, LocalDateTime.of(2018, 6, 26, 20, 0),
				"Saint-Pétersbourg", true);
		Game game40 = new Game(40, "Match 40", islande, croatie, pouleD, LocalDateTime.of(2018, 6, 26, 20, 0),
				"Rostov-sur-le-Don", true);

		gameService.addGame(game6);
		gameService.addGame(game8);
		gameService.addGame(game23);
		gameService.addGame(game25);
		gameService.addGame(game39);
		gameService.addGame(game40);

		// ---------------------------------------------
		// POULE E
		// ---------------------------------------------

		teams.clear();

		Poule pouleE = new Poule();
		pouleService.addPoule(pouleE);

		Team bresil = new Team(17, "Brésil", 1, pouleE);
		Team suisse = new Team(18, "Suisse", 2, pouleE);
		Team costaRica = new Team(19, "Costa Rica", 3, pouleE);
		Team serbie = new Team(20, "Serbie", 4, pouleE);

		teamService.addTeam(bresil);
		teamService.addTeam(suisse);
		teamService.addTeam(costaRica);
		teamService.addTeam(serbie);

		players.clear();

		player1 = new Player("Alisson", "", "G", 1, bresil);
		player2 = new Player("Thiago", "Silva", "D", 2, bresil);
		player3 = new Player("Miranda", "", "D", 3, bresil);
		player4 = new Player("Geromel", "Pedro", "D", 4, bresil);
		player5 = new Player("Casemiro", "", "M", 5, bresil);
		player6 = new Player("Filipe Luis", "", "D", 6, bresil);
		player7 = new Player("Costa", "Douglas", "A", 7, bresil);
		player8 = new Player("Augusto", "Renato", "M", 8, bresil);
		player9 = new Player("Jesus", "Gabriel", "A", 9, bresil);
		player10 = new Player("Neymar", "Jr", "A", 10, bresil);
		player11 = new Player("Coutinho", "Philippe", "M", 11, bresil);
		player12 = new Player("Marcelo", "", "D", 12, bresil);
		player13 = new Player("Marquinhos", "", "D", 13, bresil);
		player14 = new Player("Danilo", "", "D", 14, bresil);
		player15 = new Player("Paulinho", "", "M", 15, bresil);
		player16 = new Player("Cassio", "", "G", 16, bresil);
		player17 = new Player("Fernandinho", "", "M", 17, bresil);
		player18 = new Player("Fred", "", "M", 18, bresil);
		player19 = new Player("Willian", "", "M", 19, bresil);
		player20 = new Player("Firmino", "Roberto", "A", 20, bresil);
		player21 = new Player("Taison", "", "A", 21, bresil);
		player22 = new Player("Fagner", "", "D", 22, bresil);
		player23 = new Player("Ederson", "", "G", 23, bresil);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		bresil.setPlayers(players);
		teamService.updateTeamPlayers(bresil);

		players.clear();

		player1 = new Player("Sommer", "Yann", "G", 1, suisse);
		player2 = new Player("Lichtsteiner", "Stephan", "D", 2, suisse);
		player3 = new Player("Moubandhe", "Francois", "D", 3, suisse);
		player4 = new Player("Elvedi", "Nico", "D", 4, suisse);
		player5 = new Player("Akanji", "Manuel", "D", 5, suisse);
		player6 = new Player("Lang", "Michael", "D", 6, suisse);
		player7 = new Player("Embolo", "Breel", "A", 7, suisse);
		player8 = new Player("Freuler", "Remo", "M", 8, suisse);
		player9 = new Player("Seferovic", "Haris", "A", 9, suisse);
		player10 = new Player("Xhaka", "Granit", "M", 10, suisse);
		player11 = new Player("Behrami", "Valon", "M", 11, suisse);
		player12 = new Player("Mvogo", "Yvon", "G", 12, suisse);
		player13 = new Player("Rodriguez", "Ricardo", "D", 13, suisse);
		player14 = new Player("Zuber", "Steven", "M", 14, suisse);
		player15 = new Player("Dzemaili", "Blerim", "M", 15, suisse);
		player16 = new Player("Fernandes", "Gelson", "M", 16, suisse);
		player17 = new Player("Zakaria", "Denis", "M", 17, suisse);
		player18 = new Player("Gavranovic", "Mario", "A", 18, suisse);
		player19 = new Player("Drmic", "Josip", "A", 19, suisse);
		player20 = new Player("Djourou", "Johan", "D", 20, suisse);
		player21 = new Player("Buerki", "Roman", "G", 21, suisse);
		player22 = new Player("Schaer", "Fabian", "D", 22, suisse);
		player23 = new Player("Shaqiri", "Xherdan", "M", 23, suisse);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		suisse.setPlayers(players);
		teamService.updateTeamPlayers(suisse);

		players.clear();

		player1 = new Player("Navas", "Keylor", "G", 1, costaRica);
		player2 = new Player("Acosta", "Johnny", "D", 2, costaRica);
		player3 = new Player("Gonzalez", "Giancarlo", "D", 3, costaRica);
		player4 = new Player("Smith", "Ian", "D", 4, costaRica);
		player5 = new Player("Borges", "Celso", "M", 5, costaRica);
		player6 = new Player("Duarte", "Oscar", "D", 6, costaRica);
		player7 = new Player("Bolanos", "Christian", "M", 7, costaRica);
		player8 = new Player("Oviedo", "Bryan", "D", 8, costaRica);
		player9 = new Player("Colindres", "Daniel", "M", 9, costaRica);
		player10 = new Player("Ruiz", "Bryan", "M", 10, costaRica);
		player11 = new Player("Venegas", "Johan", "A", 11, costaRica);
		player12 = new Player("Campbell", "Joel", "A", 12, costaRica);
		player13 = new Player("Wallace", "Rodney", "M", 13, costaRica);
		player14 = new Player("Azofeifa", "Randall", "M", 14, costaRica);
		player15 = new Player("Calvo", "Francisco", "D", 15, costaRica);
		player16 = new Player("Gamboa", "Cristian", "D", 16, costaRica);
		player17 = new Player("Tejeda", "Yeltsin", "M", 17, costaRica);
		player18 = new Player("Pemberton", "Patrick", "G", 18, costaRica);
		player19 = new Player("Waston", "Kendall", "D", 19, costaRica);
		player20 = new Player("Guzman", "David", "M", 20, costaRica);
		player21 = new Player("Urena", "Marcos", "A", 21, costaRica);
		player22 = new Player("Matarrita", "Ronald", "D", 22, costaRica);
		player23 = new Player("Moreira", "Leonel", "G", 23, costaRica);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		costaRica.setPlayers(players);
		teamService.updateTeamPlayers(costaRica);

		players.clear();

		player1 = new Player("Stojkovic", "Vladimir", "G", 1, serbie);
		player2 = new Player("Rukavina", "Antonio", "D", 2, serbie);
		player3 = new Player("Tosic", "Dusko", "D", 3, serbie);
		player4 = new Player("Milivojejic", "Luka", "M", 4, serbie);
		player5 = new Player("Spajic", "Uros", "D", 5, serbie);
		player6 = new Player("Ivanovic", "Branislav", "D", 6, serbie);
		player7 = new Player("Zivkovic", "Andrija", "M", 7, serbie);
		player8 = new Player("Prijovic", "Aleksandar", "A", 8, serbie);
		player9 = new Player("Mitrovic", "Aleksandar", "A", 9, serbie);
		player10 = new Player("Tadic", "Dusan", "M", 10, serbie);
		player11 = new Player("Kolarov", "Aleksandar", "D", 11, serbie);
		player12 = new Player("Rajkovic", "Predrag", "G", 12, serbie);
		player13 = new Player("Velkovic", "Milos", "D", 13, serbie);
		player14 = new Player("Rodic", "Milan", "D", 14, serbie);
		player15 = new Player("Milenkovic", "Nikola", "D", 15, serbie);
		player16 = new Player("Grujic", "Marko", "M", 16, serbie);
		player17 = new Player("Kostic", "Filip", "M", 17, serbie);
		player18 = new Player("Radonjic", "Nemanja", "A", 18, serbie);
		player19 = new Player("Jovic", "Luka", "A", 19, serbie);
		player20 = new Player("Sergej", "Milinkovic-Savic", "M", 20, serbie);
		player21 = new Player("Matic", "Nemanja", "M", 21, serbie);
		player22 = new Player("Ljajic", "Adem", "M", 22, serbie);
		player23 = new Player("Dmitrovic", "Marko", "G", 23, serbie);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		serbie.setPlayers(players);
		teamService.updateTeamPlayers(serbie);

		teams.add(bresil);
		teams.add(suisse);
		teams.add(costaRica);
		teams.add(serbie);
		pouleE.setName("E");
		pouleE.setTeams(teams);

		pouleService.updatePoule(pouleE);

		Game game9 = new Game(9, "Match 9", costaRica, serbie, pouleE, LocalDateTime.of(2018, 6, 17, 14, 0), "Samara",
				true);
		Game game11 = new Game(11, "Match 11", bresil, suisse, pouleE, LocalDateTime.of(2018, 6, 17, 20, 0),
				"Rostov-sur-le-Don", true);
		Game game24 = new Game(24, "Match 24", bresil, costaRica, pouleE, LocalDateTime.of(2018, 6, 22, 14, 0),
				"Saint-Pétersbourg", true);
		Game game26 = new Game(26, "Match 26", serbie, suisse, pouleE, LocalDateTime.of(2018, 6, 22, 20, 0),
				"Kaliningrad", true);
		Game game43 = new Game(43, "Match 43", serbie, bresil, pouleE, LocalDateTime.of(2018, 6, 27, 20, 0), "Moscou",
				true);
		Game game44 = new Game(44, "Match 44", suisse, costaRica, pouleE, LocalDateTime.of(2018, 6, 27, 20, 0),
				"Nijni Novgorod", true);

		gameService.addGame(game9);
		gameService.addGame(game11);
		gameService.addGame(game24);
		gameService.addGame(game26);
		gameService.addGame(game43);
		gameService.addGame(game44);

		// ---------------------------------------------
		// POULE F
		// ---------------------------------------------

		teams.clear();

		Poule pouleF = new Poule();
		pouleService.addPoule(pouleF);

		Team allemagne = new Team(21, "Allemagne", 1, pouleF);
		Team mexique = new Team(22, "Mexique", 2, pouleF);
		Team suede = new Team(23, "Suède", 3, pouleF);
		Team coreeSud = new Team(24, "Corée du Sud", 4, pouleF);

		teamService.addTeam(allemagne);
		teamService.addTeam(mexique);
		teamService.addTeam(suede);
		teamService.addTeam(coreeSud);

		players.clear();

		player1 = new Player("Neuer", "Manuel", "G", 1, allemagne);
		player2 = new Player("Plattenhardt", "Marvin", "D", 2, allemagne);
		player3 = new Player("Hector", "Jonas", "D", 3, allemagne);
		player4 = new Player("Ginter", "Matthias", "D", 4, allemagne);
		player5 = new Player("Hummels", "Mats", "D", 5, allemagne);
		player6 = new Player("Khedira", "Sami", "D", 6, allemagne);
		player7 = new Player("Draxler", "Julian", "M", 7, allemagne);
		player8 = new Player("Kroos", "Toni", "M", 8, allemagne);
		player9 = new Player("Werner", "Timo", "A", 9, allemagne);
		player10 = new Player("Ozil", "Mesut", "M", 10, allemagne);
		player11 = new Player("Reus", "Marco", "A", 11, allemagne);
		player12 = new Player("Trapp", "Kevin", "G", 12, allemagne);
		player13 = new Player("Mueller", "Thomas", "M", 13, allemagne);
		player14 = new Player("Goretzka", "Leon", "M", 14, allemagne);
		player15 = new Player("Suele", "Niklas", "D", 15, allemagne);
		player16 = new Player("Ruediger", "Antonio", "D", 16, allemagne);
		player17 = new Player("Boateng", "Jerome", "D", 17, allemagne);
		player18 = new Player("Kimmich", "Joshua", "D", 18, allemagne);
		player19 = new Player("Rudy", "Sebastian", "M", 19, allemagne);
		player20 = new Player("Brandt", "Julian", "M", 20, allemagne);
		player21 = new Player("Guendogan", "Ilkay", "M", 21, allemagne);
		player22 = new Player("Ter Stegen", "Marc-Andre", "G", 22, allemagne);
		player23 = new Player("Gomez", "Mario", "A", 23, allemagne);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		allemagne.setPlayers(players);
		teamService.updateTeamPlayers(allemagne);

		players.clear();

		player1 = new Player("Corona", "Jose", "G", 1, mexique);
		player2 = new Player("Ayala", "Hugo", "D", 2, mexique);
		player3 = new Player("Salcedo", "Carlos", "D", 3, mexique);
		player4 = new Player("Marquez", "Rafael", "D", 4, mexique);
		player5 = new Player("Reyes", "Diego", "D", 5, mexique);
		player6 = new Player("Dos Santos", "Jonathan", "M", 6, mexique);
		player7 = new Player("Layun", "Miguel", "M", 7, mexique);
		player8 = new Player("Fabian", "Marco", "A", 8, mexique);
		player9 = new Player("Raul", "Jimenez", "A", 9, mexique);
		player10 = new Player("Dos Santos", "Giovani", "M", 10, mexique);
		player11 = new Player("Vela", "Carlos", "A", 11, mexique);
		player12 = new Player("Talavera", "Alfredo", "G", 12, mexique);
		player13 = new Player("Ochoa", "Guillermo", "G", 13, mexique);
		player14 = new Player("Hernandez", "Javier", "A", 14, mexique);
		player15 = new Player("Moreno", "Hector", "D", 15, mexique);
		player16 = new Player("Herrera", "Hector", "D", 16, mexique);
		player17 = new Player("Corona", "Jesus", "M", 17, mexique);
		player18 = new Player("Guardado", "Andres", "M", 18, mexique);
		player19 = new Player("Peralta", "Oribe", "A", 19, mexique);
		player20 = new Player("Aquino", "Javier", "M", 20, mexique);
		player21 = new Player("Alvarez", "Edson", "D", 21, mexique);
		player22 = new Player("Lozano", "Hirving", "A", 22, mexique);
		player23 = new Player("Gallardo", "Jesus", "M", 23, mexique);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		mexique.setPlayers(players);
		teamService.updateTeamPlayers(mexique);

		players.clear();

		player1 = new Player("Olsen", "Robin", "G", 1, suede);
		player2 = new Player("Lustig", "Mikael", "D", 2, suede);
		player3 = new Player("Lindelof", "Victor", "D", 3, suede);
		player4 = new Player("Granqvist", "Andreas", "D", 4, suede);
		player5 = new Player("Olsson", "Martin", "D", 5, suede);
		player6 = new Player("Augustinsson", "Ludwig", "D", 6, suede);
		player7 = new Player("Larsson", "Sebastian", "M", 7, suede);
		player8 = new Player("Ekdal", "Albin", "M", 8, suede);
		player9 = new Player("Berg", "Marcus", "A", 9, suede);
		player10 = new Player("Forsberg", "Emil", "M", 10, suede);
		player11 = new Player("Guidetti", "John", "A", 11, suede);
		player12 = new Player("Johnsson", "Karl-Johan", "G", 12, suede);
		player13 = new Player("Svensson", "Gustav", "M", 13, suede);
		player14 = new Player("Helander", "Filip", "D", 14, suede);
		player15 = new Player("Hiljemark", "Oscar", "M", 15, suede);
		player16 = new Player("Krafth", "Emil", "D", 16, suede);
		player17 = new Player("Calesson", "Viktor", "M", 17, suede);
		player18 = new Player("Jansson", "Pontus", "D", 18, suede);
		player19 = new Player("Rohden", "Marcus", "M", 19, suede);
		player20 = new Player("Toivonen", "Ola", "A", 20, suede);
		player21 = new Player("Durmaz", "Jimmy", "M", 21, suede);
		player22 = new Player("Kiese Thelin", "Isaac", "A", 22, suede);
		player23 = new Player("Nordfeldt", "Kristoffer", "G", 23, suede);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		suede.setPlayers(players);
		teamService.updateTeamPlayers(suede);

		players.clear();

		player1 = new Player("Kim", "Seunggyu", "G", 1, coreeSud);
		player2 = new Player("Lee", "Yong", "D", 2, coreeSud);
		player3 = new Player("Jung", "Seunghyu", "D", 3, coreeSud);
		player4 = new Player("Oh", "Bansuk", "D", 4, coreeSud);
		player5 = new Player("Yun", "Youngsun", "D", 5, coreeSud);
		player6 = new Player("Park", "Jooho", "D", 6, coreeSud);
		player7 = new Player("Son", "Heungmin", "A", 7, coreeSud);
		player8 = new Player("Ju", "Sejong", "M", 8, coreeSud);
		player9 = new Player("Kim", "Shinwook", "A", 9, coreeSud);
		player10 = new Player("Lee", "Seungwoo", "M", 10, coreeSud);
		player11 = new Player("Hwang", "Heechan", "A", 11, coreeSud);
		player12 = new Player("Kim", "Minwoo", "D", 12, coreeSud);
		player13 = new Player("Koo", "Jacheol", "M", 13, coreeSud);
		player14 = new Player("Hong", "Chul", "D", 14, coreeSud);
		player15 = new Player("Jung", "Wooyoung", "M", 15, coreeSud);
		player16 = new Player("Ki", "Sungyueng", "M", 16, coreeSud);
		player17 = new Player("Lee", "Jaesung", "M", 17, coreeSud);
		player18 = new Player("Moon", "Seonmin", "M", 18, coreeSud);
		player19 = new Player("Kim", "Younggwon", "D", 19, coreeSud);
		player20 = new Player("Jang", "Hyunsoo", "D", 20, coreeSud);
		player21 = new Player("Kim", "Jinhyeon", "G", 21, coreeSud);
		player22 = new Player("Go", "Yohan", "D", 22, coreeSud);
		player23 = new Player("Jo", "Hyeonwoo", "G", 23, coreeSud);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		coreeSud.setPlayers(players);
		teamService.updateTeamPlayers(coreeSud);

		teams.add(allemagne);
		teams.add(mexique);
		teams.add(suede);
		teams.add(coreeSud);
		pouleF.setName("F");
		pouleF.setTeams(teams);

		pouleService.updatePoule(pouleF);

		Game game10 = new Game(10, "Match 10", allemagne, mexique, pouleF, LocalDateTime.of(2018, 6, 17, 17, 0),
				"Moscou", true);
		Game game12 = new Game(12, "Match 12", suede, coreeSud, pouleF, LocalDateTime.of(2018, 6, 18, 14, 0),
				"Nijni Novgorod", true);
		Game game28 = new Game(28, "Match 28", allemagne, suede, pouleF, LocalDateTime.of(2018, 6, 23, 17, 0), "Sotchi",
				true);
		Game game29 = new Game(29, "Match 29", coreeSud, mexique, pouleF, LocalDateTime.of(2018, 6, 23, 20, 0),
				"Rostov-sur-le-Don", true);
		Game game41 = new Game(41, "Match 41", coreeSud, allemagne, pouleF, LocalDateTime.of(2018, 6, 27, 16, 0),
				"Kazan", true);
		Game game42 = new Game(42, "Match 42", mexique, suede, pouleF, LocalDateTime.of(2018, 6, 27, 16, 0),
				"Ekaterinbourg", true);

		gameService.addGame(game10);
		gameService.addGame(game12);
		gameService.addGame(game28);
		gameService.addGame(game29);
		gameService.addGame(game41);
		gameService.addGame(game42);

		// ---------------------------------------------
		// POULE G
		// ---------------------------------------------

		teams.clear();
		Poule pouleG = new Poule();
		pouleService.addPoule(pouleG);

		Team belgique = new Team(25, "Belgique", 1, pouleG);
		Team panama = new Team(26, "Panama", 2, pouleG);
		Team tunisie = new Team(27, "Tunisie", 3, pouleG);
		Team angleterre = new Team(28, "Angleterre", 4, pouleG);

		teamService.addTeam(belgique);
		teamService.addTeam(panama);
		teamService.addTeam(tunisie);
		teamService.addTeam(angleterre);

		players.clear();

		player1 = new Player("Courtois", "Thibaut", "G", 1, belgique);
		player2 = new Player("Alderweireld", "Toby", "D", 2, belgique);
		player3 = new Player("Vermaelen", "Thomas", "D", 3, belgique);
		player4 = new Player("Kompany", "Vincent", "D", 4, belgique);
		player5 = new Player("Vertonghen", "Jan", "D", 5, belgique);
		player6 = new Player("Witsel", "Axel", "M", 6, belgique);
		player7 = new Player("De Bruyne", "Kevin", "M", 7, belgique);
		player8 = new Player("Fellaini", "Marouane", "M", 8, belgique);
		player9 = new Player("Lukaku", "Romelu", "A", 9, belgique);
		player10 = new Player("Hazard", "Eden", "A", 10, belgique);
		player11 = new Player("Carrasco", "Yannick", "M", 11, belgique);
		player12 = new Player("Mignolet", "Simon", "G", 12, belgique);
		player13 = new Player("Casteels", "Koen", "G", 13, belgique);
		player14 = new Player("Mertens", "Dris", "A", 14, belgique);
		player15 = new Player("Meunier", "Thomas", "D", 15, belgique);
		player16 = new Player("Hazard", "Thorgan", "M", 16, belgique);
		player17 = new Player("Tielemans", "Youri", "M", 17, belgique);
		player18 = new Player("Januzaj", "Adnan", "A", 18, belgique);
		player19 = new Player("Dembale", "Moussa", "M", 19, belgique);
		player20 = new Player("Boyata", "Dedryck", "D", 20, belgique);
		player21 = new Player("Batshuayi", "Michy", "A", 21, belgique);
		player22 = new Player("Chadli", "Nacer", "M", 22, belgique);
		player23 = new Player("Dendoncker", "Leander", "D", 23, belgique);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		belgique.setPlayers(players);
		teamService.updateTeamPlayers(belgique);

		players.clear();

		player1 = new Player("Penedo", "Jaime", "G", 1, panama);
		player2 = new Player("Murillo", "Michael", "D", 2, panama);
		player3 = new Player("Cummings", "Harold", "D", 3, panama);
		player4 = new Player("Escobar", "Fidel", "D", 4, panama);
		player5 = new Player("Torres", "Roman", "D", 5, panama);
		player6 = new Player("Gomez", "Gabriel", "M", 6, panama);
		player7 = new Player("Perez", "Blas", "A", 7, panama);
		player8 = new Player("Barcenas", "Edgar", "M", 8, panama);
		player9 = new Player("Torres", "Gabriel", "A", 9, panama);
		player10 = new Player("Diaz", "Ismael", "A", 10, panama);
		player11 = new Player("Cooper", "Armando", "M", 11, panama);
		player12 = new Player("Calderon", "Jose", "G", 12, panama);
		player13 = new Player("Machado", "Adolfo", "D", 13, panama);
		player14 = new Player("Pimentel", "Valentin", "M", 14, panama);
		player15 = new Player("Davis", "Eric", "D", 15, panama);
		player16 = new Player("Arroyo", "Abdiel", "A", 16, panama);
		player17 = new Player("Ovalle", "Luis", "D", 17, panama);
		player18 = new Player("Tejada", "Luis", "A", 18, panama);
		player19 = new Player("Quintero", "Alberto", "M", 19, panama);
		player20 = new Player("Godoy", "Anibal", "M", 20, panama);
		player21 = new Player("Rodriguez", "Jose Luis", "M", 21, panama);
		player22 = new Player("Rodriguez", "Alex", "G", 22, panama);
		player23 = new Player("Baloy", "Felipe", "D", 23, panama);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		panama.setPlayers(players);
		teamService.updateTeamPlayers(panama);

		players.clear();

		player1 = new Player("Ben Mustapha", "Farouk", "G", 1, tunisie);
		player2 = new Player("Ben Youssef", "Syam", "D", 2, tunisie);
		player3 = new Player("Ben Alouane", "Yohan", "D", 3, tunisie);
		player4 = new Player("Meriah", "Yassine", "D", 4, tunisie);
		player5 = new Player("Haddadi", "Oussama", "D", 5, tunisie);
		player6 = new Player("Bedoui", "Rami", "D", 6, tunisie);
		player7 = new Player("Khaoui", "Saifeddine", "A", 7, tunisie);
		player8 = new Player("Ben Youssef", "Fakhreddine", "A", 8, tunisie);
		player9 = new Player("Badri", "Anice", "M", 9, tunisie);
		player10 = new Player("Khazri", "Wahbi", "A", 10, tunisie);
		player11 = new Player("Bronn", "Dylan", "D", 11, tunisie);
		player12 = new Player("Maaloul", "Ali", "D", 12, tunisie);
		player13 = new Player("Sassi", "Ferjani", "M", 13, tunisie);
		player14 = new Player("Ben Amor", "Mohamed", "M", 14, tunisie);
		player15 = new Player("Khalil", "Ahmed", "A", 15, tunisie);
		player16 = new Player("Mathlouthi", "Aymen", "G", 16, tunisie);
		player17 = new Player("Skhiri", "Ellyes", "M", 17, tunisie);
		player18 = new Player("Srarfi", "Bassem", "A", 18, tunisie);
		player19 = new Player("Khalifa", "Saber", "A", 19, tunisie);
		player20 = new Player("Chaaleli", "Ghaylen", "A", 20, tunisie);
		player21 = new Player("Naguez", "Hamdi", "D", 21, tunisie);
		player22 = new Player("Hassen", "Mouez", "G", 22, tunisie);
		player23 = new Player("Sliti", "Naim", "A", 23, tunisie);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		tunisie.setPlayers(players);
		teamService.updateTeamPlayers(tunisie);

		players.clear();

		player1 = new Player("Pickford", "Jordan", "G", 1, angleterre);
		player2 = new Player("Walker", "Kyle", "D", 2, angleterre);
		player3 = new Player("Rose", "Danny", "D", 3, angleterre);
		player4 = new Player("Dier", "Eric", "M", 4, angleterre);
		player5 = new Player("Stones", "John", "D", 5, angleterre);
		player6 = new Player("Maguire", "Harry", "D", 6, angleterre);
		player7 = new Player("Lingard", "Jesse", "M", 7, angleterre);
		player8 = new Player("Henderson", "Jordan", "M", 8, angleterre);
		player9 = new Player("Kane", "Harry", "A", 9, angleterre);
		player10 = new Player("Sterling", "Raheem", "A", 10, angleterre);
		player11 = new Player("Vardy", "Jamie", "A", 11, angleterre);
		player12 = new Player("Trippier", "Kieran", "D", 12, angleterre);
		player13 = new Player("Butland", "Jack", "G", 13, angleterre);
		player14 = new Player("Welbeck", "Danny", "A", 14, angleterre);
		player15 = new Player("Cahill", "Gary", "D", 15, angleterre);
		player16 = new Player("Jones", "Phil", "D", 16, angleterre);
		player17 = new Player("Delph", "Fabian", "D", 17, angleterre);
		player18 = new Player("Young", "Ashley", "D", 18, angleterre);
		player19 = new Player("Rashford", "Marcus", "A", 19, angleterre);
		player20 = new Player("Alli", "Dele", "M", 20, angleterre);
		player21 = new Player("Loftus-Cheek", "Ruben", "M", 21, angleterre);
		player22 = new Player("Alexander-Arnold", "Trent", "D", 22, angleterre);
		player23 = new Player("Pope", "Nick", "G", 23, angleterre);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		angleterre.setPlayers(players);
		teamService.updateTeamPlayers(angleterre);

		teams.add(belgique);
		teams.add(panama);
		teams.add(tunisie);
		teams.add(angleterre);
		pouleG.setName("G");
		pouleG.setTeams(teams);

		pouleService.updatePoule(pouleG);

		Game game13 = new Game(13, "Match 13", belgique, panama, pouleG, LocalDateTime.of(2018, 6, 18, 17, 0), "Sotchi",
				true);
		Game game14 = new Game(14, "Match 14", tunisie, angleterre, pouleG, LocalDateTime.of(2018, 6, 18, 20, 0),
				"Volgograd", true);
		Game game27 = new Game(27, "Match 27", belgique, tunisie, pouleG, LocalDateTime.of(2018, 6, 23, 14, 0),
				"Moscou", true);
		Game game30 = new Game(30, "Match 30", angleterre, panama, pouleG, LocalDateTime.of(2018, 6, 24, 14, 0),
				"Nijni Novgorod", true);
		Game game47 = new Game(47, "Match 47", angleterre, belgique, pouleG, LocalDateTime.of(2018, 6, 28, 20, 0),
				"Kaliningrad", true);
		Game game48 = new Game(48, "Match 48", panama, tunisie, pouleG, LocalDateTime.of(2018, 6, 28, 20, 0), "Saransk",
				true);

		gameService.addGame(game13);
		gameService.addGame(game14);
		gameService.addGame(game27);
		gameService.addGame(game30);
		gameService.addGame(game47);
		gameService.addGame(game48);

		// ---------------------------------------------
		// POULE H
		// ---------------------------------------------

		teams.clear();
		Poule pouleH = new Poule();
		pouleService.addPoule(pouleH);

		Team pologne = new Team(29, "Pologne", 1, pouleH);
		Team senegal = new Team(30, "Sénégal", 2, pouleH);
		Team colombie = new Team(31, "Colombie", 3, pouleH);
		Team japon = new Team(32, "Japon", 4, pouleH);

		teamService.addTeam(pologne);
		teamService.addTeam(senegal);
		teamService.addTeam(colombie);
		teamService.addTeam(japon);

		players.clear();

		player1 = new Player("Szczesny", "Wojciech", "G", 1, pologne);
		player2 = new Player("Pazdan", "Michael", "D", 2, pologne);
		player3 = new Player("Jedrzejczyk", "Artur", "D", 3, pologne);
		player4 = new Player("Cionek", "Thiago", "D", 4, pologne);
		player5 = new Player("Bednarek", "Jan", "D", 5, pologne);
		player6 = new Player("Goralski", "Jacek", "M", 6, pologne);
		player7 = new Player("Milik", "Arkadiusz", "A", 7, pologne);
		player8 = new Player("Linetty", "Karol", "M", 8, pologne);
		player9 = new Player("Lewandowski", "Robert", "A", 9, pologne);
		player10 = new Player("Krychowiak", "Grzegorz", "M", 10, pologne);
		player11 = new Player("Grosicki", "Kamil", "M", 11, pologne);
		player12 = new Player("Bialkowski", "Bartosz", "G", 12, pologne);
		player13 = new Player("Rybus", "Maciej", "M", 13, pologne);
		player14 = new Player("Teodorczyk", "Kamil", "A", 14, pologne);
		player15 = new Player("Glik", "Kamil", "D", 15, pologne);
		player16 = new Player("Blaszczykowski", "Jakub", "M", 16, pologne);
		player17 = new Player("Peszko", "Slawomir", "M", 17, pologne);
		player18 = new Player("Bereszynski", "Bartosz", "D", 18, pologne);
		player19 = new Player("Zielinski", "Piotr", "M", 19, pologne);
		player20 = new Player("Piszczek", "Lukasz", "D", 20, pologne);
		player21 = new Player("Kurzawa", "Rafal", "M", 21, pologne);
		player22 = new Player("Fabianski", "Lukasz", "G", 22, pologne);
		player23 = new Player("Kownacki", "Dawid", "A", 23, pologne);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		pologne.setPlayers(players);
		teamService.updateTeamPlayers(pologne);

		players.clear();

		player1 = new Player("Diallo", "Abdoulaye", "G", 1, senegal);
		player2 = new Player("Ciss", "Saliou", "D", 2, senegal);
		player3 = new Player("Koulibaly", "Kalidou", "D", 3, senegal);
		player4 = new Player("Mbodji", "Kara", "D", 4, senegal);
		player5 = new Player("Gueye", "Idrissa Gana", "M", 5, senegal);
		player6 = new Player("Sane", "Salif", "M", 6, senegal);
		player7 = new Player("Sow", "Moussa", "A", 7, senegal);
		player8 = new Player("Kouyate", "Cheikhou", "M", 8, senegal);
		player9 = new Player("Diouf", "Mame", "A", 9, senegal);
		player10 = new Player("Mane", "Sadio", "A", 10, senegal);
		player11 = new Player("Ndoye", "Cheikh", "M", 11, senegal);
		player12 = new Player("Sabaly", "Youssouf", "D", 12, senegal);
		player13 = new Player("Ndiaye", "Alfred", "M", 13, senegal);
		player14 = new Player("Konate", "Moussa", "A", 14, senegal);
		player15 = new Player("Sakho", "Diafra", "A", 15, senegal);
		player16 = new Player("Ndiaye", "Khadim", "G", 16, senegal);
		player17 = new Player("Ndiaye", "Pape Alioune", "M", 17, senegal);
		player18 = new Player("Sarr", "Ismaila", "A", 18, senegal);
		player19 = new Player("Niang", "Mbaye", "A", 19, senegal);
		player20 = new Player("Balde", "Keita", "A", 20, senegal);
		player21 = new Player("Gassama", "Lamine", "D", 21, senegal);
		player22 = new Player("Wague", "Moussa", "D", 22, senegal);
		player23 = new Player("Gomis", "Alfred", "G", 23, senegal);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		senegal.setPlayers(players);
		teamService.updateTeamPlayers(senegal);

		players.clear();

		player1 = new Player("Ospina", "David", "G", 1, colombie);
		player2 = new Player("Zapata", "Cristian", "D", 2, colombie);
		player3 = new Player("Murillo", "Oscar", "D", 3, colombie);
		player4 = new Player("Arias", "Santiago", "D", 4, colombie);
		player5 = new Player("Barrios", "Wilmar", "M", 5, colombie);
		player6 = new Player("Sanchez", "Carlos", "M", 6, colombie);
		player7 = new Player("Bacca", "Carlos", "A", 7, colombie);
		player8 = new Player("Aguilar", "Abel", "M", 8, colombie);
		player9 = new Player("Falcao", "Radamel", "A", 9, colombie);
		player10 = new Player("Rodriguez", "James", "M", 10, colombie);
		player11 = new Player("Cuadrado", "Juan", "M", 11, colombie);
		player12 = new Player("Vargas", "Camilo", "G", 12, colombie);
		player13 = new Player("Mina", "Yerry", "D", 13, colombie);
		player14 = new Player("Muriel", "Luis", "A", 14, colombie);
		player15 = new Player("Uribe", "Mateus", "M", 15, colombie);
		player16 = new Player("Lerma", "Jefferson", "M", 16, colombie);
		player17 = new Player("Mojica", "Johan", "D", 17, colombie);
		player18 = new Player("Fabra", "Frank", "D", 18, colombie);
		player19 = new Player("Borja", "Miguel", "A", 19, colombie);
		player20 = new Player("Quintero", "Juan", "M", 20, colombie);
		player21 = new Player("Izquierdo", "Jose", "A", 21, colombie);
		player22 = new Player("Cuadrado", "Jose", "G", 22, colombie);
		player23 = new Player("Sanchez", "Davinson", "D", 23, colombie);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		colombie.setPlayers(players);
		teamService.updateTeamPlayers(colombie);

		players.clear();

		player1 = new Player("Kawashima", "Eiji", "G", 1, japon);
		player2 = new Player("Ueda", "Naomichi", "D", 2, japon);
		player3 = new Player("Shoji", "Gen", "D", 3, japon);
		player4 = new Player("Honda", "Keisuke", "M", 4, japon);
		player5 = new Player("Nagatomo", "Yuto", "D", 5, japon);
		player6 = new Player("Endo", "Wataru", "D", 6, japon);
		player7 = new Player("Shibasaki", "Gaku", "M", 7, japon);
		player8 = new Player("Haraguchi", "Genki", "M", 8, japon);
		player9 = new Player("Okazaki", "Shinji", "A", 9, japon);
		player10 = new Player("Kagawa", "Shinji", "M", 10, japon);
		player11 = new Player("Usami", "Takashi", "M", 11, japon);
		player12 = new Player("Higashiguchi", "Massaki", "G", 12, japon);
		player13 = new Player("Muto", "Yoshinori", "A", 13, japon);
		player14 = new Player("Inui", "Takashi", "M", 14, japon);
		player15 = new Player("Osako", "Yuka", "A", 15, japon);
		player16 = new Player("Yamaguchi", "Hotaru", "M", 16, japon);
		player17 = new Player("Hasebe", "Makoto", "M", 17, japon);
		player18 = new Player("Oshima", "Ryota", "M", 18, japon);
		player19 = new Player("Sakai", "Hiroki", "D", 19, japon);
		player20 = new Player("Makino", "Tomoaki", "D", 20, japon);
		player21 = new Player("Sakai", "Gotoku", "D", 21, japon);
		player22 = new Player("Yoshida", "Maya", "D", 22, japon);
		player23 = new Player("Nakamura", "Kosuke", "G", 23, japon);

		playerService.addPlayer(player1);
		playerService.addPlayer(player2);
		playerService.addPlayer(player3);
		playerService.addPlayer(player4);
		playerService.addPlayer(player5);
		playerService.addPlayer(player6);
		playerService.addPlayer(player7);
		playerService.addPlayer(player8);
		playerService.addPlayer(player9);
		playerService.addPlayer(player10);
		playerService.addPlayer(player11);
		playerService.addPlayer(player12);
		playerService.addPlayer(player13);
		playerService.addPlayer(player14);
		playerService.addPlayer(player15);
		playerService.addPlayer(player16);
		playerService.addPlayer(player17);
		playerService.addPlayer(player18);
		playerService.addPlayer(player19);
		playerService.addPlayer(player20);
		playerService.addPlayer(player21);
		playerService.addPlayer(player22);
		playerService.addPlayer(player23);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);
		players.add(player5);
		players.add(player6);
		players.add(player7);
		players.add(player8);
		players.add(player9);
		players.add(player10);
		players.add(player11);
		players.add(player12);
		players.add(player13);
		players.add(player14);
		players.add(player15);
		players.add(player16);
		players.add(player17);
		players.add(player18);
		players.add(player19);
		players.add(player20);
		players.add(player21);
		players.add(player22);
		players.add(player23);
		japon.setPlayers(players);
		teamService.updateTeamPlayers(japon);

		teams.add(pologne);
		teams.add(senegal);
		teams.add(colombie);
		teams.add(japon);
		pouleH.setName("H");
		pouleH.setTeams(teams);

		pouleService.updatePoule(pouleH);

		Game game15 = new Game(15, "Match 15", pologne, senegal, pouleH, LocalDateTime.of(2018, 6, 19, 14, 0), "Moscou",
				true);
		Game game16 = new Game(16, "Match 16", colombie, japon, pouleH, LocalDateTime.of(2018, 6, 19, 17, 0), "Saransk",
				true);
		Game game31 = new Game(31, "Match 31", japon, senegal, pouleH, LocalDateTime.of(2018, 6, 24, 17, 0),
				"Ekaterinbourg", true);
		Game game32 = new Game(32, "Match 32", pologne, colombie, pouleH, LocalDateTime.of(2018, 6, 24, 20, 0), "Kazan",
				true);
		Game game45 = new Game(45, "Match 45", japon, pologne, pouleH, LocalDateTime.of(2018, 6, 28, 16, 0),
				"Volgograd", true);
		Game game46 = new Game(46, "Match 46", senegal, colombie, pouleH, LocalDateTime.of(2018, 6, 28, 16, 0),
				"Samara", true);

		gameService.addGame(game15);
		gameService.addGame(game16);
		gameService.addGame(game31);
		gameService.addGame(game32);
		gameService.addGame(game45);
		gameService.addGame(game46);

		// ---------------------------------------------
		// HUITIEMES
		// ---------------------------------------------

		Game game49 = new Game(49, "Huitième 1", null, null, null, LocalDateTime.of(2018, 6, 30, 16, 0), "Kazan",
				false);
		Game game50 = new Game(50, "Huitième 2", null, null, null, LocalDateTime.of(2018, 6, 30, 20, 0), "Sotchi",
				false);
		Game game51 = new Game(51, "Huitième 3", null, null, null, LocalDateTime.of(2018, 7, 1, 16, 0), "Moscou",
				false);
		Game game52 = new Game(52, "Huitième 4", null, null, null, LocalDateTime.of(2018, 7, 1, 20, 0),
				"Ninji Novgorod", false);
		Game game53 = new Game(53, "Huitième 5", null, null, null, LocalDateTime.of(2018, 7, 2, 16, 0), "Samara",
				false);
		Game game54 = new Game(54, "Huitième 6", null, null, null, LocalDateTime.of(2018, 7, 2, 20, 0), "Rostov",
				false);
		Game game55 = new Game(55, "Huitième 7", null, null, null, LocalDateTime.of(2018, 7, 3, 16, 0),
				"Saint-Pétersbourg", false);
		Game game56 = new Game(56, "Huitième 8", null, null, null, LocalDateTime.of(2018, 7, 3, 20, 0), "Moscou",
				false);

		gameService.addGame(game49);
		gameService.addGame(game50);
		gameService.addGame(game51);
		gameService.addGame(game52);
		gameService.addGame(game53);
		gameService.addGame(game54);
		gameService.addGame(game55);
		gameService.addGame(game56);
		// ---------------------------------------------
		// QUART
		// ---------------------------------------------

		Game game57 = new Game(57, "Quart 1", null, null, null, LocalDateTime.of(2018, 7, 6, 16, 0), "Ninji Novgorod",
				false);
		Game game58 = new Game(58, "Quart 2", null, null, null, LocalDateTime.of(2018, 7, 6, 20, 0), "Kazan", false);
		Game game59 = new Game(59, "Quart 3", null, null, null, LocalDateTime.of(2018, 7, 7, 16, 0), "Sotchi", false);
		Game game60 = new Game(60, "Quart 4", null, null, null, LocalDateTime.of(2018, 7, 7, 20, 0), "Samara", false);

		gameService.addGame(game57);
		gameService.addGame(game58);
		gameService.addGame(game59);
		gameService.addGame(game60);

		// ---------------------------------------------
		// DEMI
		// ---------------------------------------------

		Game game61 = new Game(61, "Demi 1", null, null, null, LocalDateTime.of(2018, 7, 10, 20, 0),
				"Saint-Pétersbourg", false);
		Game game62 = new Game(62, "Demi 2", null, null, null, LocalDateTime.of(2018, 7, 11, 20, 0), "Moscou", false);

		gameService.addGame(game61);
		gameService.addGame(game62);

		// ---------------------------------------------
		// FINALES
		// ---------------------------------------------

		Game game63 = new Game(63, "Petite finale", null, null, null, LocalDateTime.of(2018, 7, 14, 16, 0),
				"Saint-Pétersbourg", false);
		Game game64 = new Game(64, "Finale", null, null, null, LocalDateTime.of(2018, 7, 15, 17, 0), "Moscou", false);

		gameService.addGame(game63);
		gameService.addGame(game64);

		return new ModelAndView("redirect:game/");
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
		return new ModelAndView("redirect:/game/");
	}

	@RequestMapping(value = "/deleteGame", method = RequestMethod.GET)
	public ModelAndView deleteGame(HttpServletRequest request)
	{
		int gameId = Integer.parseInt(request.getParameter("id"));
		gameService.deleteGame(gameId);
		return new ModelAndView("redirect:/game/");
	}

	@RequestMapping(value = "/editGame", method = RequestMethod.GET)
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

	@RequestMapping(value = "/editGame", method = RequestMethod.POST)
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

		return new ModelAndView("redirect:/game/");
	}

	@RequestMapping(value = "/editGameCompo", method = RequestMethod.GET)
	public ModelAndView editGameCompo(HttpServletRequest request)
	{
		int gameId = Integer.parseInt(request.getParameter("id"));
		Game game = gameService.getGame(gameId);

		ModelAndView model = new ModelAndView("gameEditCompo");
		model.addObject("game", game);

		List<Player> goals1 = playerService.getGoals(game.getTeam1());
		model.addObject("goals1", goals1);

		List<Player> defensers1 = playerService.getDefensers(game.getTeam1());
		model.addObject("defensers1", defensers1);

		List<Player> middles1 = playerService.getMiddles(game.getTeam1());
		model.addObject("middles1", middles1);

		List<Player> strikers1 = playerService.getStrikers(game.getTeam1());
		model.addObject("strikers1", strikers1);

		List<Player> goals2 = playerService.getGoals(game.getTeam1());
		model.addObject("goals2", goals2);

		List<Player> defensers2 = playerService.getDefensers(game.getTeam1());
		model.addObject("defensers2", defensers2);

		List<Player> middles2 = playerService.getMiddles(game.getTeam1());
		model.addObject("middles2", middles2);

		List<Player> strikers2 = playerService.getStrikers(game.getTeam1());
		model.addObject("strikers2", strikers2);

		model.addObject("titulars", gameService.getTitulars(game));
		model.addObject("substitutes", gameService.getSubstitutes(game));
		model.addObject("editCompoForm", new GameEditCompoForm(game));

		return model;
	}

	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public ModelAndView resetGameGet(HttpServletRequest request)
	{
		Game game = gameService.getGame(Integer.parseInt(request.getParameter("id")));

		if (game.getGameInPoule())
			teamService.updateTeamsForReset(game);

		game = gameService.resetGame(game);
		playerStatsService.deletePlayerStats(game);

		if (game.getGameInPoule())
			teamService.updateTeamsPositions(game.getPoule());

		return new ModelAndView("redirect:/game/");
	}
}
