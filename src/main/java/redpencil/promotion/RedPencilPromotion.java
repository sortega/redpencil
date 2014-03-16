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
        boolean tooEarly = timestamp.isBefore(start);
        boolean tooLate = timestamp.isAfter(expiration());
        return !(tooEarly || tooLate);
    }

    @Override
    public Promotion changePrice(Currency newPrice, DateTime timestamp) {
        if (priceDrop.canBeExtendedTo(newPrice)) {
            return new RedPencilPromotion(priceDrop.changePrice(newPrice), start);
        }
        return new NoPromotion(newPrice, timestamp);
    }

    @Override
    public NoPromotion afterPromotion() {
        return new NoPromotion(priceDrop.getCurrentPrice(), expiration());
    }

    private DateTime expiration() {
        return start.plusDays(30);
    }
}
