package com.bank.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    private Integer acctID;
    private String custName;
    private String city;
    private String state;
    private String country;
    private int phoneNo;
    private String password;

    public Customer() {

    }

    public Customer(Integer acctID, String custName, String city, String state, String country, int phoneNo,
                    String password) {
        super();
        this.acctID = acctID;
        this.custName = custName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.phoneNo = phoneNo;
        this.password = password;
    }

    public Integer getAcctID() {
        return acctID;
    }

    public void setAcctID(Integer acctID) {
        this.acctID = acctID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo=phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

