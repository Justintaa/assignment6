package org.example.Amazon;

import org.example.Amazon.Cost.ItemType;
import org.example.Amazon.Cost.PriceRule;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AmazonIntegrationTest {

    private Amazon amazon;
    private ShoppingCartAdaptor cart;
    private Database database;

    @BeforeEach
    void setUp() {
        database = new Database();
        database.resetDatabase(); // Reset DB before each test

        cart = new ShoppingCartAdaptor(database); // Use ShoppingCartAdaptor
        List<PriceRule> rules = new ArrayList<>(); // No price rules for now
        amazon = new Amazon(cart, rules);
    }

    @Test
    @DisplayName("integration-based: calculate() with empty cart should return 0")
    void testCalculateEmptyCart() {
        assertEquals(0.0, amazon.calculate());
    }

    @Test
    @DisplayName("integration-based: adding item should reflect in cart")
    void testAddToCart() {
        Item item = new Item(ItemType.ELECTRONIC, "Laptop", 1, 1000.0);
        amazon.addToCart(item);

        assertEquals(1, cart.getItems().size());
    }


    @AfterEach
    void tearDown() {
        database.close();
    }
}
