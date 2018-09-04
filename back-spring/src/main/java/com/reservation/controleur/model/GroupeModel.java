package com.reservation.controleur.model;

import java.util.Calendar;
import java.util.List;

public class GroupeModel {

	private int id;
	private String name;
	private String icon;
	private Calendar date; // YYYY/MM/dd/HH/mm
	private List<GroupModelUser> users;

	
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
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public List<GroupModelUser> getUsers() {
		return users;
	}
	public void setUsers(List<GroupModelUser> users) {
		this.users = users;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GroupeModel [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", icon=");
		builder.append(icon);
		builder.append(", date=");
		builder.append(date);
		builder.append(", users=");
		builder.append(users);
		builder.append("]");
		return builder.toString();
	}

	
}
