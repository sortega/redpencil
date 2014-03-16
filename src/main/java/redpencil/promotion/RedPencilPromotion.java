package redpencil.promotion;

import redpencil.currency.Currency;
import redpencil.currency.Discount;

public class RedPencilPromotion implements Promotion {

    private final Currency currentPrice;
    private final Currency initialPrice;

    public RedPencilPromotion(Currency currentPrice, Currency initialPrice) {
        this.currentPrice = currentPrice;
        this.initialPrice = initialPrice;
    }

    @Override
    public Discount discount() {
        return currentPrice.discountFrom(initialPrice);
    }
}
