package redpencil;

import org.junit.Before;

import static org.junit.Assert.assertEquals;

public abstract class BaseProductTest {
    protected final Currency initialPrice = new Currency(100);
    protected Product instance;

    @Before
    public void setUp() throws Exception {
        instance = new Product(initialPrice);
    }

    protected void assertNoDiscount() {
        assertDiscount(Discount.none());
    }

    protected void assertDiscount(Discount discount) {
        assertEquals(discount, instance.currentDiscount());
    }
}
