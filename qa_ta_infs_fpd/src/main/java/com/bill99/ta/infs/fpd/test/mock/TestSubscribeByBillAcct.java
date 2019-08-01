/**
 * @(#)TestSubscribeByBillAcct.java Dec 27, 2014
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
import com.bill99.fpd.mfs.api.dto.SubscribeByBillAcctRequest;
import com.bill99.fpd.mfs.api.dto.SubscribeByBillAcctResponse;
import com.bill99.fpd.mfs.api.enums.FpdOrderSource;
import com.bill99.fpd.mfs.api.service.FpdMfs;
import com.caucho.hessian.client.HessianProxyFactory;

/**
 * 人民币账户转入-TEST
 * @author <junbo.zhang@99bill.com>
 * @version 1.0 Dec 27, 2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/context-fpd-base-test.xml"})
public class TestSubscribeByBillAcct {
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
        SubscribeByBillAcctResponse resp = fpdMfs.subscribeByBillAcct(null);
        Assert.assertNull(resp);
    }
    
    @Test
    public void testBizCodeEmpty() {
        SubscribeByBillAcctRequest req = new SubscribeByBillAcctRequest();
        SubscribeByBillAcctResponse resp = fpdMfs.subscribeByBillAcct(req);
        Assert.assertEquals(FpdErrInfo.BIZ_CODE_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testReqIdEmpty() {
        SubscribeByBillAcctRequest req = new SubscribeByBillAcctRequest();
        req.setBizCode(BIZ_CODE);
        SubscribeByBillAcctResponse resp = fpdMfs.subscribeByBillAcct(req);
        Assert.assertEquals(FpdErrInfo.REQ_ID_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testReqTimeEmpty() {
        SubscribeByBillAcctRequest req = new SubscribeByBillAcctRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        SubscribeByBillAcctResponse resp = fpdMfs.subscribeByBillAcct(req);
        Assert.assertEquals(FpdErrInfo.REQ_TIME_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testAppIdEmpty() {
        SubscribeByBillAcctRequest req = new SubscribeByBillAcctRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        SubscribeByBillAcctResponse resp = fpdMfs.subscribeByBillAcct(req);
        Assert.assertEquals(FpdErrInfo.APPID_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testNoAccessEmpty() {
        SubscribeByBillAcctRequest req = new SubscribeByBillAcctRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID + BIZ_CODE);
        SubscribeByBillAcctResponse resp = fpdMfs.subscribeByBillAcct(req);
        Assert.assertEquals(FpdErrInfo.APP_UNAUTHORIZED.code(), resp.getErrorCode());
    }
    
    @Test
    public void testProductCodeEmpty() {
        SubscribeByBillAcctRequest req = new SubscribeByBillAcctRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        SubscribeByBillAcctResponse resp = fpdMfs.subscribeByBillAcct(req);
        Assert.assertEquals(FpdErrInfo.PRODUCT_CODE_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testMemberCodeEmpty() {
        SubscribeByBillAcctRequest req = new SubscribeByBillAcctRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        SubscribeByBillAcctResponse resp = fpdMfs.subscribeByBillAcct(req);
        Assert.assertEquals(FpdErrInfo.MEMBER_CODE_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testMemberCodeIllegal() {
        SubscribeByBillAcctRequest req = new SubscribeByBillAcctRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(0L);
        SubscribeByBillAcctResponse resp = fpdMfs.subscribeByBillAcct(req);
        Assert.assertEquals(FpdErrInfo.MEMBER_CODE_ILLEGAL.code(), resp.getErrorCode());
    }
    
    @Test
    public void testAmtEmpty() {
        SubscribeByBillAcctRequest req = new SubscribeByBillAcctRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(M_CODE);
        SubscribeByBillAcctResponse resp = fpdMfs.subscribeByBillAcct(req);
        Assert.assertEquals(FpdErrInfo.AMOUNT_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testAmtIllegal() {
        SubscribeByBillAcctRequest req = new SubscribeByBillAcctRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(M_CODE);
        req.setAmount(0L);
        SubscribeByBillAcctResponse resp = fpdMfs.subscribeByBillAcct(req);
        Assert.assertEquals(FpdErrInfo.AMOUNT_ILLEGAL.code(), resp.getErrorCode());
    }
    
    @Test
    public void testOrderSourceEmpty() {
        SubscribeByBillAcctRequest req = new SubscribeByBillAcctRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(M_CODE);
        req.setAmount(10L);
        SubscribeByBillAcctResponse resp = fpdMfs.subscribeByBillAcct(req);
        Assert.assertEquals(FpdErrInfo.ORDER_SOURCE_NULL.code(), resp.getErrorCode());
    }
    
    @Test
    public void testMemberNotExists() {
        SubscribeByBillAcctRequest req = new SubscribeByBillAcctRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(M_CODE + 999999999);
        req.setAmount(10L);
        req.setOrderSource(FpdOrderSource.MOBILE);
        SubscribeByBillAcctResponse resp = fpdMfs.subscribeByBillAcct(req);
        Assert.assertEquals(FpdErrInfo.MEMBER_NOT_EXISTS.code(), resp.getErrorCode());
    }
    
    @Test
    public void testMemberStatusIllegal() {
        SubscribeByBillAcctRequest req = new SubscribeByBillAcctRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(10011801838L);
        req.setAmount(10L);
        req.setOrderSource(FpdOrderSource.MOBILE);
        SubscribeByBillAcctResponse resp = fpdMfs.subscribeByBillAcct(req);
        Assert.assertEquals(FpdErrInfo.MEMBER_ILLEGAL_STATUS.code(), resp.getErrorCode());
    }
    
    @Test
    public void testProductNotExists() {
        SubscribeByBillAcctRequest req = new SubscribeByBillAcctRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE + BIZ_CODE);
        req.setMemberCode(M_CODE);
        req.setAmount(10L);
        req.setOrderSource(FpdOrderSource.MOBILE);
        SubscribeByBillAcctResponse resp = fpdMfs.subscribeByBillAcct(req);
        Assert.assertEquals(FpdErrInfo.PRODUCT_NOT_EXISTS.code(), resp.getErrorCode());
    }
    
    @Test
    public void testNoContractExists() {
        SubscribeByBillAcctRequest req = new SubscribeByBillAcctRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(10011511666L);
        req.setAmount(10L);
        req.setOrderSource(FpdOrderSource.MOBILE);
        SubscribeByBillAcctResponse resp = fpdMfs.subscribeByBillAcct(req);
        Assert.assertEquals(FpdErrInfo.PRODUCT_NO_CONTRACT.code(), resp.getErrorCode());
    }
    
    @Test
    public void testSucc() {
        SubscribeByBillAcctRequest req = new SubscribeByBillAcctRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(M_CODE);
        req.setAmount(10L);
        req.setOrderSource(FpdOrderSource.MOBILE);
        req.setOrderId(BIZ_CODE + System.currentTimeMillis());
        SubscribeByBillAcctResponse resp = fpdMfs.subscribeByBillAcct(req);
        Assert.assertEquals(FpdErrInfo.SUCC.code(), resp.getErrorCode());
    }
}
