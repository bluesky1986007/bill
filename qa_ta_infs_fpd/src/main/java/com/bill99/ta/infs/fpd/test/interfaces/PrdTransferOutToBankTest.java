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
import com.bill99.seashell.domain.dto.http.FpdRedeemToDebitCardRequest;
import com.bill99.seashell.domain.dto.http.FpdRedeemToDebitCardResponse;
import com.bill99.seashell.domain.service.FpdProductSvc;
import com.bill99.ta.infs.fpd.service.ConvertDataService;
import com.bill99.ta.infs.fpd.service.InterfaceCheck;


public class PrdTransferOutToBankTest extends BaseTestCase {
  
  @Autowired
  private FpdProductSvc fpdProductSvc;
  @Autowired
  private ConvertDataService convertDataService;
  @Autowired
  private InterfaceCheck interfaceCheck;
  
  @Test(dataProvider = "prdTransferOutToBank")
  public void prdTransferOutToBank(Map<String, String> datadriven) throws ParseException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	  	  
	  Reporter.start("------"+datadriven.get("seq")+" "+datadriven.get("comment")+"-------");
	  //创建请求对象
	  FpdRedeemToDebitCardRequest req = convertDataService.convertReqData(datadriven, new FpdRedeemToDebitCardRequest());

	  //发起请求，获取返回进行验证
	  FpdRedeemToDebitCardResponse rsp = fpdProductSvc.redeemToDebitCard(req);
	  
	  //结果检核
	  interfaceCheck.prdTransferOutToBankCheck(datadriven, rsp);
	  
	  //打印部分信息
	  System.out.println("errorcode:"+rsp.getErrorCode());
	  System.out.println("errormsg:"+rsp.getErrorMsg());
	  	  
	  Reporter.end("--------"+datadriven.get("seq")+" "+datadriven.get("comment")+"---------");

  }
  
  @DataProvider(name = "prdTransferOutToBank")
  public Iterator<Object[]> data1test() throws IOException {
  	return ExcelProviderByEnv(this, "prdTransferOutToBank");
  }
  
}
