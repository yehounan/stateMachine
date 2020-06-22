package com.yezi.statemachinedemo.business.entity;

import com.yezi.statemachinedemo.business.enums.TradeStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: yezi
 * @Date: 2020/6/19 13:56
 */
@Data
@Entity
@Table(name = "trade")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单状态
     */
    @Enumerated(value = EnumType.STRING)
    private TradeStatus status;

    /**
     * 订单号
     */
    private String tradeNo;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
