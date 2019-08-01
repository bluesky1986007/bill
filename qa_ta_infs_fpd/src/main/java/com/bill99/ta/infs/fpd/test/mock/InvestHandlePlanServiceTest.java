/**
 * @(#)InvestPlanRemitResultCallbackTest.java Sep 30, 2014
 *
 * Copyright (c) 2004-2010 99Bill Corporation. All Rights Reserved.
 */
package com.bill99.ta.infs.fpd.test.mock;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.bill99.qa.framework.testcase.BaseTestCase;
import com.bill99.seashell.domain.mdp.dto.InvestPlanHandleRequest;
import com.bill99.seashell.domain.mdp.dto.InvestPlanHandleResponse;
import com.bill99.seashell.domain.mdp.service.InvestHandlePlanService;

public class InvestHandlePlanServiceTest extends BaseTestCase{
	
	@Autowired
    private InvestHandlePlanService investHandlePlanService;
    
    @Test(enabled=false)
    public void test1() {
    	//投资确认
    	InvestPlanHandleRequest handleRequest = new InvestPlanHandleRequest();
    	handleRequest.setOperator("1001");
    	handleRequest.setPlanId(1998L);
    	handleRequest.setRequestDate(new Date());
    	InvestPlanHandleResponse response1 = investHandlePlanService.confirmInvestPlan(handleRequest);
    	System.out.println(response1.getStatus());
    	System.out.println(response1.getErrorMsg());
    	InvestPlanHandleResponse response2 = investHandlePlanService.confirmInvestPlan(handleRequest);
    	System.out.println(response2.getStatus());
    	System.out.println(response2.getErrorMsg());
    	InvestPlanHandleResponse response3 = investHandlePlanService.confirmInvestPlan(handleRequest);
    	System.out.println(response3.getStatus());
    	System.out.println(response3.getErrorMsg());
    	InvestPlanHandleResponse response4 = investHandlePlanService.confirmInvestPlan(handleRequest);
    	System.out.println(response4.getStatus());
    	System.out.println(response4.getErrorMsg());
    	InvestPlanHandleResponse response5 = investHandlePlanService.confirmInvestPlan(handleRequest);
    	System.out.println(response5.getStatus());
    	System.out.println(response5.getErrorMsg());
    	
    }
    
    @Test(enabled=true)
    public void test2() {
    	//重新出款
    	InvestPlanHandleRequest handleRequest = new InvestPlanHandleRequest();
    	handleRequest.setOperator("1234");
    	handleRequest.setPlanId(1995L);
    	handleRequest.setRequestDate(new Date());
    	
    	InvestPlanHandleResponse response1 = investHandlePlanService.reFundout(handleRequest);
    	System.out.println(response1.getStatus());
    	System.out.println(response1.getErrorMsg());
    	InvestPlanHandleResponse response2 = investHandlePlanService.reFundout(handleRequest);
    	System.out.println(response2.getStatus());
    	System.out.println(response2.getErrorMsg());
    	InvestPlanHandleResponse response3 = investHandlePlanService.reFundout(handleRequest);
    	System.out.println(response3.getStatus());
    	System.out.println(response3.getErrorMsg());
    	InvestPlanHandleResponse response4 = investHandlePlanService.reFundout(handleRequest);
    	System.out.println(response4.getStatus());
    	System.out.println(response4.getErrorMsg());
    	InvestPlanHandleResponse response5 = investHandlePlanService.reFundout(handleRequest);
    	System.out.println(response5.getStatus());
    	System.out.println(response5.getErrorMsg());

    }
}
