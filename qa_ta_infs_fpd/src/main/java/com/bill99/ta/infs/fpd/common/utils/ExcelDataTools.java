package com.bill99.ta.infs.fpd.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.StringUtils;

import shelper.common.MyDate;

import com.bill99.fpd.mfs.api.enums.FpdOrderSource;
import com.bill99.fpd.mfs.api.enums.RedeemTimeType;
import com.bill99.seashell.domain.dto.common.enums.AccountBalTypeEnum;
import com.bill99.seashell.domain.dto.common.enums.CurrencyCodeEnum;
import com.bill99.seashell.domain.dto.common.enums.DirectionTypeEnum;
//import com.bill99.seashell.domain.dto.common.enums.FpdOrderSourceEnum;
import com.bill99.seashell.domain.dto.common.enums.SubTypeEnum;
import com.bill99.seashell.domain.dto.http.item.FpdChangeBalanceBatchReqItem;

public class ExcelDataTools {

	/**
	 * 将数据驱动中的数据转换为所需类型的数据后再复制给各接口字段
	 * @return
	 * @throws ParseException 
	 */
	public static Map<String, Object> convertInputData(Map<String, String> datadriven) throws ParseException{
		
		Map<String, Object> params = new HashMap<String, Object>();
		Iterator<Entry<String, String>> it = datadriven.entrySet().iterator();
		//遍历每一列数据
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			
			if (StringUtils.hasLength(entry.getValue())) {
				String title = entry.getKey();//获取数据表中表头信息
				
				//表头中以req_开头的请求数据
			    if (StringUtils.startsWithIgnoreCase(title,"req_")) {
					//表头信息中以"_"为分隔符
					String[] titleSection =  title.split("_");
					//将数据驱动中的数据转换为Date型
					if (3==titleSection.length&&"date".equalsIgnoreCase(titleSection[2])) {
						Date myDate = null;
						SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
						//auto就表示当前时间
						if ("auto".equals(entry.getValue())) {
							String myDateString = MyDate.getUserDate("yyyyMMddHHmmss");
							myDate = formatter.parse(myDateString);
							params.put(titleSection[1],  myDate);
						} else {
			    			myDate = formatter.parse(entry.getValue());
			    			params.put(titleSection[1], myDate);
						}
					//将数据驱动中的数据转换为long型				
					} else if(3==titleSection.length&&"long".equalsIgnoreCase(titleSection[2])){						
		    			 params.put(titleSection[1], Long.parseLong(entry.getValue()));
					}else if(3==titleSection.length&&"Integer".equalsIgnoreCase(titleSection[2])){
						 params.put(titleSection[1], Integer.parseInt(entry.getValue()));
					}else if(3==titleSection.length&&"SubTypeEnum".equalsIgnoreCase(titleSection[2])){
						params.put(titleSection[1], SubTypeEnum.get(Integer.parseInt(entry.getValue())));
					}else if(3==titleSection.length&&"AccountBalTypeEnum".equalsIgnoreCase(titleSection[2])){
						params.put(titleSection[1], AccountBalTypeEnum.get(entry.getValue()));
					}else if(3==titleSection.length&&"CurrencyCodeEnum".equalsIgnoreCase(titleSection[2])){
						params.put(titleSection[1], CurrencyCodeEnum.CNY);
					}else if(3==titleSection.length&&"DirectionTypeEnum".equalsIgnoreCase(titleSection[2])){
						params.put(titleSection[1], DirectionTypeEnum.get(Integer.parseInt(entry.getValue())));
					}
//					else if(3==titleSection.length&&"FpdOrderSourceEnum".equalsIgnoreCase(titleSection[2])){
//						params.put(titleSection[1], FpdOrderSourceEnum.valueOf(entry.getValue()));
//					}
//					else if(3==titleSection.length&&"FpdRedeemTimeTypeEnum".equalsIgnoreCase(titleSection[2])){
//						params.put(titleSection[1], com.bill99.seashell.domain.dto.common.enums.FpdRedeemTimeType.valueOf(entry.getValue()));
//					}
					else if(3==titleSection.length&&"FpdOrderSource".equalsIgnoreCase(titleSection[2])){
						params.put(titleSection[1], FpdOrderSource.valueOf(entry.getValue()));
					}else if(3==titleSection.length&&"RedeemTimeType".equalsIgnoreCase(titleSection[2])){
						params.put(titleSection[1], RedeemTimeType.valueOf(entry.getValue()));
					}
					
					else{//剩余默认都是String型的
						
						//如果为请求id值为auto，将系统当前时间作为请求id
						if (("requestId".equalsIgnoreCase(titleSection[1])||"orderId".equalsIgnoreCase(titleSection[1]))&&"auto".equals(entry.getValue())) {
							String autoValueString = System.currentTimeMillis()+"";
							params.put(titleSection[1],autoValueString);
						}else{
							params.put(titleSection[1],entry.getValue());
						}																	
					}				
				}   
			}
		}

