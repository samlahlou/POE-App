package com.reservation.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the localisation database table.
 * 
 */
@Entity
@Table(name="localisation")
public class LocalisationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id de la localisation
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idlocal;

	/**
	 * adresse de la localisation
	 */
	@Column(length=45)
	private String localisation;

	/**
	 * nom de la localisation
	 */
	@Column(length=45)
	private String name;

	/**
	 * liste des salles �tant a cette localisation
	 */
	//bi-directional many-to-one association to Salle
	@OneToMany(mappedBy="localisation", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<SalleEntity> salles;

	/**
	 * liste des users �tant a cette localisation
	 */
	//bi-directional many-to-one association to User
	@JsonIgnore
	@OneToMany(mappedBy="localisation", cascade = CascadeType.ALL)
	private List<UserEntity> users;

	public LocalisationEntity() {
	}

	public int getIdlocal() {
		return this.idlocal;
	}

	public void setIdlocal(int idlocal) {
		this.idlocal = idlocal;
	}

	public String getLocalisation() {
		return this.localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SalleEntity> getSalles() {
		return this.salles;
	}

	public void setSalles(List<SalleEntity> salles) {
		this.salles = salles;
	}

	public SalleEntity addSalle(SalleEntity salle) {
		getSalles().add(salle);
		salle.setLocalisation(this);

		return salle;
	}

	public SalleEntity removeSalle(SalleEntity salle) {
		getSalles().remove(salle);
		salle.setLocalisation(null);

		return salle;
	}

	public List<UserEntity> getUsers() {
		return this.users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public UserEntity addUser(UserEntity user) {
		getUsers().add(user);
		user.setLocalisation(this);

		return user;
	}

	public UserEntity removeUser(UserEntity user) {
		getUsers().remove(user);
		user.setLocalisation(null);

		return user;
	}

}