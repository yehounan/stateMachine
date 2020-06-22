package com.yezi.statemachinedemo.config;

import com.yezi.statemachinedemo.business.enums.TradeEvent;
import com.yezi.statemachinedemo.business.enums.TradeStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @Description:
 * @Author: yezi
 * @Date: 2020/6/19 17:25
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<TradeStatus, TradeEvent> {

    @Override
    public void configure(StateMachineStateConfigurer<TradeStatus, TradeEvent> states)
            throws Exception {
        states
                .withStates()
                .initial(TradeStatus.TO_PAY)
                .states(EnumSet.allOf(TradeStatus.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<TradeStatus, TradeEvent> transitions)
            throws Exception {
        transitions
                .withExternal()
                //支付 -> 发货
                .source(TradeStatus.TO_PAY).target(TradeStatus.TO_DELIVER)
                .event(TradeEvent.PAY)
                .and()
                .withExternal()
                //发货 -> 收货
                .source(TradeStatus.TO_DELIVER).target(TradeStatus.TO_RECIEVE)
                .event(TradeEvent.SHIP)
                .and()
                .withExternal()
                //收货 -> 完成
                .source(TradeStatus.TO_RECIEVE).target(TradeStatus.COMPLETE)
                .event(TradeEvent.CONFIRM);
    }
}