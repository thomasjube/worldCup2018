package com.tjube.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tjube.model.Poule;
import com.tjube.model.Team;

@Repository
public class TeamDAOImpl
	implements TeamDAO
{

	@PersistenceContext(unitName = "JpaPersistenceUnit")
	private EntityManager m_entityManager = null;

	@Override
	public void addTeam(Team team)
	{
		m_entityManager.persist(team);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Team> getAllTeams()
	{
		TypedQuery<Team> query = m_entityManager.createNamedQuery(Team.QN.GET_ALL_TEAMS, Team.class);
		return query.getResultList();
	}

	@Override
	public void deleteTeam(Integer teamId)
	{
		Team team = getTeam(teamId);

		if (!m_entityManager.contains(team))
			team = m_entityManager.merge(team);

		m_entityManager.remove(team);

	}

	@Override
	public Team getTeam(int empid)
	{
		TypedQuery<Team> query = m_entityManager.createNamedQuery(Team.QN.GET_TEAM_BY_ID, Team.class);
		query.setParameter("id", empid);

		return JPAUtils.getSingleResult(query);
	}

	@Override
	public Team getTeam(String string)
	{
		TypedQuery<Team> query = m_entityManager.createNamedQuery(Team.QN.findTeamByName, Team.class);
		query.setParameter("name", string);

		return query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Team> getOrderTeams(Poule poule)
	{
		TypedQuery<Team> query = m_entityManager.createNamedQuery(Team.QN.findTeamsByPoule, Team.class);
		query.setParameter("poule", poule);
		return query.getResultList();
	}

	@Override
	public Team updateTeam(Team team)
	{
		Team teamToUpdate = getTeam(team.getId());
		teamToUpdate.setBut_mis(team.getBut_mis());
		teamToUpdate.setBut_pris(team.getBut_pris());
		teamToUpdate.setDiff(team.getDiff());
		teamToUpdate.setGameDraw(team.getGameDraw());
		teamToUpdate.setGameLost(team.getGameLost());
		teamToUpdate.setGamePlayed(team.getGamePlayed());
		teamToUpdate.setGameWin(team.getGameWin());
		teamToUpdate.setName(team.getName());
		teamToUpdate.setPlayers(team.getPlayers());
		teamToUpdate.setPoint(team.getPoint());
		teamToUpdate.setPosition_poule(team.getPosition_poule());
		teamToUpdate.setPoule(team.getPoule());

		return teamToUpdate;

	}

	@Override
	public Collection<Team> getTeamsByPoint(Poule poule)
	{
		TypedQuery<Team> query = m_entityManager.createNamedQuery(Team.QN.getTeamsByPoint, Team.class);
		query.setParameter("poule", poule);
		return query.getResultList();

	}

}
