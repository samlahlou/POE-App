package com.reservation.controleur.model;

import java.util.Date;
import java.util.List;


public class meetingModel {
	
	public int id;
	public Date horaire; //date de début
	public Date duree;
	public SalleModel salle ;
	//public LocalisationModel place;
	public List<GroupModelUser> users;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getHoraire() {
		return horaire;
	}
	public void setHoraire(Date horaire) {
		this.horaire = horaire;
	}
	public Date getDuree() {
		return duree;
	}
	public void setDuree(Date duree) {
		this.duree = duree;
	}
	public SalleModel getSalle() {
		return salle;
	}
	public void setSalle(SalleModel salle) {
		this.salle = salle;
	}
	/*public LocalisationModel getPlace() {
		return place;
	}
	public void setPlace(LocalisationModel place) {
		this.place = place;
	}*/
	public List<GroupModelUser> getUsers() {
		return users;
	}
	public void setUsers(List<GroupModelUser> users) {
		this.users = users;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("meetingModel [id=");
		builder.append(id);
		builder.append(", horaire=");
		builder.append(horaire);
		builder.append(", duree=");
		builder.append(duree);
		builder.append(", salle=");
		builder.append(salle);
		builder.append(", users=");
		builder.append(users);
		builder.append("]");
		return builder.toString();
	}
	
	
}
