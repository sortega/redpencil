package redpencil.product;

import org.joda.time.DateTime;
import redpencil.currency.Currency;
import redpencil.promotion.Promotion;

interface PriceHistory {
    Currency priceAt(DateTime timestamp);
    Promotion promotionAt(DateTime timestamp);
}
