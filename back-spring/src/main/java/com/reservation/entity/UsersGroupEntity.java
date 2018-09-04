package com.reservation.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usersgroup database table.
 * 
 */
@Entity
@Table(name="usersgroup")
public class UsersGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id peu utile
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	/**
	 * 1 = le membre est admin du groupe<br>
	 * n'a aucun effect si atribu� a un groupe
	 */
	private int isAdmin;

	//bi-directional many-to-one association to Groupe
	/**
	 * id du groupe en question
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="groupeId")
	private GroupeEntity groupeId;

	//bi-directional many-to-one association to Groupe
	/**
	 * id du groupe apartenant au groupeId
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="groupeUserId", nullable=false)
	private GroupeEntity groupeMemberId;

	//bi-directional many-to-one association to User
	/**
	 * id du membre apartenant au groupeId
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usersId")
	private UserEntity user;

	public UsersGroupEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public GroupeEntity getgroupeId() {
		return this.groupeId;
	}

	public void setgroupeId(GroupeEntity groupeId) {
		this.groupeId = groupeId;
	}

	public GroupeEntity getgroupeMemberId() {
		return this.groupeMemberId;
	}

	public void setgroupeMemberId(GroupeEntity groupeMemberId) {
		this.groupeMemberId = groupeMemberId;
	}

	public UserEntity getUser() {
		return this.user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}