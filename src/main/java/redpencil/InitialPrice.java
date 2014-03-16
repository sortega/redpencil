package redpencil;

import org.joda.time.DateTime;
import redpencil.currency.Currency;
import redpencil.promotion.NoPromotion;
import redpencil.promotion.Promotion;

class InitialPrice implements PriceHistory {

    private final Currency price;

    public InitialPrice(Currency price) {
        this.price = price;
    }

    @Override
    public Currency priceAt(DateTime timestamp) {
        return price;
    }

    @Override
    public Promotion promotionAt(DateTime timestamp) {
        return NoPromotion.get();
    }
}
