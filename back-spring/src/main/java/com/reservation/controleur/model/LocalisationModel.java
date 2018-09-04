package com.reservation.controleur.model;


public class LocalisationModel {
	

	private int id;
	private String name;
    private String adresse;
    
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
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LocalisationModel [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", adresse=");
		builder.append(adresse);
		builder.append("]");
		return builder.toString();
	}
    
}
