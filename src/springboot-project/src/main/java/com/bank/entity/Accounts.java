package com.bank.entity;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer aid;
    private Integer amount;
    private String remarks;
    private String date;
    private String transacType;

    public Accounts() {

    }

    public Accounts(Integer aid, Integer amount, String remarks, String date, String transacType) {
        this.transacType = transacType;
        this.aid = aid;
        this.amount = amount;
        this.remarks = remarks;
        this.date = date;

    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransacType() {
        return transacType;
    }

    public void setTransacType(String transacType) {
        this.transacType = transacType;
    }
}
