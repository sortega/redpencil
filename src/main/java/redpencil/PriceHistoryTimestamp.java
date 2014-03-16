package redpencil;

import org.joda.time.DateTime;

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

    public Discount discountForPrice(Currency newPrice) {
        Currency currentPrice = history.priceAt(changeDate);
        return newPrice.discountFrom(currentPrice);
    }
}
