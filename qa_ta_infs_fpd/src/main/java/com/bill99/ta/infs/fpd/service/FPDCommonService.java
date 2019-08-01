package com.bill99.ta.infs.fpd.service;


import org.openqa.selenium.WebDriver;


public interface FPDCommonService {
	

	/**
	 * website登入
	 * @param driver
	 * @param userName  用户名
	 * @param userPwd   用户密码
	 * @return
	 */
	public WebDriver uiWebLogin(WebDriver driver,String userName,String userPwd);
	
}
