package redpencil.promotion;

import redpencil.currency.Currency;
import redpencil.currency.Discount;

class PriceDrop {

    private final Currency currentPrice;
    private final Currency initialPrice;

    public PriceDrop(Currency currentPrice, Currency initialPrice) {
        this.currentPrice = currentPrice;
        this.initialPrice = initialPrice;
    }

    public Discount discount() {
        return currentPrice.discountFrom(initialPrice);
    }
}