package redpencil.product;

import org.joda.time.DateTime;
import org.junit.Test;
import redpencil.currency.Currency;

import static org.junit.Assert.assertEquals;

public class ProductPricesTest extends BaseProductTest {

    @Test
    public void shouldHaveACurrentPrice() throws Exception {
        assertEquals(initialPrice, instance.currentPrice());
    }

    @Test
    public void shouldRememberLastPrice() throws Exception {
        DateTime changeDate = DateTime.parse("2014-01-01");
        instance.changePrice(new Currency(80), changeDate);
        DateTime beforePriceChange = changeDate.minusHours(1);
        assertEquals(initialPrice, instance.priceAt(beforePriceChange));
        DateTime afterPriceChange = changeDate.plusHours(1);
        assertEquals(new Currency(80), instance.priceAt(afterPriceChange));
    }

    @Test
    public void shouldRegisterPriceChanges() throws Exception {
        instance.changePrice(new Currency(99, 99));
        instance.changePrice(new Currency(80));
        assertEquals(new Currency(80), instance.currentPrice());
    }
}
