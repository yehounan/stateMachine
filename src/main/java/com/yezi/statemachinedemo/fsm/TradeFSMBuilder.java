package com.yezi.statemachinedemo.fsm;

import com.yezi.statemachinedemo.business.entity.Trade;
import com.yezi.statemachinedemo.business.enums.TradeEvent;
import com.yezi.statemachinedemo.business.enums.TradeStatus;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.statemachine.StateMachine;

/**
 * @Description: 订单状态机构建器
 * @Author: yezi
 * @Date: 2020/6/22 15:24
 */
public interface TradeFSMBuilder {

    /**
     * @return
     */
    TradeStatus supportState();

    /**
     * @param trade
     * @param beanFactory
     * @return
     * @throws Exception
     */
    StateMachine<TradeStatus, TradeEvent> build(Trade trade, BeanFactory beanFactory) throws Exception;
}