		return params;		
	}
	
	
	/**
	 * 转换并封装数据驱动中的list数据
	 * 
	 * @param datadriven
	 * @param clazz
	 * @param params
	 * @return
	 */
	public static Map<String, Object> convertInputListData(
			Map<String, String> datadriven, String sheetName)
			throws ParseException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		List dataList = new ArrayList();
		Map<String, Object> params = new HashMap<String, Object>();

		// list有几条数据
		int accountListNum = Integer.parseInt(datadriven.get("listNum"));
		for (int i = 0; i < accountListNum; i++) {
			Map<String, Object> listparams = new HashMap<String, Object>();
			Iterator<Entry<String, String>> it = datadriven.entrySet().iterator();
			// 遍历每一列数据
			while (it.hasNext()) {
				Entry<String, String> entry = it.next();

				if (StringUtils.hasLength(entry.getValue())) {
					String title = entry.getKey();// 获取数据表中表头信息
					// 表头以ls_开头的都是list数据
					if (StringUtils.startsWithIgnoreCase(title, "ls_")) {
						// 表头信息中以"_"为分隔符
						String[] titleSection = title.split("_");
						// 单元格中的list数据用换行符隔开
						String[] value = entry.getValue().split("\n");
						// 若list数据中最后一个为空，则不进行赋值
						if (i <= (value.length - 1)) {

							if ("req".equalsIgnoreCase(titleSection[1])) {
								// 转换long型
								if (4 == titleSection.length&& "long".equalsIgnoreCase(titleSection[3])) {
									listparams.put(titleSection[2],Long.parseLong(value[i]));
								} else if (4 == titleSection.length
										&& "AccountBalTypeEnum"
												.equalsIgnoreCase(titleSection[3])) {
									listparams.put(titleSection[2],
											AccountBalTypeEnum.get(value[i]));
								} else if (4 == titleSection.length
										&& "CurrencyCodeEnum"
												.equalsIgnoreCase(titleSection[3])) {
									listparams.put(titleSection[2],
											CurrencyCodeEnum.CNY);
								} else if (4 == titleSection.length
										&& "DirectionTypeEnum"
												.equalsIgnoreCase(titleSection[3])) {
									listparams.put(titleSection[2],
											DirectionTypeEnum.get(Integer
													.parseInt(value[i])));
								} else {
									// 默认为String
									listparams.put(titleSection[2], value[i]);
								}
							}
						}
					}
				}
			}

			// 理财账户交易接口
			if ("accountTrade".equalsIgnoreCase(sheetName)) {
				FpdChangeBalanceBatchReqItem listItem = new FpdChangeBalanceBatchReqItem();
				System.out.println((i + 1) + listparams.toString());
				PropertyUtils.copyProperties(listItem, listparams);
				dataList.add(listItem);
			}
		}

		params.put("accountList", dataList);

		return params;
	}
	
	/**
	 * 转换类对象中的数据
	 * @param datadriven
	 * @return
	 */
	public static Map<String, Object> convertClassData(Map<String, String> datadriven){
		
		Map<String, Object> calssParams = new HashMap<String, Object>();
		
		Iterator<Entry<String, String>> it = datadriven.entrySet().iterator();
		
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			
			if (StringUtils.hasLength(entry.getValue())) {
				String title = entry.getKey();// 获取数据表中表头信息
				
				if (StringUtils.startsWithIgnoreCase(title, "cl_")) {
					String[] titleSection = title.split("_");
					
					if ("req".equalsIgnoreCase(titleSection[1])) {
						
						if (4 == titleSection.length&& "Integer".equalsIgnoreCase(titleSection[3])) {
							calssParams.put(titleSection[2],Integer.parseInt(entry.getValue()));
						} else {
							calssParams.put(titleSection[2],entry.getValue());
						}
						
					}
				
				}
		
			}		
		}
				
		return calssParams;
	}
	

	
	/**
	 * 获取Excel中预期结果数据
	 * @param datadriven
	 * @return
	 * @throws ParseException
	 */
	public static Map<String, Object> getExpRspData(
			Map<String, String> datadriven) {

		Map<String, Object> params = new HashMap<String, Object>();
		Iterator<Entry<String, String>> it = datadriven.entrySet().iterator();
		// 遍历每一行数据
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();

			// 获取数据表中表头信息
			String title = entry.getKey();
			// 表头中以rsp_开头的为预期结果数据
			if (StringUtils.startsWithIgnoreCase(title, "rsp_")) {
				// 表头信息中以"_"为分隔符
				String[] titleSection = title.split("_");	
				params.put(titleSection[1], entry.getValue());
			}

		}

		return params;
	}

	
}