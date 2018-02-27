package com.jason.framework;

import com.jason.framework.utils.SimpleLogger;

public class testLogger {
	public static SimpleLogger log = SimpleLogger.getLogger(testLogger.class);
	
	public static void main(String[] args) {
		log.info("wiii");
		log.info("测试结果：{},测试报告：{}", new Object[]{"111","222"});
		log.debug("测试结果：{},测试报告：{}", new Object[]{"111","222"});
	}
}
