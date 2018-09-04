package com.reservation.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the reservation database table.
 * 
 */
@Entity
@Table(name="reservation")
public class ReservationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id de la r�servation
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idreservation;

	/**
	 * date de fon de la r�union
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date dateEnd;

	/**
	 * date de d�but de la r�union
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date dateStart;

	/**
	 * salle de r�union
	 */
	//bi-directional many-to-one association to Salle
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="salleId", nullable=false)
	@JsonIgnore
	private SalleEntity salle;
	
	/**
	 * User cr�ateur de la r�union
	 */
	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usersIdCreator", nullable=false)
	@JsonIgnore
	private UserEntity userCreator;

	/**
	 * liste des invit�s (user/groupe/exeterne)
	 */
	//bi-directional many-to-one association to Reservationbyuser
	@OneToMany(mappedBy="reservation", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<ReservationByUserEntity> invite;

	public ReservationEntity() {
	}

	public int getIdreservation() {
		return this.idreservation;
	}

	public void setIdreservation(int idreservation) {
		this.idreservation = idreservation;
	}

	public Date getDateEnd() {
		return this.dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Date getDateStart() {
		return this.dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public SalleEntity getSalle() {
		return this.salle;
	}

	public void setSalle(SalleEntity salle) {
		this.salle = salle;
	}

	public UserEntity getUser() {
		return this.userCreator;
	}

	public void setUser(UserEntity userCreator) {
		this.userCreator = userCreator;
	}

	public List<ReservationByUserEntity> getInvite() {
		return this.invite;
	}

	public void setInvite(List<ReservationByUserEntity> invite) {
		this.invite = invite;
	}

	public ReservationByUserEntity addReservationbyuser(ReservationByUserEntity reservationbyuser) {
		getInvite().add(reservationbyuser);
		reservationbyuser.setReservation(this);

		return reservationbyuser;
	}

	public ReservationByUserEntity removeReservationbyuser(ReservationByUserEntity reservationbyuser) {
		getInvite().remove(reservationbyuser);
		reservationbyuser.setReservation(null);

		return reservationbyuser;
	}

}