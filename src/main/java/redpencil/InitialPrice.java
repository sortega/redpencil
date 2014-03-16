package redpencil;

import org.joda.time.DateTime;

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
    public Discount currentDiscount() {
        return Discount.none();
    }
}
