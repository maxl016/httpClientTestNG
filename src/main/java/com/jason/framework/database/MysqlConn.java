package com.jason.framework.database;

import javax.swing.table.DefaultTableModel;

public class MysqlConn extends DataConn {
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	public MysqlConn(String url, String user, String password) {
		super(DRIVER, url, user, password);
	}

	public static final String getUrl(String ip, int port, String dbname,
			String code) {
		String url = "jdbc:mysql://" + ip + ":" + port + "/" + dbname
				+ "?useUnicode=true&characterEncoding=" + code + "&allowMultiQueries=true";
		return url;
	}

	public String getUUID() {
		String sql = "select uuid()";
		DefaultTableModel dtm = queryData(sql);
		return DealTable.getString(dtm, 0, 0);
	}

	public String getPageSql(String sql, int numPerPage, int page) {
		int s = numPerPage * page;
		int e = numPerPage;
		String fsql = sql + " limit " + s + "," + e;
		return fsql;
	}
}
