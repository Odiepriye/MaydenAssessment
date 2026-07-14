package application;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;

public class DataStoreTest {
    @TempDir
    File tempDir;

    @Test
    void test_saveAndLoadData() throws IOException {
        Application app = new Application(150.0);
        Grocery grocery = new Grocery(1.20, "Milk");
        Grocery grocery2 = new Grocery(1.30, "Milk");
        Grocery grocery3 = new Grocery(1.40, "Milk");
        app.addToList(grocery);
        app.addToList(grocery2);
        app.addToList(grocery3);
        DataStore dataStore = new DataStore(new File(tempDir, "test.json"));
        dataStore.save(app);
        Application loadedApp = dataStore.load(160.0);
        assertEquals(app.getLimit(), loadedApp.getLimit());
        assertEquals(app.getCurrent(), loadedApp.getCurrent());
        assertEquals(app.getGroceryList().size(), loadedApp.getGroceryList().size());
        assertEquals(app.getCrossedList().size(), loadedApp.getCrossedList().size());
    }
}
