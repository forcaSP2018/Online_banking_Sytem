import org.example.CheckingAccount;
import org.example.Costumer;
import org.example.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

public class TestCustomer {
    @Test
    public void testViewAccountInfoOutput() {

        CheckingAccount account = new CheckingAccount(200.0, 2.0);
        account.deposit(50.0, TransactionType.SALARY);
        Costumer customer = new Costumer("Alice", account);
        Assertions.assertEquals("Checkings".toLowerCase(), account.getAccountType());
       Assertions. assertEquals(250.0, account.getBalance(), 0.01);
        Assertions.assertEquals(2.0, account.getLoanIntrestRate(), 0.01);
        Assertions.assertFalse(account.getTransactions().isEmpty(), "Transaction history should not be empty after deposit.");
    }
}
