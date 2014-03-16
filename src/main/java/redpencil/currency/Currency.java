package redpencil.currency;

import java.text.NumberFormat;

public final class Currency {

    private final int decimalPositions;
    private final int amount;

    public Currency(int amount) {
        this(amount, 0);
    }

    public Currency(int amount, int decimals) {
        this.decimalPositions = localCurrencyFormat().getMaximumFractionDigits();
        requireValidAmount(amount);
        requireValidDecimals(decimals);
        this.amount = amount * maxDecimalsValue() + decimals;
    }

    public Discount discountFrom(Currency other) {
        float difference = other.amount - amount;
        float percent = difference * 100 / other.amount;
        return Discount.of(percent);
    }

    private void requireValidAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
    }

    private void requireValidDecimals(int decimals) {
        if (decimals < 0 || decimals >= maxDecimalsValue()) {
            throw new IllegalArgumentException("Invalid decimals");
        }
    }

    private int maxDecimalsValue() {
        int maxDecimalsValue = 1;
        for (int i = 0; i < decimalPositions; i++) {
            maxDecimalsValue *= 10;
        }
        return maxDecimalsValue;
    }

    @Override
    public String toString() {
        double floatingValue = (double) amount / maxDecimalsValue();
        return localCurrencyFormat().format(floatingValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return amount == currency.amount &&
                decimalPositions == currency.decimalPositions;
    }

    @Override
    public int hashCode() {
        return 31 * decimalPositions + amount;
    }

    private static NumberFormat localCurrencyFormat() {
        return NumberFormat.getCurrencyInstance();
    }
}
