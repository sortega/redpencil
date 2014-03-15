package redpencil;

import java.text.NumberFormat;

public final class Currency {

    private final int decimals;
    private final int amount;

    public Currency(int amount) {
        this.decimals = localCurrencyFormat().getMaximumFractionDigits();
        this.amount = amount * decimals;
    }

    @Override
    public String toString() {
        return localCurrencyFormat().format((double) amount / decimals);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return amount == currency.amount && decimals == currency.decimals;
    }

    @Override
    public int hashCode() {
        return 31 * decimals + amount;
    }

    private static NumberFormat localCurrencyFormat() {
        return NumberFormat.getCurrencyInstance();
    }
}
