package org.cae.entity;

public class Admin extends Entity {

	private Integer adminId;
	private String adminUseraccount;
	private String adminPassword;
	private String adminUsername;
	private Admin(Builder builder){
		this.adminId=builder.adminId;
		this.adminUseraccount=builder.adminUseraccount;
		this.adminPassword=builder.adminPassword;
		this.adminUsername=builder.adminUsername;
	}
	
	public static class Builder implements IBuilder<Admin>{
		private Integer adminId;
		private String adminUseraccount;
		private String adminPassword;
		private String adminUsername;
		public Builder(){}
		public Builder adminId(Integer adminId){
			this.adminId=adminId;
			return this;
		}
		public Builder adminUseraccount(String adminUseraccount){
			this.adminUseraccount=adminUseraccount;
			return this;
		}
		public Builder adminPassword(String adminPassword){
			this.adminPassword=adminPassword;
			return this;
		}
		public Builder adminUsername(String adminUsername){
			this.adminUsername=adminUsername;
			return this;
		}
		@Override
		public Admin build(){
			return new Admin(this);
		}
	}
	public Integer getAdminId() {
		return adminId;
	}
	public String getAdminUseraccount() {
		return adminUseraccount;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public String getAdminUsername() {
		return adminUsername;
	}
	
}
