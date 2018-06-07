package com.tjube.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(name = Penalti.QN.GET_PENALTI_BY_ID, query = "select p from Penalti p where p.id =:id"),
		@NamedQuery(name = Penalti.QN.GET_ALL_PENALTIS, query = "select p from Penalti p ") })
@Entity
@Table(name = "PENALTI")
public class Penalti
	implements Serializable
{

	//==================================================================================================================================================================================================
	//
	// Query names
	//
	//==================================================================================================================================================================================================

	public static class QN
	{
		public static final String GET_PENALTI_BY_ID = "Penalti.getPenaltiById";
		public static final String GET_ALL_PENALTIS = "Penalti.getAllPenaltis";
	}

	private static final long serialVersionUID = 8650018318706702179L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String name;

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

}
