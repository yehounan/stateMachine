package com.yezi.statemachinedemo.statemachine.action;

import com.yezi.statemachinedemo.business.entity.Trade;
import com.yezi.statemachinedemo.business.enums.TradeStatus;
import com.yezi.statemachinedemo.statemachine.TradeAction;
import com.yezi.statemachinedemo.statemachine.TradeStateContext;
import com.yezi.statemachinedemo.statemachine.params.StateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: 订单支付动作
 * @Author: yezi
 * @Date: 2020/6/22 15:22
 */
@Slf4j
@Component
public class PayAction extends TradeAction {


    @Override
    protected void evaluateInternal(Trade trade, StateRequest request, TradeStateContext tsc) {
        pay(trade);
    }

    /**
     * 待支付状态变更为待发货状态
     *
     * @param trade
     */
    private void pay(Trade trade) {
        trade.setStatus(TradeStatus.TO_DELIVER);
        update(trade);
        log.info("订单号{}，支付成功。", trade.getTradeNo());
    }
}
