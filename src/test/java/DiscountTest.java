import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DiscountTest {
    private Discount discount;
    private Item banana;

    @Before
    public void before() {
        this.discount = new Discount();
        this.banana = new Item("banana", 75);
    }

    @Test
    public void noBOGOFDiscountsToStart() {
        assertTrue(discount.getBOGOFItems().isEmpty());
    }

    @Test
    public void canAddItemToBOGOFList() {
        discount.addItemToBOGOFList(banana);
        assertTrue(discount.getBOGOFItems().contains(banana));
    }

    @Test
    public void isItemInBOGOFListIsInList() {
        discount.addItemToBOGOFList(banana);
        assertTrue(discount.isItemBOGOF(banana));
    }

    @Test
    public void isItemInBOGOFListIsNotInList() {
        assertFalse(discount.isItemBOGOF(banana));
    }


    @Test
    public void moneyOffOverAmountStartsAtZero() {
        assertEquals(0, discount.getMoneyOffOverAmount());
    }

    @Test
    public void setMoneyOffOverAmount() {
        discount.setMoneyOffOverAmount(2000);
        assertEquals(2000, discount.getMoneyOffOverAmount());
    }

    @Test
    public void moneyOffOverAmountDiscountStartsAtZero() {
        assertEquals(0.00, discount.getMoneyOffOverAmountDiscount(), 0.00001);
    }

    @Test
    public void membershipDiscountStartsAtZero() {
        assertEquals(0.00, discount.getMembershipDiscount(), 0.001);
    }

    @Test
    public void canSetPercentageOffWhenOverAmount() {
        discount.setPercentageOffWhenOverAmount(10);
        assertEquals(0.1, discount.getMoneyOffOverAmountDiscount(), 0.001);
    }

    @Test
    public void canSetPercentageOffForMembership() {
        discount.setPercentageOffForMembership(2);
        assertEquals(0.02, discount.getMembershipDiscount(), 0.001);
    }

}
