package com.yezi.statemachinedemo.statemachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @Description:
 * @Author: yezi
 * @Date: 2020/6/19 17:52
 */
@Slf4j
@WithStateMachine
public class EventListener {


    @OnTransition(target = "TO_PAY")
    public void create() {
        log.info("订单创建，待支付");
    }

    @OnTransition(source = "TO_PAY", target = "TO_DELIVER")
    public void pay() {
        log.info("用户完成支付，待发货");
    }

    @OnTransition(source = "TO_DELIVER", target = "TO_RECIEVE")
    public void receive() {
        log.info("商家已发货，待收货");
    }

    @OnTransition(source = "TO_RECIEVE", target = "COMPLETE")
    public void confirm() {
        log.info("用户确认收货，订单完成");
    }

}
