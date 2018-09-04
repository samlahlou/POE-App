package com.reservation.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id unique de l'user
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idusers;

	/**
	 * 0 = non admin <br>
	 * 1 = admin
	 */
	private int admin;

	/**
	 * email de l'utilisateur
	 */
	@Column(length=45)
	private String email;

	/**
	 * 0 = non groupCreator <br>
	 * 1 = groupCreator
	 */
	private int groupCreator;

	/**
	 * id long (uid) permetant une connection rapide 
	 */
	@Column(name="id_long", length=45)
	private String idLong;

	/**
	 * prenom de l'utilisateur
	 */
	@Column(length=45)
	private String name;

	/**
	 * password de l'utilisateur
	 */
	@Column(length=45)
	private String password;

	/**
	 * nom de famille
	 */
	@Column(length=45)
	private String surname;

	/**
	 * r�servation que l'user a cr��e
	 */
	//bi-directional many-to-one association to Reservation
	@OneToMany(mappedBy="userCreator")
	@JsonIgnore
	private List<ReservationEntity> reservationsCreator;

	/**
	 * r�union ou l'user est invit�
	 */
	//bi-directional many-to-one association to Reservationbyuser
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<ReservationByUserEntity> reunion;

	/**
	 * localisation de l'user
	 */
	//bi-directional many-to-one association to Localisation
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="localisationId", nullable=false)
	private LocalisationEntity localisation;

	/**
	 * groupe o� l'user est membre
	 */
	//bi-directional many-to-one association to Usersgroup
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<UsersGroupEntity> usersgroups;

	public UserEntity() {
	}

	public int getIdusers() {
		return this.idusers;
	}

	public void setIdusers(int idusers) {
		this.idusers = idusers;
	}

	public int getAdmin() {
		return this.admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGroupCreator() {
		return this.groupCreator;
	}

	public void setGroupCreator(int groupCreator) {
		this.groupCreator = groupCreator;
	}

	public String getIdLong() {
		return this.idLong;
	}

	public void setIdLong(String idLong) {
		this.idLong = idLong;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<ReservationEntity> getReservationsCreator() {
		return this.reservationsCreator;
	}

	public void setReservationsCreator(List<ReservationEntity> reservations) {
		this.reservationsCreator = reservations;
	}

	public ReservationEntity addReservation(ReservationEntity reservation) {
		getReservationsCreator().add(reservation);
		reservation.setUser(this);

		return reservation;
	}

	public ReservationEntity removeReservation(ReservationEntity reservation) {
		getReservationsCreator().remove(reservation);
		reservation.setUser(null);

		return reservation;
	}

	public List<ReservationByUserEntity> getReunion() {
		return this.reunion;
	}

	public void setReunion(List<ReservationByUserEntity> reunion) {
		this.reunion = reunion;
	}

	public ReservationByUserEntity addReservationbyuser(ReservationByUserEntity reservationbyuser) {
		getReunion().add(reservationbyuser);
		reservationbyuser.setUser(this);

		return reservationbyuser;
	}

	public ReservationByUserEntity removeReservationbyuser(ReservationByUserEntity reservationbyuser) {
		getReunion().remove(reservationbyuser);
		reservationbyuser.setUser(null);

		return reservationbyuser;
	}

	public LocalisationEntity getLocalisation() {
		return this.localisation;
	}

	public void setLocalisation(LocalisationEntity localisation) {
		this.localisation = localisation;
	}

	public List<UsersGroupEntity> getUsersgroups() {
		return this.usersgroups;
	}

	public void setUsersgroups(List<UsersGroupEntity> usersgroups) {
		this.usersgroups = usersgroups;
	}

	public UsersGroupEntity addUsersgroup(UsersGroupEntity usersgroup) {
		getUsersgroups().add(usersgroup);
		usersgroup.setUser(this);

		return usersgroup;
	}

	public UsersGroupEntity removeUsersgroup(UsersGroupEntity usersgroup) {
		getUsersgroups().remove(usersgroup);
		usersgroup.setUser(null);

		return usersgroup;
	}

}