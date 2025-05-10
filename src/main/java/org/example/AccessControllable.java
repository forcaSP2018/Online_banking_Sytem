package org.example;

import java.util.List;

public interface AccessControllable {
    void retrieveLogs();
    boolean hasAccess(String operation);
}
