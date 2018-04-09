import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShoppingBasketTest {
    private ShoppingBasket shoppingBasket;
    private Item apple;
    private Item macBook;

    @Before
    public void before() {
        Customer customer = new Customer("Fred", true);

        this.apple = new Item("Apple", 50);
        this.macBook = new Item("MacBook Pro", 150000);

        Discount discount = new Discount();
        discount.setMoneyOffOverAmount(2000);
        discount.setPercentageOffWhenOverAmount(10);
        discount.setPercentageOffForMembership(2);
        discount.addItemToBOGOFList(apple);

        this.shoppingBasket = new ShoppingBasket(discount, customer);
    }

    @Test
    public void basketStartsEmpty() {
        assertEquals(0, shoppingBasket.getNumberOfUniqueItems());
    }

    @Test
    public void canAddToBasketOneItem() {
        shoppingBasket.addItem(apple);
        assertEquals(1, shoppingBasket.getNumberOfUniqueItems());
    }

    @Test
    public void canAddToBasketTwoDifferentItems() {
        shoppingBasket.addItem(apple);
        shoppingBasket.addItem(macBook);
        assertEquals(2, shoppingBasket.getNumberOfUniqueItems());
    }

    @Test
    public void canAddToBasketTwoOfTheSameItem() {
        shoppingBasket.addItem(apple);
        shoppingBasket.addItem(apple);
        assertEquals(1, shoppingBasket.getNumberOfUniqueItems());
        assertEquals(2, shoppingBasket.getNumberOfCertainItem(apple));
    }

    @Test
    public void canRemoveItemFromBasketOneUniqueItem() {
        shoppingBasket.addItem(apple);
        assertEquals(1, shoppingBasket.getNumberOfUniqueItems());
        shoppingBasket.removeItem(apple);
        assertEquals(0, shoppingBasket.getNumberOfUniqueItems());
    }

    @Test
    public void canRemoveItemFromBasketMultipleOfTheSameItem() {
        for (int i = 0; i < 5; i++) {
            shoppingBasket.addItem(apple);
        }
        assertEquals(5, shoppingBasket.getNumberOfCertainItem(apple));
        shoppingBasket.removeItem(apple);
        assertEquals(4, shoppingBasket.getNumberOfCertainItem(apple));
    }

    @Test
    public void canEmptyBasket() {
        for (int i = 0; i < 5; i++) {
            shoppingBasket.addItem(apple);
            shoppingBasket.addItem(macBook);
        }
        assertEquals(2, shoppingBasket.getNumberOfUniqueItems());
        shoppingBasket.emptyBasket();
        assertEquals(0, shoppingBasket.getNumberOfUniqueItems());
    }

    @Test
    public void checkTotalOfBasket() {

        for (int i = 0; i < 5; i++) {
            shoppingBasket.addItem(apple);
            shoppingBasket.addItem(macBook);
        }

        String result = shoppingBasket.totalOfBasket();
        assertEquals("£6616.32", result);
    }
}
