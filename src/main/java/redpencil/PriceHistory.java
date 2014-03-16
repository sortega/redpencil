package redpencil;

import org.joda.time.DateTime;

interface PriceHistory {
    Currency priceAt(DateTime timestamp);

    Discount currentDiscount();
}
