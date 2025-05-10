import org.example.AuthenticatedCustomer;
import org.example.CheckingAccount;
import org.example.InMemoryLoginStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestAuthenticatedCustomer {
    @Test
    public void testSuccessfulLogin() {
        InMemoryLoginStore.register("alice", "secure123");
        CheckingAccount account = new CheckingAccount(100.0, 1.5);
        AuthenticatedCustomer customer = new AuthenticatedCustomer("Alice", account);
        boolean result = customer.login("alice", "secure123");
        Assertions.assertTrue(result, "Login should succeed with correct credentials.");
    }

    @Test
    public void testFailedLoginWrongPassword() {
        InMemoryLoginStore.register("alice", "secure123");
        CheckingAccount account = new CheckingAccount(100.0, 1.5);
        AuthenticatedCustomer customer = new AuthenticatedCustomer("Alice", account);
        boolean result = customer.login("alice", "wrongpass");
        Assertions.assertFalse(result, "Login should fail with incorrect password.");
    }

    @Test
    public void testFailedLoginUnknownUser() {
        CheckingAccount account = new CheckingAccount(100.0, 1.5);
        AuthenticatedCustomer customer = new AuthenticatedCustomer("Unknown", account);
        boolean result = customer.login("unknown", "nopass");
        Assertions.assertFalse(result, "Login should fail for unknown username.");
    }

    @Test
    public void testLogout() {
        InMemoryLoginStore.register("alice", "secure123");
        CheckingAccount account = new CheckingAccount(100.0, 1.5);
        AuthenticatedCustomer customer = new AuthenticatedCustomer("Alice", account);
        customer.login("alice", "secure123");
        customer.logout();
        Assertions.assertTrue(true, "Logout completes without exception.");
    }
}
