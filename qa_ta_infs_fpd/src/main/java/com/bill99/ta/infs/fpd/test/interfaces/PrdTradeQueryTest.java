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


import com.bill99.fpd.mfs.api.dto.query.FinaOrderQueryRequest;
import com.bill99.fpd.mfs.api.dto.query.FinaOrderQueryRespItem;
import com.bill99.fpd.mfs.api.dto.query.FinaOrderQueryResponse;
import com.bill99.fpd.mfs.api.service.FpdMfsQuery;
import com.bill99.qa.framework.testcase.BaseTestCase;
import com.bill99.ta.infs.fpd.service.ConvertDataService;
import com.bill99.ta.infs.fpd.service.InterfaceCheck;


public class PrdTradeQueryTest extends BaseTestCase {
  
  @Autowired
  private FpdMfsQuery fpdMfsQuery;
  @Autowired
  private ConvertDataService convertDataService;
  @Autowired
  private InterfaceCheck interfaceCheck;
  
  @Test(dataProvider = ""+"PrdTradeQueryTest")
  public void PrdTradeQuery(Map<String, String> datadriven) throws ParseException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
	  	  
	  Reporter.start("------"+datadriven.get("seq")+" "+datadriven.get("comment")+"-------");
	  //创建请求对象
	  FinaOrderQueryRequest req = convertDataService.convertReqData(datadriven, new FinaOrderQueryRequest());

	  //发起请求，获取返回进行验证
	  FinaOrderQueryResponse rsp = fpdMfsQuery.queryFinaOrder(req);
	  
	  //结果检核
	  interfaceCheck.prdTradeQueryCheck(datadriven, rsp);
	  
	  //打印部分信息
	  System.out.println("errorcode:"+rsp.getErrorCode());
	  System.out.println("errormsg:"+rsp.getErrorMsg());
//	  System.out.println("bizCode:"+rsp.getBizCode());
//	  System.out.println("targetPage:"+rsp.getPage().getTargetPage());
//	  System.out.println("pageSize:"+rsp.getPage().getPageSize());
//	  System.out.println("pageRecord:"+rsp.getPage().getTotalRecord());

	  //获取返回的list信息
	  int listSize = rsp.getOrderList().size();
	  for (int i = 0; i < listSize; i++) {
		FinaOrderQueryRespItem lsData = rsp.getOrderList().get(i);
		System.out.println((i + 1) 
				+ " orderId=" + lsData.getOrderId() 
				+ ", orderType=" + lsData.getOrderType()
				+ ", orderApplyTime=" + lsData.getOrderApplyTime()
				+ ", amount=" + lsData.getAmt() 
				+ ", orderStatus=" + lsData.getOrderStatus() 
				+ ", finaOrderId=" + lsData.getFinaOrderId());
		}
	  	  
	  Reporter.end("--------"+datadriven.get("seq")+" "+datadriven.get("comment")+"---------");

  }
  
  @DataProvider(name = "PrdTradeQueryTest")
  public Iterator<Object[]> data1test() throws IOException {
  	return ExcelProviderByEnv(this, "PrdTradeQueryTest");
  }
  
}
