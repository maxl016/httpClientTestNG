package com.jason.framework.testng;

import java.util.HashMap;
import java.util.Map;

import com.jason.framework.Method.APIMthod;

public class loginTest {
	public static void main(String[] args) {
		String url1 = "http://172.18.1.52:8082/app/login";
//		Map<String, String> param = new HashMap<String, String>();
//		
//		param.put("accountName", "91629352030");
//		param.put("password", "123456");
//		param.put("loginType", "1");
//		String res = APIMthod.sendPostByHttpCient(url1, param);
//		System.out.println(res);
//		
		String info = "http://172.18.1.52:8082/app/user/main";
		String xx = "http://172.18.1.52:8082/app/verifyCode/capcha";
		System.out.println(APIMthod.sendGetByHttpCient(xx));
		System.out.println(APIMthod.sendGetByHttpCient(info));
	}
}
