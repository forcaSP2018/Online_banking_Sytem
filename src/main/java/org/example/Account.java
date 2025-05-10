package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

abstract class Account {
    protected String accountType;
    protected double balance;
    protected double loanIntrestRate;
    protected List<Transaction> transactions = new ArrayList<>();
    protected boolean closed = false;
    protected boolean flaggedForReview = false;


    public Account(String accountType, double balance, double loanIntrestRate) {
        this.accountType = accountType;
        this.balance = balance;
        this.loanIntrestRate = loanIntrestRate;
    }

    /**
     * Flags the account for review, typically invoked by a manager during an audit.
     * Once flagged, the account may be reviewed or closed if conditions are met.
     */
    public void flagForReview() {
        flaggedForReview = true;
        System.out.println("Account has been flagged for review.");
    }

    /**
     *Checks whether the account is currently flagged for review.
     * @return true if the account is flagged for review; false otherwise.
     */
    public boolean isFlaggedForReview() {
        return flaggedForReview;
    }

    /**
     * makes deposits into the balance
     * @param amount the amount of money
     */
    public void deposit(double amount, TransactionType type) {
        if (closed) {
            System.out.println("Cannot deposit: Account is closed.");
            return;
        }
        balance += amount;
        transactions.add(new Transaction(type.name(),amount, LocalDateTime.now()));
    }

    /**
     * allows the customer to withdraw money but cannot withdraw if the amount is more then the balance
     * @param amount the amount that the customer wants to withdraw
     */
    public void withdraw(double amount) {
        if (closed) {
            System.out.println("Cannot withdraw: Account is closed.");
            return;
        }
        if (amount > balance) {
            System.out.println("Withdrawal denied: Insufficient funds.");
            return;
        }
        balance -= amount;
        transactions.add(new Transaction("debit", amount, LocalDateTime.now()));
    }

    /**
     * prints all the transactions done by the user with dates
     */
    public void printTransactionHistory() {
        if (transactions.isEmpty()) {
            System.out.println("there have been no transactions");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }

    /**
     *prints the transaction by the type of the transaction that the user is looking for
     * @param type the type inputted by the user
     */
    public void printTransactionsByType(String type) {
        boolean found = false;
        for (Transaction t : transactions) {
            if (t.getType().equalsIgnoreCase(type)) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No transactions found for type: " + type);
        }
    }

    /**
     *Attempts to close the account. An account may only be closed if its balance is zero.
     *Once closed, no further deposits or withdrawals will be allowed.
     */
    public void closeAccount() {
        if (balance != 0) {
            System.out.println("Cannot close account: Balance is not zero.");
            return;
        }
        closed = true;
        System.out.println("Account has been closed.");
    }

    /**
     *Checks whether the account is currently closed.
     * @return true if the account is closed; false otherwise
     */
    public boolean isClosed() {
        return closed;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(balance, account.balance) == 0 && Double.compare(loanIntrestRate, account.loanIntrestRate) == 0 && Objects.equals(transactions, account.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, loanIntrestRate, transactions);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getLoanIntrestRate() {
        return loanIntrestRate;
    }

    public void setLoanIntrestRate(double loanIntrestRate) {
        this.loanIntrestRate = loanIntrestRate;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
