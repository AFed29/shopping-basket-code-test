import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShoppingBasketTest {
    ShoppingBasket shoppingBasket;
    Item item1;
    Item item2;

    @Before
    public void before() {
        this.shoppingBasket = new ShoppingBasket();
        this.item1 = new Item("Apple", 50);
        this.item2 = new Item("MacBook Pro", 150000);
    }

    @Test
    public void basketStartsEmpty() {
        assertEquals(0, shoppingBasket.getNumberOfUniqueItems());
    }

    @Test
    public void canAddToBasketOneItem() {
        shoppingBasket.addItem(item1);
        assertEquals(1, shoppingBasket.getNumberOfUniqueItems());
    }

    @Test
    public void canAddToBasketTwoDifferentItems() {
        shoppingBasket.addItem(item1);
        shoppingBasket.addItem(item2);
        assertEquals(2, shoppingBasket.getNumberOfUniqueItems());
    }

    @Test
    public void canAddToBasketTwoOfTheSameItem() {
        shoppingBasket.addItem(item1);
        shoppingBasket.addItem(item1);
        assertEquals(1, shoppingBasket.getNumberOfUniqueItems());
        assertEquals(2, shoppingBasket.getNumberOfCertainItem(item1));
    }

    @Test
    public void canRemoveItemFromBasketOneUniqueItem() {
        shoppingBasket.addItem(item1);
        assertEquals(1, shoppingBasket.getNumberOfUniqueItems());
        shoppingBasket.removeItem(item1);
        assertEquals(0, shoppingBasket.getNumberOfUniqueItems());
    }

    @Test
    public void canRemoveItemFromBasketMultipleOfTheSameItem() {
        for (int i = 0; i < 5; i++) {
            shoppingBasket.addItem(item1);
        }
        assertEquals(5, shoppingBasket.getNumberOfCertainItem(item1));
        shoppingBasket.removeItem(item1);
        assertEquals(4, shoppingBasket.getNumberOfCertainItem(item1));
    }

    @Test
    public void canEmptyBasket() {
        for (int i = 0; i < 5; i++) {
            shoppingBasket.addItem(item1);
            shoppingBasket.addItem(item2);
        }
        assertEquals(2, shoppingBasket.getNumberOfUniqueItems());
        shoppingBasket.emptyBasket();
        assertEquals(0, shoppingBasket.getNumberOfUniqueItems());
    }
}
