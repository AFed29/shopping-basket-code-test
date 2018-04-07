import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CustomerTest {
    private Customer customer1;
    private Customer customer2;

    @Before
    public void before() {
        customer1 = new Customer("Bob", true);
        customer2 = new Customer("Steven", false);
    }

    @Test
    public void getName() {
        assertEquals("Bob", customer1.getName());
    }

    @Test
    public void getIsMemberIsMember() {
        assertTrue(customer1.isMember());
    }

    @Test
    public void getIsMemberIsNotMember() {
        assertFalse(customer2.isMember());
    }
}