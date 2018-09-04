package com.reservation.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the groupe database table.
 * 
 */
@Entity
@Table(name="groupe")
public class GroupeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id du groupe.<br>
	 * ne peux pas etre null.<br>
	 * est unique.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idGroupe;

	/**
	 * Corespond au nom 'Glyphicons' repr�sentant le groupe.
	 */
	@Column(length=45)
	private String icon;

	/**
	 * Nom du groupe.
	 */
	@Column(length=45)
	private String name;

	/**
	 * liste des reservation o� le groupe est associ�.
	 */
	//bi-directional many-to-one association to Reservationbyuser
	@OneToMany(mappedBy="groupe")
	private List<ReservationByUserEntity> reservationbyusers;

	/**
	 * retourne la liste des groupes ou ce groupe est pr�sent
	 */
	//bi-directional many-to-one association to Usersgroup
	@OneToMany(mappedBy="groupeId")
	private List<UsersGroupEntity> usersInGroup;

	/**
	 * Permet de recuperer tout les membres du groupe
	 * soit des membres, soit d'autre groupe
	 */
	//bi-directional many-to-one association to Usersgroup
	@OneToMany(mappedBy="groupeMemberId", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<UsersGroupEntity> usersgroups;

	public GroupeEntity() {
	}

	public int getIdGroupe() {
		return this.idGroupe;
	}

	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ReservationByUserEntity> getReservationbyusers() {
		return this.reservationbyusers;
	}

	public void setReservationbyusers(List<ReservationByUserEntity> reservationbyusers) {
		this.reservationbyusers = reservationbyusers;
	}

	public ReservationByUserEntity addReservationbyuser(ReservationByUserEntity reservationbyuser) {
		getReservationbyusers().add(reservationbyuser);
		reservationbyuser.setGroupe(this);

		return reservationbyuser;
	}

	public ReservationByUserEntity removeReservationbyuser(ReservationByUserEntity reservationbyuser) {
		getReservationbyusers().remove(reservationbyuser);
		reservationbyuser.setGroupe(null);

		return reservationbyuser;
	}

	public List<UsersGroupEntity> getusersInGroup() {
		return this.usersInGroup;
	}

	public void setusersInGroup(List<UsersGroupEntity> usersInGroup) {
		this.usersInGroup = usersInGroup;
	}

	public UsersGroupEntity addusersInGroup(UsersGroupEntity usersInGroup) {
		getusersInGroup().add(usersInGroup);
		usersInGroup.setgroupeId(this);

		return usersInGroup;
	}

	public UsersGroupEntity removeusersInGroup(UsersGroupEntity usersInGroup) {
		getusersInGroup().remove(usersInGroup);
		usersInGroup.setgroupeId(null);

		return usersInGroup;
	}

	public List<UsersGroupEntity> getUsersgroups() {
		return this.usersgroups;
	}

	public void setUsersgroups(List<UsersGroupEntity> usersgroups) {
		this.usersgroups = usersgroups;
	}

	public UsersGroupEntity addUsersgroups(UsersGroupEntity usersgroups) {
		getUsersgroups().add(usersgroups);
		usersgroups.setgroupeMemberId(this);

		return usersgroups;
	}

	public UsersGroupEntity removeUsersgroups(UsersGroupEntity usersgroups) {
		getUsersgroups().remove(usersgroups);
		usersgroups.setgroupeMemberId(null);

		return usersgroups;
	}

}