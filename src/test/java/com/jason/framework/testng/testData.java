package com.jason.framework.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jason.framework.utils.DateUtils;

public class testData {
	public static void main(String[] args) throws ParseException {
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("1", "123");
		map1.put("2", "456");
		map1.put("3", "789");
		
		List<String> r = new ArrayList<String>();
		
		for (String str : map1.keySet()) {
			if(str.equals("1")){
				r.add(str);
			}
		}
		System.out.println(map1);
	}
	
	public static boolean compareTime(String big, String small){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			Date b = format.parse(big);
			Date s = format.parse(small);
			
			if (b.getTime() <= s.getTime()) {
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	 /**
     * 当月第一天
     * @return
     */
    private static String getFirstDay() {
    	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
         Calendar c = Calendar.getInstance();    
         c.add(Calendar.MONTH, 0);
         c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
         String first = format.format(c.getTime());
         
        return first;

    }
    
    /**
     * 当月最后一天
     * @return
     */
    private static String getLastDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        String last = df.format(ca.getTime());
        
        return last;
    }
}
