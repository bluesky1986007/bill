package com.bill99.ta.infs.fpd.test.selftest;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import shelper.iffixture.HttpFixture;

import com.bill99.qa.framework.testcase.BaseTestCase;
import com.bill99.ta.infs.fpd.orm.ibatis.GetMapData;

public class CompanyAdd extends BaseTestCase {
	
	@Autowired
	private GetMapData getMapDataFromSeashellDb;
	
	HttpFixture  hf;
	WebDriver dr;
	
	int num;

	String env;
	String linkMan;
	String workorderId;
	String unitName;
	String signId;
	String membercode;
	String registerUrl;

	@BeforeClass
	public void beforeClass() {		  
		  hf=new HttpFixture();
		  
		  env = "02";//dev,02
//		  num = 69;
//		  unitName="company"+num;
//		  linkMan="tester"+num;
		  
		  unitName="companytest";		 		  
		  linkMan="companytester";
		  
		  
	  }
  
   @Test(enabled=true)
   public void fun(){//创建企业账户
	  
	   loginIntra();   
	   createUser();
	   getIntraInfo(unitName+"@123.com");
	   
	   confirm();
	   
	   if ("dev".equals(env)) {
		  functionDev();
	   } else {
		   function();

	   }
  
	   activation();
	   UIregister();
	   
	   System.out.println(unitName+"@123.com");
	   System.out.println(membercode);
  }


   public void activation(){
	   System.out.println("activation begin...");
	   
		hf.nextRequest();
		hf.setUrl("https://intra.99bill.com/maintra/signup/biz/active.htm?method=showInfo&applyId="+signId);
		hf.Get();

		String rspBody = hf.getResponseBody();
		registerUrl = rspBody.substring(rspBody.indexOf("https"), rspBody.indexOf("</p>"));
		System.out.println(registerUrl);		

		System.out.println("activation end...");
   }
   
   public void UIregister(){
	   
	   if ("dev".equals(env)) {
		String devUrl = registerUrl.replaceFirst("website", "mbrentry");
		registerUrl = devUrl;
	} 
		dr = new InternetExplorerDriver();
		dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dr.get(registerUrl);
		
		new WebDriverWait(dr,30).until(ExpectedConditions.visibilityOfElementLocated(By.id("formFocus"))).sendKeys("hello");
		dr.findElement(By.id("password")).sendKeys("qa99bill");
		dr.findElement(By.id("passwordRepeat")).sendKeys("qa99bill");
		dr.findElement(By.id("payPwd")).sendKeys("123123");
		dr.findElement(By.id("payPwdRepeat")).sendKeys("123123");
		dr.findElement(By.id("verifiedCode")).sendKeys("8888");
		dr.findElement(By.xpath("//input[@value='确定'and@type='submit']")).click();
		dr.quit();

   }
   
   
   public void loginIntra(){
	   
	   System.out.println("loginIntra bgein.....");
	   
	    if ("dev".equals(env)) {
		   hf.setUrl("http://intra.99bill.com/ssoLogin/login.htm");
	    } else {
		   hf.setUrl("https://intra.99bill.com/ssoLogin/login.htm");
	    }
		hf.Get();
		
	    //输入用户名和密码登入fsc系统
		hf.nextRequest();
		hf.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		if ("dev".equals(env)) {
			hf.setUrl("http://intra.99bill.com/ssoLogin/login.htm");
		}else {
			hf.setUrl("https://intra.99bill.com/ssoLogin/login.htm");
		}		
		hf.addParamValue("method","login");
		hf.addParamValue("password","99bill99");
		if ("dev".equals(env)) {
			hf.addParamValue("userName","admin");
		}else {
			hf.addParamValue("userName","qatest_sh");
		}
		hf.addParamValue("tokenPWD","");
		hf.Post();
		
		if (!"dev".equals(env)) {
			hf.nextRequest();		
			hf.setUrl("https://intra.99bill.com/subintra/subindex.htm");
			hf.Get();
			
//			System.out.println(hf.getResponseBody());
			boolean result=hf.findStringinResponse("欢迎您");
			System.out.println(result);		
		}
		 System.out.println("loginIntra end.....");
   }
   
