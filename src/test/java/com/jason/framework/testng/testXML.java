package com.jason.framework.testng;


import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.jason.framework.dataProvider.CaseInfo;
import com.jason.framework.dataProvider.XMLDataProvider;

@Listeners({ com.jason.framework.testng.AssertionListener.class, com.jason.framework.testng.MethodListenter.class,com.jason.framework.testng.CustomListener.class})

public class testXML extends XMLDataProvider {
private static Logger logger = Logger.getLogger(testXML.class); 
	
	private static String url = "";

	@BeforeClass(description="测试类初始化")
	public void setupClass(){
	}
	
	@BeforeMethod(description="测试方法初始化")
	public void setupMethod(){

	}
	
	@Test(dataProvider = XMLDataProvider.providerName,dataProviderClass = XMLDataProvider.class)
	public void xmlTest(CaseInfo c){
		//获取接口名称
    	url = XMLDataProvider.getRequestUrl(c);
    	
    	logger.debug(url);
    	logger.debug(XMLDataProvider.getRequestParam(c));
	}
}
