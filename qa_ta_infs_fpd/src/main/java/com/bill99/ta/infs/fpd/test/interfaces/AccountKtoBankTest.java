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
import com.bill99.seashell.domain.dto.http.FpdPay2BankRequest;
import com.bill99.seashell.domain.dto.http.FpdPay2BankResponse;
import com.bill99.seashell.domain.service.FpdAccountSvc;
import com.bill99.ta.infs.fpd.service.ConvertDataService;
import com.bill99.ta.infs.fpd.service.InterfaceCheck;


public class AccountKtoBankTest extends BaseTestCase {
  
  @Autowired
  private FpdAccountSvc fpdAccountSvc;
  @Autowired
  private ConvertDataService convertDataService;
  @Autowired
  private InterfaceCheck interfaceCheck;
  
  @Test(dataProvider = ""+"accountKtoBank")
  public void accountKtoBank(Map<String, String> datadriven) throws ParseException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	  	  
	  Reporter.start("------"+datadriven.get("seq")+" "+datadriven.get("comment")+"-------");
	  //创建请求对象
	  FpdPay2BankRequest req = convertDataService.convertReqData(datadriven, new FpdPay2BankRequest());

	  //发起请求，获取返回进行验证
	  FpdPay2BankResponse rsp = fpdAccountSvc.pay2Bank(req);
	  
	  //结果检核
	  interfaceCheck.accountKtoBankCheck(datadriven, rsp);
	  
	  //打印部分信息
//	  System.out.println("errorcode:"+rsp.getErrorCode());
//	  System.out.println("errormsg:"+rsp.getErrorMsg());
//	  System.out.println("amount:"+rsp.getAmount());
	  System.out.println("innerSettleSeqId:"+rsp.getInnerSettleSeqId());
	  System.out.println("tranSerialNumber:"+rsp.getTranSerialNumber());
	  	  
	  Reporter.end("--------"+datadriven.get("seq")+" "+datadriven.get("comment")+"---------");

  }
  
  @DataProvider(name = "accountKtoBank")
  public Iterator<Object[]> data1test() throws IOException {
  	return ExcelProviderByEnv(this, "accountKtoBank");
  }
  
}
