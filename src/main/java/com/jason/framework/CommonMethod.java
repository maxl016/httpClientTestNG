package com.jason.framework;

import javax.swing.table.DefaultTableModel;

import com.jason.framework.constants.constant;
import com.jason.framework.database.MysqlConn;
import com.jason.framework.utils.PropertiesUtils;
import com.jason.framework.utils.SimpleLogger;

public class CommonMethod {
	public static SimpleLogger log = SimpleLogger.getLogger(CommonMethod.class);
	
	public static final DefaultTableModel query_server(String sql) {
		
		log.debug("BeginExecSql:{}",new Object[]{sql});
		
		MysqlConn projectdb = new MysqlConn(PropertiesUtils.GetValueByKey(constant.JDBC, "jdbc.url"), 
				PropertiesUtils.GetValueByKey(constant.JDBC, "jdbc.username"), PropertiesUtils.GetValueByKey(constant.JDBC, "jdbc.password"));
		projectdb.init();
		DefaultTableModel dtm = projectdb.queryData(sql);
		projectdb.close();
		
		return dtm;
	}
	
	public static final void update(String sql){
		log.debug("BeginExecSql:{}",new Object[]{sql});
		
		MysqlConn projectdb = new MysqlConn(PropertiesUtils.GetValueByKey(constant.JDBC, "jdbc.url"), 
				PropertiesUtils.GetValueByKey(constant.JDBC, "jdbc.username"), PropertiesUtils.GetValueByKey(constant.JDBC, "jdbc.password"));
		projectdb.init();
		
		int dtm = projectdb.editData(sql);
		System.out.println("dtm: " + dtm);
	}
	
}
