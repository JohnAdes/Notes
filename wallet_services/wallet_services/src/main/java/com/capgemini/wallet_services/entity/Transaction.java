package com.capgemini.wallet_services.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Transaction {


    enum TransactionType {
        Topup, Transfer, Withdraw, Deposit
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    @DateTimeFormat(pattern = "mm-dd-yyyy")
    private LocalDate date;
    private TransactionType transactionType;
    private double totalBalance,amount;
    @ManyToOne
    @JoinColumn(name = "account_account_id")
    private Account account;

    public Transaction() {
    }

    public Account getAccount() {
        return account;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


}

