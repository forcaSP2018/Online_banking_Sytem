import org.example.AuthenticadedManager;
import org.example.InMemoryLoginStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class testAuthenticadedManager {
    @Test
    public void testSuccessfulLogin() {
        InMemoryLoginStore.register("bob", "adminpass");
        AuthenticadedManager manager = new AuthenticadedManager("Bob");
        boolean result = manager.login("bob", "adminpass");
       Assertions.assertTrue(result, "Login should succeed with correct credentials.");
    }

    @Test
    public void testFailedLoginWrongPassword() {
        InMemoryLoginStore.register("bob", "adminpass");
      AuthenticadedManager manager = new AuthenticadedManager("Bob");
        boolean result = manager.login("bob", "wrongpass");
        Assertions.assertFalse(result, "Login should fail with incorrect password.");
    }

    @Test
    public void testFailedLoginUnknownUser() {
        AuthenticadedManager manager = new AuthenticadedManager("Unknown");
        boolean result = manager.login("unknown", "whatever");
        Assertions.assertFalse(result, "Login should fail for unknown username.");
    }

    @Test
    public void testLogout() {
        InMemoryLoginStore.register("bob", "adminpass");
        AuthenticadedManager manager = new AuthenticadedManager("Bob");
        manager.login("bob", "adminpass");
        manager.logout();
        Assertions.assertTrue(true, "Logout completes without exception.");
    }
}
