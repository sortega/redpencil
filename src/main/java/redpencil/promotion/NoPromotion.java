package redpencil.promotion;

import org.joda.time.DateTime;
import redpencil.currency.Currency;
import redpencil.currency.Discount;

public class NoPromotion implements Promotion {

    private final Currency currentPrice;
    private final DateTime start;

    public NoPromotion(Currency price, DateTime start) {
        this.currentPrice = price;
        this.start = start;
    }

    @Override
    public Discount discount() {
        return Discount.none();
    }

    @Override
    public boolean includes(DateTime timestamp) {
        return false;
    }

    @Override
    public Promotion changePrice(Currency newPrice, DateTime timestamp) {
        if (stableFor30Days(timestamp) && eligiblePrice(newPrice)) {
            return new RedPencilPromotion(newPrice, currentPrice, timestamp);
        }
        return new NoPromotion(newPrice, timestamp);
    }

    @Override
    public NoPromotion afterPromotion() {
        return this;
    }

    private boolean stableFor30Days(DateTime timestamp) {
        return !timestamp.minusDays(30).isBefore(start);
    }

    private boolean eligiblePrice(Currency newPrice) {
        return newPrice.discountFrom(currentPrice).inPromotionRange();
    }
}
