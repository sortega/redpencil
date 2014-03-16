package redpencil.currency;

import org.junit.Test;
import redpencil.currency.Currency;

public class CurrencyTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldRequirePositiveAmount() throws Exception {
        new Currency(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRequireValidDecimals() throws Exception {
        new Currency(1, 101);
    }
}
