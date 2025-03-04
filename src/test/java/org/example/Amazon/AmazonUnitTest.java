package org.example.Amazon;

import org.example.Amazon.Cost.PriceRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AmazonUnitTest {

    private Amazon amazon;
    private ShoppingCart mockCart;
    private PriceRule mockRule;

    @BeforeEach
    void setUp() {
        mockCart = mock(ShoppingCart.class); // Mock ShoppingCart
        mockRule = mock(PriceRule.class);    // Mock PriceRule
        amazon = new Amazon(mockCart, List.of(mockRule));
    }

    @Test
    @DisplayName("specification-based: calculate() should return 0 when cart is empty")
    void testCalculateEmptyCart() {
        when(mockCart.getItems()).thenReturn(Collections.emptyList());
        when(mockRule.priceToAggregate(anyList())).thenReturn(0.0);

        double result = amazon.calculate();

        assertEquals(0.0, result);
        verify(mockRule, times(1)).priceToAggregate(anyList());
    }

    @Test
    @DisplayName("specification-based: calculate() should run price rules correctly")
    void testCalculateWithPriceRules() {
        Item item1 = new Item(null, "Laptop", 1, 1000.0);
        Item item2 = new Item(null, "Mouse", 2, 50.0);

        when(mockCart.getItems()).thenReturn(Arrays.asList(item1, item2));
        when(mockRule.priceToAggregate(anyList())).thenReturn(1100.0);

        double result = amazon.calculate();

        assertEquals(1100.0, result);
        verify(mockRule, times(1)).priceToAggregate(anyList());
    }

    @Test
    @DisplayName("specification-based: addToCart() Should add item to cart")
    void testAddToCart() {
        Item mockItem = mock(Item.class);
        amazon.addToCart(mockItem);

        verify(mockCart, times(1)).add(mockItem);
    }
}
