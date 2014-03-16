package redpencil.promotion;

import org.joda.time.DateTime;
import redpencil.currency.Currency;
import redpencil.currency.Discount;

public class RedPencilPromotion implements Promotion {

    private final PriceDrop priceDrop;
    private final DateTime start;

    public RedPencilPromotion(
            Currency currentPrice, Currency initialPrice, DateTime start) {
        this(new PriceDrop(currentPrice, initialPrice), start);
    }

    private RedPencilPromotion(PriceDrop priceDrop, DateTime start) {
        this.priceDrop = priceDrop;
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

    @Override
    public Promotion changePrice(Currency newPrice, DateTime timestamp) {
        PriceDrop newDrop = priceDrop.changePrice(newPrice);
        if (newDrop.inPromotionRange()) {
            return new RedPencilPromotion(newDrop, start);
        }
        return new NoPromotion(newPrice);
    }

    private DateTime expiration() {
        return start.plusDays(30);
    }
}
