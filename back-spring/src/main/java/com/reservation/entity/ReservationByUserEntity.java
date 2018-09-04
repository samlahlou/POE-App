package com.reservation.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the reservationbyuser database table.
 * 
 */
@Entity
@Table(name="reservationbyuser")
public class ReservationByUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id unique
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	/**
	 * Email d'un invit� ext�rieur
	 */
	@Column(name="email_ext", length=45)
	private String emailExt;

	/**
	 * id d'un groupe invit�
	 */
	//bi-directional many-to-one association to Groupe
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="groupeId")
	private GroupeEntity groupe;

	/**
	 * id de la r�union
	 */
	//bi-directional many-to-one association to Reservation
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="reservationId", nullable=false)
	private ReservationEntity reservation;

	/**
	 * id d'un user invit�
	 */
	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usersId")
	private UserEntity user;

	public ReservationByUserEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailExt() {
		return this.emailExt;
	}

	public void setEmailExt(String emailExt) {
		this.emailExt = emailExt;
	}

	public GroupeEntity getGroupe() {
		return this.groupe;
	}

	public void setGroupe(GroupeEntity groupe) {
		this.groupe = groupe;
	}

	public ReservationEntity getReservation() {
		return this.reservation;
	}

	public void setReservation(ReservationEntity reservation) {
		this.reservation = reservation;
	}

	public UserEntity getUser() {
		return this.user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}