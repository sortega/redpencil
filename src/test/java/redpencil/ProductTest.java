package redpencil;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductTest {

    @Test
    public void shouldHaveACurrentPrice() throws Exception {
        Product instance = new Product(new Currency(100));
        assertEquals(new Currency(100), instance.currentPrice());
    }
}
