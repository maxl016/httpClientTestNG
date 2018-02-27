package com.jason.framework.testng;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class Assertion {
    
    public static boolean flag = true;
     
    public static List<Error> errors = new ArrayList<Error>();
     
    public static void verifyEquals(Object actual, Object expected){
        try{
            Assert.assertEquals(actual, expected);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }
     
    public static void verifyEquals(Object actual, Object expected, String message){
        try{
            Assert.assertEquals(actual, expected, message);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }
    
    public static void verifyFail(String msg){
    	try {
    		Assert.fail(msg);
		} catch (Error e) {
			errors.add(e);
            flag = false;
		}
    }
}