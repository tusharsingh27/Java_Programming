package com.bank.controller;

import com.bank.entity.Accounts;
import com.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    // createAccount
    @PostMapping("/account")
    public void createAccount(@RequestBody Accounts accounts) {
        accountService.createAccount(accounts);
    }

    //getAccount
    @GetMapping("/account/{aid}")
    public Accounts getAccount(@PathVariable("aid") Integer aid) {
        return accountService.getAccountInfo(aid);
    }

    // checkAmount
    @GetMapping("/account/{aid}/amount")
    public int getAmount(@PathVariable ("aid") Integer aid) {
        return accountService.getAmount(aid);
    }

    // depositAmount
    @PutMapping("/account/{aid}/amount")
    public void depositAmount(@PathVariable("aid") Integer aid, @PathVariable("balance") Integer amount) {
        accountService.depositAmount(aid, amount);
    }

    // deleteAccount
    @DeleteMapping("/account/{aid}")
    public void deleteAccount(@PathVariable("aid") Integer aid) {
        accountService.deleteAccount(aid);
    }

}



