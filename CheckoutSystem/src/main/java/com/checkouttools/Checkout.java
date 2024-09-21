package com.checkouttools;

import java.util.HashMap;
import java.util.Map;

public class Checkout {
  private ShopPricing pricingRule;
  private Map<String, Integer> items;

  public Checkout(ShopPricing pricingRule) {
    this.pricingRule = pricingRule;
    this.items = new HashMap<>();
  }

  public void scan(String sku) {
    items.put(sku, items.getOrDefault(sku, 0) + 1);
    System.out.println("Scanned item: " + sku);
    System.out.println("Running total: " + calculateTotal() + " pence");
  }

  public int calculateTotal() {
    int total = 0;

    for (Map.Entry<String, Integer> entry : items.entrySet()) {
      String sku = entry.getKey();
      int quantity = entry.getValue();
      int unitPrice = pricingRule.getUnitPrice(sku);

      SpecialPrice specialPrice = pricingRule.getSpecialPrice(sku);
      if (specialPrice != null && quantity >= specialPrice.quantity) {
        int specialBundles = quantity / specialPrice.quantity;
        int remainder = quantity % specialPrice.quantity;
        total += specialBundles * specialPrice.price + remainder * unitPrice;
      } else {
        total += quantity * unitPrice;
      }
    }
    return total;
  }

  public int finalizeTotal() {
    int total = calculateTotal();
    System.out.println("Final total: " + total + " pence");
    return total;
  }
}
