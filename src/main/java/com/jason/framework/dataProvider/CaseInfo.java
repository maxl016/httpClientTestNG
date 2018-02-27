package com.jason.framework.dataProvider;


import java.util.Map; 

import com.jason.framework.constants.constant;

public class CaseInfo {
	//测试用例序号
	private Map<String,String> caseNo;
	//测试用例名称（与case中方法名一致）
	private Map<String,String> caseName;
	//测试用例描述
	private Map<String,String> caseDesc;
	//测试用例url
	private Map<String,String> caseUrl;
	//测试用例参数
	private Map<String,String> caseParam;
	//预期结果（断言）
	private Map<String,String> caseExpect;
	//是否执行
	private Map<String,String> caseIsExec;
	
	public Map<String, String> getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(Map<String, String> caseNo) {
		this.caseNo = caseNo;
	}
	public Map<String, String> getCaseName() {
		return caseName;
	}
	public void setCaseName(Map<String, String> caseName) {
		this.caseName = caseName;
	}
	public Map<String, String> getCaseDesc() {
		return caseDesc;
	}
	public void setCaseDesc(Map<String, String> caseDesc) {
		this.caseDesc = caseDesc;
	}
	public Map<String, String> getCaseUrl() {
		return caseUrl;
	}
	public void setCaseUrl(Map<String, String> caseUrl) {
		this.caseUrl = caseUrl;
	}
	public Map<String, String> getCaseParam() {
		return caseParam;
	}
	public void setCaseParam(Map<String, String> caseParam) {
		this.caseParam = caseParam;
	}
	public Map<String, String> getCaseExpect() {
		return caseExpect;
	}
	public void setCaseExpect(Map<String, String> caseExpect) {
		this.caseExpect = caseExpect;
	}
	public Map<String, String> getCaseIsExec() {
		return caseIsExec;
	}
	public void setCaseIsExec(Map<String, String> caseIsExec) {
		this.caseIsExec = caseIsExec;
	}
	
	public String toString(){
		return getCaseDesc().get(constant.caseDesc) + "|" + getCaseUrl() + "," + getCaseParam() ;
	}
}
