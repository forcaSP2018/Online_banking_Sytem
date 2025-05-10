package org.example;

import java.util.Objects;

public class Costumer extends User {
    private Account account;

    public Costumer(String name, Account account) {
        super(name);
        this.account = account;
    }

    /**
     *gets the details of the account of the user
     */
    @Override
    public void viewAccountInfo() {
        System.out.println("Account Type: " + account.getAccountType());
        System.out.println("Balance: $" + account.getBalance());
        System.out.println("Loan Interest Rate: " + account.getLoanIntrestRate() + "%");
        System.out.println("Transaction History:");
        account.printTransactionHistory();
    }
    public void viewTransactionsByType(String type) {
        account.printTransactionsByType(type);
    }
    public void deposit(double amount, TransactionType type) {
        account.deposit(amount,type );
    }
    public void withdraw(double amount) {
        account.withdraw(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Costumer costumer = (Costumer) o;
        return Objects.equals(account, costumer.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), account);
    }
}
