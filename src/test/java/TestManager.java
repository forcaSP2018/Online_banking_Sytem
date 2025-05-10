import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestManager {
    @Test
    public void testAuditCheckingAccountZeroBalance() {
        CheckingAccount checking = new CheckingAccount(0.0, 1.5);
        Manager manager = new Manager("Manager1");
        manager.auditAccount(checking);
        Assertions.assertTrue(checking.isFlaggedForReview());
    }

    @Test
    public void testAuditSavingsAccountPositiveBalance() {
        SavingsAccount savings = new SavingsAccount(150.0, 2.0);
        Manager manager = new Manager("Manager2");
        manager.auditAccount(savings);
        Assertions.assertFalse(savings.isFlaggedForReview());
    }

    @Test
    public void testAuditSavingsAccountZeroBalance() {
        SavingsAccount savings = new SavingsAccount(0.0, 2.0);
        Manager manager = new Manager("Manager3");
        manager.auditAccount(savings);
        Assertions.assertTrue(savings.isFlaggedForReview());
    }

    @Test
    public void testCloseFlaggedCheckingAccount() {
        CheckingAccount account = new CheckingAccount(0.0, 1.5);
        account.flagForReview();
        Manager manager = new Manager("Closer1");
        manager.closeAccountIfEligible(account);
        Assertions.assertTrue(account.isClosed());
    }

    @Test
    public void testCloseSavingsAccountFailsNotFlagged() {
        SavingsAccount account = new SavingsAccount(0.0, 2.0);
        Manager manager = new Manager("Closer2");
        manager.closeAccountIfEligible(account);
        Assertions.assertFalse(account.isClosed());
    }

    @Test
    public void testCloseSavingsAccountFailsNonZeroBalance() {
        SavingsAccount account = new SavingsAccount(50.0, 2.0);
        account.flagForReview();
        Manager manager = new Manager("Closer3");
        manager.closeAccountIfEligible(account);
        Assertions.assertFalse(account.isClosed());
    }

    @Test
    public void testRetrieveLogsMixedAccounts() {
        CheckingAccount c = new CheckingAccount(100.0, 1.5);
        SavingsAccount s = new SavingsAccount(200.0, 2.0);
        c.deposit(25.0, TransactionType.GIFT);
        s.deposit(75.0, TransactionType.SALARY);

        List<Account> accounts = new ArrayList<>();
        Manager manager = new Manager("Logger1");
        manager.retrieveLogs(accounts);

        Assertions.assertEquals(1, c.getTransactions().size());
       Assertions. assertEquals(1, s.getTransactions().size());
    }

    @Test
    public void testRetrieveLogsOnlySavings() {
        Account s1 = new SavingsAccount(300.0, 1.8);
        Account s2 = new SavingsAccount(0.0, 2.1);
        s1.deposit(100.0, TransactionType.E_TRANSFER);

        List<Account> accounts = Arrays.asList(s1, s2);
        Manager manager = new Manager("Logger2");
        manager.retrieveLogs(accounts);

        Assertions.assertEquals(1, s1.getTransactions().size());
       Assertions. assertTrue(s2.getTransactions().isEmpty());
    }

    @Test
    public void testRetrieveLogsOnlyChecking() {
        Account c1 = new CheckingAccount(400.0, 1.0);
        Account c2 = new CheckingAccount(0.0, 0.5);
        c1.deposit(50.0, TransactionType.SALARY);

        List<Account> accounts = Arrays.asList(c1, c2);
        Manager manager = new Manager("Logger3");
        manager.retrieveLogs(accounts);

        Assertions.assertEquals(1, c1.getTransactions().size());
        Assertions.assertTrue(c2.getTransactions().isEmpty());
    }

    @Test
    public void testHasAccessValidOps() {
        Manager manager = new Manager("AccessMan");
        Assertions.assertTrue(manager.hasAccess("viewlogs"));
        Assertions.assertTrue(manager.hasAccess("audit"));
        Assertions.assertTrue(manager.hasAccess("flag"));
    }

    @Test
    public void testHasAccessInvalidOp() {
        Manager manager = new Manager("AccessMan2");
        Assertions.assertFalse(manager.hasAccess("write"));
    }

    @Test
    public void testHasAccessCaseInsensitive() {
        Manager manager = new Manager("AccessMan3");
        Assertions.assertTrue(manager.hasAccess("ClOsEaCcOuNt"));
    }
}
