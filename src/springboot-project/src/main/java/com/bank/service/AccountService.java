package com.bank.service;

import com.bank.entity.Accounts;
import com.bank.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountsRepository accountRepository;

    public void createAccount(Accounts accounts) {
        accountRepository.save(accounts);
    }

    public void deleteAccount(Integer aid) {
        accountRepository.deleteById(aid);
    }

    public int getAmount(Integer aid) {
        return accountRepository.findAmountByAid(aid);
    }

    public void depositAmount(Integer aid, Integer amount) {
        accountRepository.saveAmountByAid(aid, amount);

    }

    public Accounts getAccountInfo(Integer aid) {
        return accountRepository.findById(aid).orElse(null);
    }
}





