package com.jason.framework.testng;

import com.jason.framework.database.MybatisSqlSession;

public class testMybatis {
	public static void main(String[] args) {
		System.out.println(testMybatis.class.getResource("/mybatis-configuration.xml").getPath());
		
		MybatisSqlSession.resource = testMybatis.class.getResource("/mybatis-configuration.xml").getPath();
		
	}
}
