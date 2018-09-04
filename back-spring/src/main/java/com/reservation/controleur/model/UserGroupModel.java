package com.reservation.controleur.model;

public class UserGroupModel {
	
		private int id;
		private int groupeId;
		private int usersId;
		private int groupeUserId;
		private int isAdmin;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getGroupeId() {
			return groupeId;
		}

		public void setGroupeId(int groupeId) {
			this.groupeId = groupeId;
		}

		public int getUsersId() {
			return usersId;
		}

		public void setUsersId(int usersId) {
			this.usersId = usersId;
		}

		public int getGroupeUserId() {
			return groupeUserId;
		}

		public void setGroupeUserId(int groupeUserId) {
			this.groupeUserId = groupeUserId;
		}

		public int getIsAdmin() {
			return isAdmin;
		}

		public void setIsAdmin(int isAdmin) {
			this.isAdmin = isAdmin;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("UserGroupModel [id=");
			builder.append(id);
			builder.append(", groupeId=");
			builder.append(groupeId);
			builder.append(", usersId=");
			builder.append(usersId);
			builder.append(", groupeUserId=");
			builder.append(groupeUserId);
			builder.append(", isAdmin=");
			builder.append(isAdmin);
			builder.append("]");
			return builder.toString();
		}

}
