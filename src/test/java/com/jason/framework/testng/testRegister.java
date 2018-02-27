package com.jason.framework.testng;
import java.util.HashMap;
import java.util.Map;

import com.jason.framework.HttpInvoker;

public class testRegister {
	
	public static void main(String[] args) {
		String getVerity = "http://172.18.1.52:8082/app/register/verify-code";
		String xx = "http://172.18.1.52:8082/app/verifyCode/capcha";
		String getCapcha = "http://172.18.1.52:8082/app/register/validateCaptcha";
		
		Map<String, String> parameterMap = new HashMap<>();
		
		parameterMap.put("phone", "15012344510");
		parameterMap.put("type", "1");
		
		try {
			for (int i = 0; i < 3; i++) {
				String res = HttpInvoker.post(getVerity, parameterMap);
			}
			
			HttpInvoker.get(xx);
			
			Map<String, String> capcha = new HashMap<>();
			
			capcha.put("phone", "15012344510");
			capcha.put("captcha", "9999");
			capcha.put("type", "1");
			
			String res = HttpInvoker.post(getCapcha, capcha);
			System.out.println("11111==" + res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
