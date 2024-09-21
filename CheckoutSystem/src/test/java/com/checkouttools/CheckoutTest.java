package com.checkouttools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheckoutTest {
  private Checkout checkout;

  @BeforeEach
  void setup() {

    ShopPricing prices = new ShopPricing();
    prices.addUnitPrice("A", 50);
    prices.addSpecialPrice("A", 3, 130);
    prices.addUnitPrice("B", 30);
    prices.addSpecialPrice("B", 2, 45);
    prices.addUnitPrice("C", 20);
    prices.addUnitPrice("D", 15);

    checkout = new Checkout(prices);
  }

  @Test
  void testScanSingleItem() {
    checkout.scan("A");
    assertEquals(50, checkout.calculateTotal());
  }

  @Test
  void testScanMultipleItemsWithoutSpecialPrice() {
    checkout.scan("A");
    checkout.scan("B");
    checkout.scan("C");

    assertEquals(50 + 30 + 20, checkout.calculateTotal());
  }

  @Test
  void testApplySpecialPriceA() {
    checkout.scan("A");
    checkout.scan("A");
    checkout.scan("A");

    assertEquals(130, checkout.calculateTotal());
  }

  @Test
  void testApplySpecialPriceB() {
    checkout.scan("B");
    checkout.scan("B");

    assertEquals(45, checkout.calculateTotal());
  }

  @Test
  void testMixedItemsAndSpecialPrices() {
    checkout.scan("A");
    checkout.scan("B");
    checkout.scan("A");
    checkout.scan("B");
    checkout.scan("A");

    assertEquals(130 + 45, checkout.calculateTotal());
  }

  @Test
  void testFinalizeTotal() {
    checkout.scan("A");
    checkout.scan("B");
    assertEquals(80, checkout.finalizeTotal());
  }

  @Test
  void testScanWithoutSpecialPrices() {
    checkout.scan("C");
    checkout.scan("D");
    assertEquals(35, checkout.calculateTotal());
  }

  @Test
  void testEmptyCart() {
    assertEquals(0, checkout.calculateTotal());
  }
}
