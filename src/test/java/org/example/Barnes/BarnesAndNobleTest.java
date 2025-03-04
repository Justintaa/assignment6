package org.example.Barnes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BarnesAndNobleTest {

    private BarnesAndNoble barnesAndNoble;
    private BookDatabase bookDatabase;
    private BuyBookProcess buyBookProcess;

    @BeforeEach
    void setUp() {
        bookDatabase = mock(BookDatabase.class);
        buyBookProcess = mock(BuyBookProcess.class);
        barnesAndNoble = new BarnesAndNoble(bookDatabase, buyBookProcess);
    }

    @Test
    @DisplayName("specification-based")
    void testGetPriceForCart_NullOrder() {
        assertNull(barnesAndNoble.getPriceForCart(null));
    }

    @Test
    @DisplayName("specification-based")
    void testGetPriceForCart_EmptyOrder() {
        PurchaseSummary summary = barnesAndNoble.getPriceForCart(Collections.emptyMap());
        assertEquals(0, summary.getTotalPrice());
    }

    @Test
    @DisplayName("specification-based")
    void testGetPriceForCart_AvailableBooks() {
        Book book = new Book("123456", 20, 5); // Price is now int
        when(bookDatabase.findByISBN("123456")).thenReturn(book);

        Map<String, Integer> order = new HashMap<>();
        order.put("123456", 2);

        PurchaseSummary summary = barnesAndNoble.getPriceForCart(order);

        assertEquals(40, summary.getTotalPrice()); // Updated for int price
        verify(buyBookProcess).buyBook(book, 2);
    }

    @Test
    @DisplayName("specification-based")
    void testGetPriceForCart_PartialAvailability() {
        Book book = new Book("789101", 15, 1);
        when(bookDatabase.findByISBN("789101")).thenReturn(book);

        Map<String, Integer> order = new HashMap<>();
        order.put("789101", 3);

        PurchaseSummary summary = barnesAndNoble.getPriceForCart(order);

        assertEquals(15, summary.getTotalPrice()); // Updated for int price
        verify(buyBookProcess).buyBook(book, 1);
    }

    @Test
    @DisplayName("structural-based")
    void testRetrieveBook_BookNotFound() {
        when(bookDatabase.findByISBN("999999")).thenReturn(null);

        Map<String, Integer> order = new HashMap<>();
        order.put("999999", 1);

        assertThrows(NullPointerException.class, () -> barnesAndNoble.getPriceForCart(order));
    }

    @Test
    @DisplayName("structural-based")
    void testRetrieveBook_Interactions() {
        Book book = new Book("111111", 10, 5);
        when(bookDatabase.findByISBN("111111")).thenReturn(book);

        Map<String, Integer> order = new HashMap<>();
        order.put("111111", 3);

        barnesAndNoble.getPriceForCart(order);

        verify(bookDatabase).findByISBN("111111");
        verify(buyBookProcess).buyBook(book, 3);
    }
}
