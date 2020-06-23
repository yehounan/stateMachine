package com.yezi.statemachinedemo.fsm.action;

import com.yezi.statemachinedemo.business.entity.Trade;
import com.yezi.statemachinedemo.business.enums.TradeStatus;
import com.yezi.statemachinedemo.fsm.TradeAction;
import com.yezi.statemachinedemo.fsm.TradeStateContext;
import com.yezi.statemachinedemo.fsm.params.StateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: 确认收货动作（订单完成）
 * @Author: yezi
 * @Date: 2020/6/22 16:57
 */
@Slf4j
@Component
public class ConfirmAction extends TradeAction {


    @Override
    protected void evaluateInternal(Trade trade, StateRequest request, TradeStateContext tsc) {
        complete(trade);
    }

    /**
     * 待发货状态变更为完成状态
     *
     * @param trade
     */
    private void complete(Trade trade) {
        trade.setStatus(TradeStatus.COMPLETE);
        update(trade);
        log.info("订单号{}，确认收货成功，订单完成。", trade.getTradeNo());
    }
}
