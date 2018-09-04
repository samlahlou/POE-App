package com.reservation.controleur.model;

public class SalleModel {
	
	private int id;
	private String name;
	private String equipement;
	private LocalisationModel localisation;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEquipement() {
		return equipement;
	}
	public void setEquipement(String equipement) {
		this.equipement = equipement;
	}
	public LocalisationModel getLocalisation() {
		return localisation;
	}
	public void setLocalisation(LocalisationModel localisation) {
		this.localisation = localisation;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SalleModel [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", equipement=");
		builder.append(equipement);
		builder.append(", localisation=");
		builder.append(localisation);
		builder.append("]");
		return builder.toString();
	}
	
}
