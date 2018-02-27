package com.jason.framework.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class DataConn {
	private String driver = null;
	private String url = null;
	private String user = null;
	private String password = null;
	private Connection conn = null;

	public DataConn(String driver, String url, String user, String password) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public DataConn(Connection conn) {
		this.conn = conn;
	}

	public void init() {
		initConn();
	}

	public void initConn() {
		try {
			if (this.conn == null) {
				Class.forName(this.driver).newInstance();
				this.conn = DriverManager.getConnection(this.url, this.user,
						this.password);
			}
			setCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void setCommit(boolean isauto) {
		try {
			this.conn.setAutoCommit(isauto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConn() {
		return this.conn;
	}

	public int[] queryMeta(String sql) {
		try {

			Statement stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int[] colType = new int[rsmd.getColumnCount()];
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				colType[i] = rsmd.getColumnType(i + 1);
			}
			rs.close();
			return colType;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public DefaultTableModel queryData(String sql) {
		try {
			Statement stmt = this.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel tablemodel = new DefaultTableModel();
			int n = rsmd.getColumnCount();
			String[] cols = new String[n];

			for (int i = 1; i <= n; i++) {
				cols[(i - 1)] = rsmd.getColumnLabel(i);
			}
			tablemodel.setColumnIdentifiers(cols);
			while (rs.next()) {
				Vector<Object> data = new Vector();
				for (int i = 1; i <= n; i++) {
					data.add(rs.getObject(i));
				}
				tablemodel.addRow(data);
			}
			rs.close();
			return tablemodel;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int editData(String sql) {
		try {
			Statement stmt = this.conn.createStatement();
			int result = stmt.executeUpdate(sql);
			commit();
			stmt.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			rollback();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return -1;
	}

	public int[] batEditData(String[] sql) {
		try {
			Statement stmt = this.conn.createStatement();
			for (int i = 0; i < sql.length; i++) {
				stmt.addBatch(sql[i]);
			}
			int[] seq = stmt.executeBatch();
			commit();
			stmt.close();
			return seq;
		} catch (SQLException e) {
			e.printStackTrace();
			rollback();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return null;
	}

	public void commit() {
		try {
			this.conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void rollback() {
		try {
			this.conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		closeConn();
	}

	public void closeConn() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public DefaultTableModel query(String select, String from, String where,
			String groupby, String having, String orderby) {
		String sql = "";
		if (select != null) {
			sql = sql + "select " + select;
		}
		if (from != null) {
			sql = sql + " from " + select;
		}
		if (where != null) {
			sql = sql + " where " + where;
		}
		if (groupby != null) {
			sql = sql + " group by " + groupby;
		}
		if (having != null) {
			sql = sql + " having " + having;
		}
		if (orderby != null) {
			sql = sql + " order by " + orderby;
		}
		return queryData(sql);
	}

	public DefaultTableModel query(String select, String from, String where,
			String orderby) {
		return query(select, from, where, null, null, orderby);
	}

	public DefaultTableModel query(String select) {
		return query(select, null, null, null);
	}

	public int queryTotal(String from, String where, String groupby,
			String having, String orderby) {
		String select = "select count(*)";
		DefaultTableModel dtm = query(select, from, where, groupby, having,
				orderby);
		int total = DealTable.getInt(dtm, 0, 0);
		return total;
	}

	public int getPageCount(int total, int numPerPage) {
		double t = total;
		double n = numPerPage;
		int p = (int) Math.ceil(t / n);
		return p;
	}

	public DefaultTableModel queryPage(String from, String where,
			String groupby, String having, String orderby, int page_pos) {
		DefaultTableModel dtm = null;
		return dtm;
	}
}
