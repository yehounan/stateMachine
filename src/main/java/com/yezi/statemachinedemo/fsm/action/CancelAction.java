package com.yezi.statemachinedemo.fsm.action;

import com.yezi.statemachinedemo.business.entity.Trade;
import com.yezi.statemachinedemo.business.enums.TradeStatus;
import com.yezi.statemachinedemo.fsm.TradeAction;
import com.yezi.statemachinedemo.fsm.TradeStateContext;
import com.yezi.statemachinedemo.fsm.params.StateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: 订单取消动作
 * @Author: yezi
 * @Date: 2020/6/22 15:22
 */
@Slf4j
@Component
public class CancelAction extends TradeAction {


    @Override
    protected void evaluateInternal(Trade trade, StateRequest request, TradeStateContext tsc) {
        cancel(trade);
    }

    /**
     * 待支付状态变更为取消状态
     *
     * @param trade
     */
    private void cancel(Trade trade) {
        trade.setStatus(TradeStatus.VOID);
        update(trade);
        log.info("订单号{}，取消成功。", trade.getTradeNo());
    }
}
