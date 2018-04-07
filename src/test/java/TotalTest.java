import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TotalTest {
    private ShoppingBasket shoppingBasket;
    private Customer customer1;
    private Customer customer2;
    private Discount discount;
    private Item item1;
    private Item item2;

    @Before
    public void before() {
        customer1 = new Customer("Alan", false);
        customer2 = new Customer("Phil", true);

         item1 = new Item("pear", 100);
         item2 = new Item("Easter Egg", 450);

        shoppingBasket = new ShoppingBasket();

        discount = new Discount();
    }

    @Test
    public void totalIsZeroWhenShoppingBasketIsEmpty() {
        int total = Total.total(shoppingBasket,discount, customer1);
        assertEquals(0, total);
    }

    @Test
    public void getTotalWithOneItemNoDiscounts() {
        shoppingBasket.addItem(item1);
        int total = Total.total(shoppingBasket, discount, customer1);
        assertEquals(100, total);
    }

    @Test
    public void getTotalWithBOGOFDiscountOnOneItem() {
        for (int i = 0; i < 5; i++) {
            shoppingBasket.addItem(item1);
        }
        discount.addItemToBOGOFList(item1);
        int total = Total.total(shoppingBasket,discount, customer1);
        assertEquals(300, total);
    }

    @Test
    public void getTotalNoBOGOFOver20PoundsDiscount() {
        for (int i = 0; i < 5; i++) {
            shoppingBasket.addItem(item2);
        }
        discount.setPercentageOffWhenOverAmount(10);
        discount.setMoneyOffOverAmount(2000);
        int total = Total.total(shoppingBasket, discount, customer1);
        assertEquals(2025, total);
    }

    @Test
    public void getTotalOnlyMembershipDiscount() {
        for (int i = 0; i < 5; i++) {
            shoppingBasket.addItem(item1);
        }
        discount.setPercentageOffForMembership(2);
        int total = Total.total(shoppingBasket, discount, customer2);
        assertEquals(490, total);
    }

    @Test
    public void getTotalWithAllDiscounts() {
        for (int i = 0; i < 5; i++) {
            shoppingBasket.addItem(item1);
            shoppingBasket.addItem(item2);
        }
        discount.addItemToBOGOFList(item1);
        discount.setMoneyOffOverAmount(2000);
        discount.setPercentageOffWhenOverAmount(10);
        discount.setPercentageOffForMembership(2);
        int total = Total.total(shoppingBasket, discount, customer2);
        assertEquals(2249, total);
    }

}
