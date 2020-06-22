package com.yezi.statemachinedemo;

import com.yezi.statemachinedemo.business.enums.TradeEvent;
import com.yezi.statemachinedemo.statemachine.TradeFSMService;
import com.yezi.statemachinedemo.statemachine.params.StateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StatemachineDemoApplicationTests {


    @Autowired
    private TradeFSMService tradeFSMService;

    @Test
    void contextLoads() {
        StateRequest stateRequest = StateRequest.builder()
                .tid(1L)
                .event(TradeEvent.SHIP)
                .build();
        tradeFSMService.changeState(stateRequest);

    }

}
