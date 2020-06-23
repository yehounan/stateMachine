package com.yezi.statemachinedemo.fsm.params;

import com.yezi.statemachinedemo.business.enums.TradeEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 状态扭转 请求参数
 * Created by yezi on 22/6/2020.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateRequest {

    /**
     * 订单编号
     */
    private Long tid;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 事件操作
     */
    private TradeEvent event;

    /**
     * 数据
     */
    private Object data;
}
