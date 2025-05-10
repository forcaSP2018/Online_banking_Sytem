package org.example;

public class AuthenticatedCustomer extends Costumer implements LoginSystem{
    private boolean loggedIn = false;

    public AuthenticatedCustomer(String name, Account account) {
        super(name, account);
    }

    /**
     *Attempts to authenticate the customer using the provided username and password.
     *If authentication is successful, the customer is marked as logged in
     * @param username the login username to authenticate.
     * @param password the login password to authenticate.
     * @return true if the login is successful and the account is open; false otherwise.
     */
    @Override
    public boolean login(String username, String password) {
        if (InMemoryLoginStore.validate(username, password)) {
            loggedIn = true;
            System.out.println("Customer " + getName() + " logged in successfully.");
            return true;
        } else {
            System.out.println("Login failed for customer.");
            return false;
        }
    }

    /**
     *Logs out the currently authenticated customer and updates login state.
     */
    @Override
    public void logout() {
        loggedIn = false;
        System.out.println("Customer " + getName() + " logged out.");
    }
}
