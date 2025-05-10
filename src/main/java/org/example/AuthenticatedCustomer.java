package org.example;

public class AuthenticatedCustomer extends Costumer implements LoginSystem{
    private boolean loggedIn = false;

    public AuthenticatedCustomer(String name, Account account) {
        super(name, account);
    }


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

    @Override
    public void logout() {
        loggedIn = false;
        System.out.println("Customer " + getName() + " logged out.");
    }
}
