package com.yezi.statemachinedemo.fsm;

import com.yezi.statemachinedemo.business.entity.Trade;
import com.yezi.statemachinedemo.business.enums.TradeEvent;
import com.yezi.statemachinedemo.business.enums.TradeStatus;
import com.yezi.statemachinedemo.service.TradeService;
import com.yezi.statemachinedemo.fsm.params.StateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * @Description:
 * @Author: yezi
 * @Date: 2020/6/22 15:13
 */
@Slf4j
public abstract class TradeAction implements Action<TradeStatus, TradeEvent> {

    @Autowired
    private TradeService tradeService;

    @Override
    public void execute(StateContext<TradeStatus, TradeEvent> stateContext) {
        TradeStateContext tsc = new TradeStateContext(stateContext);
        try {
            evaluateInternal(tsc.getTrade(), tsc.getRequest(), tsc);
        } catch (Exception e) {
            //捕获此处异常，将异常信息放入订单状态机上下文
            tsc.put(Exception.class, e);
            if (e instanceof UndeclaredThrowableException) {
                //如果发生包装异常，需要获取包装异常中的具体异常信息
                Throwable undeclaredThrowable = ((UndeclaredThrowableException) e).getUndeclaredThrowable();
                undeclaredThrowable.printStackTrace();
                log.error(String.format("订单处理, 从状态[ %s ], 经过事件[ %s ], 到状态[ %s ], 出现异常[ %s ]", stateContext.getSource().getId(), stateContext.getEvent(), stateContext.getTarget().getId(), undeclaredThrowable));
            } else {
                e.printStackTrace();
                log.error(String.format("订单处理, 从状态[ %s ], 经过事件[ %s ], 到状态[ %s ], 出现异常[ %s ]", stateContext.getSource().getId(), stateContext.getEvent(), stateContext.getTarget().getId(), e));
            }

        }
    }


    /**
     * 更新订单
     *
     * @param trade
     */
    protected void update(Trade trade) {
        tradeService.update(trade);
    }


    protected abstract void evaluateInternal(Trade trade, StateRequest request, TradeStateContext tsc);
}
