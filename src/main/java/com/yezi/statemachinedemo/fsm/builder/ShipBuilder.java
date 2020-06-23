package com.yezi.statemachinedemo.fsm.builder;

import com.yezi.statemachinedemo.business.entity.Trade;
import com.yezi.statemachinedemo.business.enums.TradeEvent;
import com.yezi.statemachinedemo.business.enums.TradeStatus;
import com.yezi.statemachinedemo.fsm.Builder;
import com.yezi.statemachinedemo.fsm.action.ShipAction;
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
public class ShipBuilder implements Builder {


    @Autowired
    private ShipAction shipAction;


    @Override
    public TradeStatus supportState() {
        return TradeStatus.TO_DELIVER;
    }


    @Override
    public StateMachine<TradeStatus, TradeEvent> build(Trade trade, BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<TradeStatus, TradeEvent> builder = StateMachineBuilder.builder();

        builder.configureStates()
                .withStates()
                .initial(TradeStatus.TO_DELIVER)
                .states(EnumSet.allOf(TradeStatus.class));

        builder.configureTransitions()
                //发货 -> 收货
                .withExternal()
                .source(TradeStatus.TO_DELIVER).target(TradeStatus.TO_RECIEVE)
                .event(TradeEvent.SHIP)
                .action(shipAction);

        return builder.build();
    }
}
