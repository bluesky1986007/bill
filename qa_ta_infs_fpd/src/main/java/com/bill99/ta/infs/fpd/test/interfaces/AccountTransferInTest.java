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
import com.bill99.seashell.domain.dto.http.FpdSubscribeRequest;
import com.bill99.seashell.domain.dto.http.FpdSubscribeResponse;
import com.bill99.seashell.domain.service.FpdAccountSvc;
import com.bill99.ta.infs.fpd.service.ConvertDataService;
import com.bill99.ta.infs.fpd.service.InterfaceCheck;


public class AccountTransferInTest extends BaseTestCase {
  
  @Autowired
  private FpdAccountSvc fpdAccountSvc;
  @Autowired
  private ConvertDataService convertDataService;
  @Autowired
  private InterfaceCheck interfaceCheck;
  
  @Test(dataProvider = "accountTransferIn")
  public void accountTransferIn(Map<String, String> datadriven) throws ParseException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	  	  
	  Reporter.start("------"+datadriven.get("seq")+" "+datadriven.get("comment")+"-------");
	  //创建请求对象
	  FpdSubscribeRequest req = convertDataService.convertReqData(datadriven, new FpdSubscribeRequest());

	  //发起请求，获取返回进行验证
	  FpdSubscribeResponse rsp = fpdAccountSvc.subscribe(req);
	  
	  //结果检核
	  interfaceCheck.accountTransferInCheck(datadriven, rsp);
	  
	  //打印部分信息
//	  System.out.println("errorcode:"+rsp.getErrorCode());
//	  System.out.println("errormsg:"+rsp.getErrorMsg());
	  System.out.println("tranSerialNumber:"+rsp.getTransSerialNumber());
	  	  
	  Reporter.end("--------"+datadriven.get("seq")+" "+datadriven.get("comment")+"---------");

  }
  
  @DataProvider(name = "accountTransferIn")
  public Iterator<Object[]> data1test() throws IOException {
  	return ExcelProviderByEnv(this, "accountTransferIn");
  }
  
}
