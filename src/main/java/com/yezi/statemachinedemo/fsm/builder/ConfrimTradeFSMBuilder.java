package com.yezi.statemachinedemo.fsm.builder;

import com.yezi.statemachinedemo.business.entity.Trade;
import com.yezi.statemachinedemo.business.enums.TradeEvent;
import com.yezi.statemachinedemo.business.enums.TradeStatus;
import com.yezi.statemachinedemo.fsm.TradeFSMBuilder;
import com.yezi.statemachinedemo.fsm.action.ConfirmAction;
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
public class ConfrimTradeFSMBuilder implements TradeFSMBuilder {


    @Autowired
    private ConfirmAction confirmAction;


    @Override
    public TradeStatus supportState() {
        return TradeStatus.TO_RECIEVE;
    }


    @Override
    public StateMachine<TradeStatus, TradeEvent> build(Trade trade, BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<TradeStatus, TradeEvent> builder = StateMachineBuilder.builder();

        builder.configureStates()
                .withStates()
                .initial(TradeStatus.TO_RECIEVE)
                .states(EnumSet.allOf(TradeStatus.class));

        builder.configureTransitions()
                //收货 -> 完成
                .withExternal()
                .source(TradeStatus.TO_RECIEVE).target(TradeStatus.COMPLETE)
                .event(TradeEvent.CONFIRM)
                .action(confirmAction);

        return builder.build();
    }
}
