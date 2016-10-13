package com.bill99.ta.infs.fpd.test.app;


import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



@ContextConfiguration(locations={"/qacontext/applicationContext.xml"})
public class AppSelfTest extends AbstractTestNGSpringContextTests {
	
	private AndroidDriver  driver;
//	private AppiumDriver driver;
	

	@BeforeClass(alwaysRun=true)
	public void setUp() throws Exception {	
		
		//设置apk的路径,获得程序当前路径System.getProperty("user.dir")
		 File classpathRoot = new File(System.getProperty("user.dir"));
		 File appDir = new File(classpathRoot, "apps");
		 File app = new File(appDir, "easyrongchuangye.apk");
		 
		//设置自动化相关参数,运行平台为Android（或ios）,与browser_name相矛盾，不能共存，
		//运行的设备为模拟器  Android Emulator
		 DesiredCapabilities capabilities = new DesiredCapabilities();		 
		 capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		 capabilities.setCapability("platformName", "Android");	
		 capabilities.setCapability("deviceName", "Android Emulator");
		 
//		 capabilities.setCapability("deviceName","device");
//		 capabilities.setCapability("automationName","Appium");
		 
	     //support Chinese 
	     capabilities.setCapability("unicodeKeyboard" ,"True");
	     capabilities.setCapability("resetKeyboard", "True");
		 
		//设置安卓系统版本,要和启动的模拟器平台保持一致
		 capabilities.setCapability("platformVersion", "4.3");
//		 capabilities.setCapability("udid", "emulator-5554");
		 //设置apk路径
		 capabilities.setCapability("app", app.getAbsolutePath());
		 
		//设置app的主包名和主类名，包名和类名稍后介绍如何获取
		 capabilities.setCapability("appPackage", "com.easyrongchuangye");
		 capabilities.setCapability("appActivity", ".activities.EasyrongChuangyeActivity");
	    //no need sign
	     capabilities.setCapability("noSign", "True");
		 
		//初始化，在模拟器上启动安装apk
//		 driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);		 
		 driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);


	  }
		
	
  
   @Test
   public void addContact() throws InterruptedException{
	   
	   //wait for 5s
       driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
       
       WebElement ui = driver.findElementByName("助理");
       ui.click();
       driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
       
       WebElement ui1 = driver.findElementByName("我的");
       ui1.click();
       
	  
  }

   
   @AfterClass(alwaysRun=true)
   public void tearDown() throws Exception {
	   
	   driver.quit();

   }
 
//  @DataProvider(name = "accountBalance")
//  public Iterator<Object[]> data1test() throws IOException {
//  	return new ExcelProvider(this, "accountBalance");
//  }
//  
}
