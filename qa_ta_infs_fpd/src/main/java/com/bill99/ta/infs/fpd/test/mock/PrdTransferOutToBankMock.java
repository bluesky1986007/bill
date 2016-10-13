package com.bill99.ta.infs.fpd.test.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;


import com.bill99.qa.framework.testcase.BaseTestCase;
import com.bill99.seashell.domain.dto.http.FpdPay2BankResultNotifyRequest;
import com.bill99.seashell.domain.dto.http.FpdPay2BankResultNotifyResponse;
import com.bill99.seashell.domain.service.FpdProductSvc;

public class PrdTransferOutToBankMock extends BaseTestCase {
  
  @Autowired
  private FpdProductSvc fpdProductSvc;
  
  @Test(enabled = true)
  public void prdTransferOutToBank() {

	  //创建请求对象
	  FpdPay2BankResultNotifyRequest req = new FpdPay2BankResultNotifyRequest();
	  	  
      req.setAmount(4L);// 金额
      req.setBankAcctId("6222021001009834836");// 卡号
      req.setBankCode(100);//银行编号
      req.setMemberCode(10011813338L);
      req.setStatus(1);// 1:出款成功 2：出款失败     
      req.setOrderSeqId(1234555L);//wihtdrawworkorder 表的交易号，由于开发环境没法联调则这个交易号可以随便写个数字

	  //发起请求，获取返回进行验证
	  FpdPay2BankResultNotifyResponse res = fpdProductSvc.pay2BankResultNotify(req);
	  
      if ("0000".equals(res.getErrorCode())) {
    	    
          System.out.print("错误码:" + res.getErrorCode() + "   ");
          System.out.print("错误信息:" + res.getErrorMsg() + "   ");
          System.out.print("金额:" + res.getAmount() + "   ");
          System.out.print("卡号:" + res.getBankAcctId());
      } else {
          System.out.print("错误码:" + res.getErrorCode() + "   ");
          System.out.print("错误信息:" + res.getErrorMsg() + "   ");
          System.out.print("金额:" + res.getAmount() + "   ");
          System.out.print("卡号:" + res.getBankAcctId());
      }	  

  }

  
}
