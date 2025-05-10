package org.example;

public class AuthenticadedManager extends Manager implements LoginSystem {
    private boolean loggedIn = false;

    public AuthenticadedManager(String name) {
        super(name);
    }

    @Override
    public boolean login(String username, String password) {
        if (InMemoryLoginStore.validate(username, password)) {
            loggedIn = true;
            System.out.println("Manager " + getName() + " logged in successfully.");
            return true;
        } else {
            System.out.println("Login failed for manager.");
            return false;
        }
    }

    @Override
    public void logout() {
        loggedIn = false;
        System.out.println("Manager " + getName() + " logged out.");
    }
}
