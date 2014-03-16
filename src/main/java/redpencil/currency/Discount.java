package redpencil.currency;

public final class Discount {

    private static final Discount MINIMUM_PROMOTION_DISCOUNT = Discount.of(5);
    private static final Discount MAXIMUM_PROMOTION_DISCOUNT = Discount.of(30);
    private static final float DELTA = 0.1f;
    private static final Discount NONE = new Discount(0);

    public static Discount of(float percent) {
        return percent == 0f ? none() : new Discount(percent);
    }

    public static Discount none() {
        return NONE;
    }

    private final float percent;

    private Discount(float percent) {
        this.percent = percent;
    }

    public boolean inPromotionRange() {
        return this.compare(MINIMUM_PROMOTION_DISCOUNT) >= 0 &&
                this.compare(MAXIMUM_PROMOTION_DISCOUNT) < 0;
    }

    @Override
    public String toString() {
        return String.format("%4.1f %%", percent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return Math.abs(discount.percent - percent) < DELTA;
    }

    @Override
    public int hashCode() {
        return percent != +0.0f ? Float.floatToIntBits(percent) : 0;
    }

    public int compare(Discount other) {
        return Float.compare(percent, other.percent);
    }
}
