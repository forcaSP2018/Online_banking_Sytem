package org.example;

public class AuthenticadedManager extends Manager implements LoginSystem {
    private boolean loggedIn = false;

    public AuthenticadedManager(String name) {
        super(name);
    }

    /**
     *Attempts to authenticate the manager using the provided username and password.
     *If authentication is successful, the manager is marked as logged in.
     * @param username the login username to authenticate.
     * @param password the login password to authenticate.
     * @return true if the login is successful; false otherwise.
     */
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

    /**
     *Logs out the currently authenticated manager and updates login state.
     */
    @Override
    public void logout() {
        loggedIn = false;
        System.out.println("Manager " + getName() + " logged out.");
    }
}
