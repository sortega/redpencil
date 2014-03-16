package redpencil;

import org.joda.time.DateTime;
import org.junit.Test;
import redpencil.currency.Currency;
import redpencil.currency.Discount;

public class ProductPromotionsTest extends BaseProductTest {

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
    public void shouldListDiscountsFor30Days() throws Exception {
        DateTime promotionStart = DateTime.parse("2014-03-15");
        instance.changePrice(new Currency(90), promotionStart);
        DateTime promotionEnd = promotionStart.plusDays(30);
        assertDiscount(Discount.of(10), promotionEnd);
        assertNoDiscount(promotionEnd.plusSeconds(1));
    }

    @Test
    public void shouldNotInterruptPromotionOnFurtherPriceDrop() throws Exception {
        instance.changePrice(new Currency(90), DateTime.parse("2014-01-01"));
        instance.changePrice(new Currency(88), DateTime.parse("2014-01-15"));
        assertDiscount(Discount.of(12), DateTime.parse("2014-01-16"));
        assertNoDiscount(DateTime.parse("2014-02-01"));
    }

    @Test
    public void shouldInterruptPromotionOnPriceIncrease() throws Exception {
        instance.changePrice(new Currency(90), DateTime.parse("2014-01-01"));
        instance.changePrice(new Currency(91), DateTime.parse("2014-01-15"));
        assertNoDiscount(DateTime.parse("2014-01-16"));
    }
}
