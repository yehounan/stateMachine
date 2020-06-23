package com.yezi.statemachinedemo.fsm.builder;

import com.yezi.statemachinedemo.business.entity.Trade;
import com.yezi.statemachinedemo.business.enums.TradeEvent;
import com.yezi.statemachinedemo.business.enums.TradeStatus;
import com.yezi.statemachinedemo.fsm.TradeFSMBuilder;
import com.yezi.statemachinedemo.fsm.action.CancelAction;
import com.yezi.statemachinedemo.fsm.action.PayAction;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

/**
 * @Description:
 * @Author: yezi
 * @Date: 2020/6/19 17:13
 */
@Component
public class PayTradeFSMBuilder implements TradeFSMBuilder {


    @Autowired
    private PayAction payAction;

    @Autowired
    private CancelAction cancelAction;


    @Override
    public TradeStatus supportState() {
        return TradeStatus.TO_PAY;
    }


    @Override
    public StateMachine<TradeStatus, TradeEvent> build(Trade trade, BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<TradeStatus, TradeEvent> builder = StateMachineBuilder.builder();

        builder.configureStates()
                .withStates()
                .initial(TradeStatus.TO_PAY)
                .states(EnumSet.allOf(TradeStatus.class));
        /**
         * 待支付状态的订单当前有2种状态流转，一个是支付之后发货，一个只取消；
         * 2个状态是平行状态只是执行的动作不同
         */
        builder.configureTransitions()
                //待支付 -> 发货
                .withExternal()
                .source(TradeStatus.TO_PAY).target(TradeStatus.TO_DELIVER)
                .event(TradeEvent.PAY)
                .action(payAction)
                .and()
                //待支付 -> 取消
                .withExternal()
                .source(TradeStatus.TO_PAY).target(TradeStatus.VOID)
                .event(TradeEvent.VOID)
                .action(cancelAction);

        return builder.build();
    }
}
