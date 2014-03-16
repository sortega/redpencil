package redpencil.promotion;

import org.joda.time.DateTime;
import redpencil.currency.Discount;

public interface Promotion {
    Discount discount();
    boolean includes(DateTime timestamp);
}
