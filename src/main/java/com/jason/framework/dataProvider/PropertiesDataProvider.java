package com.jason.framework.dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;

import com.jason.framework.constants.constant;
import com.jason.framework.exception.NestedBusinessException;

public class PropertiesDataProvider {
	public static final String providerName = "propertiesDataProvider";
	public static String filePath = constant.propertiesPath;
	
	@DataProvider(name = providerName)
    public Object[][] dataProvider(){
        return this.getTestData();
    }
	
	private Object[][] getTestData(){
        List<Map<String, String>> listData = getTestMethodData();
        Object[][] object = new Object[listData.size()][];
        for (int i = 0; i < listData.size(); i++) {
            object[i] = new Object[]{listData.get(i)};
        }
        return object;
    }
	
	public List<Map<String, String>> getTestMethodData(){
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        list.add(getPropertyData(filePath));
        return list;
    }
	
	private static Properties loadPropertiesFile(String filePath){
        Properties p = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream(new File(filePath));
            p.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return p;
    }
     
    /**
     * 将property转换成Map
     * @param key
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map<String, String> getPropertyData(String filePath){
    	if (StringUtils.isEmpty(filePath)) {
			throw new NestedBusinessException("文件名不能为空！");
		}
        try{
            return new HashMap<String, String>((Map)loadPropertiesFile(filePath));
        }catch(Exception e){
            e.printStackTrace();
        }
        return new HashMap<String, String>();
    }
    
    public static void main(String[] args) {
    	PropertiesDataProvider p = new PropertiesDataProvider();
    	List<Map<String, String>> list = p.getTestMethodData();
    	for (Map<String, String> map : list) {
			System.out.println(map.get("username"));
			System.out.println(map.get("password"));
		}
	}
}
