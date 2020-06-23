package com.yezi.statemachinedemo.controller;

import com.yezi.statemachinedemo.business.entity.Trade;
import com.yezi.statemachinedemo.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: yezi
 * @Date: 2020/6/19 13:42
 */
@RestController
public class TradeController {


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

    @GetMapping("/pay/{id}")
    public String pay(@PathVariable("id") Long id) {
        tradeService.pay(id);
        return "OJBK";
    }

    @GetMapping("/ship/{id}")
    public String ship(@PathVariable("id") Long id) {
        tradeService.ship(id);
        return "OJBK";
    }

    @GetMapping("/confirm/{id}")
    public String confirm(@PathVariable("id") Long id) {
        tradeService.confirm(id);
        return "OJBK";
    }



}
