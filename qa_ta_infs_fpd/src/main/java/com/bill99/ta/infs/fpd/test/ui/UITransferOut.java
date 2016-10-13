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
import org.openqa.selenium.support.ui.Select;
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
public class UITransferOut extends BaseTestCase {

	private WebDriver dr;
	
	@Autowired
	private FPDCommonService fpdCommonService;
	@Autowired
	private InterfaceCheck interfaceCheck;

	@BeforeClass
	public void beforeClass() {

	}
	
	@Test(enabled = true,dataProvider = "transferOut", description = "理财产品转出")
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

		if ("C".equals(datadriven.get("userType"))) {
			//企业用户
			dr.findElement(By.linkText("我的活期通")).click();
			
			//转出
//			new WebDriverWait(dr,30).until(ExpectedConditions.elementToBeClickable(By.linkText("转出"))).click();
			new WebDriverWait(dr,30).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='u43'and@class='text']/span[2]/a"))).click();
						
			if ("acct".equalsIgnoreCase(datadriven.get("transferOutTo"))) {
				//选择转出到人民币账户
				new WebDriverWait(dr,30).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='radio'and@name='redemptionType'and@value='1']"))).click();
//				dr.findElement(By.xpath("//input[@type='radio'and@name='redemptionType'and@value='1']")).click();
			}else if("bank".equalsIgnoreCase(datadriven.get("transferOutTo"))){
				//选择转出到银行卡
				new WebDriverWait(dr,30).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='radio'and@name='redemptionType'and@value='3']"))).click();
//				dr.findElement(By.xpath("//input[@type='radio'and@name='redemptionType'and@value='3']")).click();
				
				//选择银行卡
				if ("招商银行".equals(datadriven.get("bankName"))) {
					//03环境企业还款到银行卡用招商银行
					dr.findElement(By.xpath("//input[@type='radio'and@name='debitBankId'and@value='497364']")).click();
					
				} else {
					//还款到中国工商银行
					dr.findElement(By.xpath("//input[@type='radio'and@name='debitBankId'and@value='487841']")).click();

				}
		
			}
			
			dr.findElement(By.id("payPin")).sendKeys(datadriven.get("payPwd"));//支付密码
			
		} else {
			//个人用户
			dr.findElement(By.linkText("我的理财通")).click();
						
			if ("credit".equalsIgnoreCase(datadriven.get("transferOutTo"))) {
				//信用卡还款
//				new WebDriverWait(dr,30).until(ExpectedConditions.elementToBeClickable(By.className("ButtonGray"))).click();
				new WebDriverWait(dr,30).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='ButtonGray'and@value='信用卡还款']"))).click();
				new Select(dr.findElement(By.id("bankId"))).selectByVisibleText(datadriven.get("bankName"));
				
				dr.findElement(By.id("bankAcctId")).sendKeys(datadriven.get("card"));
				dr.findElement(By.id("bankAcctIdConfirm")).sendKeys(datadriven.get("card"));
				
			}else{
				//转出
				new WebDriverWait(dr,30).until(ExpectedConditions.elementToBeClickable(By.className("ButtonOrange"))).click();
			}

		}
	
		//输入金额
		new WebDriverWait(dr,30).until(ExpectedConditions.visibilityOfElementLocated(By.id("redemptionAmt"))).sendKeys(datadriven.get("amount"));
		//下一步
		dr.findElement(By.id("next")).click();
		
		//检查
		interfaceCheck.uiTransferOutCheck(dr);
		
		dr.quit();
		
		Reporter.end(datadriven.get("comment"));

	}
  
	  @DataProvider(name = "transferOut")
	  public Iterator<Object[]> data2test() throws IOException {
	  	return ExcelProviderByEnv(this, "transferOut");
	  }
	  
		@AfterClass
		public void afterClass() {

		}
}
