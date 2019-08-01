/**
 * @(#)TestRedeemToCreditCard.java Dec 31, 2014
 *
 * Copyright (c) 2004-2010 99Bill Corporation. All Rights Reserved.
 */
package com.bill99.ta.infs.fpd.test.mock;

import java.util.Date;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bill99.fpd.common.err.FpdErrInfo;
import com.bill99.fpd.mfs.api.dto.RedeemToCreditCardRequest;
import com.bill99.fpd.mfs.api.dto.RedeemToCreditCardResponse;
import com.bill99.fpd.mfs.api.enums.FpdOrderSource;
import com.bill99.fpd.mfs.api.service.FpdMfs;
import com.caucho.hessian.client.HessianProxyFactory;

/**
 * 转出到信用卡-TEST
 * @author <junbo.zhang@99bill.com>
 * @version 1.0 Dec 31, 2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/context-fpd-base-test.xml"})
public class TestRedeemToCreditCard {
    private static FpdMfs fpdMfs;
    private static final String BIZ_CODE = "1002";
    private static final String APP_ID = "TEST_APP_ID";
    private static final String P_CODE = "DCC0000013";
    private static final Long M_CODE = 10012071335L;
    
    @BeforeClass
    public static void setUp() throws Exception {
        String url = "http://192.168.15.240:8086/app-mfs/hessian/fpdMfsDo";
        HessianProxyFactory factory = new HessianProxyFactory();
        fpdMfs = (FpdMfs) factory.create(FpdMfs.class, url);
    }
    
    @Test
    public void testReqNull() {
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(null);
        Assert.assertNull(resp);
    }
    
    @Test
    public void testBizCodeEmpty() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.BIZ_CODE_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testReqIdEmpty() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.REQ_ID_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testReqTimeEmpty() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.REQ_TIME_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testAppIdEmpty() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.APPID_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testNoAccessEmpty() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID + BIZ_CODE);
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.APP_UNAUTHORIZED.code(), resp.getErrorCode());
    }
    
    @Test
    public void testProductCodeEmpty() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.PRODUCT_CODE_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testMemberCodeEmpty() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.MEMBER_CODE_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testMemberCodeIllegal() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(0L);
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.MEMBER_CODE_ILLEGAL.code(), resp.getErrorCode());
    }
    
    @Test
    public void testAmtEmpty() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(M_CODE);
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.AMOUNT_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testAmtIllegal() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(M_CODE);
        req.setAmount(0L);
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.AMOUNT_ILLEGAL.code(), resp.getErrorCode());
    }
    
    @Test
    public void testOrderSourceEmpty() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(M_CODE);
        req.setAmount(10L);
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.ORDER_SOURCE_NULL.code(), resp.getErrorCode());
    }
    
    @Test
    public void testMemberNotExists() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(M_CODE + 999999999);
        req.setAmount(10L);
        req.setOrderSource(FpdOrderSource.MOBILE);
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.MEMBER_NOT_EXISTS.code(), resp.getErrorCode());
    }
    
    @Test
    public void testMemberStatusIllegal() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(10011801838L);
        req.setAmount(10L);
        req.setOrderSource(FpdOrderSource.MOBILE);
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.MEMBER_ILLEGAL_STATUS.code(), resp.getErrorCode());
    }
    
    @Test
    public void testProductNotExists() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE + BIZ_CODE);
        req.setMemberCode(M_CODE);
        req.setAmount(10L);
        req.setOrderSource(FpdOrderSource.MOBILE);
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.PRODUCT_NOT_EXISTS.code(), resp.getErrorCode());
    }
    
    @Test
    public void testNoContractExists() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(10011511666L);
        req.setAmount(10L);
        req.setOrderSource(FpdOrderSource.MOBILE);
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.PRODUCT_NO_CONTRACT.code(), resp.getErrorCode());
    }
    
    @Test
    public void testRedeemTimeTypeEmpty() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(M_CODE);
        req.setAmount(30L);
        req.setOrderSource(FpdOrderSource.MOBILE);
        req.setOrderId(BIZ_CODE + System.currentTimeMillis());
        req.setRedeemTimeType(null);
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.REDEEM_TIME_TYPE_NULL.code(), resp.getErrorCode());
    }
    
    @Test
    public void testBankAcctEmpty() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(M_CODE);
        req.setAmount(30L);
        req.setOrderSource(FpdOrderSource.MOBILE);
        req.setOrderId(BIZ_CODE + System.currentTimeMillis());
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.BANKACCT_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testBankAcctIllegal() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(M_CODE);
        req.setAmount(30L);
        req.setOrderSource(FpdOrderSource.MOBILE);
        req.setOrderId(BIZ_CODE + System.currentTimeMillis());
        req.setBankAcct("62258865x567");
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.BANKACCT_ILLEGAL.code(), resp.getErrorCode());
    }
    
    @Test
    public void testBankAcctIllegal_1() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(M_CODE);
        req.setAmount(30L);
        req.setOrderSource(FpdOrderSource.MOBILE);
        req.setOrderId(BIZ_CODE + System.currentTimeMillis());
        req.setBankAcct("62258865.1231231");
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.BANKACCT_ILLEGAL.code(), resp.getErrorCode());
    }
    
    @Test
    public void testBankJCEmpty() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(M_CODE);
        req.setAmount(30L);
        req.setOrderSource(FpdOrderSource.MOBILE);
        req.setOrderId(BIZ_CODE + System.currentTimeMillis());
        req.setBankAcct("6225886589638888");
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.BANK_JC_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testPayeeNameEmpty() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(M_CODE);
        req.setAmount(30L);
        req.setOrderSource(FpdOrderSource.MOBILE);
        req.setOrderId(BIZ_CODE + System.currentTimeMillis());
        req.setBankAcct("6225886589638888");
        req.setBankJC("CMB");
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.PAYEE_NAME_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testIDNumberEmpty() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(M_CODE);
        req.setAmount(30L);
        req.setOrderSource(FpdOrderSource.MOBILE);
        req.setOrderId(BIZ_CODE + System.currentTimeMillis());
        req.setBankAcct("6225886589638888");
        req.setBankJC("CMB");
        req.setPayeeName("小O");
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.IDNUMBER_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testSucc() {
        RedeemToCreditCardRequest req = new RedeemToCreditCardRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(M_CODE);
        req.setAmount(20L);
        req.setOrderSource(FpdOrderSource.MOBILE);
        req.setOrderId(BIZ_CODE + System.currentTimeMillis());
        req.setBankAcct("6225886589638888");
        req.setBankJC("CMB");
        req.setPayeeName("小O");
        req.setIdNumber("360563199112102156");
        RedeemToCreditCardResponse resp = fpdMfs.redeemToCreditCard(req);
        Assert.assertEquals(FpdErrInfo.SUCC.code(), resp.getErrorCode());
    }
}
