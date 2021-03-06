package redpencil.product;

import org.joda.time.DateTime;
import org.junit.Test;
import redpencil.currency.Currency;
import redpencil.currency.Discount;

public class ProductPromotionStartTest extends BaseProductTest {

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

    @Test
    public void shouldHaveStablePricesFor30Days() throws Exception {
        DateTime now = DateTime.now();
        DateTime aWeekAgo = now.minusWeeks(1);
        instance.changePrice(new Currency(1000), aWeekAgo);
        instance.changePrice(new Currency(900), now);
        assertNoDiscount();
    }

    @Test
    public void shouldHaveNoPromotionOnTheLast30Days() throws Exception {
        instance.changePrice(new Currency(90), DateTime.parse("2014-01-01"));
        instance.changePrice(new Currency(95), DateTime.parse("2014-01-15"));
        instance.changePrice(new Currency(80), DateTime.parse("2014-02-01"));
        assertNoDiscount(DateTime.parse("2014-02-01"));
    }
}
