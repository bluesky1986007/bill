package com.bill99.ta.infs.fpd.test.selftest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@ContextConfiguration(locations={"/qacontext/applicationContext.xml"})
public class SelfTestXlh extends AbstractTestNGSpringContextTests {
	

	@BeforeClass
	public void beforeClass() {		  

	  }
  
   @Test(enabled=true)
   public void fun(){
	   System.out.println("test!");
	   
	   
	  
  }

   
   @AfterClass
   public void afterClass() {

   }
 
//  @DataProvider(name = "accountBalance")
//  public Iterator<Object[]> data1test() throws IOException {
//  	return new ExcelProvider(this, "accountBalance");
//  }
//  
}
