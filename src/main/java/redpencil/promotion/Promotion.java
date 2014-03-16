package redpencil.promotion;

import org.joda.time.DateTime;
import redpencil.currency.Currency;
import redpencil.currency.Discount;

public interface Promotion {
    Discount discount();
    boolean includes(DateTime timestamp);
    Promotion changePrice(Currency newPrice, DateTime timestamp);
    NoPromotion afterPromotion();
}
