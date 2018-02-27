package com.jason.framework.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import com.jason.framework.exception.NestedBusinessException;

public class FileUtils {
	public static SimpleLogger log = SimpleLogger.getLogger(FileUtils.class);
	
	public static String readTxtFile(String filePath) {
        StringBuffer appInfolistInput = new StringBuffer();
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    appInfolistInput.append(lineTxt);
                }
                read.close();
                bufferedReader.close();
            } else {
            	log.error("找不到指定的文件");
            }
        } catch (Exception e) {
           throw new NestedBusinessException("读取文件内容出错 ", e);
        }
        return appInfolistInput.toString();
    }
	
	public void readByte(String fileName) {
        InputStream is = null;
        try {
            is = new FileInputStream(fileName);
            byte[] byteBuffer = new byte[is.available()];
            int read = 0;
            while((read = is.read(byteBuffer)) != -1){
                System.out.write(byteBuffer, 0, read);
            }
        } catch (FileNotFoundException e) {        
            e.printStackTrace();
        } catch (IOException e) {          
            e.printStackTrace();
        }finally{          
            try {
                if(is != null){
                    is.close();
                }
            } catch (IOException e) {              
                e.printStackTrace();
            }
        }      
    }
	
	public void writeBuffer(String fileName){
        try {
            File file = new File(fileName);
            BufferedWriter output = new BufferedWriter(new FileWriter(file));          
            output.write("hello wrold");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void writeByte(String fileName){
        try {
            File file = new File(fileName);
            OutputStream os = new FileOutputStream(file);
            String s = "hello world";          
            byte[] byteBuffer = s.getBytes();
            os.write(byteBuffer, 0, byteBuffer.length);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
