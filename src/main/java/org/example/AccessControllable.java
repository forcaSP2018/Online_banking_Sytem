package org.example;

public interface AccessControllable {
    void retrieveLogs();
    boolean hasAccess(String operation);
}
