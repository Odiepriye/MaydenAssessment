package application;

import java.io.*;
import java.util.ArrayList;

// Persists the shopping list as one line per item: name|price|crossed
// First line of the file is the spending limit.
public class DataStore {
    private final File file;

    public DataStore(File file) {
        this.file = file;
    }

    public void save(Application app) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println(app.getLimit());
            ArrayList<Grocery> crossed = new ArrayList<>(app.getCrossedList());
            for (Grocery item : app.getGroceryList()) {
                boolean isCrossed = crossed.contains(item);
                writer.println(item.getName() + "|" + item.getPrice() + "|" + isCrossed);
            }
        }
    }

    public Application load(double defaultLimit) throws IOException {
        if (!file.exists()) {
            return new Application(defaultLimit);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            double limit = Double.parseDouble(reader.readLine());
            Application app = new Application(limit);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                Grocery item = new Grocery(Double.parseDouble(parts[1]), parts[0]);
                app.addToList(item);
                if (Boolean.parseBoolean(parts[2])) {
                    app.crossOffItem(item);
                }
            }
            return app;
        }
    }
}
