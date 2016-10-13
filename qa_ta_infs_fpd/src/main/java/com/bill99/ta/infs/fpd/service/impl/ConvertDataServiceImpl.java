package com.bill99.ta.infs.fpd.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.testng.Reporter;

import com.bill99.fpd.api.dto.Page;
import com.bill99.ta.infs.fpd.common.utils.ExcelDataTools;
import com.bill99.ta.infs.fpd.service.ConvertDataService;


public class ConvertDataServiceImpl implements ConvertDataService {

	@Override
	public <T> T convertReqData(Map<String, String> datadriven, T clazz) throws ParseException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		
		Map<String,Object> params = new HashMap<String, Object>();
		Map<String,Object> listparams = new HashMap<String, Object>();
		Map<String,Object> classparams = new HashMap<String, Object>();
		String sheetName = datadriven.get("sheetName");//工作表名
	
		//转换数据驱动中的数据
		params = ExcelDataTools.convertInputData(datadriven);
		
		//理财账户交易接口
		if ("accountTrade".equalsIgnoreCase(sheetName)) {
			//转换数据驱动中list数据
			listparams = ExcelDataTools.convertInputListData(datadriven, sheetName);
			params.put("accountList", listparams.get("accountList"));
		}
		
		if ("PrdTradeQueryTest".equalsIgnoreCase(sheetName)) {
			Page pageItem = new Page();
			classparams = ExcelDataTools.convertClassData(datadriven);
			PropertyUtils.copyProperties(pageItem, classparams);
			params.put("page", pageItem);
		}
	
		Reporter.log("所有excel封装数据："+params);				
		//将map中的数据和请求类中的数据进行一一匹配
		PropertyUtils.copyProperties(clazz, params);
		
		//将map对象中的数据再转换成String型打印出来
		Map finalmap = BeanUtils.describe(clazz);
		Reporter.log("请求数据：" + finalmap);
	
		return clazz;
	}

}


