package org.cae.monitor.entity;

import org.cae.entity.Entity;

public class DBStatic extends Entity {

	private String dbName;
	private String dbVersion;
	private String dbDriverName;
	private String dbUrl;
	private String dbUsername;
	private String dbJdbcVersion;
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getDbVersion() {
		return dbVersion;
	}
	public void setDbVersion(String dbVersion) {
		this.dbVersion = dbVersion;
	}
	public String getDbDriverName() {
		return dbDriverName;
	}
	public void setDbDriverName(String dbDriverName) {
		this.dbDriverName = dbDriverName;
	}
	public String getDbUrl() {
		return dbUrl;
	}
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	public String getDbUsername() {
		return dbUsername;
	}
	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}
	public String getDbJdbcVersion() {
		return dbJdbcVersion;
	}
	public void setDbJdbcVersion(String dbJdbcVersion) {
		this.dbJdbcVersion = dbJdbcVersion;
	}
	
}
