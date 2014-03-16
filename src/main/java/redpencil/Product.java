package redpencil;

import org.joda.time.DateTime;
import redpencil.currency.Currency;
import redpencil.currency.Discount;
import redpencil.promotion.Promotion;

public class Product {

    private PriceHistory priceHistory;

    public Product(Currency initialPrice) {
        this.priceHistory = new InitialPrice(initialPrice);
    }

    public Currency currentPrice() {
        return priceAt(DateTime.now());
    }

    public Currency priceAt(DateTime timestamp) {
        return priceHistory.priceAt(timestamp);
    }

    public void changePrice(Currency price) {
        changePrice(price, DateTime.now());
    }

    public void changePrice(Currency price, DateTime changeDate) {
        priceHistory = new ChangedPrice(price, changeDate, priceHistory);
    }

    public Discount discountAt(DateTime timestamp) {
        return promotionAt(timestamp).discount();
    }

    private Promotion promotionAt(DateTime timestamp) {
        return priceHistory.promotionAt(timestamp);
    }
}
