package com.jason.framework.testng;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class CustomListener extends TestListenerAdapter {
	Logger logger = Logger.getLogger(CustomListener.class);

	@Override
	public void onTestFailure(ITestResult tr) {
		logger.debug("【" + tr.getName() + "】" + "--Test method failed\n");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		logger.debug("【" + tr.getName() + "】" + "--Test method failed\n");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		logger.debug("【" + tr.getName() + "】" + "--Test method success\n");
	}
}