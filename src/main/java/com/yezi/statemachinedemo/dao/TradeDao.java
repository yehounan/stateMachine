package com.yezi.statemachinedemo.dao;

import com.yezi.statemachinedemo.business.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: yezi
 * @Date: 2020/6/19 14:21
 */
@Repository
public interface TradeDao extends JpaRepository<Trade, Long>, JpaSpecificationExecutor<Trade> {


}
