package com.jason.framework.testng;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jason.framework.Method.APIMthod;
import com.jason.framework.constants.constant;
import com.jason.framework.dataProvider.CaseInfo;
import com.jason.framework.dataProvider.ExcelDataProvider;
import com.jason.framework.utils.ExcelReader;

public class testObject {
	private String loginUrl ="";
	private String logoutUrl="";
	private Map<String, String> loginParam = new HashMap<String, String>();
	
	@BeforeClass
	public void setupClass(){
		ExcelDataProvider.caseExcelPath = constant.caseExcelPath;
		ExcelDataProvider.sheetName = "账户模块";
		Map<String, String> map = ExcelReader.getDataByValue(ExcelDataProvider.caseExcelPath, "账户模块", "login");
		loginUrl = map.get("接口地址");
		
		Map json = JSONObject.fromObject(map.get("请求参数"));
		Map<String, String> param = new HashMap<String, String>();
		param.put("accountName", json.get("accountName").toString());
		param.put("password", json.get("password").toString());
		System.out.println(json);
		System.out.println(APIMthod.sendPostByHttpCient(loginUrl, param));
		System.out.println(map + loginUrl);
	}
	
	@Test(dataProvider=ExcelDataProvider.providerName, dataProviderClass = ExcelDataProvider.class, description = "账户资金-净资产")
	public void login1(CaseInfo c){
		System.out.println(ExcelDataProvider.getRequestParam(c));
		
		String str = APIMthod.sendPostByHttpCient(ExcelDataProvider.getRequestUrl(c), ExcelDataProvider.getRequestParam(c));
		System.out.println("======" + str + "=======");
	}
	
	@AfterClass
	public void teardownClass(){
		logoutUrl = loginUrl.replace("login", "logout");
		//loginOut.logout(logoutUrl);
	}
}
