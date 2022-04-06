package com.bank.controller;

import com.bank.entity.Logger;
import com.bank.service.LoggerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoggerController {

   private final LoggerService loggerService;

    // addLog
    @PostMapping("/account")
    public void addLog(@RequestBody Logger logger) {
     loggerService.addLog(logger);
    }

    // showLog
    @GetMapping("/account/{acctID}")
    public Logger showLog(@PathVariable ("acctID") Integer acctID) {
        return loggerService.showLog(acctID);
    }

    //delete log
    @DeleteMapping("/account/{acctID}")
    public void deleteLog(@PathVariable ("acctID") Integer acctID) {
        loggerService.deleteLog(acctID);
    }
}

