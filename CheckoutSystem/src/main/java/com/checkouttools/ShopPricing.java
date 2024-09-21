package com.checkouttools;

import java.util.HashMap;
import java.util.Map;

public class ShopPricing {
  private Map<String, Integer> unitPrices;
  private Map<String, SpecialPrice> specialPrices;

  public ShopPricing() {
    unitPrices = new HashMap<>();
    specialPrices = new HashMap<>();
  }

  public void addUnitPrice(String sku, int price) {
    unitPrices.put(sku, price);
  }

  public void addSpecialPrice(String sku, int quantity ,int specialPrice) {
    specialPrices.put(sku, new SpecialPrice(quantity, specialPrice));
  }

  public int getUnitPrice(String sku) {
    return unitPrices.getOrDefault(sku, 0);
  }

  public SpecialPrice getSpecialPrice(String sku) {
    return specialPrices.get(sku);
  }
}
