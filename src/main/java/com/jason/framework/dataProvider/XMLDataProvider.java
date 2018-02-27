package com.jason.framework.dataProvider;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.log4testng.Logger;

import com.jason.framework.constants.constant;
import com.jason.framework.utils.XmlParse;

public class XMLDataProvider extends CaseHelper{
	Logger logger = Logger.getLogger(XMLDataProvider.class);
	
	public static final String providerName = "XMLDataProvider";
	public static String filePath = constant.XMLPath;
	private static List<Map<String, String>> list;
	
	public XMLDataProvider() {    
        getXmlData();      
    }
	
	public static void getXmlData(){
        list = XmlParse.parser3Xml(filePath);
    }
	
	@DataProvider(name = providerName)
    public static Object[][] providerMethod(Method method){        
		
		Object[][] object = null;
		list = XmlParse.parser3Xml(filePath);
		object = CaseHelper.getObjArrByList(list, method);
		
		return object;
    }
	
}
