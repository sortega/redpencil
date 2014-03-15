package redpencil;

public final class Discount {

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
