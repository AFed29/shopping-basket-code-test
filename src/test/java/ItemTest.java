import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {
    Item item;

    @Before
    public void before() {
        item = new Item("Computer", 30000);
    }

    @Test
    public void canGetName() {
        assertEquals("Computer", item.getName());
    }

    @Test
    public void canGetPrice() {
        assertEquals(30000, item.getPrice());
    }
}
