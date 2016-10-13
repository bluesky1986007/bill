package com.bill99.ta.infs.fpd.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.openqa.selenium.WebDriver;

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

public interface InterfaceCheck {
	
	/**
	 * 理财账户签约账户接口检查
	 * @param datadriven
	 * @param rsp
	 */
	public void accountContractCheck(Map<String, String> datadriven,FpdproductContractResonse rsp) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;

	/**
	 * 理财账户转入申请账户接口检查
	 * @param datadriven
	 * @param rsp
	 */
	public void accountTransferInCheck(Map<String, String> datadriven,FpdSubscribeResponse rsp) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	
	/**
	 * 变更DPM余额账户接口检核
	 * @param datadriven
	 * @param rsp
	 */
	public void accountBalanceDPMCheck(Map<String, String> datadriven,FpdChangeBalanceResponse rsp) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	
	/**
	 * 理财账户转出执行账户接口检核
	 * @param datadriven
	 * @param rsp
	 */
	public void accountTransferOutCheck(Map<String, String> datadriven,FpdredeemToAccountResonse rsp) throws IllegalAccessException,InvocationTargetException, NoSuchMethodException;
	
	/**
	 * 理财账户交易账户接口检核
	 * @param datadriven
	 * @param rsp
	 */
	public void accountTradeCheck(Map<String, String> datadriven,FpdChangeBalanceBatchResponse rsp) throws IllegalAccessException,InvocationTargetException, NoSuchMethodException;
	/**
	 * 快到银账户接口检核
	 * @param datadriven
	 * @param rsp
	 */
	public void accountKtoBankCheck(Map<String, String> datadriven,FpdPay2BankResponse rsp) throws IllegalAccessException,InvocationTargetException, NoSuchMethodException;
		
	/**
	 * 企业转出至银行卡产品接口检核
	 * @param datadriven
	 * @param rsp
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void prdTransferOutToBankCheck(Map<String, String> datadriven,FpdRedeemToDebitCardResponse rsp) throws IllegalAccessException,InvocationTargetException, NoSuchMethodException;
	/**
	 * 快到快转入申请--产品接口
	 * @param datadriven
	 * @param rsp
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void prdTransferInFromAcctCheck(Map<String, String> datadriven,SubscribeByBillAcctResponse rsp) throws IllegalAccessException,InvocationTargetException, NoSuchMethodException;
	/**
	 * 快到快转出到人民币账户--产品接口
	 * @param datadriven
	 * @param rsp
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void prdTransferOutToAcctCheck(Map<String, String> datadriven,RedeemToBillAcctResponse rsp) throws IllegalAccessException,InvocationTargetException, NoSuchMethodException;
	/**
	 * 传出到信用卡还款--产品接口
	 * @param datadriven
	 * @param rsp
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void prdTransferOutToCreditcardCheck(Map<String, String> datadriven,RedeemToCreditCardResponse rsp) throws IllegalAccessException,InvocationTargetException, NoSuchMethodException;
	/**
	 * 理财产品交易查询--产品接口
	 * @param datadriven
	 * @param rsp
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void prdTradeQueryCheck(Map<String, String> datadriven,FinaOrderQueryResponse rsp) throws IllegalAccessException,InvocationTargetException, NoSuchMethodException;

	/**
	 * 人民币转入理财通检核
	 * @param dr
	 */
	public void uiTransferInFromAcctCheck(WebDriver dr);
	
	/**
	 * 理财转出检核
	 * @param dr
	 */
	public void uiTransferOutCheck(WebDriver dr);
	
	/**
	 * 理财查询检核
	 * @param dr
	 */
	public void uiQueryCheck(WebDriver dr);
	
		
}
