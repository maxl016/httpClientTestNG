package com.jason.framework.constants;

public class constant {
	public static String caseExcelPath = System.getProperty("user.dir") + "\\src\\main\\resources\\ExcelData.xlsx";
	public static String propertiesPath = System.getProperty("user.dir") + "\\src\\main\\resources\\propData.properties";
	public static String XMLPath = System.getProperty("user.dir") + "\\src\\main\\resources\\xmlData.xml";
	public static final String APP_PATH = System.getProperty("user.dir");
	public static final String USER_PATH = APP_PATH + "/resources";
	public static String JDBC = USER_PATH + "/jdbc.properties";
	//caseData数据结构
	public static String caseNo = "序号";
	public static String caseName = "用例名称";
	public static String caseDesc = "用例描述";
	public static String caseUrl = "接口地址";
	public static String caseParam = "请求参数";
	public static String caseExpect = "预期结果";
	public static String Isexec = "是否运行";
	
}
