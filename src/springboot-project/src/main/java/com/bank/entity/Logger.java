package com.bank.entity;

import javax.persistence.*;

@Entity
@Table(name="logger")
public class Logger {

    @Id
    private Integer acctID;
    private String date;
    private String transacType;
    private String transacStatus;
    private int initBal;
    private int finalBal;

    public Logger() {

    }

    public Logger (Integer acctID,String date, String transacType, String transacStatus, int initBal, int finalBal) {
        super();
      this.date=date;
        this.acctID = acctID;
        this.transacType = transacType;
        this.transacStatus = transacStatus;
        this.initBal = initBal;
        this.finalBal = finalBal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAcctID() {
        return acctID;
    }

    public void setAcctID(Integer acctID) {
        this.acctID = acctID;
    }

    public String getTransacType() {
        return transacType;
    }

    public void setTransacType(String transacType) {
        this.transacType = transacType;
    }

    public String getTransacStatus() {
        return transacStatus;
    }

    public void setTransacStatus(String transacStatus) {
        this.transacStatus = transacStatus;
    }

    public int getInitBal() {
        return initBal;
    }

    public void setInitBal(int initBal) {
        this.initBal = initBal;
    }

    public int getFinalBal() {
        return finalBal;
    }

    public void setFinalBal(int finalBal) {
        this.finalBal = finalBal;
    }

}


