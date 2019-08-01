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


import com.bill99.fpd.mfs.api.dto.RedeemToCreditCardRequest;
import com.bill99.fpd.mfs.api.dto.RedeemToCreditCardResponse;
import com.bill99.fpd.mfs.api.service.FpdMfs;
import com.bill99.qa.framework.testcase.BaseTestCase;
import com.bill99.ta.infs.fpd.service.ConvertDataService;
import com.bill99.ta.infs.fpd.service.InterfaceCheck;


public class PrdTransferOutToCreditcardTest extends BaseTestCase {
  
  @Autowired
  private FpdMfs fpdMfs;
  @Autowired
  private ConvertDataService convertDataService;
  @Autowired
  private InterfaceCheck interfaceCheck;
  
  @Test(dataProvider = ""+"PrdTransferOutToCreditcardTest")
  public void PrdTransferOutToCreditcard(Map<String, String> datadriven) throws ParseException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	  	  
	  Reporter.start("------"+datadriven.get("seq")+" "+datadriven.get("comment")+"-------");
	  //创建请求对象
	  RedeemToCreditCardRequest req = convertDataService.convertReqData(datadriven, new RedeemToCreditCardRequest());

	  //发起请求，获取返回进行验证
	  RedeemToCreditCardResponse rsp = fpdMfs.redeemToCreditCard(req);
	  
	  //结果检核
	  interfaceCheck.prdTransferOutToCreditcardCheck(datadriven, rsp);
	  
	  //打印部分信息
	  System.out.println("errorcode:"+rsp.getErrorCode());
	  System.out.println("errormsg:"+rsp.getErrorMsg());  
	  	  
	  Reporter.end("--------"+datadriven.get("seq")+" "+datadriven.get("comment")+"---------");

  }
  
  @DataProvider(name = "PrdTransferOutToCreditcardTest")
  public Iterator<Object[]> data1test() throws IOException {
  	return ExcelProviderByEnv(this, "PrdTransferOutToCreditcardTest");
  }
  
}
