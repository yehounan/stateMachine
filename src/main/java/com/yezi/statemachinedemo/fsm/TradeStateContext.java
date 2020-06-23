package com.yezi.statemachinedemo.fsm;


import com.yezi.statemachinedemo.business.entity.Trade;
import com.yezi.statemachinedemo.business.enums.TradeEvent;
import com.yezi.statemachinedemo.business.enums.TradeStatus;
import com.yezi.statemachinedemo.fsm.params.StateRequest;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;

/**
 * @Description: 订单状态上下文：对当前状态机上下文的包装，主要作用于存放订单处理过程中出现的异常信息
 * @Author: yezi
 * @Date: 2020/6/22 15:13
 */
public class TradeStateContext {

    private StateContext<TradeStatus, TradeEvent> stateContext;

    public TradeStateContext(StateContext<TradeStatus, TradeEvent> stateContext) {
        this.stateContext = stateContext;
    }

    /**
     * 将订单处理过程中发生的异常放入订单状态上下文
     *
     * @param key
     * @param value
     * @return
     */
    public TradeStateContext put(Object key, Object value) {
        stateContext.getExtendedState().getVariables().put(key, value);
        return this;
    }

    /**
     * 获取当前状态机所处理的订单
     *
     * @return
     */
    public Trade getTrade() {
        return this.stateContext.getExtendedState().get(Trade.class, Trade.class);
    }

    /**
     * 获取当前状态机所处理的请求
     *
     * @return
     */
    public StateRequest getRequest() {
        return this.stateContext.getExtendedState().get(StateRequest.class, StateRequest.class);
    }

    /**
     * 获取操作人信息
     *
     * @return
     */
    public String getOperator() {
        return getRequest().getOperator();
    }

    /**
     * 请求数据
     *
     * @param <T>
     * @return
     */
    public <T> T getRequestData() {
        return (T) getRequest().getData();
    }

    /**
     * 当前状态机
     *
     * @return
     */
    public StateMachine<TradeStatus, TradeEvent> getStateMachine() {
        return this.stateContext.getStateMachine();
    }

    /**
     * 当前状态机上下文
     *
     * @return
     */
    public StateContext<TradeStatus, TradeEvent> getStateContext() {
        return stateContext;
    }
}
