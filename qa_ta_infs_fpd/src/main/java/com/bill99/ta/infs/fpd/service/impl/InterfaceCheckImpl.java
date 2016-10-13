package com.bill99.ta.infs.fpd.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.bill99.fpd.mfs.api.dto.RedeemToBillAcctResponse;
import com.bill99.fpd.mfs.api.dto.RedeemToCreditCardResponse;
import com.bill99.fpd.mfs.api.dto.SubscribeByBillAcctResponse;
import com.bill99.fpd.mfs.api.dto.query.FinaOrderQueryResponse;
import com.bill99.seashell.domain.dto.http.FpdChangeBalanceBatchResponse;
import com.bill99.seashell.domain.dto.http.FpdChangeBalanceResponse;
import com.bill99.seashell.domain.dto.http.FpdPay2BankResponse;
import com.bill99.seashell.domain.dto.http.FpdRedeemToDebitCardResponse;
import com.bill99.seashell.domain.dto.http.FpdSubscribeResponse;
import com.bill99.seashell.domain.dto.http.FpdproductContractResonse;
import com.bill99.seashell.domain.dto.http.FpdredeemToAccountResonse;
import com.bill99.ta.infs.fpd.common.utils.ExcelDataTools;
import com.bill99.ta.infs.fpd.service.InterfaceCheck;

public class InterfaceCheckImpl implements InterfaceCheck {

	@Override
	public void accountContractCheck(Map<String, String> datadriven,
			FpdproductContractResonse rsp) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		// 将返回数据通过map形式输出
		Map finalmap = BeanUtils.describe(rsp);
		Reporter.log("返回数据：" + finalmap);

