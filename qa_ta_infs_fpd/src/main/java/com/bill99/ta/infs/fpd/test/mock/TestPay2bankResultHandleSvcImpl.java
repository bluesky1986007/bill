package com.bill99.ta.infs.fpd.test.mock;

import com.bill99.seashell.common.util.DateUtil;
import com.bill99.seashell.domain.dto.http.FpdPay2BankResultNotifyRequest;
import com.bill99.seashell.domain.dto.http.FpdPay2BankResultNotifyResponse;
import com.bill99.seashell.domain.service.FpdProductSvc;
import com.caucho.hessian.client.HessianProxyFactory;

public class TestPay2bankResultHandleSvcImpl {

    /**
     * @param args
     */
    public static void main(String[] args) {

    	String url = "http://192.168.15.240:8081/app-finaprod/hessian/fpdProductSvcDo";//dev

       HessianProxyFactory factory = new HessianProxyFactory();
       try {
            DateUtil.parse("yyyyMMddHHMMSS", "20121212121212");
            FpdProductSvc fpdProductSvc = (FpdProductSvc) factory.create(FpdProductSvc.class, url);
            FpdPay2BankResultNotifyRequest req = new FpdPay2BankResultNotifyRequest();

            req.setAmount(4L);
            req.setBankAcctId("6222021001009834836");
            req.setBankCode(100);
            req.setMemberCode(10011813338L);
            req.setStatus(1);           
            req.setOrderSeqId(1234555L);
        
            
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

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
