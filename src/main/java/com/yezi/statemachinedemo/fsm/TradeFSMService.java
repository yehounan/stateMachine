package com.yezi.statemachinedemo.fsm;

import com.yezi.statemachinedemo.business.entity.Trade;
import com.yezi.statemachinedemo.business.enums.TradeEvent;
import com.yezi.statemachinedemo.business.enums.TradeStatus;
import com.yezi.statemachinedemo.service.TradeService;
import com.yezi.statemachinedemo.fsm.params.StateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Description: 订单状态机服务
 * @Author: yezi
 * @Date: 2020/6/22 15:24
 */
@Slf4j
@Service
public class TradeFSMService {

    @Autowired
    private TradeService tradeService;

    @Autowired
    private BuilderFactory builderFactory;

    /**
     * 订单状态变更
     *
     * @param request
     * @return
     */
    public boolean changeState(StateRequest request) {
        Trade trade = tradeService.findById(request.getTid());
        log.info("trade={}", trade);
        if (Objects.isNull(trade)) {
            log.error("创建订单状态机失败,无法从状态 {} 转向 => {}", trade.getStatus(), request.getEvent());
            throw new RuntimeException("订单不存在");
        }
        //1.根据订单创建状态机
        StateMachine<TradeStatus, TradeEvent> stateMachine = builderFactory.create(trade);
        //2.将参数传入状态机
        stateMachine.getExtendedState().getVariables().put(StateRequest.class, request);
        //3.发送当前请求的状态
        boolean isSend = stateMachine.sendEvent(request.getEvent());
        if (!isSend) {
            log.error("创建订单状态机失败,无法从状态 {} 转向 => {}", trade.getStatus(), request.getEvent());
            throw new RuntimeException("创建订单状态机失败");
        }
        //4. 判断处理过程中是否出现了异常
        Exception exception = stateMachine.getExtendedState().get(Exception.class, Exception.class);
        if (exception != null) {
            if (exception.getClass().isAssignableFrom(RuntimeException.class)) {
                throw (RuntimeException) exception;
            } else {
                throw new RuntimeException("状态机处理出现异常");
            }
        }
        return true;
    }
}
