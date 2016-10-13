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
import com.bill99.seashell.domain.dto.http.FpdChangeBalanceRequest;
import com.bill99.seashell.domain.dto.http.FpdChangeBalanceResponse;
import com.bill99.seashell.domain.service.FpdAccountSvc;
import com.bill99.ta.infs.fpd.service.ConvertDataService;
import com.bill99.ta.infs.fpd.service.InterfaceCheck;


public class AccountBalanceDPMTest extends BaseTestCase {
  
  @Autowired
  private FpdAccountSvc fpdAccountSvc;
  @Autowired
  private ConvertDataService convertDataService;
  @Autowired
  private InterfaceCheck interfaceCheck;
  
  @Test(dataProvider = "accountBalance")
  public void accountBalance(Map<String, String> datadriven) throws ParseException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	  	  
	  Reporter.start("------"+datadriven.get("seq")+" "+datadriven.get("comment")+"-------");
	  //创建请求对象
	  FpdChangeBalanceRequest req = convertDataService.convertReqData(datadriven, new FpdChangeBalanceRequest());

	  //发起请求，获取返回进行验证
	  FpdChangeBalanceResponse rsp = fpdAccountSvc.changeBalance(req);
	  
	  //打印部分信息
//	  System.out.println("errorcode:"+rsp.getErrorCode());
//	  System.out.println("errormsg:"+rsp.getErrorMsg());
	  System.out.println("accountBalType"+rsp.getAccountBalType());
	  System.out.println("currencyCode"+rsp.getCurrencyCode());
	  System.out.println("direction"+rsp.getDirection());
	  System.out.println("tranSerialNumber:"+rsp.getTransSerialNumber());
	  System.out.println("amount:"+rsp.getAmount());
	  
	  //结果检核
	  interfaceCheck.accountBalanceDPMCheck(datadriven, rsp);	  
	  	  
	  Reporter.end("--------"+datadriven.get("seq")+" "+datadriven.get("comment")+"---------");

  }
  
  @DataProvider(name = "accountBalance")
  public Iterator<Object[]> data1test() throws IOException {
  	return ExcelProviderByEnv(this, "accountBalance");
  }
  
}
