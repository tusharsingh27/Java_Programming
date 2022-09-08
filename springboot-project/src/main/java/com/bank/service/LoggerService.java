package com.bank.service;

import com.bank.entity.Logger;
import com.bank.repository.LoggerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LoggerService {

    private final LoggerRepository loggerRepository;

    public void addLog(Logger logger) {
        loggerRepository.save(logger);
    }

    public Logger showLog(Integer acctID) {
        return loggerRepository.findById(acctID).orElse(null);
    }

    public void deleteLog(Integer acctID) {
        loggerRepository.deleteById(acctID);
    }
}

