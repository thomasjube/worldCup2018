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

import com.tjube.controller.GameEditForm.ScorerPlayer;
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
		
		Map<LocalDate,Collection<Game>> mapGames = new LinkedHashMap<>();
		Game previous = null;
		Collection<Game> games = new ArrayList<>();
		int i = 0;
		for(Game g : listGame)
		{
			if(previous == null)
			{
				games.add(g);
			}
			else if(!previous.getDate().isEqual(g.getDate()))
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
			
			if(i++ == listGame.size()-1)
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
		Collection<Team> teams = new ArrayList<Team>();
		Collection<Player> players = new ArrayList<Player>();
		
		// populate Teams
		
		// ---------------------------------------------
		// POULE A
		// ---------------------------------------------
		
		Poule pouleA = new Poule();
		pouleService.addPoule(pouleA);
		
		Team russie = new Team("Russie",1,pouleA);
		Team arabieSaoudite = new Team("Arabie Saoudite",2,pouleA);
		Team egypte = new Team("Egypte",3,pouleA);
		Team uruguay = new Team("Uruguay",4,pouleA);
		
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
		Player player7 = new Player("Kuziaev", "Daler", "M",7 , russie);
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
		teamService.updateTeam(russie);
		
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
		teamService.updateTeam(arabieSaoudite);
		
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
		teamService.updateTeam(egypte);
		
		players.clear();
		
		player1 = new Player("Muslera", "Fernando", "G", 1, uruguay);
		player2 = new Player("Gimenez", "Jose", "D", 2, uruguay);
		player3 = new Player("Godin", "Diego", "D", 3, uruguay);
		player4 = new Player("Varela", "Guillermo", "D", 4, uruguay);
		player5 = new Player("Sanchez", "Carlos", "M", 5, uruguay);
		player6 = new Player("Bentancur", "Rodrigo", "M", 6, uruguay);
		player7 = new Player("Rodriguez", "Cristian", "M", 7, uruguay);
		player8 = new Player("Nandez", "Nahitan", "M", 8, uruguay);
		player9 = new Player("Suarez", "Luis", "A",9 , uruguay);
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
		teamService.updateTeam(uruguay);
		
		teams.add(russie);
		teams.add(arabieSaoudite);
		teams.add(egypte);
		teams.add(uruguay);
		pouleA.setName("A");
		pouleA.setTeams(teams);
		
		pouleService.updatePoule(pouleA);
		
		Game game1 = new Game("Match 1",russie,arabieSaoudite,pouleA,LocalDateTime.of(2018, 6, 14, 17, 0),"Moscou",true);
		Game game2 = new Game("Match 2",egypte,uruguay,pouleA,LocalDateTime.of(2018,6,15,14,0),"Ekaterinbourg",true);
		Game game17 = new Game("Match 17",russie,egypte,pouleA,LocalDateTime.of(2018,6,19,20,0),"Saint-Pétersbourg",true);
		Game game19 = new Game("Match 19",uruguay,arabieSaoudite,pouleA,LocalDateTime.of(2018,6,20,17,0),"Rostov-sur-le-don",true);
		Game game33 = new Game("Match 33",uruguay,russie,pouleA,LocalDateTime.of(2018,6,25,16,0),"Samara",true);
		Game game34 = new Game("Match 34",arabieSaoudite,egypte,pouleA,LocalDateTime.of(2018,6,25,16,0),"Volgograd",true);
		
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
		
		Team portugal = new Team("Portugal",1,pouleB);
		Team espagne = new Team("Espagne",2,pouleB);
		Team maroc = new Team("Maroc",3,pouleB);
		Team iran = new Team("Iran",4,pouleB);
		
		teamService.addTeam(portugal);
		teamService.addTeam(espagne);
		teamService.addTeam(maroc);
		teamService.addTeam(iran);
		
		players.clear();
		
		player1 = new Player("", "", "G", 1, portugal);
		player2 = new Player("", "", "D", 2, portugal);
		player3 = new Player("", "", "D", 3, portugal);
		player4 = new Player("", "", "D", 4, portugal);
		player5 = new Player("", "", "D", 5, portugal);
		player6 = new Player("", "", "D", 6, portugal);
		player7 = new Player("", "", "M", 7, portugal);
		player8 = new Player("", "", "M", 8, portugal);
		player9 = new Player("", "", "M", 9, portugal);
		player10 = new Player("", "", "A", 10, portugal);
		player11 = new Player("", "", "M", 11, portugal);
		player12 = new Player("", "", "M", 12, portugal);
		player13 = new Player("", "", "D", 13, portugal);
		player14 = new Player("", "", "M", 14, portugal);
		player15 = new Player("", "", "M", 15, portugal);
		player16 = new Player("", "", "M", 16, portugal);
		player17 = new Player("", "", "M", 17, portugal);
		player18 = new Player("", "", "M", 18, portugal);
		player19 = new Player("", "", "A", 19, portugal);
		player20 = new Player("", "", "A", 20, portugal);
		player21 = new Player("", "", "G", 21, portugal);
		player22 = new Player("", "", "G", 22, portugal);
		player23 = new Player("", "", "D", 23, portugal);
		
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
		teamService.updateTeam(portugal);
		
players.clear();
		
		player1 = new Player("", "", "G", 1, espagne);
		player2 = new Player("", "", "D", 2, espagne);
		player3 = new Player("", "", "D", 3, espagne);
		player4 = new Player("", "", "D", 4, espagne);
		player5 = new Player("", "", "D", 5, espagne);
		player6 = new Player("", "", "D", 6, espagne);
		player7 = new Player("", "", "M", 7, espagne);
		player8 = new Player("", "", "M", 8, espagne);
		player9 = new Player("", "", "M", 9, espagne);
		player10 = new Player("", "", "A", 10, espagne);
		player11 = new Player("", "", "M", 11, espagne);
		player12 = new Player("", "", "M", 12, espagne);
		player13 = new Player("", "", "D", 13, espagne);
		player14 = new Player("", "", "M", 14, espagne);
		player15 = new Player("", "", "M", 15, espagne);
		player16 = new Player("", "", "M", 16, espagne);
		player17 = new Player("", "", "M", 17, espagne);
		player18 = new Player("", "", "M", 18, espagne);
		player19 = new Player("", "", "A", 19, espagne);
		player20 = new Player("", "", "A", 20, espagne);
		player21 = new Player("", "", "G", 21, espagne);
		player22 = new Player("", "", "G", 22, espagne);
		player23 = new Player("", "", "D", 23, espagne);
		
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
		teamService.updateTeam(espagne);
		
players.clear();
		
		player1 = new Player("", "", "G", 1, maroc);
		player2 = new Player("", "", "D", 2, maroc);
		player3 = new Player("", "", "D", 3, maroc);
		player4 = new Player("", "", "D", 4, maroc);
		player5 = new Player("", "", "D", 5, maroc);
		player6 = new Player("", "", "D", 6, maroc);
		player7 = new Player("", "", "M", 7, maroc);
		player8 = new Player("", "", "M", 8, maroc);
		player9 = new Player("", "", "M", 9, maroc);
		player10 = new Player("", "", "A", 10, maroc);
		player11 = new Player("", "", "M", 11, maroc);
		player12 = new Player("", "", "M", 12, maroc);
		player13 = new Player("", "", "D", 13, maroc);
		player14 = new Player("", "", "M", 14, maroc);
		player15 = new Player("", "", "M", 15, maroc);
		player16 = new Player("", "", "M", 16, maroc);
		player17 = new Player("", "", "M", 17, maroc);
		player18 = new Player("", "", "M", 18, maroc);
		player19 = new Player("", "", "A", 19, maroc);
		player20 = new Player("", "", "A", 20, maroc);
		player21 = new Player("", "", "G", 21, maroc);
		player22 = new Player("", "", "G", 22, maroc);
		player23 = new Player("", "", "D", 23, maroc);
		
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
		teamService.updateTeam(maroc);
		
		players.clear();
		
		player1 = new Player("", "", "G", 1, iran);
		player2 = new Player("", "", "D", 2, iran);
		player3 = new Player("", "", "D", 3, iran);
		player4 = new Player("", "", "D", 4, iran);
		player5 = new Player("", "", "D", 5, iran);
		player6 = new Player("", "", "D", 6, iran);
		player7 = new Player("", "", "M", 7, iran);
		player8 = new Player("", "", "M", 8, iran);
		player9 = new Player("", "", "M", 9, iran);
		player10 = new Player("", "", "A", 10, iran);
		player11 = new Player("", "", "M", 11, iran);
		player12 = new Player("", "", "M", 12, iran);
		player13 = new Player("", "", "D", 13, iran);
		player14 = new Player("", "", "M", 14, iran);
		player15 = new Player("", "", "M", 15, iran);
		player16 = new Player("", "", "M", 16, iran);
		player17 = new Player("", "", "M", 17, iran);
		player18 = new Player("", "", "M", 18, iran);
		player19 = new Player("", "", "A", 19, iran);
		player20 = new Player("", "", "A", 20, iran);
		player21 = new Player("", "", "G", 21, iran);
		player22 = new Player("", "", "G", 22, iran);
		player23 = new Player("", "", "D", 23, iran);
		
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
		teamService.updateTeam(iran);
		
		teams.add(portugal);
		teams.add(espagne);
		teams.add(maroc);
		teams.add(iran);
		
		pouleB.setName("B");
		pouleB.setTeams(teams);
		
		pouleService.updatePoule(pouleB);
		
		Game game3 = new Game("Match 3",maroc,iran,pouleB,LocalDateTime.of(2018,6,15,17,0),"Saint-Pétersbourg",true);
		Game game4 = new Game("Match 4",portugal,espagne,pouleB,LocalDateTime.of(2018,6,15,20,0),"Sotchi",true);
		Game game18 = new Game("Match 18",portugal,maroc,pouleB,LocalDateTime.of(2018,6,20,14,0),"Moscou",true);
		Game game20 = new Game("Match 20",iran,espagne,pouleB,LocalDateTime.of(2018,6,20,20,0),"Kazan",true);
		Game game35 = new Game("Match 35",iran,portugal,pouleB,LocalDateTime.of(2018,6,25,20,0),"Saransk",true);
		Game game36 = new Game("Match 36",espagne,maroc,pouleB,LocalDateTime.of(2018,6,25,20,0),"Kaliningrad",true);
		
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
		
		Team france = new Team("France",1,pouleC);
		Team australie = new Team("Australie",2,pouleC);
		Team perou = new Team("Pérou",3,pouleC);
		Team danemark = new Team("Danemark",4,pouleC);
		
		teamService.addTeam(france);
		teamService.addTeam(australie);
		teamService.addTeam(perou);
		teamService.addTeam(danemark);
		
		teams.add(france);
		teams.add(australie);
		teams.add(perou);
		teams.add(danemark);
		pouleC.setName("C");
		pouleC.setTeams(teams);
		
		pouleService.updatePoule(pouleC);
		
		Game game5 = new Game("Match 5",france,australie,pouleC,LocalDateTime.of(2018,6,16,12,0),"Kazan",true);
		Game game7 = new Game("Match 7",perou,danemark,pouleC,LocalDateTime.of(2018,6,16,18,0),"Saransk",true);
		Game game21 = new Game("Match 21",danemark,australie,pouleC,LocalDateTime.of(2018,6,21,14,0),"Samara",true);
		Game game22 = new Game("Match 22",france,perou,pouleC,LocalDateTime.of(2018,6,21,17,0),"Ekaterinbourg",true);
		Game game37 = new Game("Match 37",danemark,france,pouleC,LocalDateTime.of(2018,6,26,16,0),"Moscou",true);
		Game game38 = new Game("Match 38",australie,perou,pouleC,LocalDateTime.of(2018,6,26,16,0),"Sotchi",true);
		
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
		
		Team argentine = new Team("Argentine",1,pouleD);
		Team islande = new Team("Islande",2,pouleD);
		Team croatie = new Team("Croatie",3,pouleD);
		Team nigeria = new Team("Nigéria",4,pouleD);
		
		teamService.addTeam(argentine);
		teamService.addTeam(islande);
		teamService.addTeam(croatie);
		teamService.addTeam(nigeria);
		
		teams.add(argentine);
		teams.add(islande);
		teams.add(croatie);
		teams.add(nigeria);
		pouleD.setName("D");
		pouleD.setTeams(teams);
		
		pouleService.updatePoule(pouleD);
		
		Game game6 = new Game("Match 6",argentine,islande,pouleD,LocalDateTime.of(2018,6,16,15,0),"Moscou",true);
		Game game8 = new Game("Match 8",croatie,nigeria,pouleD,LocalDateTime.of(2018,6,16,21,0),"Kaliningrad",true);
		Game game23 = new Game("Match 23",argentine,croatie,pouleD,LocalDateTime.of(2018,6,21,20,0),"Nijni Novgorod",true);
		Game game25 = new Game("Match 25",nigeria,islande,pouleD,LocalDateTime.of(2018,6,22,17,0),"Volgograd",true);
		Game game39 = new Game("Match 39",nigeria,argentine,pouleD,LocalDateTime.of(2018,6,26,20,0),"Saint-Pétersbourg",true);
		Game game40 = new Game("Match 40",islande,croatie,pouleD,LocalDateTime.of(2018,6,26,20,0),"Rostov-sur-le-Don",true);
		
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
		
		Team bresil = new Team("Brésil",1,pouleE);
		Team suisse = new Team("Suisse",2,pouleE);
		Team costaRica = new Team("Costa Rica",3,pouleE);
		Team serbie = new Team("Serbie",4,pouleE);
		
		teamService.addTeam(bresil);
		teamService.addTeam(suisse);
		teamService.addTeam(costaRica);
		teamService.addTeam(serbie);
		
		teams.add(bresil);
		teams.add(suisse);
		teams.add(costaRica);
		teams.add(serbie);
		pouleE.setName("E");
		pouleE.setTeams(teams);
		
		pouleService.updatePoule(pouleE);
		
		
		Game game9 = new Game("Match 9",costaRica,serbie,pouleE,LocalDateTime.of(2018,6,17,14,0),"Samara",true);
		Game game11 = new Game("Match 11",bresil,suisse,pouleE,LocalDateTime.of(2018,6,17,20,0),"Rostov-sur-le-Don",true);
		Game game24 = new Game("Match 24",bresil,costaRica,pouleE,LocalDateTime.of(2018,6,22,14,0),"Saint-Pétersbourg",true);
		Game game26 = new Game("Match 26",serbie,suisse,pouleE,LocalDateTime.of(2018,6,22,20,0),"Kaliningrad",true);
		Game game43 = new Game("Match 43",serbie,bresil,pouleE,LocalDateTime.of(2018,6,27,20,0),"Moscou",true);
		Game game44 = new Game("Match 44",suisse,costaRica,pouleE,LocalDateTime.of(2018,6,27,20,0),"Nijni Novgorod",true);
		
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
		
		Team allemagne = new Team("Allemagne",1,pouleF);
		Team mexique = new Team("Mexique",2,pouleF);
		Team suede = new Team("Suède",3,pouleF);
		Team coreeSud = new Team("Corée du Sud",4,pouleF);
		
		teamService.addTeam(allemagne);
		teamService.addTeam(mexique);
		teamService.addTeam(suede);
		teamService.addTeam(coreeSud);
		
		teams.add(allemagne);
		teams.add(mexique);
		teams.add(suede);
		teams.add(coreeSud);
		pouleF.setName("F");
		pouleF.setTeams(teams);
		
		pouleService.updatePoule(pouleF);
		
		Game game10 = new Game("Match 10",allemagne,mexique,pouleF,LocalDateTime.of(2018,6,17,17,0),"Moscou",true);
		Game game12 = new Game("Match 12",suede,coreeSud,pouleF,LocalDateTime.of(2018,6,18,14,0),"Nijni Novgorod",true);
		Game game28 = new Game("Match 28",allemagne,suede,pouleF,LocalDateTime.of(2018,6,23,17,0),"Sotchi",true);
		Game game29 = new Game("Match 29",coreeSud,mexique,pouleF,LocalDateTime.of(2018,6,23,20,0),"Rostov-sur-le-Don",true);
		Game game41 = new Game("Match 41",coreeSud,allemagne,pouleF,LocalDateTime.of(2018,6,27,16,0),"Kazan",true);
		Game game42 = new Game("Match 42",mexique,suede,pouleF,LocalDateTime.of(2018,6,27,16,0),"Ekaterinbourg",true);
		
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
		
		Team belgique = new Team("Belgique",1,pouleG);
		Team panama = new Team("Panama",2,pouleG);
		Team tunisie = new Team("Tunisie",3,pouleG);
		Team angleterre = new Team("Angleterre",4,pouleG);
		
		teamService.addTeam(belgique);
		teamService.addTeam(panama);
		teamService.addTeam(tunisie);
		teamService.addTeam(angleterre);
		
		teams.add(belgique);
		teams.add(panama);
		teams.add(tunisie);
		teams.add(angleterre);
		pouleG.setName("G");
		pouleG.setTeams(teams);
		
		pouleService.updatePoule(pouleG);
		
		
		Game game13 = new Game("Match 13",belgique,panama,pouleG,LocalDateTime.of(2018,6,18,17,0),"Sotchi",true);
		Game game14 = new Game("Match 14",tunisie,angleterre,pouleG,LocalDateTime.of(2018,6,18,20,0),"Volgograd",true);
		Game game27 = new Game("Match 27",belgique,tunisie,pouleG,LocalDateTime.of(2018,6,23,14,0),"Moscou",true);
		Game game30 = new Game("Match 30",angleterre,panama,pouleG,LocalDateTime.of(2018,6,24,14,0),"Nijni Novgorod",true);
		Game game47 = new Game("Match 47",angleterre,belgique,pouleG,LocalDateTime.of(2018,6,28,20,0),"Kaliningrad",true);
		Game game48 = new Game("Match 48",panama,tunisie,pouleG,LocalDateTime.of(2018,6,28,20,0),"Saransk",true);
		
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
		
		Team pologne = new Team("Pologne",1,pouleH);
		Team senegal = new Team("Sénégal",2,pouleH);
		Team colombie = new Team("Colombie",3,pouleH);
		Team japon = new Team("Japon",4,pouleH);
		
		teamService.addTeam(pologne);
		teamService.addTeam(senegal);
		teamService.addTeam(colombie);
		teamService.addTeam(japon);
		
		teams.add(pologne);
		teams.add(senegal);
		teams.add(colombie);
		teams.add(japon);
		pouleH.setName("H");
		pouleH.setTeams(teams);

		pouleService.updatePoule(pouleH);
		
		Game game15 = new Game("Match 15",pologne,senegal,pouleH,LocalDateTime.of(2018,6,19,14,0),"Moscou",true);
		Game game16 = new Game("Match 16",colombie,japon,pouleH,LocalDateTime.of(2018,6,19,17,0),"Saransk",true);
		Game game31 = new Game("Match 31",japon,senegal,pouleH,LocalDateTime.of(2018,6,24,17,0),"Ekaterinbourg",true);
		Game game32 = new Game("Match 32",pologne,colombie,pouleH,LocalDateTime.of(2018,6,24,20,0),"Kazan",true);
		Game game45 = new Game("Match 45",japon,pologne,pouleH,LocalDateTime.of(2018,6,28,16,0),"Volgograd",true);
		Game game46 = new Game("Match 46",senegal,colombie,pouleH,LocalDateTime.of(2018,6,28,16,0),"Samara",true);
		
		gameService.addGame(game15);
		gameService.addGame(game16);
		gameService.addGame(game31);
		gameService.addGame(game32);
		gameService.addGame(game45);
		gameService.addGame(game46);
		
		// ---------------------------------------------
		// HUITIEMES
		// ---------------------------------------------
		
		Game game49 = new Game("Huitième 1",null,null,null,LocalDateTime.of(2018, 6,30,16,0),"Kazan",false);
		Game game50 = new Game("Huitième 2",null,null,null,LocalDateTime.of(2018, 6,30,20,0),"Sotchi",false);
		Game game51 = new Game("Huitième 3",null,null,null,LocalDateTime.of(2018, 7,1,16,0),"Moscou",false);
		Game game52 = new Game("Huitième 4",null,null,null,LocalDateTime.of(2018, 7,1,20,0),"Ninji Novgorod",false);
		Game game53 = new Game("Huitième 5",null,null,null,LocalDateTime.of(2018, 7,2,16,0),"Samara",false);
		Game game54 = new Game("Huitième 6",null,null,null,LocalDateTime.of(2018, 7,2,20,0),"Rostov",false);
		Game game55 = new Game("Huitième 7",null,null,null,LocalDateTime.of(2018, 7,3,16,0),"Saint-Pétersbourg",false);
		Game game56 = new Game("Huitième 8",null,null,null,LocalDateTime.of(2018, 7,3,20,0),"Moscou",false);
		
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
		
		Game game57 = new Game("Quart 1",null,null,null,LocalDateTime.of(2018, 7,6,16,0),"Ninji Novgorod",false);
		Game game58 = new Game("Quart 2",null,null,null,LocalDateTime.of(2018, 7,6,20,0),"Kazan",false);
		Game game59 = new Game("Quart 3",null,null,null,LocalDateTime.of(2018, 7,7,16,0),"Sotchi",false);
		Game game60 = new Game("Quart 4",null,null,null,LocalDateTime.of(2018, 7,7,20,0),"Samara",false);
		

		gameService.addGame(game57);
		gameService.addGame(game58);
		gameService.addGame(game59);
		gameService.addGame(game60);
		
		// ---------------------------------------------
		// DEMI
		// ---------------------------------------------
		
		Game game61 = new Game("Demi 1",null,null,null,LocalDateTime.of(2018, 7,10,20,0),"Saint-Pétersbourg",false);
		Game game62 = new Game("Demi 2",null,null,null,LocalDateTime.of(2018, 7,11,20,0),"Moscou",false);
		
		gameService.addGame(game61);
		gameService.addGame(game62);
		
		// ---------------------------------------------
		// FINALES
		// ---------------------------------------------
	
		Game game63 = new Game("Petite finale",null,null,null,LocalDateTime.of(2018, 7,14,16,0),"Saint-Pétersbourg",false);
		Game game64 = new Game("Finale",null,null,null,LocalDateTime.of(2018, 7,15,17,0),"Moscou",false);
		
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
		model.addObject("editForm", new GameEditForm(game));

		return model;
	}
	
	@RequestMapping(value = "/editGame", method = RequestMethod.POST)
	public ModelAndView editGamePost(HttpServletRequest request,@ModelAttribute("editForm") GameEditForm gameEditForm)
	{
		Game game = gameService.getGame(gameEditForm.getId());
		ModelAndView model = new ModelAndView("gameEdit");
		model.addObject("game", game);
		
		game = gameService.updateGame(gameEditForm.getId(),gameEditForm.getScore1(),gameEditForm.getScore2(),gameEditForm.isProlong(),gameEditForm.getScoreProlong1(),gameEditForm.getScoreProlong2(),gameEditForm.isPeno(),gameEditForm.getScorePeno1(),gameEditForm.getScorePeno2());
		
		for(ScorerPlayer scorer : gameEditForm.getScorerPlayers1())
		{
			Player scorerPlayer = playerService.getPlayer(scorer.getIdPlayer());
			playerStatsService.addPlayerStats(new PlayerStats(game,scorerPlayer,scorer.getMinute(),Action.GOAL));
			
			Player passer = playerService.getPlayer(scorer.getIdPasser());
			playerStatsService.addPlayerStats(new PlayerStats(game,passer,scorer.getMinute(),Action.PASS));
		}
		
		for(ScorerPlayer scorer : gameEditForm.getScorerPlayers2())
		{
			Player scorerPlayer = playerService.getPlayer(scorer.getIdPlayer());
			playerStatsService.addPlayerStats(new PlayerStats(game,scorerPlayer,scorer.getMinute(),Action.GOAL));
			
			Player passer = playerService.getPlayer(scorer.getIdPasser());
			playerStatsService.addPlayerStats(new PlayerStats(game,passer,scorer.getMinute(),Action.PASS));
		}
		
		if(game.getGameInPoule())
		{
			teamService.updateTeams(game);
			teamService.updateTeamsPositions(game.getPoule());
		}
		
		return new ModelAndView("redirect:/game/");
	}
	
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public ModelAndView resetGameGet(HttpServletRequest request)
	{
		Game game = gameService.getGame(Integer.parseInt(request.getParameter("id")));
		
		if(game.getGameInPoule())
			teamService.updateTeamsForReset(game);
		
		game = gameService.resetGame(game);
		
		if(game.getGameInPoule())
			teamService.updateTeamsPositions(game.getPoule());
		
		return new ModelAndView("redirect:/game/");
	}
}
