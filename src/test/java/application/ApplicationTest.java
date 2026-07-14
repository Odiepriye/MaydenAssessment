package application;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ApplicationTest {
    
    @Test
    void test_isAddedToList() {
        Application app = new Application(150.0);
        Grocery grocery = new Grocery(1.20, "Milk");
        assertTrue(app.addToList(grocery));
        assertEquals(1, app.getGroceryList().size());
    }
    @Test
    void test_isRemovedFromList() {
        Application app = new Application(150.0);
        Grocery grocery = new Grocery(1.20, "Milk");
        Grocery grocery2 = new Grocery(1.30, "Milk");
        Grocery grocery3 = new Grocery(1.40, "Milk");
        app.addToList(grocery);
        app.addToList(grocery2);
        app.addToList(grocery3);
        app.removeFromList(grocery);
        assertTrue(!app.getGroceryList().contains(grocery));
        assertEquals(2, app.getGroceryList().size());
    }
    @Test
    void test_isCrossedOff() {
        Application app = new Application(150.0);
        Grocery grocery = new Grocery(1.20, "Milk");
        app.addToList(grocery);
        app.crossOffItem(grocery);
        assertTrue(app.getCrossedList().contains(grocery));
        assertEquals(1, app.getGroceryList().size());
        assertEquals(1, app.getCrossedList().size());
    }
    @Test
    void test_isReordered() {
        Application app = new Application(150.0);
        Grocery grocery = new Grocery(1.20, "Milk");
        Grocery grocery2 = new Grocery(1.30, "Milk");
        Grocery grocery3 = new Grocery(1.40, "Milk");
        app.addToList(grocery);
        app.addToList(grocery2);
        app.addToList(grocery3);
        app.reorderItems(grocery3, 0);
        app.reorderItems(grocery2, 1);
        assertTrue(app.getGroceryList().get(0).equals(grocery3));
        assertTrue(app.getGroceryList().get(1).equals(grocery2));
    }
    @Test
    void test_checkCurrentTotal() {
        Application app = new Application(150.0);
        Grocery grocery = new Grocery(1.20, "Milk");
        Grocery grocery2 = new Grocery(1.30, "Eggs");
        app.addToList(grocery);
        app.addToList(grocery2);
        assertEquals(2.50, app.getCurrent());
    }
    @Test
    void test_isOverLimit() {
        Application app = new Application(150.0);
        Grocery grocery = new Grocery(100.0, "Milk");
        Grocery grocery2 = new Grocery(60.0, "Eggs");
        app.addToList(grocery);
        assertFalse(app.isOverLimit());
        app.addToList(grocery2);
        assertTrue(app.isOverLimit());
    }
    @Test
    void test_duplicateItemIsRejected() {
        Application app = new Application(150.0);
        Grocery grocery = new Grocery(1.20, "Milk");
        Grocery duplicate = new Grocery(1.20, "Milk");
        app.addToList(grocery);
        assertFalse(app.addToList(duplicate));
        assertEquals(1, app.getGroceryList().size());
        assertEquals(1.20, app.getCurrent());
    }

    @Test
    void test_removingCrossedItemClearsCrossedStatus() {
        Application app = new Application(150.0);
        Grocery grocery = new Grocery(1.20, "Milk");
        app.addToList(grocery);
        app.crossOffItem(grocery);
        app.removeFromList(grocery);
        assertFalse(app.getCrossedList().contains(grocery));
        assertEquals(0, app.getGroceryList().size());
    }

    @Test
    void test_removingItemNotInListIsNoOp() {
        Application app = new Application(150.0);
        Grocery grocery = new Grocery(1.20, "Milk");
        app.addToList(grocery);
        Grocery notOnList = new Grocery(2.00, "Bread");
        app.removeFromList(notOnList);
        assertEquals(1, app.getGroceryList().size());
        assertEquals(1.20, app.getCurrent());
    }

}