   /**
 * 创建企业账户
 */
  public void createUser(){
	  
	  System.out.println("createUser begin...");
		hf.nextRequest();
		hf.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		hf.addParamValue("city", "10031001");
		hf.addParamValue("email", unitName+"@123.com");
		hf.addParamValue("linkMan", linkMan);
		hf.addParamValue("linkTel", "12345678901");
		hf.addParamValue("merchantName", "");	
		hf.addParamValue("pageId", "1");	
		hf.addParamValue("province", "10031");
		hf.addParamValue("remark", "");
		hf.addParamValue("sheetId", "");
		hf.addParamValue("Submit", "继  续");
		hf.addParamValue("trade", "2");
		hf.addParamValue("unitName", unitName);
		hf.addParamValue("webSiteAddress", "");
		if ("dev".equals(env)) {
			hf.setUrl("http://intra.99bill.com/maintra/signup/biz/addworkorderview.htm");
		} else {
			hf.setUrl("https://intra.99bill.com/maintra/signup/biz/addworkorderview.htm");
		}
		hf.Post();
		System.out.println("createUser end...");
   }

   
   public void confirm(){
	   System.out.println("confirm begin....");
		hf.nextRequest();
		hf.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		hf.addParamValue("allCheckBoxs", workorderId);
		hf.addParamValue("method", "submit");
		hf.addParamValue("size", "10");
		hf.setUrl("https://intra.99bill.com/maintra/signup/biz/csvrcworkorderview.htm");
		hf.Post();
		
		hf.nextRequest();
		hf.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		hf.addParamValue("status", "4");
		hf.addParamValue("Submit", "确  定");
		hf.addParamValue("textarea", "pass");
		hf.setUrl("https://intra.99bill.com/maintra/signup/biz/checkworkorderview.htm?sheetId="+workorderId);
		hf.Post();
		
		//开通功能		
		hf.nextRequest();
		hf.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		hf.addParamValue("agreement", "1");
		hf.addParamValue("checkCode", "8888");
		hf.addParamValue("companyName", unitName);
		hf.addParamValue("email", unitName+"@123.com");
		hf.addParamValue("greeting", "hello");	
		hf.addParamValue("identityType", "1");	
		hf.addParamValue("merchantName", "");
		hf.addParamValue("password", "qa99bill");
		hf.addParamValue("passwordRepeat", "qa99bill");
		hf.addParamValue("payPwd", "123123");
		hf.addParamValue("payPwdRepeat", "123123");
		hf.addParamValue("RadioGroup1", "on");
		hf.addParamValue("signupId", signId);
		hf.setUrl("https://www.99bill.com/mbrentry/signup/bzsignupfinish.htm");
		hf.Post();
		System.out.println("confirm end....");   
   }
   
   public void function(){	
	   
	   System.out.println("function begin...");
	    getMemberCode();
	   
		hf.nextRequest();
		hf.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		hf.addParamValue("featureCode", "F1_3");
		hf.addParamValue("featureCode", "F14_1");
		hf.addParamValue("featureCode", "F1_2");
		hf.addParamValue("featureCode", "F31_1");
		hf.addParamValue("featureCode", "F14_2");
		hf.addParamValue("featureCode", "F63_7");
		hf.addParamValue("featureCode", "F63_8");
		hf.addParamValue("featureCode", "F63_4");
		hf.addParamValue("featureCode", "F63_5");
		hf.addParamValue("featureCode", "F63_6");
		hf.addParamValue("memberCode", membercode);
		hf.addParamValue("productCode", "311");
		hf.addParamValue("Submit", "保存");
		hf.setUrl("https://intra.99bill.com/intra/product/setmemberproductparameter.htm?method=subscribe");
		hf.Post();
			
		hf.nextRequest();
		hf.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		hf.addParamValue("id", "");
		hf.addParamValue("memberCode", membercode);
		hf.addParamValue("memo", "");
		hf.addParamValue("securityProduct", "40");
		hf.addParamValue("securityProductOld", "0");
		hf.setUrl("https://intra.99bill.com/intra/product/setsecurityproduct.htm?method=save");
		hf.Post();
		System.out.println("function end...");   
   }
   
   public void functionDev(){
	    getMemberCode();
		   
		hf.nextRequest();
		hf.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		hf.addParamValue("featureCode", "F14_1");
		hf.addParamValue("featureCode", "F14_2");
		hf.addParamValue("featureCode", "F1_2");
		hf.addParamValue("featureCode", "F1_3");
		hf.addParamValue("featureCode", "F31_1");
		hf.addParamValue("featureCode", "F63_4");
		hf.addParamValue("featureCode", "F63_5");
		hf.addParamValue("featureCode", "F63_6");
		hf.addParamValue("featureCode", "F63_7");
		hf.addParamValue("featureCode", "F63_8");
		hf.addParamValue("memberCode", membercode);
		hf.addParamValue("productCode", "311");
		hf.addParamValue("Submit", "保存");
		hf.setUrl("http://intraoc4j.99bill.com/intra/product/setmemberproductparameter.htm?method=subscribe");
		hf.Post();
			
		hf.nextRequest();
		hf.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		hf.addParamValue("id", "");
		hf.addParamValue("memberCode", membercode);
		hf.addParamValue("memo", "");
		hf.addParamValue("securityProduct", "40");
		hf.addParamValue("securityProductOld", "0");
		hf.setUrl("http://intraoc4j.99bill.com/intra/product/setsecurityproduct.htm?method=save");
		hf.Post();
   
   }
   
   public void getIntraInfo(String companyName){
   	   
	   Map mapData = new HashMap();
	   
		List<Map> seashellDbList = getMapDataFromSeashellDb.getIntraUserInfo(companyName);
		
		for (int i = 0; i < seashellDbList.size(); i++) {
			mapData = (Map) seashellDbList.get(i);
									
		}
	   
		System.out.println(mapData);
		workorderId = mapData.get("WORKORDERID").toString();
		System.out.println(workorderId);
		signId = mapData.get("SIGNUPID").toString();
		System.out.println(signId);
   }
   
   public void getMemberCode(){
   	   
	   Map mapData = new HashMap();
	   
		List<Map> seashellDbList = getMapDataFromSeashellDb.getIntraMember(unitName+"@123.com");
		
		for (int i = 0; i < seashellDbList.size(); i++) {
			mapData = (Map) seashellDbList.get(i);
									
		}
	   
		membercode = mapData.get("MEMBERCODE").toString();
		System.out.println(membercode);

   }
   
   
   @AfterClass
   public void afterClass() {
 	  hf.nextRequest();
   }
 
//  @DataProvider(name = "accountBalance")
//  public Iterator<Object[]> data1test() throws IOException {
//  	return new ExcelProvider(this, "accountBalance");
//  }
//  
}
