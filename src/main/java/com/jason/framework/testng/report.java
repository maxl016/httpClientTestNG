package com.jason.framework.testng;

import java.util.List;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

public class report implements IReporter{

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {
		// TODO Auto-generated method stub
		for (ISuite iSuite : suites) {
			iSuite.getName();
			
		}
		
		for (XmlSuite iSuite : xmlSuites) {
		}
	}
	
}
