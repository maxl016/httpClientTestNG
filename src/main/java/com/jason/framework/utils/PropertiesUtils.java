package com.jason.framework.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;






import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.jason.framework.constants.constant;
import com.jason.framework.exception.NestedBusinessException;

public class PropertiesUtils {
	private static SimpleLogger log = SimpleLogger.getLogger(PropertiesUtils.class);
	
	private static Map<String, String> values = new HashMap<String, String>();
	private static Properties pps = new Properties();
	private static InputStream in = null;
	
	//根据Key读取Value
    public static String GetValueByKey(String filePath, String key) {
    	String value = "";
        try {
            in = new BufferedInputStream (new FileInputStream(filePath));  
            pps.load(in);
            value = pps.getProperty(key);
            return value;
            
        }catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
    
    //读取Properties的全部信息
    public static Map<String, String> GetAllProperties(String filePath){
    	if (StringUtils.isEmpty(filePath)) {
			throw new NestedBusinessException("Properties文件路径不能为空！");
		}
		try {
			in = new BufferedInputStream(new FileInputStream(filePath));
			pps.load(in);
			Enumeration<?> en = pps.propertyNames(); //得到配置文件的名字
	        
	        while(en.hasMoreElements()) {
	            String strKey = (String) en.nextElement();
	            String strValue = pps.getProperty(strKey);
	            values.put(strKey, strValue);
	        }
	        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        log.debug("读取Properties全部信息：" + values);
        return values;
    }
    
    //写入Properties信息
    public static void WriteProperties (String filePath, String pKey, String pValue) {
        
		try {
			in = new FileInputStream(filePath);
			//从输入流中读取属性列表（键和元素对） 
	        pps.load(in);
	        //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。  
	        //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
	        OutputStream out = new FileOutputStream(filePath);
	        pps.setProperty(pKey, pValue);
	        //以适合使用 load 方法加载到 Properties 表中的格式，  
	        //将此 Properties 表中的属性列表（键和元素对）写入输出流  
	        pps.store(out, "Update " + pKey + " name");
	        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
