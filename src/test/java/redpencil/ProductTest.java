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
        assertNoDiscount();
    }

    @Test
    public void shouldListDiscountWhenPriceDropsAtLeast5Percent() throws Exception {
        instance.changePrice(new Currency(95));
        assertDiscount(Discount.of(5));
    }

    @Test
    public void shouldNotConsiderADiscountUnder5Percent() throws Exception {
        instance.changePrice(new Currency(95, 50));
        assertNoDiscount();
    }

    @Test
    public void shouldNotConsiderDiscountsOver30Percent() throws Exception {
        instance.changePrice(new Currency(69, 90));
        assertNoDiscount();
    }

    private void assertNoDiscount() {
        assertDiscount(Discount.none());
    }

    private void assertDiscount(Discount discount) {
        assertEquals(discount, instance.currentDiscount());
    }
}
