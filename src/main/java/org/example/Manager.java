package org.example;

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
            // TODO: Implement: mark account for review or to disable it
        }
    }

    @Override
    public void retrieveLogs() {
        // TODO: Retrieve and format activity logs (e.g., transactions, actions)
    }

    @Override
    public boolean hasAccess(String operation) {
        // TODO: Define access control logic by operation type (view, audit, restrict)
        return false;
    }

}
