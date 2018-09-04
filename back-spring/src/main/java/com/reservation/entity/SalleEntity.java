package com.reservation.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the salle database table.
 * 
 */
@Entity
@Table(name="salle")
public class SalleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id de la salle
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idsalle;

	/**
	 * �quipement pr�sent dans la salle <br>
	 * s�par� par des ','
	 */
	@Column(length=45)
	private String equipement;

	/**
	 * nom de la salle
	 */
	@Column(length=45)
	private String name;

	/**
	 * r�servation associ� � cette salle
	 */
	//bi-directional many-to-one association to Reservation
	@OneToMany(mappedBy="salle")
	@JsonIgnore
	private List<ReservationEntity> reservations;

	/**
	 * localisation de la salle
	 */
	//bi-directional many-to-one association to Localisation
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="localisationId", nullable=false)
	private LocalisationEntity localisation;

	public SalleEntity() {
	}

	public int getIdsalle() {
		return this.idsalle;
	}

	public void setIdsalle(int idsalle) {
		this.idsalle = idsalle;
	}

	public String getEquipement() {
		return this.equipement;
	}

	public void setEquipement(String equipement) {
		this.equipement = equipement;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ReservationEntity> getReservations() {
		return this.reservations;
	}

	public void setReservations(List<ReservationEntity> reservations) {
		this.reservations = reservations;
	}

	public ReservationEntity addReservation(ReservationEntity reservation) {
		getReservations().add(reservation);
		reservation.setSalle(this);

		return reservation;
	}

	public ReservationEntity removeReservation(ReservationEntity reservation) {
		getReservations().remove(reservation);
		reservation.setSalle(null);

		return reservation;
	}

	public LocalisationEntity getLocalisation() {
		return this.localisation;
	}

	public void setLocalisation(LocalisationEntity localisation) {
		this.localisation = localisation;
	}

}