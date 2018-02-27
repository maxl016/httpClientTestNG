package com.jason.framework.testng;



import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.jason.framework.utils.SimpleLogger;

public class MethodListenter implements IInvokedMethodListener {
	
	SimpleLogger log = SimpleLogger.getLogger(this.getClass());
	
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		
		if (method.isTestMethod()) {
			Object[] obj = testResult.getParameters();
			String methodName = method.getTestMethod().getMethodName();
			
			log.debug("MethodNmae：{}", new Object[]{methodName});
			
			if (obj.length >0) {
				for (Object object : obj) {
					log.debug("Business parameter:{} " + new Object[]{object.toString()});
				}
			}
		}
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
	}
}
