package redpencil;

import org.joda.time.DateTime;
import redpencil.currency.Currency;
import redpencil.promotion.NoPromotion;
import redpencil.promotion.Promotion;

class InitialPrice implements PriceHistory {

    private static final DateTime EPOCH = new DateTime(0);

    private final Currency price;
    private final NoPromotion promotion;

    public InitialPrice(Currency price) {
        this.price = price;
        this.promotion = new NoPromotion(price, EPOCH);
    }

    @Override
    public Currency priceAt(DateTime timestamp) {
        return price;
    }

    @Override
    public Promotion promotionAt(DateTime timestamp) {
        return promotion;
    }
}
