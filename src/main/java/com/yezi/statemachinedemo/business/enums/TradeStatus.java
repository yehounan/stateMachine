package com.yezi.statemachinedemo.business.enums;

/**
 * @Description: 订单状态
 * @Author: yezi
 * @Date: 2020/6/19 14:01
 */
public enum TradeStatus {
    //待支付
    TO_PAY,
    //待发货
    TO_DELIVER,
    //待收货
    TO_RECIEVE,
    //完成
    COMPLETE,
    //取消
    VOID;
}
