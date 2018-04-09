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

         discount = new Discount();

        shoppingBasket = new ShoppingBasket(discount, customer1);
    }

    @Test
    public void totalIsZeroWhenShoppingBasketIsEmpty() {
        int total = Total.total(shoppingBasket);
        assertEquals(0, total);
    }

    @Test
    public void getTotalWithOneItemNoDiscounts() {
        shoppingBasket.addItem(item1);
        int total = Total.total(shoppingBasket);
        assertEquals(100, total);
    }

    @Test
    public void getTotalWithBOGOFDiscountOnOneItem() {
        for (int i = 0; i < 5; i++) {
            shoppingBasket.addItem(item1);
        }
        discount.addItemToBOGOFList(item1);
        int total = Total.total(shoppingBasket);
        assertEquals(300, total);
    }

    @Test
    public void getTotalNoBOGOFOver20PoundsDiscount() {
        for (int i = 0; i < 5; i++) {
            shoppingBasket.addItem(item2);
        }
        discount.setPercentageOffWhenOverAmount(10);
        discount.setMoneyOffOverAmount(2000);
        int total = Total.total(shoppingBasket);
        assertEquals(2025, total);
    }

    @Test
    public void getTotalOnlyMembershipDiscount() {
        discount.setPercentageOffForMembership(2);
        ShoppingBasket shoppingBasket = new ShoppingBasket(discount, customer2);

        for (int i = 0; i < 5; i++) {
            shoppingBasket.addItem(item1);
        }

        int total = Total.total(shoppingBasket);
        assertEquals(490, total);
    }

    @Test
    public void getTotalWithAllDiscounts() {
        discount.addItemToBOGOFList(item1);
        discount.setMoneyOffOverAmount(2000);
        discount.setPercentageOffWhenOverAmount(10);
        discount.setPercentageOffForMembership(2);

        ShoppingBasket shoppingBasket = new ShoppingBasket(discount, customer2);

        for (int i = 0; i < 5; i++) {
            shoppingBasket.addItem(item1);
            shoppingBasket.addItem(item2);
        }
        int total = Total.total(shoppingBasket);
        assertEquals(2249, total);
    }

    @Test
    public void canCreatePrettyTotalNoLeadingZeros() {
        String result = Total.prettyTotal(2784);
        assertEquals("£27.84", result);
    }

    @Test
    public void canCreatePrettyTotalLeadingZeros() {
        String result = Total.prettyTotal(3001);
        assertEquals("£30.01", result);
    }
}
