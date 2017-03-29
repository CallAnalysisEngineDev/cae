package org.cae.common;

public class ServiceResult {

	private boolean result;
	private String errInfo;
	public ServiceResult(DaoResult daoResult){
		this.result=daoResult.isResult();
		this.errInfo=daoResult.getErrInfo();
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getErrInfo() {
		return errInfo;
	}
	public void setErrInfo(String errInfo) {
		this.errInfo = errInfo;
	}
	
}
