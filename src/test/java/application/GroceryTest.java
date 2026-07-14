package application;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GroceryTest {
    @Test
    void test_sameNameAndPrice_areEqual() {
        Grocery grocery = new Grocery(1.20, "Milk");
        Grocery grocery2 = new Grocery(1.20, "Milk");
        assertEquals(grocery, grocery2);
        assertTrue(grocery.equals(grocery2));
        assertEquals(grocery.hashCode(), grocery2.hashCode());
    }
    @Test
    void test_sameNameAndDifferentPrice_areNotEqual() {
        Grocery grocery = new Grocery(1.20, "Milk");
        Grocery grocery2 = new Grocery(1.30, "Milk");
        assertNotEquals(grocery, grocery2);
        assertFalse(grocery.equals(grocery2));
        assertNotEquals(grocery.hashCode(), grocery2.hashCode());
    }
    @Test
    void test_differentName_areNotEqual() {
        Grocery grocery = new Grocery(1.20, "Milk");
        Grocery grocery2 = new Grocery(1.20, "Eggs");
        assertNotEquals(grocery, grocery2);
        assertFalse(grocery.equals(grocery2));
    }
    @Test
    void test_sameNameDifferentCase(){
        Grocery grocery = new Grocery(1.20, "Eggs");
        Grocery grocery2 = new Grocery(1.20, "eggs");
        assertTrue(grocery.equals(grocery2));
    }
}
