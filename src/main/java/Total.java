import java.util.Map;

public class Total {
    private Total() {}

    public static int total(ShoppingBasket shoppingBasket, Discount discount, Customer customer) {
        int total = 0;
        for (Map.Entry<Item, Integer> entry : shoppingBasket.getItems().entrySet()) {
            if (discount.isItemBOGOF(entry.getKey())) {
                if ((entry.getValue() % 2) == 0) {
                    int itemTotal = entry.getKey().getPrice() * (entry.getValue() / 2);
                    total += itemTotal;
                } else {
                    int itemTotal =entry.getKey().getPrice() * (entry.getValue() / 2 + 1);
                    total += itemTotal;
                }
            } else {
                int itemTotal = entry.getKey().getPrice() * entry.getValue();
                total += itemTotal;
            }
        }
        if (total > discount.getMoneyOffOverAmount()) {
            total = totalAfterDiscount(total, discount.getMoneyOffOverAmountDiscount());
        }
        if (customer.isMember()) {
            total = totalAfterDiscount(total, discount.getMembershipDiscount());
        }
        return total;
    }

    private static int totalAfterDiscount(int total, double discount) {
        double newTotal = total - total * discount;
        return ((int) newTotal);
    }
}


