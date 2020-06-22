package com.yezi.statemachinedemo.statemachine.builder;

import com.yezi.statemachinedemo.business.entity.Trade;
import com.yezi.statemachinedemo.business.enums.TradeEvent;
import com.yezi.statemachinedemo.business.enums.TradeStatus;
import com.yezi.statemachinedemo.statemachine.Builder;
import com.yezi.statemachinedemo.statemachine.action.ConfirmAction;
import com.yezi.statemachinedemo.statemachine.action.PayAction;
import com.yezi.statemachinedemo.statemachine.action.ShipAction;
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
public class TradeBuilder implements Builder {


    @Autowired
    private PayAction payAction;

    @Autowired
    private ShipAction shipAction;

    @Autowired
    private ConfirmAction confirmAction;


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

        builder.configureTransitions()
                //支付 -> 发货
                .withExternal()
                .source(TradeStatus.TO_PAY).target(TradeStatus.TO_DELIVER)
                .event(TradeEvent.PAY)
                .action(payAction)
                .and()
                //发货 -> 收货
                .withExternal()
                .source(TradeStatus.TO_DELIVER).target(TradeStatus.TO_RECIEVE)
                .event(TradeEvent.SHIP)
                .action(shipAction)
                .and()
                //收货 -> 完成
                .withExternal()
                .source(TradeStatus.TO_RECIEVE).target(TradeStatus.COMPLETE)
                .event(TradeEvent.CONFIRM)
                .action(confirmAction);

        return builder.build();
    }
}
