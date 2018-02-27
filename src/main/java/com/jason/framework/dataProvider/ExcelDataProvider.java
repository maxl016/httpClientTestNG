package com.jason.framework.dataProvider;


import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.jason.framework.constants.constant;
import com.jason.framework.exception.NestedBusinessException;
import com.jason.framework.utils.ExcelReader;


public class ExcelDataProvider extends CaseHelper{
	public static String caseExcelPath = constant.caseExcelPath;
	public static String sheetName;
	public static String domain;
	public static final String providerName = "excelDataProvider";
	
	@SuppressWarnings("static-access")
	public ExcelDataProvider(String dataPath, String sheetName, String domain) {
		this.caseExcelPath = dataPath;
		this.sheetName = sheetName;
		this.domain = domain;
	}

	@DataProvider(name = providerName)
	public static Object[][] dataInfo(Method m) throws IOException {

		Object[][] object = null;
		List<Map<String, String>> list = ExcelReader.getExcelToList(caseExcelPath, sheetName);
		object = CaseHelper.getObjArrByList(list, m);
		return object;
	}
}
