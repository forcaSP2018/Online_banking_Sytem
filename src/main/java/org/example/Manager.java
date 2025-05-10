package org.example;

import java.util.List;

public class Manager extends User implements AccessControllable{
    public Manager(String name) {
        super(name);
    }

    @Override
    public void viewAccountInfo() {
        System.out.println("Manager viewing logs...");
        retrieveLogs();
    }

    /**
     * this allows manager to audit the account and determine if they should take into review or disable it all together
     * @param account looks at the users account
     */
    public void auditAccount(Account account) {
        System.out.println("Auditing Account: " + account.getAccountType());
        account.printTransactionHistory();
        if (account.getBalance() == 0) {
            System.out.println("Warning: User has zero balance — consider revoking access.");
            account.flagForReview();
        }
    }

    /**
     * Closes the specified account if it meets two conditions:
     *1. The account balance is zero.
     *2. The account has been flagged for review.
     *If either condition is not met, the account remains open.
     * @param account  account the account to evaluate and close if eligible.
     */
    public void closeAccountIfEligible(Account account) {
        if (account.getBalance() == 0 && account.isFlaggedForReview()) {
            account.closeAccount();
        } else {
            System.out.println("Account cannot be closed: Either not flagged or balance is not zero.");
        }
    }

    /**
     *Default retrieval method to comply with the AccessControllable interface.
     *This method does not operate on any accounts directly. It simply informs
     *the user to use the overloaded version with a list of accounts.
     */
    public void retrieveLogs(){
        System.out.println("No default account list provided. Use retrieveLogs(List<Account>) to audit specific accounts.");
    }

    /**
     *Retrieves and prints transaction logs for each account provided in the list.
     *This method is used by managers to audit customer account activity.
     * @param accounts accounts the list of customer accounts to retrieve logs from.
     */
    public void retrieveLogs(List<Account> accounts) {
        System.out.println("=== Retrieving All Account Logs ===");
        for (Account account : accounts) {
            System.out.println("Logs for account: " + account.getAccountType());
            account.printTransactionHistory();
        }
    }

    /**
     * Evaluates whether the manager has permission to perform a given operation.
     * Recognized operations include: "viewlogs", "audit", "closeaccount", "retrieve", and "flag".
     * @param operation operation the name of the operation to check.
     * @return true if the operation is permitted; false otherwise.
     */
    @Override
    public boolean hasAccess(String operation) {
        String op = operation.toLowerCase();
        return op.equals("viewlogs") ||
                op.equals("audit") ||
                op.equals("closeaccount") ||
                op.equals("retrieve") ||
                op.equals("flag");
    }

}
