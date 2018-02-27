package com.jason.framework.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.jason.framework.exception.NestedBusinessException;

public class XmlParse {
    private static SimpleLogger log = SimpleLogger.getLogger(XmlParse.class);
    
    private String filePath;
    private Document document; 
    private static Map<String, String> map = new HashMap<String, String>();
    public XmlParse(){
    	
    }
    
    public XmlParse(String filePath) {     
        this.filePath = filePath;
        this.load(this.filePath);
    }  
     
    private void load(String filePath){
        File file = new File(filePath);
        if (file.exists()) {
            SAXReader saxReader = new SAXReader();
            try {
                document = saxReader.read(file);
            } catch (DocumentException e) {    
                throw new NestedBusinessException("文件加载异常:" + filePath , e);
            }
        } else{
            log.error("文件不存在 : " + filePath);
        }          
    }  
     
    public Element getElementObject(String elementPath) {
        return (Element) document.selectSingleNode(elementPath);
    }  
     
    @SuppressWarnings("unchecked")
    public List<Element> getElementObjects(String elementPath) {
        return document.selectNodes(elementPath);
    }
     
    @SuppressWarnings("unchecked")
    public Map<String, String> getChildrenInfoByElement(Element element){
        Map<String, String> map = new HashMap<String, String>();
        List<Element> children = element.elements();
        for (Element e : children) {
            map.put(e.getName(), e.getText());
        }
        return map;
    }
     
    public boolean isExist(String elementPath){
        boolean flag = false;
        Element element = this.getElementObject(elementPath);
        if(element != null) flag = true;
        return flag;
    }
 
    public String getElementText(String elementPath) {
        Element element = this.getElementObject(elementPath);
        if(element != null){
            return element.getText().trim();
        }else{
            return null;
        }      
    }

    @SuppressWarnings("unchecked")
	public static List<Map<String, String>> parser3Xml(String fileName) {
        File inputXml = new File(fileName);    
        List<Map<String, String>> list=new ArrayList<Map<String, String>>();                

        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(inputXml);
            Element employees = document.getRootElement();
            for (Iterator<Object> i = employees.elementIterator(); i.hasNext();) {
                Element employee = (Element) i.next();
                Map<String,String> tempMap = new HashMap<String,String>();
                for (Iterator j = employee.elementIterator(); j.hasNext();) {
                    Element node = (Element) j.next();                    
                    tempMap.put(node.getName(), node.getText());                    
                }
                list.add(tempMap);
            }
        } catch (DocumentException e) {
        	throw new NestedBusinessException("读取xml文件异常", e);
        }
        return list;
    }
     
   public static Map<String, String> getDataByValue(String xmlPath, String value){
	   List<Map<String, String>> list = XmlParse.parser3Xml(xmlPath);
	   for (Map<String, String> data : list) {
		   if (data.containsValue(value)) {
			   map = data;
		   }
		}
	   return map;
   }
}
