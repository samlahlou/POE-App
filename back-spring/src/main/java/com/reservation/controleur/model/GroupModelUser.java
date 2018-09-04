package com.reservation.controleur.model;

public class GroupModelUser {
	
	
	private int id;
	private int isAdmin;
	private String name;
	private String firstname;
	/**
	 * 1 = user (name = name | firstname = firstname)
	 * 2 = group (name = name | firstname = icon)
	 * 3 = ext (name = email)
	 */
	private int type;
	
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
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GroupModelUser [id=");
		builder.append(id);
		builder.append(", isAdmin=");
		builder.append(isAdmin);
		builder.append(", name=");
		builder.append(name);
		builder.append(", firstname=");
		builder.append(firstname);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}
	

}
