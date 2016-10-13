package com.bill99.ta.infs.fpd.test.selftest;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import shelper.iffixture.HttpFixture;

import com.bill99.qa.framework.testcase.BaseTestCase;

public class UserRegister extends BaseTestCase {
	
	HttpFixture  hf;
	String unitName;
	String signId;

	@BeforeClass
	public void beforeClass() {		  
		hf=new HttpFixture();
		
//		unitName = CompanyAdd.unitName;
//		signId = CompanyAdd.signId;
	  }
  
   @Test(enabled=false)
   public void fun(){	   
	   
		hf.nextRequest();
		hf.setUrl("");
		hf.Get();
		
		hf.nextRequest();
		hf.setUrl("https://www.99bill.com/mbrentry/validatecode/validatecode.htm");
		hf.Get();
		
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
		System.out.println(signId);
		hf.addParamValue("signupId", signId);
		hf.setUrl("https://www.99bill.com/mbrentry/signup/bzsignupfinish.htm");
		hf.Post();
		
		System.out.println(unitName+"@123.com");
  }
   
   @Test(enabled=true)
   public void getInfo(){
	   

	   
   }
   
//   public void getIntraInfo(String companyName){
//   	   
//	   Map mapData = new HashMap();
//	   
//		List<Map> seashellDbList = getMapDataFromSeashellDb.getIntraUserInfo(companyName);
//		
//		for (int i = 0; i < seashellDbList.size(); i++) {
//			mapData = (Map) seashellDbList.get(i);
//									
//		}
//	   
//		signId = mapData.get("SIGNUPID").toString();
//		System.out.println(signId);
//   }
   
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
