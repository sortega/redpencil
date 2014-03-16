package redpencil;

import org.joda.time.DateTime;

public class ChangedPrice implements PriceHistory {

    private final Currency price;
    private final PriceHistoryTimestamp previousHistory;

    public ChangedPrice(
            Currency price, DateTime changeDate, PriceHistory previousHistory) {
        this.price = price;
        this.previousHistory =
                new PriceHistoryTimestamp(changeDate, previousHistory);
    }

    @Override
    public Currency priceAt(DateTime timestamp) {
        if (previousHistory.includes(timestamp)) {
            return previousHistory.priceAt(timestamp);
        }
        return price;
    }

    @Override
    public Discount currentDiscount() {
        Discount discount = previousHistory.discountForPrice(this.price);
        if (!discount.inPromotionRange()) {
            return Discount.none();
        }
        return discount;
    }
}
