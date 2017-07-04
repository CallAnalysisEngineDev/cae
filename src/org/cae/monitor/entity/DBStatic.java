package org.cae.monitor.entity;

import org.cae.entity.Entity;
import org.cae.entity.IBuilder;

public class DBStatic extends Entity {

	private String dbName;
	private String dbVersion;
	private String dbDriverName;
	private String dbUrl;
	private String dbUsername;
	private String dbJdbcVersion;
	private DBStatic(Builder builder){
		this.dbName=builder.dbName;
		this.dbVersion=builder.dbVersion;
		this.dbDriverName=builder.dbDriverName;
		this.dbUrl=builder.dbUrl;
		this.dbUsername=builder.dbUsername;
		this.dbJdbcVersion=builder.dbJdbcVersion;
	}
	public static class Builder implements IBuilder<DBStatic>{

		private String dbName;
		private String dbVersion;
		private String dbDriverName;
		private String dbUrl;
		private String dbUsername;
		private String dbJdbcVersion;
		public Builder(){}
		public Builder dbName(String dbName){
			this.dbName=dbName;
			return this;
		}
		public Builder dbVersion(String dbVersion){
			this.dbVersion=dbVersion;
			return this;
		}
		public Builder dbDriverName(String dbDriverName){
			this.dbDriverName=dbDriverName;
			return this;
		}
		public Builder dbUrl(String dbUrl){
			this.dbUrl=dbUrl;
			return this;
		}
		public Builder dbUsername(String dbUsername){
			this.dbUsername=dbUsername;
			return this;
		}
		public Builder dbJdbcVersion(String dbJdbcVersion){
			this.dbJdbcVersion=dbJdbcVersion;
			return this;
		}
		@Override
		public DBStatic build() {
			return new DBStatic(this);
		}
		
	}
	public String getDbName() {
		return dbName;
	}
	public String getDbVersion() {
		return dbVersion;
	}
	public String getDbDriverName() {
		return dbDriverName;
	}
	public String getDbUrl() {
		return dbUrl;
	}
	public String getDbUsername() {
		return dbUsername;
	}
	public String getDbJdbcVersion() {
		return dbJdbcVersion;
	}
}
