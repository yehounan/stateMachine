package com.yezi.statemachinedemo.fsm;


import com.yezi.statemachinedemo.business.entity.Trade;
import com.yezi.statemachinedemo.business.enums.TradeEvent;
import com.yezi.statemachinedemo.business.enums.TradeStatus;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description: 状态机工厂
 * @Author: yezi
 * @Date: 2020/6/19 17:13
 */
@Component
public class BuilderFactory implements InitializingBean {

    private Map<TradeStatus, TradeFSMBuilder> builderMap = new ConcurrentHashMap<>();


    @Autowired
    private List<TradeFSMBuilder> tradeFSMBuilders;


    @Autowired
    private BeanFactory beanFactory;


    public StateMachine<TradeStatus, TradeEvent> create(Trade trade) {
        TradeStatus tradeStatus = trade.getStatus();
        TradeFSMBuilder tradeFSMBuilder = builderMap.get(tradeStatus);
        if (tradeFSMBuilder == null) {
            throw new RuntimeException("构建器创建失败");
        }
        //创建订单状态机
        StateMachine<TradeStatus, TradeEvent> sm;
        try {
            sm = tradeFSMBuilder.build(trade, beanFactory);
            sm.start();
        } catch (Exception e) {
            throw new RuntimeException("状态机创建失败");
        }
        //将订单放入状态机
        sm.getExtendedState().getVariables().put(Trade.class, trade);
        return sm;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        builderMap = tradeFSMBuilders.stream().collect(Collectors.toMap(TradeFSMBuilder::supportState, Function.identity()));
    }
}
