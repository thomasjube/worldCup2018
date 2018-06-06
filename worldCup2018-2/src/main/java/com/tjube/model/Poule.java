package com.tjube.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tjube.model.Team;

@NamedQueries({
	@NamedQuery(
	name = "findNextPoule",
	query = "select p from Poule p where p.id > :pouleId order by p.name asc"
	),
	@NamedQuery(
	name = "findLastPoule",
	query = "select p from Poule p where p.id < :pouleId order by p.name desc"
	)
})

@Entity
@Table(name = "POULE")
public class Poule
	implements Serializable
{

	private static final long serialVersionUID = -4871604316480630379L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String name;
	
	@OneToMany(fetch=FetchType.EAGER)
    private Collection<Team> teams;

	public Poule() {
		// TODO Auto-generated constructor stub
	}
	
	public Poule(String name, Collection<Team> teams) {
		super();
		this.name = name;
		this.teams = teams;
	}



	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public Collection<Team> getTeams() {
		return teams;
	}
	
	public void setTeams(Collection<Team> teams) {
		this.teams = teams;
	}

}
