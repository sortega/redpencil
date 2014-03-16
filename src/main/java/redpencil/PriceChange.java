package redpencil;

import org.joda.time.DateTime;

public class PriceChange {

    private final Currency price;
    private final DateTime changeDate;

    public PriceChange(Currency price, DateTime changeDate) {
        this.price = price;
        this.changeDate = changeDate;
    }
}
