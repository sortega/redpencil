package redpencil;

import org.joda.time.DateTime;
import org.junit.Before;
import redpencil.currency.Currency;
import redpencil.currency.Discount;

import static org.junit.Assert.assertEquals;

public abstract class BaseProductTest {
    protected final Currency initialPrice = new Currency(100);
    protected Product instance;

    @Before
    public void setUp() throws Exception {
        instance = new Product(initialPrice);
    }

    protected void assertNoDiscount() {
        assertNoDiscount(DateTime.now());
    }

    protected void assertNoDiscount(DateTime timestamp) {
        assertDiscount(Discount.none(), timestamp);
    }

    protected void assertDiscount(Discount discount) {
        assertDiscount(discount, DateTime.now());
    }

    protected void assertDiscount(Discount discount, DateTime timestamp) {
        assertEquals(discount, instance.discountAt(timestamp));
    }
}
