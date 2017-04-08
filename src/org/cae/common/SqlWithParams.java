package org.cae.common;

public class SqlWithParams {

	private String sql;//where子句
	private Object[] params;
	public SqlWithParams(){}
	public SqlWithParams(String sql,Object[] params){
		this.sql=sql;
		this.params=params;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}
	
}