		// 获取预期结果数据并与返回数据比对
		Map result = ExcelDataTools.getExpRspData(datadriven);
		Reporter.log("bizCode",result.get("bizCode").equals(null == rsp.getBizCode() ? "null" : rsp.getBizCode()));
		Reporter.log("serviceCode",result.get("serviceCode").equals(null == rsp.getServiceCode() ? "null" : rsp.getServiceCode()));
		Reporter.log("errorCode",result.get("errorCode").equals(null == rsp.getErrorCode() ? "null" : rsp.getErrorCode()));
		Reporter.log("errorMsg",result.get("errorMsg").equals(null == rsp.getErrorMsg() ? "null" : rsp.getErrorMsg()));
		Reporter.log("accountType",result.get("accountType").equals(null == rsp.getAccountType() ? "null" : rsp.getAccountType()));

	}

	@Override
	public void accountTransferInCheck(Map<String, String> datadriven,
			FpdSubscribeResponse rsp) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		// 将返回数据通过map形式输出
		Map finalmap = BeanUtils.describe(rsp);
		Reporter.log("返回数据：" + finalmap);

		// 获取预期结果数据并与返回数据比对
		Map result = ExcelDataTools.getExpRspData(datadriven);
		Reporter.log("bizCode",result.get("bizCode").equals(null == rsp.getBizCode() ? "null" : rsp.getBizCode()));
		Reporter.log("serviceCode",result.get("serviceCode").equals(null == rsp.getServiceCode() ? "null" : rsp.getServiceCode()));
		Reporter.log("errorCode",result.get("errorCode").equals(null==rsp.getErrorCode()?"null":rsp.getErrorCode()));
		Reporter.log("errorMsg",result.get("errorMsg").equals(null==rsp.getErrorMsg()?"null":rsp.getErrorMsg()));
		Reporter.log("productCode",result.get("productCode").equals(null == rsp.getProductCode() ? "null" : rsp.getProductCode()));
		Reporter.log("orderId",result.get("orderId").equals(null == rsp.getOrderId() ? "null" : rsp.getOrderId()));

	}

	@Override
	public void accountBalanceDPMCheck(Map<String, String> datadriven,
			FpdChangeBalanceResponse rsp) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		// 将返回数据通过map形式输出
		Map finalmap = BeanUtils.describe(rsp);
		Reporter.log("返回数据：" + finalmap);

		// 获取预期结果数据并与返回数据比对
		Map result = ExcelDataTools.getExpRspData(datadriven);
		Reporter.log("bizCode",result.get("bizCode").equals(null == rsp.getBizCode() ? "null" : rsp.getBizCode()));
		Reporter.log("serviceCode",result.get("serviceCode").equals(null == rsp.getServiceCode() ? "null" : rsp.getServiceCode()));
		Reporter.log("errorCode",result.get("errorCode").equals(null==rsp.getErrorCode()?"null":rsp.getErrorCode()));
		Reporter.log("errorMsg",result.get("errorMsg").equals(null==rsp.getErrorMsg()?"null":rsp.getErrorMsg()));
		Reporter.log("orderId",result.get("orderId").equals(null == rsp.getOrderId() ? "null" : rsp.getOrderId()));
		Reporter.log("accountCode",result.get("accountCode").equals(null == rsp.getAccountCode() ? "null" : String.valueOf(rsp.getAccountCode())));
		Reporter.log("amount",result.get("amount").equals(null == rsp.getAmount() ? "null" : String.valueOf(rsp.getAmount())));
	}

	@Override
	public void accountTransferOutCheck(Map<String, String> datadriven,
			FpdredeemToAccountResonse rsp) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		// 将返回数据通过map形式输出
		Map finalmap = BeanUtils.describe(rsp);
		Reporter.log("返回数据：" + finalmap);

		// 获取预期结果数据并与返回数据比对
		Map result = ExcelDataTools.getExpRspData(datadriven);
		Reporter.log("bizCode",result.get("bizCode").equals(null == rsp.getBizCode() ? "null" : rsp.getBizCode()));
		Reporter.log("serviceCode",result.get("serviceCode").equals(null == rsp.getServiceCode() ? "null" : rsp.getServiceCode()));
		Reporter.log("errorCode",result.get("errorCode").equals(null==rsp.getErrorCode()?"null":rsp.getErrorCode()));
		Reporter.log("errorMsg",result.get("errorMsg").equals(null==rsp.getErrorMsg()?"null":rsp.getErrorMsg()));
		Reporter.log("productCode",result.get("productCode").equals(null == rsp.getProductCode() ? "null" : rsp.getProductCode()));
		Reporter.log("orderId",result.get("orderId").equals(null == rsp.getOrderId() ? "null" : rsp.getOrderId()));
	}

	@Override
	public void accountTradeCheck(Map<String, String> datadriven,
			FpdChangeBalanceBatchResponse rsp) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		// 将返回数据通过map形式输出
		Map finalmap = BeanUtils.describe(rsp);
		Reporter.log("返回数据：" + finalmap);

		// 获取预期结果数据并与返回数据比对
		Map result = ExcelDataTools.getExpRspData(datadriven);
		Reporter.log("bizCode",result.get("bizCode").equals(null == rsp.getBizCode() ? "null" : rsp.getBizCode()));
		Reporter.log("serviceCode",result.get("serviceCode").equals(null == rsp.getServiceCode() ? "null" : rsp.getServiceCode()));
		Reporter.log("errorCode",result.get("errorCode").equals(null==rsp.getErrorCode()?"null":rsp.getErrorCode()));
		Reporter.log("errorMsg",result.get("errorMsg").equals(null==rsp.getErrorMsg()?"null":rsp.getErrorMsg()));
		Reporter.log("dealCount",result.get("dealCount").equals(null == rsp.getDealCount() ? "null" : String.valueOf(rsp.getDealCount())));
		Reporter.log("totalAmt",result.get("totalAmt").equals(null == rsp.getTotalAmt() ? "null" : String.valueOf(rsp.getTotalAmt())));
		Reporter.log("tradeType",result.get("tradeType").equals(null == rsp.getTradeType() ? "null" : rsp.getTradeType()));
		Reporter.log("createOperator",result.get("createOperator").equals(null == rsp.getCreateOperator() ? "null" : rsp.getCreateOperator()));
		
	}

	@Override
	public void accountKtoBankCheck(Map<String, String> datadriven,
			FpdPay2BankResponse rsp) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		// 将返回数据通过map形式输出
		Map finalmap = BeanUtils.describe(rsp);
		Reporter.log("返回数据：" + finalmap);

		// 获取预期结果数据并与返回数据比对
		Map result = ExcelDataTools.getExpRspData(datadriven);
		Reporter.log("bizCode",result.get("bizCode").equals(null == rsp.getBizCode() ? "null" : rsp.getBizCode()));
		Reporter.log("serviceCode",result.get("serviceCode").equals(null == rsp.getServiceCode() ? "null" : rsp.getServiceCode()));
		Reporter.log("errorCode",result.get("errorCode").equals(null==rsp.getErrorCode()?"null":rsp.getErrorCode()));
		Reporter.log("errorMsg",result.get("errorMsg").equals(null==rsp.getErrorMsg()?"null":rsp.getErrorMsg()));
		Reporter.log("orderId",result.get("orderId").equals(null == rsp.getOrderId() ? "null" : String.valueOf(rsp.getOrderId())));
		Reporter.log("amount",result.get("amount").equals(null == rsp.getAmount() ? "null" : String.valueOf(rsp.getAmount())));
				
	}

	@Override
	public void prdTransferOutToBankCheck(Map<String, String> datadriven,
			FpdRedeemToDebitCardResponse rsp) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		// 将返回数据通过map形式输出
		Map finalmap = BeanUtils.describe(rsp);
		Reporter.log("返回数据：" + finalmap);

		// 获取预期结果数据并与返回数据比对
		Map result = ExcelDataTools.getExpRspData(datadriven);
		Reporter.log("bizCode",result.get("bizCode").equals(null == rsp.getBizCode() ? "null" : rsp.getBizCode()));
		Reporter.log("serviceCode",result.get("serviceCode").equals(null == rsp.getServiceCode() ? "null" : rsp.getServiceCode()));
		Reporter.log("errorCode",result.get("errorCode").equals(null==rsp.getErrorCode()?"null":rsp.getErrorCode()));
		Reporter.log("errorMsg",result.get("errorMsg").equals(null==rsp.getErrorMsg()?"null":rsp.getErrorMsg()));
		Reporter.log("orderId",result.get("orderId").equals(null == rsp.getOrderId() ? "null" : String.valueOf(rsp.getOrderId())));
		Reporter.log("productCode",result.get("productCode").equals(null == rsp.getProductCode() ? "null" : String.valueOf(rsp.getProductCode())));
		
	}

	@Override
	public void uiTransferInFromAcctCheck(WebDriver dr) {
		// TODO Auto-generated method stub		
		String resultText = new WebDriverWait(dr,30).until(ExpectedConditions.visibilityOfElementLocated(By.className("success"))).getText();
		
		Assert.assertEquals(resultText, "您的转入申请已提交成功。");
	}

	@Override
	public void uiTransferOutCheck(WebDriver dr) {
		// TODO Auto-generated method stub
		String resultText = new WebDriverWait(dr,30).until(ExpectedConditions.visibilityOfElementLocated(By.className("success"))).getText();

		Assert.assertEquals(resultText.contains("您的转出申请已成功提交"), true);
		
		
	}

	@Override
	public void uiQueryCheck(WebDriver dr) {
		// TODO Auto-generated method stub
		boolean isQuery = new WebDriverWait(dr,30).until(ExpectedConditions.textToBePresentInElement(By.linkText("跳转"), "跳转"));
		
		Assert.assertEquals(isQuery, true);
		
	}

	@Override
	public void prdTransferInFromAcctCheck(Map<String, String> datadriven,
			SubscribeByBillAcctResponse rsp) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		// 将返回数据通过map形式输出
		Map finalmap = BeanUtils.describe(rsp);
		Reporter.log("返回数据：" + finalmap);

		// 获取预期结果数据并与返回数据比对
		Map result = ExcelDataTools.getExpRspData(datadriven);
		Reporter.log("bizCode",result.get("bizCode").equals(null == rsp.getBizCode() ? "null" : rsp.getBizCode()));
		Reporter.log("serviceCode",result.get("serviceCode").equals(null == rsp.getServiceCode() ? "null" : rsp.getServiceCode()));
		Reporter.log("errorCode",result.get("errorCode").equals(null==rsp.getErrorCode()?"null":rsp.getErrorCode()));
		Reporter.log("errorMsg",result.get("errorMsg").equals(null==rsp.getErrorMsg()?"null":rsp.getErrorMsg()));
		Reporter.log("amount",result.get("amount").equals(null==rsp.getAmount()?"null":String.valueOf(rsp.getAmount())));
	}

	@Override
	public void prdTransferOutToAcctCheck(Map<String, String> datadriven,
			RedeemToBillAcctResponse rsp) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		// 将返回数据通过map形式输出
		Map finalmap = BeanUtils.describe(rsp);
		Reporter.log("返回数据：" + finalmap);

		// 获取预期结果数据并与返回数据比对
		Map result = ExcelDataTools.getExpRspData(datadriven);
		Reporter.log("bizCode",result.get("bizCode").equals(null == rsp.getBizCode() ? "null" : rsp.getBizCode()));
		Reporter.log("serviceCode",result.get("serviceCode").equals(null == rsp.getServiceCode() ? "null" : rsp.getServiceCode()));
		Reporter.log("amount",result.get("amount").equals(null==rsp.getAmount()?"null":String.valueOf(rsp.getAmount())));
		Reporter.log("errorCode",result.get("errorCode").equals(null==rsp.getErrorCode()?"null":rsp.getErrorCode()));
		Reporter.log("errorMsg",result.get("errorMsg").equals(null==rsp.getErrorMsg()?"null":rsp.getErrorMsg()));		
	
	}

	@Override
	public void prdTransferOutToCreditcardCheck(Map<String, String> datadriven,
			RedeemToCreditCardResponse rsp) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		// 将返回数据通过map形式输出
		Map finalmap = BeanUtils.describe(rsp);
		Reporter.log("返回数据：" + finalmap);

		// 获取预期结果数据并与返回数据比对
		Map result = ExcelDataTools.getExpRspData(datadriven);
		Reporter.log("bizCode",result.get("bizCode").equals(null == rsp.getBizCode() ? "null" : rsp.getBizCode()));
		Reporter.log("serviceCode",result.get("serviceCode").equals(null == rsp.getServiceCode() ? "null" : rsp.getServiceCode()));
		Reporter.log("productCode",result.get("productCode").equals(null == rsp.getProductCode() ? "null" : rsp.getProductCode()));
		Reporter.log("amount",result.get("amount").equals(null==rsp.getAmount()?"null":String.valueOf(rsp.getAmount())));
		Reporter.log("errorCode",result.get("errorCode").equals(null==rsp.getErrorCode()?"null":rsp.getErrorCode()));
		Reporter.log("errorMsg",result.get("errorMsg").equals(null==rsp.getErrorMsg()?"null":rsp.getErrorMsg()));		
		
	}

	@Override
	public void prdTradeQueryCheck(Map<String, String> datadriven,
			FinaOrderQueryResponse rsp) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		// 将返回数据通过map形式输出
		Map finalmap = BeanUtils.describe(rsp);
		Reporter.log("返回数据：" + finalmap);

		// 获取预期结果数据并与返回数据比对
		Map result = ExcelDataTools.getExpRspData(datadriven);
		Reporter.log("bizCode",result.get("bizCode").equals(null == rsp.getBizCode() ? "null" : rsp.getBizCode()));
		Reporter.log("serviceCode",result.get("serviceCode").equals(null == rsp.getServiceCode() ? "null" : rsp.getServiceCode()));
		Reporter.log("appId",result.get("appId").equals(null == rsp.getAppId() ? "null" : rsp.getAppId()));
		Reporter.log("targetPage",result.get("targetPage").equals(null==rsp.getPage().getTargetPage()?"null":String.valueOf(rsp.getPage().getTargetPage())));
		Reporter.log("pageSize",result.get("pageSize").equals(null==rsp.getPage().getPageSize()?"null":String.valueOf(rsp.getPage().getPageSize())));
		Reporter.log("errorCode",result.get("errorCode").equals(null==rsp.getErrorCode()?"null":rsp.getErrorCode()));
		Reporter.log("errorMsg",result.get("errorMsg").equals(null==rsp.getErrorMsg()?"null":rsp.getErrorMsg()));
		
	}
	
	

}
