package com.jason.framework.testng;


import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.jason.framework.CommonMethod;
import com.jason.framework.Method.APIMthod;
import com.jason.framework.constants.constant;
import com.jason.framework.utils.FileUtils;

public class testMysql {
	public static void main(String[] args) {
		constant.JDBC = System.getProperty("user.dir") + "\\src\\main\\resources\\jdbc.properties";
		
		String sql1 = FileUtils.readTxtFile(System.getProperty("user.dir") + "\\src\\main\\resources\\sql1.sql");
		String sql2 = FileUtils.readTxtFile(System.getProperty("user.dir") + "\\src\\main\\resources\\sql2.sql");
		String sql3 = FileUtils.readTxtFile(System.getProperty("user.dir") + "\\src\\main\\resources\\sql3.sql");
		while(true){
			DefaultTableModel table = CommonMethod.query_server(sql1);
			if (table.getValueAt(0, 0).equals("0") && table.getValueAt(1, 0).equals("0")) {
				
				System.out.println("table.getValueAt(0, 0) = " + table.getValueAt(0, 0));
				System.out.println("table.getValueAt(1, 0) = " + table.getValueAt(0, 0));
				
				String str0 = APIMthod.sendGetByHttpCient("http://172.18.1.52:8081/admin/cur/pay/interest");
				
				System.out.println("str0 = " + str0);
			}
			
			if (table.getValueAt(0, 0).equals("1") && table.getValueAt(1, 0).equals("1")) {

				CommonMethod.update(sql2);
				CommonMethod.update(sql3);
				String str1 = APIMthod.sendGetByHttpCient("http://172.18.1.52:8081/admin/cur/pay/interest");
				
				System.out.println("str1 = " + str1);
			}
		}	
	}
}
