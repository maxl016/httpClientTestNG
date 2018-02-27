package com.jason.framework.testng;

import java.util.List;
import java.util.Map;

import com.jason.framework.utils.XmlParse;

public class testSql {
	public static void main(String[] args) {
		 List<Map<String, String>> tt =  XmlParse.parser3Xml("D:/work/framework/src/main/resources/temp.xml");
		System.out.println(tt.get(0).get("sql"));
	}
}
