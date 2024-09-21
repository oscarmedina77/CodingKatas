package com.checkouttools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ShopPricingTest {

  @Test
  void testUnitPrice() {
    ShopPricing prices = new ShopPricing();
    prices.addUnitPrice("A", 50);
    prices.addUnitPrice("B", 30);

    assertEquals(50, prices.getUnitPrice("A"));
    assertEquals(30, prices.getUnitPrice("B"));
    assertEquals(0, prices.getUnitPrice("C"));
  }

  @Test
  void testSpecialPrice() {
    ShopPricing pricingRule = new ShopPricing();
    pricingRule.addSpecialPrice("A", 3, 130);

    SpecialPrice specialPrice = pricingRule.getSpecialPrice("A");
    assertNotNull(specialPrice);
    assertEquals(3, specialPrice.quantity);
    assertEquals(130, specialPrice.price);
  }
}
