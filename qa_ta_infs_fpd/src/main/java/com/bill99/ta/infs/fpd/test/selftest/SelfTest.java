package com.bill99.ta.infs.fpd.test.selftest;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import shelper.iffixture.HttpFixture;

import com.bill99.qa.framework.testcase.BaseTestCase;
import com.bill99.ta.infs.fpd.orm.ibatis.GetMapData;

public class SelfTest extends BaseTestCase {
	
	@Autowired
	private GetMapData getMapDataFromSeashellDb;
	
	HttpFixture  nhf;

	String unitName;
	String signId;

	@BeforeClass
	public void beforeClass() {		  
		nhf=new HttpFixture();
		
		  this.unitName="company32";
	  }
  
   @Test(enabled=true)
   public void fun(){
	   
	   getIntraInfo(unitName);
	   
	   
		HttpFixture nhf = new HttpFixture();
		nhf.nextRequest();
		nhf.setUrl("https://www.99bill.com/website/signup/bzsignupfinishpage.htm?validateCode=b53e8ff8dc0bcd34%209edb0da2d45bd86df3252152b4b73f8212fc277a2663a9c318b9a5b7a3127667");
		nhf.Get();
		
		nhf.nextRequest();
		nhf.setUrl("https://www.99bill.com/mbrentry/validatecode/validatecode.htm");
		nhf.Get();
		
		nhf.nextRequest();
		nhf.addHeaderValue("Content-Type", "application/x-www-form-urlencoded");
		nhf.addParamValue("agreement", "1");
		nhf.addParamValue("checkCode", "8888");
		nhf.addParamValue("companyName", unitName);
		nhf.addParamValue("email", unitName+"@123.com");
		nhf.addParamValue("greeting", "hello");	
		nhf.addParamValue("identityType", "1");	
		nhf.addParamValue("merchantName", "");
		nhf.addParamValue("password", "qa99bill");
		nhf.addParamValue("passwordRepeat", "qa99bill");
		nhf.addParamValue("payPwd", "123123");
		nhf.addParamValue("payPwdRepeat", "123123");
		nhf.addParamValue("RadioGroup1", "on");
		System.out.println(signId);
		nhf.addParamValue("signupId", signId);
		nhf.setUrl("https://www.99bill.com/mbrentry/signup/bzsignupfinish.htm");
		nhf.Post();
		
		System.out.println(unitName+"@123.com");
  }
   
   public void getIntraInfo(String companyName){
   	   
	   Map mapData = new HashMap();
	   
		List<Map> seashellDbList = getMapDataFromSeashellDb.getIntraUserInfo(companyName);
		
		for (int i = 0; i < seashellDbList.size(); i++) {
			mapData = (Map) seashellDbList.get(i);
									
		}
	   
		signId = mapData.get("SIGNUPID").toString();
		System.out.println(signId);
   }
   
   @AfterClass
   public void afterClass() {
	   nhf.nextRequest();
   }
 
//  @DataProvider(name = "accountBalance")
//  public Iterator<Object[]> data1test() throws IOException {
//  	return new ExcelProvider(this, "accountBalance");
//  }
//  
}
