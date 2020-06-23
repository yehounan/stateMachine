package com.yezi.statemachinedemo.service;

import com.yezi.statemachinedemo.business.entity.Trade;
import com.yezi.statemachinedemo.business.enums.TradeEvent;
import com.yezi.statemachinedemo.business.enums.TradeStatus;
import com.yezi.statemachinedemo.dao.TradeDao;
import com.yezi.statemachinedemo.fsm.TradeFSMService;
import com.yezi.statemachinedemo.fsm.params.StateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Description:
 * @Author: yezi
 * @Date: 2020/6/19 14:29
 */
@Service
public class TradeService {


    @Autowired
    private TradeDao tradeDao;

    @Autowired
    private TradeFSMService tradeFSMService;

    /**
     * 创建订单
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Trade create() {
        Trade trade = new Trade();
        trade.setStatus(TradeStatus.TO_PAY);
        trade.setTradeNo(UUID.randomUUID().toString());
        trade.setCreateTime(LocalDateTime.now());
        return tradeDao.save(trade);
    }

    /**
     * 更新订单
     *
     * @param trade
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Trade update(Trade trade) {
        return tradeDao.save(trade);

    }

    public Trade findById(Long id) {
        return tradeDao.findById(id).get();
    }


    public void pay(Long id) {
        Trade trade = tradeDao.findById(id).get();
        if (!trade.getStatus().equals(TradeStatus.TO_PAY)) {
            throw new RuntimeException("订单状态异常，不能支付");
        }
        StateRequest stateRequest = StateRequest.builder()
                .tid(trade.getId())
                .event(TradeEvent.PAY)
                .build();
        tradeFSMService.changeState(stateRequest);
    }

    public void ship(Long id) {
        Trade trade = tradeDao.findById(id).get();
        if (!trade.getStatus().equals(TradeStatus.TO_DELIVER)) {
            throw new RuntimeException("订单状态异常，不能发货");
        }
        StateRequest stateRequest = StateRequest.builder()
                .tid(trade.getId())
                .event(TradeEvent.SHIP)
                .build();
        tradeFSMService.changeState(stateRequest);
    }

    public void confirm(Long id) {
        Trade trade = tradeDao.findById(id).get();
        if (!trade.getStatus().equals(TradeStatus.TO_RECIEVE)) {
            throw new RuntimeException("订单状态异常，不能确认收货");
        }
        StateRequest stateRequest = StateRequest.builder()
                .tid(trade.getId())
                .event(TradeEvent.CONFIRM)
                .build();
        tradeFSMService.changeState(stateRequest);
    }
}
