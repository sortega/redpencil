package redpencil;

import org.joda.time.DateTime;
import redpencil.currency.Currency;
import redpencil.promotion.NoPromotion;
import redpencil.promotion.Promotion;

public class ChangedPrice implements PriceHistory {

    private final Currency price;
    private final PriceHistoryTimestamp history;

    public ChangedPrice(
            Currency price, DateTime changeDate, PriceHistory history) {
        this.history = new PriceHistoryTimestamp(changeDate, history);
        this.price = price;
    }

    @Override
    public Currency priceAt(DateTime timestamp) {
        return history.includes(timestamp) ? history.priceAt(timestamp) : price;
    }

    @Override
    public Promotion promotionAt(DateTime timestamp) {
        if (history.includes(timestamp)) {
            return history.promotionAt(timestamp);
        }
        Promotion promotion = currentPromotion();
        if (promotion.includes(timestamp))
            return promotion;
        return new NoPromotion(price);
    }

    private Promotion currentPromotion() {
        return history.promotionForPrice(price);
    }
}
