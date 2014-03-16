package redpencil.promotion;

import org.joda.time.DateTime;
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

    @Override
    public boolean includes(DateTime timestamp) {
        return false;
    }
}
