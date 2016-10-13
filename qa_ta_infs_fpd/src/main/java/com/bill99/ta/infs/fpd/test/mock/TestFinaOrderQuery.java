/**
 * @(#)TestFinaOrderQuery.java Jan 5, 2015
 *
 * Copyright (c) 2004-2010 99Bill Corporation. All Rights Reserved.
 */
package com.bill99.ta.infs.fpd.test.mock;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bill99.fpd.api.dto.Page;
import com.bill99.fpd.common.err.FpdErrInfo;
import com.bill99.fpd.mfs.api.dto.query.FinaOrderQueryRequest;
import com.bill99.fpd.mfs.api.dto.query.FinaOrderQueryResponse;
import com.bill99.fpd.mfs.api.service.FpdMfsQuery;
import com.caucho.hessian.client.HessianProxyFactory;

/**
 * 理财工单查询-TEST
 * @author <junbo.zhang@99bill.com>
 * @version 1.0 Jan 5, 2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/context-fpd-base-test.xml"})
public class TestFinaOrderQuery {
    private static FpdMfsQuery fpdMfsQuery;
    private static final String BIZ_CODE = "1002";
    private static final String APP_ID = "TEST_APP_ID";
    private static final String P_CODE = "DCC0000013";
    private static final Long M_CODE = 10012071335L;
    
    @BeforeClass
    public static void setUp() throws Exception {
        String url = "http://192.168.15.240:8086/app-mfs/hessian/fpdMfsQueryDo";
        HessianProxyFactory factory = new HessianProxyFactory();
        fpdMfsQuery = (FpdMfsQuery) factory.create(FpdMfsQuery.class, url);
    }
    
    @Test
    public void testReqNull() {
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(null);
        Assert.assertNull(resp);
    }
    
    @Test
    public void testBizCodeEmpty() {
        FinaOrderQueryRequest req = new FinaOrderQueryRequest();
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(req);
        Assert.assertEquals(FpdErrInfo.BIZ_CODE_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testReqIdEmpty() {
        FinaOrderQueryRequest req = new FinaOrderQueryRequest();
        req.setBizCode(BIZ_CODE);
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(req);
        Assert.assertEquals(FpdErrInfo.REQ_ID_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testReqTimeEmpty() {
        FinaOrderQueryRequest req = new FinaOrderQueryRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(req);
        Assert.assertEquals(FpdErrInfo.REQ_TIME_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testAppIdEmpty() {
        FinaOrderQueryRequest req = new FinaOrderQueryRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(req);
        Assert.assertEquals(FpdErrInfo.APPID_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testNoAccessEmpty() {
        FinaOrderQueryRequest req = new FinaOrderQueryRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID + BIZ_CODE);
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(req);
        Assert.assertEquals(FpdErrInfo.APP_UNAUTHORIZED.code(), resp.getErrorCode());
    }
    
    @Test
    public void testMemberCodeIllegal() {
        FinaOrderQueryRequest req = new FinaOrderQueryRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setMemberCode(0L);
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(req);
        Assert.assertEquals(FpdErrInfo.MEMBER_CODE_ILLEGAL.code(), resp.getErrorCode());
    }
    
    @Test
    public void testStartQueryTimeNull() {
        FinaOrderQueryRequest req = new FinaOrderQueryRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(req);
        Assert.assertEquals(FpdErrInfo.START_TIME_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testEndQueryTimeNull() {
        FinaOrderQueryRequest req = new FinaOrderQueryRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setStartTime(new Date());
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(req);
        Assert.assertEquals(FpdErrInfo.END_TIME_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testStartTimeGreatThanEndTime() {
        FinaOrderQueryRequest req = new FinaOrderQueryRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setStartTime(new Date());
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - 1);
        req.setEndTime(cal.getTime());
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(req);
        Assert.assertEquals(FpdErrInfo.END_TIME_NOT_THAN_START.code(), resp.getErrorCode());
    }
    
    @Test
    public void testStartTimeCompareEndTimeThan3Month() {
        FinaOrderQueryRequest req = new FinaOrderQueryRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -4);
        req.setStartTime(cal.getTime());
        req.setEndTime(new Date());
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(req);
        Assert.assertEquals(FpdErrInfo.TIME_RANGE_THAN_3MONTH.code(), resp.getErrorCode());
    }
    
    @Test
    public void testStartTimeGreatThanEndTime12Month() {
        FinaOrderQueryRequest req = new FinaOrderQueryRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -13);
        req.setStartTime(cal.getTime());
        cal.add(Calendar.MONTH, 2);
        req.setEndTime(cal.getTime());
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(req);
        Assert.assertEquals(FpdErrInfo.TIME_THAN_12MONTH.code(), resp.getErrorCode());
    }
    
    @Test
    public void testPageEmpty() {
        FinaOrderQueryRequest req = new FinaOrderQueryRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        req.setStartTime(cal.getTime());
        req.setEndTime(new Date());
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(req);
        Assert.assertEquals(FpdErrInfo.PAGE_EMPTY.code(), resp.getErrorCode());
    }
    
    @Test
    public void testTargetPageIllegal() {
        FinaOrderQueryRequest req = new FinaOrderQueryRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        req.setStartTime(cal.getTime());
        req.setEndTime(new Date());
        Page page = new Page();
        page.setTargetPage(0);
        req.setPage(page);
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(req);
        Assert.assertEquals(FpdErrInfo.TARGET_PAGE_ILLEGAL.code(), resp.getErrorCode());
    }
    
    @Test
    public void testPageSizeIllegal() {
        FinaOrderQueryRequest req = new FinaOrderQueryRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        req.setStartTime(cal.getTime());
        req.setEndTime(new Date());
        Page page = new Page();
        page.setPageSize(0);
        req.setPage(page);
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(req);
        Assert.assertEquals(FpdErrInfo.PAGE_SIZE_ILLEGAL.code(), resp.getErrorCode());
    }
    
    @Test
    public void testPageSizeExceedMax() {
        FinaOrderQueryRequest req = new FinaOrderQueryRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        req.setStartTime(cal.getTime());
        req.setEndTime(new Date());
        Page page = new Page();
        page.setPageSize(1001);
        req.setPage(page);
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(req);
        Assert.assertEquals(FpdErrInfo.PAGE_SIZE_EXCEED_MAX.code(), resp.getErrorCode());
    }
    
    @Test
    public void testMemberNotExists() {
        FinaOrderQueryRequest req = new FinaOrderQueryRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setMemberCode(M_CODE + 999999999);
        Calendar cal = Calendar.getInstance();
        req.setEndTime(cal.getTime());
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - 1);
        req.setStartTime(cal.getTime());
        Page page = new Page();
        req.setPage(page);
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(req);
        Assert.assertEquals(FpdErrInfo.MEMBER_NOT_EXISTS.code(), resp.getErrorCode());
    }
    
    @Test
    public void testMemberStatusIllegal() {
        FinaOrderQueryRequest req = new FinaOrderQueryRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setMemberCode(10011801838L);
        Calendar cal = Calendar.getInstance();
        req.setEndTime(cal.getTime());
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - 1);
        req.setStartTime(cal.getTime());
        Page page = new Page();
        req.setPage(page);
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(req);
        Assert.assertEquals(FpdErrInfo.MEMBER_ILLEGAL_STATUS.code(), resp.getErrorCode());
    }
    
    @Test
    public void testProductNotExists() {
        FinaOrderQueryRequest req = new FinaOrderQueryRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE + BIZ_CODE);
        Calendar cal = Calendar.getInstance();
        req.setEndTime(cal.getTime());
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - 1);
        req.setStartTime(cal.getTime());
        Page page = new Page();
        req.setPage(page);
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(req);
        Assert.assertEquals(FpdErrInfo.PRODUCT_NOT_EXISTS.code(), resp.getErrorCode());
    }
    
    @Test
    public void testNoContractExists() {
        FinaOrderQueryRequest req = new FinaOrderQueryRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(10011511666L);
        Calendar cal = Calendar.getInstance();
        req.setEndTime(cal.getTime());
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - 1);
        req.setStartTime(cal.getTime());
        Page page = new Page();
        req.setPage(page);
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(req);
        Assert.assertEquals(FpdErrInfo.PRODUCT_NO_CONTRACT.code(), resp.getErrorCode());
    }
    
    @Test
    public void testSucc() {
        FinaOrderQueryRequest req = new FinaOrderQueryRequest();
        req.setBizCode(BIZ_CODE);
        req.setRequestId(BIZ_CODE + System.currentTimeMillis());
        req.setRequestTime(new Date());
        req.setAppId(APP_ID);
        req.setProductCode(P_CODE);
        req.setMemberCode(M_CODE);
        Calendar cal = Calendar.getInstance();
        req.setEndTime(cal.getTime());
        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - 1);
        req.setStartTime(cal.getTime());
        Page page = new Page();
        req.setPage(page);
        FinaOrderQueryResponse resp = fpdMfsQuery.queryFinaOrder(req);
        Assert.assertEquals(FpdErrInfo.SUCC.code(), resp.getErrorCode());
    }
}
