package redpencil;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductTest {

    @Test
    public void shouldHaveACurrentPrice() throws Exception {
        Product instance = new Product(new Currency(100));
        assertEquals(new Currency(100), instance.currentPrice());
    }

    @Test
    public void shouldRegisterPriceChanges() throws Exception {
        Product instance = new Product(new Currency(100));
        instance.changePrice(new Currency(99, 99));
        instance.changePrice(new Currency(80));
        assertEquals(new Currency(80), instance.currentPrice());
    }
}
