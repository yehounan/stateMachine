package com.yezi.statemachinedemo.statemachine.action;

import com.yezi.statemachinedemo.business.entity.Trade;
import com.yezi.statemachinedemo.business.enums.TradeStatus;
import com.yezi.statemachinedemo.statemachine.TradeAction;
import com.yezi.statemachinedemo.statemachine.TradeStateContext;
import com.yezi.statemachinedemo.statemachine.params.StateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: 发货动作
 * @Author: yezi
 * @Date: 2020/6/22 16:55
 */
@Slf4j
@Component
public class ShipAction extends TradeAction {


    @Override
    protected void evaluateInternal(Trade trade, StateRequest request, TradeStateContext tsc) {
        ship(trade);
    }

    /**
     * 发货状态变更为待收货状态
     *
     * @param trade
     */
    private void ship(Trade trade) {
        trade.setStatus(TradeStatus.TO_RECIEVE);
        update(trade);
        log.info("订单号{}，发货成功。", trade.getTradeNo());
    }
}
