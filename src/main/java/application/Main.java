package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        File dataFile = new File("data/shopping-list.txt");
        DataStore dataStore = new DataStore(dataFile);

        Application app;
        if (dataFile.exists()) {
            app = dataStore.load(0);
            System.out.println("Loaded saved shopping list. Limit: " + app.getLimit());
        } else {
            System.out.print("Set a spending limit: ");
            double limit = Double.parseDouble(scanner.nextLine());
            app = dataStore.load(limit);
        }

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    app.viewList();
                    break;
                case "2": {
                    System.out.print("Item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Item price: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    boolean added = app.addToList(new Grocery(price, name));
                    if (added) {
                        System.out.println("Added.");
                    }
                    dataStore.save(app);
                    break;
                }
                case "3": {
                    Grocery item = pickItem(scanner, app);
                    if (item != null) {
                        app.removeFromList(item);
                        dataStore.save(app);
                    }
                    break;
                }
                case "4": {
                    Grocery item = pickItem(scanner, app);
                    if (item != null) {
                        app.crossOffItem(item);
                        dataStore.save(app);
                    }
                    break;
                }
                case "5": {
                    Grocery item = pickItem(scanner, app);
                    if (item != null) {
                        System.out.print("New position (0-based): ");
                        int position = Integer.parseInt(scanner.nextLine());
                        app.reorderItems(item, position);
                        dataStore.save(app);
                    }
                    break;
                }
                case "6":
                    System.out.println("Total: " + app.getCurrent() + " / Limit: " + app.getLimit());
                    if (app.isOverLimit()) {
                        System.out.println("You are over your spending limit!");
                    }
                    break;
                case "7":
                    new Email().send(app);
                    break;
                case "8":
                    running = false;
                    break;
                default:
                    System.out.println("Unknown option.");
            }
        }
        scanner.close();
    }

    private static Grocery pickItem(Scanner scanner, Application app) {
        ArrayList<Grocery> items = app.getGroceryList();
        if (items.isEmpty()) {
            System.out.println("List is empty.");
            return null;
        }
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + ") " + items.get(i).getName() + " - " + items.get(i).getPrice());
        }
        System.out.print("Pick item number: ");
        int index = Integer.parseInt(scanner.nextLine());
        if (index < 0 || index >= items.size()) {
            System.out.println("Invalid selection.");
            return null;
        }
        return items.get(index);
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1) View list");
        System.out.println("2) Add item");
        System.out.println("3) Remove item");
        System.out.println("4) Cross off item");
        System.out.println("5) Reorder item");
        System.out.println("6) View total / limit status");
        System.out.println("7) Share list via email");
        System.out.println("8) Quit");
        System.out.print("> ");
    }
}
