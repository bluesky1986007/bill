/**
 * @(#)InvestPlanRemitResultCallbackTest.java Sep 30, 2014
 *
 * Copyright (c) 2004-2010 99Bill Corporation. All Rights Reserved.
 */
package com.bill99.ta.infs.fpd.test.mock;


import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.bill99.qa.framework.testcase.BaseTestCase;
import com.bill99.seashell.domain.mdp.service.InvestPlanRemitResultCallbackService;

public class InvestPlanRemitResultCallbackTest extends BaseTestCase{
	
	@Autowired
    private InvestPlanRemitResultCallbackService investPlanRemitResultCallbackService;
//    @Before
//    public void setUp() throws Exception {
////        investPlanRemitResultCallbackService = (InvestPlanRemitResultCallbackService) BeanHome.getInstance().getBean("investPlanRemitResultCallbackService");
//    }
    
    @Test(enabled=true)
    public void testRemitSucc() {
        // 参数1：交易号
        // 参数2：状态(1成功，0失败)
        investPlanRemitResultCallbackService.investPlanRemitResultHandle(1412933325684L, 1);
    }
    
//    @Test(enabled=true)
//    public void testRemitFail() {
//        investPlanRemitResultCallbackService.investPlanRemitResultHandle(1412932990595L, 0);
//    }
}
