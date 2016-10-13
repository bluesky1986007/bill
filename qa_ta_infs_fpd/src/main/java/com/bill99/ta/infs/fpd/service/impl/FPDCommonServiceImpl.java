package com.bill99.ta.infs.fpd.service.impl;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import shelper.autoit3.Autoit3;
import shelper.environment.Environment;

import com.bill99.ta.infs.fpd.service.FPDCommonService;

public class FPDCommonServiceImpl implements FPDCommonService {

	private String webLoginValidate;
	
	private String webLoginUrl;

	@Override
	public WebDriver uiWebLogin(WebDriver dr,String userName,String userPwd){
		// TODO Auto-generated method stub
		//打开主页
		dr.get(webLoginUrl);
		new WebDriverWait(dr,30).until(ExpectedConditions.visibilityOfElementLocated(By.id("w1")));
		
		
		//找到对应的frame
		dr.switchTo().frame(0);
		//输入用户名和密码
		new WebDriverWait(dr,30).until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys(userName);
//		dr.findElement(By.id("email")).sendKeys(userName);
		dr.findElement(By.id("inputRand")).sendKeys(webLoginValidate);
		//拉动滚动条
//		JavascriptExecutor jse=(JavascriptExecutor) dr;
		((JavascriptExecutor) dr).executeScript("arguments[0].scrollIntoView();", dr.findElement(By.className("loginButton")));

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//点击登录		
//		new WebDriverWait(dr,30).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit'and@class='loginButton'and@value='登 录']"))).click();
		new WebDriverWait(dr,30).until(ExpectedConditions.elementToBeClickable(By.className("loginButton"))).click();
		
		//输入密码并登入
		Environment.set4If();
		
		new WebDriverWait(dr,30).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("忘记密码，请点击此处")));
		Autoit3.run("前台登陆密码输入.au3"+" "+userPwd);
		
		//个人和企业账户的登入按钮id不同，需要先判断再点击
		WebElement element = dr.findElement(By.xpath("//input[@class='ButtonTwoNext'and@value='登录'and@type='submit']"));
		String idString = element.getAttribute("id");
		if ("submitId".equals(idString)) {
			//企业账户
			element.click();
		} else {
			//个人账户
			dr.findElement(By.id("submit1")).click();
		}
		
		return dr;
	}

	/**
	 * @return the webLoginUrl
	 */
	public String getWebLoginUrl() {
		return webLoginUrl;
	}

	/**
	 * @param webLoginUrl the webLoginUrl to set
	 */
	public void setWebLoginUrl(String webLoginUrl) {
		this.webLoginUrl = webLoginUrl;
	}

	/**
	 * @return the webLoginValidate
	 */
	public String getWebLoginValidate() {
		return webLoginValidate;
	}

	/**
	 * @param webLoginValidate the webLoginValidate to set
	 */
	public void setWebLoginValidate(String webLoginValidate) {
		this.webLoginValidate = webLoginValidate;
	}

	
}
