package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserLoader {
    public static ArrayList<User> readUserData(String path) {
        File file = new File(path);
        ArrayList<User> users = new ArrayList<>();

        try (Scanner input = new Scanner(file)) {
            input.nextLine(); // Skip header line, if there is one

            while (input.hasNextLine()) {
                String row = input.nextLine();
                String[] data = row.split(",");  // expected format: Customer,Alice,1500.0,2.5

                if (data.length < 2) {
                    System.out.println("Invalid data row: " + row);
                    continue;
                }

                String role = data[0].trim();
                String name = data[1].trim();

                User user = null;

                if (role.equalsIgnoreCase("Customer")) {
                    double balance = (data.length > 2) ? parseDoubleSafe(data[2]) : 0.0;
                    double interest = (data.length > 3) ? parseDoubleSafe(data[3]) : 0.0;
                    Account account = new CheckingAccount(balance, interest); // Can be logic-extended
                    user = new Costumer(name, account);
                } else if (role.equalsIgnoreCase("Manager")) {
                    user = new Manager(name);
                } else {
                    System.out.println("Unknown role in row: " + row);
                    continue;
                }

                users.add(user);
            }
        } catch (IOException e) {
            System.out.printf("File %s does not exist or cannot be read.%n", path);
        }

        return users;
    }

    private static double parseDoubleSafe(String value) {
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
