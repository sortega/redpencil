package redpencil;

import org.joda.time.DateTime;

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

    public Discount currentDiscount() {
        return priceHistory.currentDiscount();
    }
}
