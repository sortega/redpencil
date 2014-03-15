package redpencil;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductTest {

    private Product instance;

    @Before
    public void setUp() throws Exception {
        instance = new Product(new Currency(100));
    }

    @Test
    public void shouldHaveACurrentPrice() throws Exception {
        assertEquals(new Currency(100), instance.currentPrice());
    }

    @Test
    public void shouldRegisterPriceChanges() throws Exception {
        instance.changePrice(new Currency(99, 99));
        instance.changePrice(new Currency(80));
        assertEquals(new Currency(80), instance.currentPrice());
    }

    @Test
    public void shouldListNoDiscountForUnchangedPrices() throws Exception {
        assertEquals(Discount.none(), instance.currentDiscount());
    }

    @Test
    public void shouldListDiscountWhenPriceDropsAtLeast5Percent() throws Exception {
        instance.changePrice(new Currency(95));
        assertEquals(Discount.of(5), instance.currentDiscount());
    }

    @Test
    public void shouldNotConsiderADiscountUnder5Percent() throws Exception {
        instance.changePrice(new Currency(95, 50));
        assertEquals(Discount.none(), instance.currentDiscount());
    }
}
