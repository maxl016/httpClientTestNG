package com.jason.framework.testng;

import org.testng.annotations.Test;

public class testAssert {
	@Test
	public void testAssert3() {
		Assertion.verifyEquals(2, 3, "比较两个数是否相等：");
		Assertion.verifyEquals(1, 2, "比较两个数是否相等：");
		
	}

	@Test
	public void testAssert4() {
		Assertion.verifyEquals(4, 3, "比较两个数是否相等：");
		Assertion.verifyEquals(2, 2, "比较两个数是否相等：");
	}
	
	@Test
	public void testAssert1(){
		Assertion.verifyEquals(true, true);
	}
	
	public static void main(String[] args) {
		String str ="账户信息总览,{接口地址=http://10.10.2.51:18080/app/lo";
		System.out.println(str.split(",")[0]);
	}
}
