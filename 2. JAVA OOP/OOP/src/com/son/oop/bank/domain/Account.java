package com.son.oop.bank.domain;

import com.son.oop.basics.Constants.AccountType;
import com.son.oop.basics.Constants.AccountStatus;
import java.util.Objects;
import java.math.BigDecimal;

public class Account {
     private final String id;
     private final AccountType type;
     private AccountStatus status = AccountStatus.ACTIVE;
     private BigDecimal balance = BigDecimal.ZERO;
     private BigDecimal minBalance = new BigDecimal("100000");

     public Account(String id, AccountType type) {
         if(id == null || id.isBlank()) throw new IllegalArgumentException("id is blank");
         this.id = id;
         this.type = Objects.requireNonNull(type);
     }

     public void deposit(BigDecimal amount) {
         // check active
         requireActive();
         requirePositive(amount);
         balance = balance.add(amount);
     }

    public void withdraw(BigDecimal amount) {
         requireActive();
         requirePositive(amount);
         if(balance.subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
             throw new IllegalArgumentException("Insufficient funds after maintaining min balance" + minBalance);
         }
         balance = balance.subtract(amount);
    }

    public void freeze() {
         status = AccountStatus.FROZEN;
    }

    public void close() {
        status = AccountStatus.CLOSED;
    }

    private void requireActive() {
         if(status != AccountStatus.ACTIVE) throw new IllegalStateException("Account is not active" + status);
    }

    private void requirePositive(BigDecimal amount) {
         if(amount == null || amount.signum() <= 0) throw new IllegalArgumentException("amount > 0 required");
    }

    @Override
    public String toString() {
         return "%s{id='%s', type='%s', balance='%s', minBalance='%s'}"
                 .formatted(getClass().getSimpleName(), id, type, balance, minBalance);
    }

}
