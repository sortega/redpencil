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

    public PriceDrop changePrice(Currency newPrice) {
        return new PriceDrop(newPrice, initialPrice);
    }

    public boolean canBeExtendedTo(Currency newPrice) {
        Discount newDiscount = newPrice.discountFrom(initialPrice);
        return newDiscount.inPromotionRange() &&
                newDiscount.compare(discount()) > 0;
    }
}
