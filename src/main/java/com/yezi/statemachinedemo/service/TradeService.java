package com.yezi.statemachinedemo.service;

import com.yezi.statemachinedemo.business.entity.Trade;
import com.yezi.statemachinedemo.business.enums.TradeStatus;
import com.yezi.statemachinedemo.dao.TradeDao;
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


}
