package com.bill99.ta.infs.fpd.orm.ibatis;

import java.util.List;
import java.util.Map;

public interface GetMapData {
	
	/**
	 * 获取intra用户信息
	 * @param companyName
	 * @return
	 */
	public List<Map> getIntraUserInfo(String companyName);
	
	public List<Map> getIntraMember(String companyEmail);
	
}
