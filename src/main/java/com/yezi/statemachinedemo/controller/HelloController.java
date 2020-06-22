package com.yezi.statemachinedemo.controller;

import com.yezi.statemachinedemo.business.entity.Trade;
import com.yezi.statemachinedemo.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: yezi
 * @Date: 2020/6/19 13:42
 */
@RestController
public class HelloController {


    @Autowired
    private TradeService tradeService;

    @GetMapping("/index")
    public String hello() {
        return "OJBK";
    }

    @PostMapping("/create")
    public Trade create() {
        return tradeService.create();
    }


}
