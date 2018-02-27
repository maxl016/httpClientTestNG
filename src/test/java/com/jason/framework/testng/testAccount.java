package com.jason.framework.testng;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.jason.framework.Method.APIMthod;
import com.jason.framework.dataProvider.CaseInfo;
import com.jason.framework.dataProvider.ExcelDataProvider;

@Listeners({ com.jason.framework.testng.AssertionListener.class, com.jason.framework.testng.MethodListenter.class })
public class testAccount {
private static Logger logger = Logger.getLogger(testAccount.class); 
	
	private static String url = "";
	private Map<String, String> requestparam = new HashMap<String, String>();

	@BeforeClass(description="测试类初始化")
	public void setupClass(){
		ExcelDataProvider.caseExcelPath = System.getProperty("user.dir") + "\\src\\main\\resources\\ExcelData.xlsx";
		ExcelDataProvider.sheetName = "登陆模块";
	}
	
	@BeforeMethod(description="测试方法初始化")
	public void setupMethod(){

	}

    @Test(dataProvider= ExcelDataProvider.providerName,dataProviderClass= ExcelDataProvider.class,description="登陆测试集")
	public void accountInfoTest(CaseInfo c){
    	//获取接口名称
    	url = ExcelDataProvider.getRequestUrl(c);
       
    	//获取用例需要的参数
		requestparam = ExcelDataProvider.getRequestParam(c);
		
		//请求
		String res = APIMthod.sendPostByHttpCient(url, requestparam);
		logger.debug(res);
		//获取预期结果
		String expect = ExcelDataProvider.getExpect(c);
		
		//Assert.assertEquals(expect, actual);
		
	}
    
    @AfterMethod(description="测试方法执行结束")
    public void teardownMethod(){
    	//请求结束退出登陆
    	Reporter.log("sdadad");
    }
    
    @AfterClass(description="测试类执行结束")
    public void teardownClass(){

    }
}
