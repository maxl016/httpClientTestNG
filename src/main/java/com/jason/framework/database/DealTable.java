package com.jason.framework.database;

import java.util.Date;

import javax.swing.table.DefaultTableModel;

public class DealTable
{
  public static final String[] getFields(DefaultTableModel model)
  {
    int num = model.getColumnCount();
    String[] fields = new String[num];
    for (int i = 0; i < num; i++) {
      fields[i] = model.getColumnName(i);
    }
    return fields;
  }
  
  public static final String getString(DefaultTableModel model, int i, int j)
  {
    Object obj = model.getValueAt(i, j);
    String v = null;
    if (obj == null) {
      v = "";
    } else if ((obj instanceof Date)) {
      v = DealDate.getTimeStr(getDate(model, i, j));
    } else {
      v = String.valueOf(model.getValueAt(i, j));
    }
    return v;
  }
  
  public static final String getString(DefaultTableModel model, int i, String colName)
  {
    try
    {
      int col = model.findColumn(colName);
      Object obj = model.getValueAt(i, col);
      String v = null;
      if (obj == null) {
        v = "";
      } else if ((obj instanceof Date)) {
        v = DealDate.getTimeStr(getDate(model, i, col));
      }
      return String.valueOf(model.getValueAt(i, col));
    }
    catch (Exception e)
    {
      System.out.println("Error colName=" + colName);
      e.printStackTrace();
    }
    return null;
  }
  
  public static final int getInt(DefaultTableModel model, int i, int j)
  {
    if (model.getValueAt(i, j) == null) {
      return 0;
    }
    String vs = String.valueOf(model.getValueAt(i, j));
    int n = Integer.parseInt(vs);
    return n;
  }
  
  public static final int getInt(DefaultTableModel model, int i, String colName)
  {
    int col = model.findColumn(colName);
    int n = getInt(model, i, col);
    return n;
  }
  
  public static final long getLong(DefaultTableModel model, int i, int j)
  {
    if (model.getValueAt(i, j) == null) {
      return 0L;
    }
    String vs = String.valueOf(model.getValueAt(i, j));
    long n = Long.parseLong(vs);
    return n;
  }
  
  public static final long getLong(DefaultTableModel model, int i, String colName)
  {
    int col = model.findColumn(colName);
    long n = getLong(model, i, col);
    return n;
  }
  
  public static final double getDouble(DefaultTableModel model, int i, int j)
  {
    if (model.getValueAt(i, j) == null) {
      return 0.0D;
    }
    String vs = String.valueOf(model.getValueAt(i, j));
    double d = Double.parseDouble(vs);
    return d;
  }
  
  public static final double getDouble(DefaultTableModel model, int i, String colName)
  {
    int col = model.findColumn(colName);
    double d = getDouble(model, i, col);
    return d;
  }
  
  public static final Date getDate(DefaultTableModel model, int i, int j)
  {
    if (model.getValueAt(i, j) == null) {
      return null;
    }
    Date d = (Date)model.getValueAt(i, j);
    return d;
  }
  
  public static final Date getDate(DefaultTableModel model, int i, String colName)
  {
    int col = model.findColumn(colName);
    Date d = getDate(model, i, col);
    return d;
  }
  
  public static final double getTotal(DefaultTableModel model, String colName)
  {
    double total = 0.0D;
    for (int i = 0; i < model.getRowCount(); i++)
    {
      String vs = getString(model, i, colName);
      double value = DealNumber.cvtDouble(vs);
      total += value;
    }
    return total;
  }
  
  public static final void setObject(DefaultTableModel model, int i, int j, Object value)
  {
    model.setValueAt(value, i, j);
  }
  
  public static final void setObject(DefaultTableModel model, int i, String colName, Object value)
  {
    int j = model.findColumn(colName);
    setObject(model, i, j, value);
  }
  
  public static final void setDecFormat(DefaultTableModel model, String colName, int count)
  {
    int col = model.findColumn(colName);
    for (int i = 0; i < model.getRowCount(); i++)
    {
      String value = "";
      String v = getString(model, i, colName);
      double number = 0.0D;
      if (!v.equals("")) {
        number = Double.parseDouble(v);
      }
      value = DealString.decFormat(number, count);
      setObject(model, i, col, value);
    }
  }
  
  public static final void setPerFormat(DefaultTableModel model, String colName, int count)
  {
    int col = model.findColumn(colName);
    for (int i = 0; i < model.getRowCount(); i++)
    {
      String value = "";
      String v = getString(model, i, colName);
      double number = 0.0D;
      if (!v.equals("")) {
        number = Double.parseDouble(v);
      }
      value = DealString.decFormat(number * 100.0D, count) + "%";
      setObject(model, i, col, value);
    }
  }
  
  public static final void setDateFormat(DefaultTableModel model, String colName, String style)
  {
    int col = model.findColumn(colName);
    for (int i = 0; i < model.getRowCount(); i++)
    {
      String value = "";
      Object obj = model.getValueAt(i, col);
      if (obj != null)
      {
        Date d = getDate(model, i, col);
        value = DealDate.parseDateStr(d, style);
      }
      setObject(model, i, col, value);
    }
  }
  
  public static final DefaultTableModel[] concat(DefaultTableModel[]... args)
  {
    int num = 0;
    for (int i = 0; i < args.length; i++) {
      if (args[i] != null) {
        num += args[i].length;
      }
    }
    DefaultTableModel[] s = new DefaultTableModel[num];
    int n = 0;
    for (int i = 0; i < args.length; i++)
    {
      DefaultTableModel[] vs = args[i];
      if (vs != null) {
        for (int j = 0; j < vs.length; j++)
        {
          s[n] = vs[j];
          n++;
        }
      }
    }
    return s;
  }
}
