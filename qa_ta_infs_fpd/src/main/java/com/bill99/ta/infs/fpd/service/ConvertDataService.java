package com.bill99.ta.infs.fpd.service;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Map;

public interface ConvertDataService {
	
	/**
	 * 将数据驱动中的数据封装转换为请求数据
	 * @param datadriven
	 * @param clazz
	 * @return
	 * @throws ParseException
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public <T extends Object> T convertReqData(Map<String, String> datadriven,T clazz) throws ParseException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;

//	public Map<String, String> converRespData(Map<String, String> datadriven);
}
