package com.bill99.ta.infs.fpd.test.interfaces;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.bill99.qa.framework.testcase.BaseTestCase;
import com.bill99.seashell.domain.dto.http.FpdredeemToAccountRequest;
import com.bill99.seashell.domain.dto.http.FpdredeemToAccountResonse;
import com.bill99.seashell.domain.service.FpdAccountSvc;
import com.bill99.ta.infs.fpd.service.ConvertDataService;
import com.bill99.ta.infs.fpd.service.InterfaceCheck;


public class AccountTransferOutTest extends BaseTestCase {
  
  @Autowired
  private FpdAccountSvc fpdAccountSvc;
  @Autowired
  private ConvertDataService convertDataService;
  @Autowired
  private InterfaceCheck interfaceCheck;
  
  @Test(dataProvider = "accountTransferOut")
  public void accountTransferOut(Map<String, String> datadriven) throws ParseException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	  	  
	  Reporter.start("------"+datadriven.get("seq")+" "+datadriven.get("comment")+"-------");
	  //创建请求对象
	  FpdredeemToAccountRequest req = convertDataService.convertReqData(datadriven, new FpdredeemToAccountRequest());

	  //发起请求，获取返回进行验证
	  FpdredeemToAccountResonse rsp = fpdAccountSvc.redeemToAccountHandle(req);
	  
	  //结果检核
	  interfaceCheck.accountTransferOutCheck(datadriven, rsp);
	  
	  //打印部分信息
//	  System.out.println("errorcode:"+rsp.getErrorCode());
//	  System.out.println("errormsg:"+rsp.getErrorMsg());
	  System.out.println("tranSerialNumber:"+rsp.getTranSerialNumber());
	  	  
	  Reporter.end("--------"+datadriven.get("seq")+" "+datadriven.get("comment")+"---------");

  }
  
  @DataProvider(name = "accountTransferOut")
  public Iterator<Object[]> data1test() throws IOException {
  	return ExcelProviderByEnv(this, "accountTransferOut");
  }
  
}
