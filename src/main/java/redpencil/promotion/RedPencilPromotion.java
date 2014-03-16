package redpencil.promotion;

import org.joda.time.DateTime;
import redpencil.currency.Currency;
import redpencil.currency.Discount;

public class RedPencilPromotion implements Promotion {

    private final PriceDrop priceDrop;
    private final DateTime start;

    public RedPencilPromotion(
            Currency currentPrice, Currency initialPrice, DateTime start) {
        this.priceDrop = new PriceDrop(currentPrice, initialPrice);
        this.start = start;
    }

    @Override
    public Discount discount() {
        return priceDrop.discount();
    }

    @Override
    public boolean includes(DateTime timestamp) {
        return !(timestamp.isBefore(start) || timestamp.isAfter(expiration()));
    }

    private DateTime expiration() {
        return start.plusDays(30);
    }
}
