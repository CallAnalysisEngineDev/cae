package org.cae.common;

import java.util.List;
import java.util.Map;

public class ServiceResult {

	private boolean successed=false;
	private Object result;
	private Integer nowPage;
	private Integer totalPage;
	private String errInfo;
	private Map<String,Object> extraInfo;
	public ServiceResult(){}
	public ServiceResult(DaoResult daoResult){
		this.successed=daoResult.isResult();
		this.errInfo=daoResult.getErrInfo();
	}
	public boolean isSuccessed() {
		return successed;
	}
	public void setSuccessed(boolean successed) {
		this.successed = successed;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String getErrInfo() {
		return errInfo;
	}
	public void setErrInfo(String errInfo) {
		this.errInfo = errInfo;
	}
	public Integer getNowPage() {
		return nowPage;
	}
	public void setNowPage(Integer nowPage) {
		this.nowPage = nowPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Map<String, Object> getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(Map<String, Object> extraInfo) {
		this.extraInfo = extraInfo;
	}
	public String toString(){
		if(this.successed){
			if(this.result instanceof List){
				String result="successed : "+this.successed+"\n"
						+ "result : \n";
				List list=(List) this.result;
				for(Object object:list){
					result+="\t"+object.toString()+"\n";
				}
				result+="\n";
				return result+"nowPage : "+this.nowPage+"\n"
						+ "totalPage : "+this.totalPage;
			}
			else{
				return "successed : "+this.successed+"\n"
						+ "result : "+this.result;
			}
		}
		else{
			return "successed : "+this.successed+"\n"
					+ "errInfo : "+this.errInfo;
		}
	}
}
