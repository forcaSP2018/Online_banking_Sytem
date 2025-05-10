package org.example;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class InMemoryLoginStore {
    private static final Map<String, String> credentials = new HashMap<>();

    public static boolean register(String username, String password) {
        String key = username.toLowerCase();
        if (credentials.containsKey(key)) {
            System.out.println("username already exists");
            return false;
        }
        else {
            credentials.put(key,password);
            System.out.println("user registered successfully");
            return true;
        }
    }

    public static boolean validate(String username, String password) {
        String key = username.toLowerCase();
        return credentials.containsKey(key) && credentials.get(key).equals(password);
    }
}
