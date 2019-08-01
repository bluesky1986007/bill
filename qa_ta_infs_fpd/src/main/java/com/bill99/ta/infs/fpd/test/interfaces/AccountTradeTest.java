package com.bill99.ta.infs.fpd.test.interfaces;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bill99.qa.framework.testcase.BaseTestCase;
import com.bill99.seashell.domain.dto.http.FpdChangeBalanceBatchRequest;
import com.bill99.seashell.domain.dto.http.FpdChangeBalanceBatchResponse;
import com.bill99.seashell.domain.dto.http.item.FpdChangeBalanceBatchRespItem;
import com.bill99.seashell.domain.service.FpdAccountSvc;
import com.bill99.ta.infs.fpd.service.ConvertDataService;
import com.bill99.ta.infs.fpd.service.InterfaceCheck;

public class AccountTradeTest extends BaseTestCase {

	@Autowired
	private FpdAccountSvc fpdAccountSvc;
	@Autowired
	private ConvertDataService convertDataService;
	@Autowired
	private InterfaceCheck interfaceCheck;

	@Test(dataProvider = ""+"accountTrade")
	public void accountTrade(Map<String, String> datadriven)
			throws ParseException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		Reporter.start("------" + datadriven.get("seq") + " " + datadriven.get("comment") + "-------");
		// 创建请求对象
		FpdChangeBalanceBatchRequest req = convertDataService.convertReqData(
				datadriven, new FpdChangeBalanceBatchRequest());

		// 发起请求，获取返回进行验证
		FpdChangeBalanceBatchResponse rsp = fpdAccountSvc
				.changeBalanceCBatch(req);

		// 结果检核
		 interfaceCheck.accountTradeCheck(datadriven, rsp);

		// 打印部分信息
//		System.out.println("errorcode:" + rsp.getErrorCode());
//		System.out.println("errormsg:" + rsp.getErrorMsg());
		// 获取并打印返回信息中list数据
		int listSize = Integer.parseInt(datadriven.get("listNum"));
		System.out.println("accountList");
		for (int i = 0; i < listSize; i++) {
			FpdChangeBalanceBatchRespItem lsData = rsp.getAccountList().get(i);
			System.out.println((i + 1) 
					+ " amount=" + lsData.getAmount() 
					+ ", accountBalType=" + lsData.getAccountBalType()
					+ ", accountCode=" + lsData.getAccountCode()
					+ ", currencyCode=" + lsData.getCurrencyCode() 
					+ ", direction=" + lsData.getDirection() 
					+ ", orderId=" + lsData.getOrderId()
					+ ", tranSerialNumber=" + lsData.getTransSerialNumber());
		}

		Reporter.end("--------" + datadriven.get("seq") + " " + datadriven.get("comment") + "---------");

	}

	@DataProvider(name = "accountTrade")
	public Iterator<Object[]> accountTrade(Method method) throws IOException {
		return ExcelProviderByEnv(this, "accountTrade");
	}

}
