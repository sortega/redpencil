package redpencil.promotion;

import redpencil.currency.Discount;

public final class NoPromotion implements Promotion {

    private static final NoPromotion INSTANCE = new NoPromotion();

    public static Promotion get() {
        return INSTANCE;
    }

    private NoPromotion() {}

    @Override
    public Discount discount() {
        return Discount.none();
    }
}
