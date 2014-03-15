package redpencil;

import org.junit.Test;

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
