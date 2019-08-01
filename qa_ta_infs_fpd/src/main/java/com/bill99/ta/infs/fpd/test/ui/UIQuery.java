package com.bill99.ta.infs.fpd.test.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.bill99.qa.framework.testcase.BaseTestCase;
import com.bill99.ta.infs.fpd.service.FPDCommonService;
import com.bill99.ta.infs.fpd.service.InterfaceCheck;

//@ContextConfiguration(locations = { "classpath:context/applicationContext.xml","classpath:context/context-mdp-client.xml" })
//public class SelfTestBank extends AbstractTestNGSpringContextTests {
public class UIQuery extends BaseTestCase {

	private WebDriver dr;
	
	@Autowired
	private FPDCommonService fpdCommonService;
	@Autowired
	private InterfaceCheck interfaceCheck;

	@BeforeClass
	public void beforeClass() {

	}
	
	@Test(enabled = true,dataProvider = ""+"query", description = "理财通查询")
	public void companyTransferInFromAcct(Map<String, String> datadriven) throws FileNotFoundException,
			IOException, InterruptedException {

    	Reporter.start(datadriven.get("comment"));
		// UI登入系统
		dr = new InternetExplorerDriver();
		dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dr.manage().window().maximize();
		dr = fpdCommonService.uiWebLogin(dr,datadriven.get("userName"),datadriven.get("userPwd"));
		
		//打开菜单
		new WebDriverWait(dr,30).until(ExpectedConditions.elementToBeClickable(By.linkText("理财助手"))).click();
		
		//打开交易查询菜单
		if ("C".equals(datadriven.get("userType"))) {
			//企业用户
			dr.findElement(By.linkText("交易查询")).click();
		} else {
            //个人用户
			dr.findElement(By.xpath("//div[@id='menuLeft']/dl[3]/dd[3]/a")).click();

		}

		//点击查询按钮
		new WebDriverWait(dr,30).until(ExpectedConditions.elementToBeClickable(By.id("seleBtt"))).click();
		
		//检查
		interfaceCheck.uiQueryCheck(dr);
		
		dr.quit();
		
		Reporter.end(datadriven.get("comment"));

	}
  
	  @DataProvider(name = "query")
	  public Iterator<Object[]> data2test() throws IOException {
	  	return ExcelProviderByEnv(this, "query");
	  }
	  
		@AfterClass
		public void afterClass() {

		}
}
