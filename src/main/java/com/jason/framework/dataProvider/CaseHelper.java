package com.jason.framework.dataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jason.framework.constants.constant;
import com.jason.framework.exception.NestedBusinessException;


public class CaseHelper {
	// 根据文件的map 转换为数组 第一个为 入参 map 第二个为用例说明,第三个参数为执行用例的预置条件
	private static Object[] getObjArrByMap(Map<String, String> caseInfoMap) {
		
		Map<String, String> caseNo = new HashMap<String, String>();
		Map<String, String> caseName = new HashMap<String, String>();
		Map<String, String> caseDesc = new HashMap<String, String>();
		Map<String, String> caseUrl = new HashMap<String, String>();
		Map<String, String> caseParam = new HashMap<String, String>();
		Map<String, String> caseExpect = new HashMap<String, String>();
		
		CaseInfo ci = new CaseInfo();
		for (String key : caseInfoMap.keySet()) {
			if (key.equals(constant.caseNo)) {
				caseNo.put(key, caseInfoMap.get(key));
			}
			if (key.equals(constant.caseName)) {
				caseName.put(key, caseInfoMap.get(key));
			}
			if (key.equals(constant.caseDesc)) {
				caseDesc.put(key, caseInfoMap.get(key));
			}
			if (key.equals(constant.caseUrl)) {
				caseUrl.put(key, caseInfoMap.get(key));
			}
			if (key.equals(constant.caseParam)) {
				caseParam.put(key, caseInfoMap.get(key));
			}
			if (key.equals(constant.caseExpect)) {
				caseExpect.put(key, caseInfoMap.get(key));
			}
		}
		ci.setCaseNo(caseNo);
		ci.setCaseName(caseName);
		ci.setCaseDesc(caseDesc);
		ci.setCaseUrl(caseUrl);
		ci.setCaseParam(caseParam);
		ci.setCaseExpect(caseExpect);
	
		return new Object[] { ci };
	}

	// /获取的list转换为 Object[][]
	public static Object[][] getObjArrByList(List<Map<String, String>> caseExcelList, Method m) {
		List<Map<String, String>> caseExcuteList = getExcuteList(caseExcelList, m);
		Object[][] objArray = new Object[caseExcuteList.size()][];
		for (int i = 0; i < caseExcuteList.size(); i++) {
			objArray[i] = getObjArrByMap(caseExcuteList.get(i));
		}
		return objArray;
	}

	// 筛选出需要执行的用例
	private static List<Map<String, String>> getExcuteList(List<Map<String, String>> caseExcelList, Method method) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (Map<String, String> m : caseExcelList) {
			String exec = m.get(constant.Isexec).trim().toLowerCase();
			if (exec.equals("y") && (m.get(constant.caseName).equals(method.getName())))  {
				list.add(m);
			}
		}
		return list;
	}
	
	//获取测试用例序号
  	public static String getCaseNo(CaseInfo caseNo) {
  		if (caseNo.getCaseNo().isEmpty()) {
  			throw new NestedBusinessException("测试用例序号不能为空！");
  		}
  		return caseNo.getCaseNo().get(constant.caseNo);
  	}
      
  	//获取测试用例名称
  	public static String getCaseName(CaseInfo caseName) {
  		if (caseName.getCaseName().isEmpty()) {
  			throw new NestedBusinessException("测试用例名称不能为空！");
  		}
  		return caseName.getCaseName().get(constant.caseName);
  	}

  	//获取测试用例描述
  	public static String getCaseDesc(CaseInfo caseDesc) {
  		if (caseDesc.getCaseDesc().isEmpty()) {
  			throw new NestedBusinessException("测试用例描述不能为空！");
  		}
  		return caseDesc.getCaseDesc().get(constant.caseDesc);
  	}
  	//获取请求地址
  	public static String getRequestUrl(CaseInfo caseUrl) {
  		if (caseUrl.getCaseUrl().get(constant.caseUrl).isEmpty()) {
  			throw new NestedBusinessException("接口地址不能为空！");
  		}
  		return caseUrl.getCaseUrl().get(constant.caseUrl);
  	}
  	
 // 获取用例需要的参数
 	public static Map<String, String> getRequestParam(CaseInfo caseParam) {
 		Map<String, String> requestparam = new HashMap<String, String>();
 		for (String t : caseParam.getCaseParam().keySet()) {
 			String request = caseParam.getCaseParam().get(t);
 			//约定以{}开始结束，{}为空参数
 			if (!request.startsWith("{") && !request.endsWith("}")) {
 				throw new NestedBusinessException("测试用例参数格式有误，请检查参数格式！");
 			}
 			String realStr = request.substring(1, request.length() - 1);
 			
 			if (realStr.isEmpty() || !realStr.contains("=")) {
 				continue;
 			}
 			String[] res = realStr.split(",");
 			for (String str : res) {
 				if (str.split("=").length == 1) {
 					if (str.split("=")[0].isEmpty()) {
 						continue;
 					}else {
 						requestparam.put(str.split("=")[0],"");
 					}
 				}else{
 					requestparam.put(str.split("=")[0], str.split("=")[1]);
 				}
 			}
 		}
 		return requestparam;
 	}
      
  	//获取预期结果
  	public static String getExpect(CaseInfo caseParam) {
  		if (caseParam.getCaseExpect().get(constant.caseExpect).isEmpty()) {
  			throw new NestedBusinessException("预期結果不能为空！");
  		}
  		return caseParam.getCaseExpect().get(constant.caseExpect);
  	}
}
