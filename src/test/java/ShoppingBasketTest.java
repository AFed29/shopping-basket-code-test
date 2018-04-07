import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShoppingBasketTest {
    private ShoppingBasket shoppingBasket;
    private Item apple;
    private Item macBook;

    @Before
    public void before() {
        this.shoppingBasket = new ShoppingBasket();
        this.apple = new Item("Apple", 50);
        this.macBook = new Item("MacBook Pro", 150000);
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
        Discount discount = new Discount();
        discount.setMoneyOffOverAmount(2000);
        discount.setPercentageOffWhenOverAmount(10);
        discount.setPercentageOffForMembership(2);
        discount.addItemToBOGOFList(apple);

        Customer customer = new Customer("Fred", true);

        for (int i = 0; i < 5; i++) {
            shoppingBasket.addItem(apple);
            shoppingBasket.addItem(macBook);
        }

        int total = shoppingBasket.totalOfBasket(discount, customer);
        assertEquals(661632, total);
    }
}
