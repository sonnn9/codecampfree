package com.son.oop.bank.service;

import java.math.BigDecimal;
import com.son.oop.bank.domain.Account;

public class Bank {
    public void transfer(Account from, Account to, BigDecimal amount) {
        if(from == null || to == null) throw new IllegalArgumentException("Account required");
        if(from == to) throw new IllegalArgumentException("cannot transfer to same account");
        from.withdraw(amount);
        to.deposit(amount);
    }
}
