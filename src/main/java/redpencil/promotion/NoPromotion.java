package redpencil.promotion;

import org.joda.time.DateTime;
import redpencil.currency.Currency;
import redpencil.currency.Discount;

public class NoPromotion implements Promotion {

    private final Currency currentPrice;

    public NoPromotion(Currency price) {
        this.currentPrice = price;
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
        if (newPrice.discountFrom(currentPrice).inPromotionRange()) {
            return new RedPencilPromotion(newPrice, currentPrice, timestamp);
        }
        return new NoPromotion(newPrice);
    }
}
