package redpencil;

import org.joda.time.DateTime;

public class ChangedPrice implements PriceHistory {

    private static final Discount MINIMUM_DISCOUNT = Discount.of(5);
    private static final Discount MAXIMUM_DISCOUNT = Discount.of(30);

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
        if (!isDiscountInRange(discount)) {
            return Discount.none();
        }
        return discount;
    }

    private boolean isDiscountInRange(Discount discount) {
        return discount.compare(MINIMUM_DISCOUNT) >= 0 &&
                discount.compare(MAXIMUM_DISCOUNT) < 0;
    }
}
