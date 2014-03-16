package redpencil;

import org.joda.time.DateTime;
import redpencil.currency.Currency;
import redpencil.promotion.Promotion;

class PriceHistoryTimestamp {

    private final DateTime changeDate;
    private final PriceHistory history;

    public PriceHistoryTimestamp(DateTime changeDate, PriceHistory history) {
        this.changeDate = changeDate;
        this.history = history;
    }

    public boolean includes(DateTime timestamp) {
        return timestamp.isBefore(changeDate);
    }

    public Currency priceAt(DateTime timestamp) {
        return history.priceAt(timestamp);
    }

    public Promotion promotionAt(DateTime timestamp) {
        return history.promotionAt(timestamp);
    }

    public Promotion promotionForPrice(Currency newPrice) {
        Promotion previousPromotion = promotionAt(changeDate);
        return previousPromotion.changePrice(newPrice, changeDate);
    }
}
