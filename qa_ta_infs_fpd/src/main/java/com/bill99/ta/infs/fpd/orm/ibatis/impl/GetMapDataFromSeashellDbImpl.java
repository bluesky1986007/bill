package com.bill99.ta.infs.fpd.orm.ibatis.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.bill99.qa.framework.jdbc.TaDbHandller;
import com.bill99.ta.infs.fpd.orm.ibatis.GetMapData;

public class GetMapDataFromSeashellDbImpl implements GetMapData{
	
	@Resource
	private TaDbHandller taMAMDbHandller;

	/**
	 * @param taSeashellDbHandller the taSeashellDbHandller to set
	 */
	public void settaMAMDbHandller(TaDbHandller taMAMDbHandller) {
		this.taMAMDbHandller = taMAMDbHandller;
	}

	@Override
	public List<Map> getIntraUserInfo(String companyName) {

		// TODO Auto-generated method stub
		//根据ibatis xml 中sql 返回数据：
		return taMAMDbHandller.queryForList("intra.intraUserInfo", companyName);
	}

	@Override
	public List<Map> getIntraMember(String companyEmail) {
		// TODO Auto-generated method stub
		return taMAMDbHandller.queryForList("intra.intraMemberCode", companyEmail);
	}

}
