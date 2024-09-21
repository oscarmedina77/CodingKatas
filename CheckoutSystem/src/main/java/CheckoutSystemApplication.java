import com.checkouttools.Checkout;
import com.checkouttools.ShopPricing;
import java.util.Scanner;

public class CheckoutSystemApplication {

  public static void main(String[] args) {

    ShopPricing pricingRule = new ShopPricing();
    pricingRule.addUnitPrice("A", 50);
    pricingRule.addSpecialPrice("A", 3, 130);
    pricingRule.addUnitPrice("B", 30);
    pricingRule.addSpecialPrice("B", 2, 45);
    pricingRule.addUnitPrice("C", 20);
    pricingRule.addUnitPrice("D", 15);

    Checkout checkout = new Checkout(pricingRule);

    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter item SKU one by one for running total, or type 'total' to get final total:");
    while (scanner.hasNext()) {
      String input = scanner.next();
      if (input.equalsIgnoreCase("total")) {
        checkout.finalizeTotal();
        break;
      } else {
        checkout.scan(input);
      }
    }

    scanner.close();

  }
}
