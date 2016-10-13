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
public class UITransferInFromAcct extends BaseTestCase {

	private WebDriver dr;
	
	@Autowired
	private FPDCommonService fpdCommonService;
	@Autowired
	private InterfaceCheck interfaceCheck;

	@BeforeClass
	public void beforeClass() {

	}
	
	@Test(enabled = true,dataProvider = "TransferInFromAcct", description = "人民币账户转入理财通")
	public void companyTransferInFromAcct(Map<String, String> datadriven) throws FileNotFoundException,
			IOException, InterruptedException {

    	Reporter.start(datadriven.get("comment"));
		// UI登入系统
		dr = new InternetExplorerDriver();
//    	System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
//    	dr = new FirefoxDriver();
		dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dr.manage().window().maximize();

		dr = fpdCommonService.uiWebLogin(dr,datadriven.get("userName"),datadriven.get("userPwd"));
		
		//打开菜单
		new WebDriverWait(dr,30).until(ExpectedConditions.elementToBeClickable(By.linkText("理财助手"))).click();
//		dr.findElement(By.linkText("理财助手")).click();	
		if ("C".equals(datadriven.get("userType"))) {
			//企业用户
			dr.findElement(By.linkText("我的活期通")).click();

		} else {
			//个人用户
			dr.findElement(By.linkText("我的理财通")).click();
//			new WebDriverWait(dr,30).until(ExpectedConditions.elementToBeClickable(By.className("ButtonOrange"))).click();

		}

		//打开转入页面
		new WebDriverWait(dr,30).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='button'and@value='转入']"))).click();
		new WebDriverWait(dr,30).until(ExpectedConditions.visibilityOfElementLocated(By.id("innvestmentAmt")));
		
		new WebDriverWait(dr,30).until(ExpectedConditions.elementToBeClickable(By.linkText("转入/转出"))).click();		
		new WebDriverWait(dr,30).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='shengou']"))).click();
		
		//选择人民币转入方式且进行转入
		new WebDriverWait(dr,30).until(ExpectedConditions.elementToBeClickable(By.id("payType2"))).click();
		
		dr.findElement(By.id("innvestmentAmt")).sendKeys(datadriven.get("amount"));//转入金额		
		dr.findElement(By.id("payPin")).sendKeys(datadriven.get("payPwd"));//支付密码			
		dr.findElement(By.id("next")).click();
		
		//检查
		interfaceCheck.uiTransferInFromAcctCheck(dr);
		
		dr.quit();
		
		Reporter.end(datadriven.get("comment"));

	}
  
	  @DataProvider(name = "TransferInFromAcct")
	  public Iterator<Object[]> data2test() throws IOException {
	  	return ExcelProviderByEnv(this, "transferIn");
	  }
	  
		@AfterClass
		public void afterClass() {

		}
}
