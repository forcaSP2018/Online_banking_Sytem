import org.example.CheckingAccount;
import org.example.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class testAccount {
    @Test
    public void testDepositIncreasesBalance() {
        CheckingAccount account = new CheckingAccount(100.0, 1.5);
        account.deposit(50.0, TransactionType.SALARY);
        Assertions.assertEquals(150.0, account.getBalance(), 0.01);
    }

    @Test
    public void testDepositFailsIfAccountClosed() {
        CheckingAccount account = new CheckingAccount(0.0, 1.5);
        account.closeAccount();
        account.deposit(50.0, TransactionType.GIFT);
        Assertions.assertEquals(0.0, account.getBalance(), 0.01); // no change
    }

    @Test
    public void testWithdrawDecreasesBalance() {
        CheckingAccount account = new CheckingAccount(100.0, 1.5);
        account.withdraw(40.0);
        Assertions.assertEquals(60.0, account.getBalance(), 0.01);
    }

    @Test
    public void testWithdrawFailsIfInsufficientFunds() {
        CheckingAccount account = new CheckingAccount(100.0, 1.5);
        account.withdraw(150.0);
       Assertions.assertEquals(100.0, account.getBalance(), 0.01); // no change
    }

    @Test
    public void testWithdrawFailsIfAccountClosed() {
        CheckingAccount account = new CheckingAccount(0.0, 1.5);
        account.closeAccount();
        account.withdraw(10.0);
        Assertions.assertEquals(0.0, account.getBalance(), 0.01);
    }

    @Test
    public void testCloseAccountFailsIfBalanceNonZero() {
        CheckingAccount account = new CheckingAccount(1000.0, 1.5);
        account.closeAccount();
        Assertions.assertFalse(account.isClosed());
    }

    @Test
    public void testCloseAccountSucceedsIfBalanceZero() {
        CheckingAccount account = new CheckingAccount(100.0, 1.5);
        account.withdraw(100.0);
        account.closeAccount();
        Assertions.assertTrue(account.isClosed());
    }
}
